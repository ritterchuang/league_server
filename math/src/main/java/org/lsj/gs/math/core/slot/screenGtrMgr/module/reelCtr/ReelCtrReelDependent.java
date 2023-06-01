package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ReelCtrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtend;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 輪代表計算者相依滾輪 TODO 依照 reelType 多型 包裝不同結果
public class ReelCtrReelDependent implements IReelCtr{
    private final ReelCtrConfig config; // 輪代表計算者設定檔
    private final ConstMathSlot.ReelType reelType; // 畫面產生類型
    private final List<ReelStripBox> reelStripBoxList; // 輪帶表資訊列表
    private final ITableUtil tableUtil; // 牌桌工具包

    public ReelCtrReelDependent(ReelCtrConfig reelCtrConfig, ITableUtil tableUtil) {
        this.config = reelCtrConfig;
        this.reelType = reelCtrConfig.getReelConfig().getReelType();
        this.reelStripBoxList = reelCtrConfig.getReelConfig().getReelStripBoxList();
        this.tableUtil = tableUtil;
    }

    /* 計算輪帶表結果 */
    // 給定輪帶表類型，隨機取一張表
    @Override
    public ReelCtrResult calculateReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, FrameResult frameResult) {
        // 1. 篩選符合此高低表的表
        List<ReelStripBox> satisfyReelStripBoxList = this.reelStripBoxList.stream().filter(reelStripBox -> reelStripBox.getReelRtpType().equals(reelRtpType)).collect(Collectors.toList());

        // 2. 隨機取得目標表
        int randomId = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(satisfyReelStripBoxList.stream().map(ReelStripBox::getWeight).collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);

        // 3. 取得目標表
        ReelStripBox targetReelStripBox = satisfyReelStripBoxList.get(randomId);

        // 4. 依照目標表計算結果
        return new ReelCtrResult(targetReelStripBox.getReelId(), targetReelStripBox.getReelStopType(), this.calculateReelStopResultExtend(frameResult, targetReelStripBox));
    }

    // 依照指定輪代表計算結果
    @Override
    public ReelCtrResult calculateReelCtrResultBySpecifyReelId(int reelId, FrameResult frameResult) {
        // 1. 取得目標表
        ReelStripBox targetReelStripBox = this.reelStripBoxList.get(reelId);

        // 2. 依照目標表計算結果
        return new ReelCtrResult(targetReelStripBox.getReelId(), targetReelStripBox.getReelStopType(), this.calculateReelStopResultExtend(frameResult, targetReelStripBox));
    }

    // 計算停輪額外資訊
    private ReelStopResultExtend calculateReelStopResultExtend(FrameResult frameResult, ReelStripBox targetReelStripBox) {
        // 1. 取得輪帶表
        List<List<Integer>> reelStripList = ((ReelConfigExtendReelDependent)targetReelStripBox.getReelConfigExtend()).getReelStripList();

        // 2. 計算每軸停輪位置
        List<Integer> reelStopIndexList = reelStripList.stream().
                map(reelStripPerColumn ->
                        this.tableUtil.getMainRandomUtil().getRandomIntWithAccuracy(reelStripPerColumn.size(), ConstMathCommon.AccuracyType.A32768))
                .collect(Collectors.toList());

        // 3. 計算破框資訊
        DampCtrResult dampCtrResult = this.calculateDampCtrResult(frameResult, targetReelStripBox.getReelId(), reelStopIndexList);

        // 3. 回傳
        return new ReelStopResultExtendStopByDependentReelIndex(dampCtrResult, reelStopIndexList);
    }


    /* 計算畫面 */
    // 計算畫面 TODO 依照不同 frameType 做處理
    @Override
    public List<List<Integer>> calculateScreenSymbol(FrameResult frameResult, ReelCtrResult reelCtrResult) {
        // 1. 取出對應額外設定
        List<Integer> screenRowList = frameResult.getFrameResultExtend().getScreenRowList();
        List<Integer> reelStopIndexList = ((ReelStopResultExtendStopByDependentReelIndex) reelCtrResult.getReelStopResultExtend()).getReelStopIndexList();
        List<List<Integer>> targetReelStrip = ((ReelConfigExtendReelDependent)this.reelStripBoxList.get(reelCtrResult.getReelId()).getReelConfigExtend()).getReelStripList();

        // 2. 創建空間
        List<List<Integer>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < screenRowList.size(); columnIndex++) {
            // 2.1 取得列數
            int rowPerColumn = screenRowList.get(columnIndex);

            // 2.2 取得對應軸輪帶表
            List<Integer> reelStripPerColumn = targetReelStrip.get(columnIndex);

            // 2.3 添加標誌
            List<Integer> screenSymbolPerColumn = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < rowPerColumn; rowIndex++) {
                // 取得此次位置
                int positionId = (reelStopIndexList.get(columnIndex) + rowIndex) % reelStripPerColumn.size();

                // 取得標誌
                screenSymbolPerColumn.add(reelStripPerColumn.get(positionId));
            }

            // 2.4 添加此軸結果
            result.add(screenSymbolPerColumn);
        }

        // 3. 回傳
        return result;
    }


    /* 計算破框 */
    // 計算破框結果
    public DampCtrResult calculateDampCtrResult(FrameResult frameResult, int reelId, List<Integer> reelStopIndexList) {
        // 1. 取出對應額外設定
        List<Integer> screenRowList = frameResult.getFrameResultExtend().getScreenRowList();
        List<List<Integer>> targetReelStrip = ((ReelConfigExtendReelDependent)this.reelStripBoxList.get(reelId).getReelConfigExtend()).getReelStripList();

        // 2. 計算上破框資訊
        List<List<Integer>> upperDampSymbolIdList = new ArrayList<>();
        if (this.config.getDampConfig().getUpperDampType().equals(ConstMathSlot.DampType.ONE_DAMP)) {
            upperDampSymbolIdList = this.calculateUpperDampOneList(screenRowList, targetReelStrip, reelStopIndexList);
        }

        // 3. 計算下破框資訊
        List<List<Integer>> lowerDampSymbolIdList = new ArrayList<>();
        if (this.config.getDampConfig().getLowerDampType().equals(ConstMathSlot.DampType.ONE_DAMP)) {
            lowerDampSymbolIdList = this.calculateLowerDampOneList(screenRowList, targetReelStrip, reelStopIndexList);
        }

        // 4. 回傳
        return new DampCtrResult(upperDampSymbolIdList, lowerDampSymbolIdList);
    }

    // TODO 待實作
    @Override
    public ReelCtrResult calculateCascadeReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, ScreenGtrResult beforeScreenGtrResult, EliminateCtrResult eliminateCtrResult) {
        return null;
    }
    @Override
    public ReelCtrResult calculateNoDampCascadeReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, ScreenGtrResult beforeScreenGtrResult, EliminateCtrResult eliminateCtrResult) {
        return null;
    }


    // 計算上破框資訊
    private List<List<Integer>> calculateUpperDampOneList(List<Integer> screenRowList, List<List<Integer>> targetReelStrip, List<Integer> reelStopIndexList) {
        // 1. 宣告變數
        List<List<Integer>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < screenRowList.size(); columnIndex++) {
            // 2. 取得輪帶表
            List<Integer> reelStripPerColumn = targetReelStrip.get(columnIndex);

            // 3. 取得此次位置
            int positionId = (reelStopIndexList.get(columnIndex) + (reelStripPerColumn.size() - 1)) % reelStripPerColumn.size();

            // 4. 取得標誌
            result.add(List.of(reelStripPerColumn.get(positionId)));
        }

        // 5. 回傳
        return result;
    }

    // 計算下破框資訊
    private List<List<Integer>> calculateLowerDampOneList(List<Integer> screenRowList, List<List<Integer>> targetReelStrip, List<Integer> reelStopIndexList) {
        // 1. 宣告變數
        List<List<Integer>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < screenRowList.size(); columnIndex++) {
            // 2. 取得列數
            int rowPerColumn = screenRowList.get(columnIndex);

            // 3. 取得輪帶表
            List<Integer> reelStripPerColumn = targetReelStrip.get(columnIndex);

            // 4. 取得此次位置
            int positionId = (reelStopIndexList.get(columnIndex) + rowPerColumn) % reelStripPerColumn.size();

            // 5. 取得標誌
            result.add(List.of(reelStripPerColumn.get(positionId)));
        }

        // 6. 回傳
        return result;
    }
}
