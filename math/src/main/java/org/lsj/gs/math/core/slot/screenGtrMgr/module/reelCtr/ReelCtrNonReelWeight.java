package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendNonReelWeight;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelStripBox;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ReelCtrConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtend;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByScreen;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.addSymbolCtrResultExtend.AddSymbolCtrResult;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 輪代表計算者相依滾輪 TODO 依照 reelType 多型 包裝不同結果
public class ReelCtrNonReelWeight implements IReelCtr{
    private final ReelCtrConfig config; // 輪代表計算者設定檔
    private final ConstMathSlot.ReelType reelType; // 畫面產生類型
    private final List<ReelStripBox> reelStripBoxList; // 輪帶表資訊列表
    private final ITableUtil tableUtil; // 牌桌工具包

    public ReelCtrNonReelWeight(ReelCtrConfig reelCtrConfig, ITableUtil tableUtil) {
        this.config = reelCtrConfig;
        this.reelType = reelCtrConfig.getReelConfig().getReelType();
        this.reelStripBoxList = reelCtrConfig.getReelConfig().getReelStripBoxList();
        this.tableUtil = tableUtil;
    }

    /* 計算一般輪帶表結果 */

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
        return new ReelCtrResult(targetReelStripBox.getReelId(), targetReelStripBox.getReelStopType(), this.calculateReelStopResultExtendWithoutDamp(targetReelStripBox, frameResult));
    }

    // 依照指定輪代表計算結果
    @Override
    public ReelCtrResult calculateReelCtrResultBySpecifyReelId(int reelId, FrameResult frameResult) {
        // 1. 取得目標表
        ReelStripBox targetReelStripBox = this.reelStripBoxList.get(reelId);

        // 2. 依照目標表計算結果
        return new ReelCtrResult(targetReelStripBox.getReelId(), targetReelStripBox.getReelStopType(), this.calculateReelStopResultExtendWithoutDamp(targetReelStripBox, frameResult));
    }

    // 計算停輪額外資訊 TODO 僅先支援第一次是無 damp
    private ReelStopResultExtend calculateReelStopResultExtendWithoutDamp(ReelStripBox targetReelStripBox, FrameResult frameResult) {
        // 1. 宣告空間
        List<List<Integer>> screenSymbol = new ArrayList<>();
        List<Integer> usedSymbolCountOnScreen = new ArrayList<>();

        // 2. 計算每軸停輪位置
        List<Integer> screenRowList = frameResult.getFrameResultExtend().getScreenRowList();
        for (int columnIndex = 0; columnIndex < screenRowList.size(); columnIndex++) {
            List<Integer> screenSymbolPerCol = new ArrayList<>();

            // 添加可視範圍
            for (int rowIndex = 0; rowIndex < screenRowList.get(columnIndex); rowIndex++) {
                int symbolId = this.generateSymbolId(targetReelStripBox.getReelId(), columnIndex);
                screenSymbolPerCol.add(symbolId);
            }

            screenSymbol.add(screenSymbolPerCol);
            usedSymbolCountOnScreen.add(screenSymbolPerCol.size());
        }

        // 3. 回傳 TODO AddSymbolType
        return new ReelStopResultExtendStopByScreen(new DampCtrResult(), screenSymbol, new AddSymbolCtrResult(screenSymbol, usedSymbolCountOnScreen));
    }


    /* 計算畫面 */

    // 計算畫面 TODO 依照不同 frameType 做處理
    @Override
    public List<List<Integer>> calculateScreenSymbol(FrameResult frameResult, ReelCtrResult reelCtrResult) {
        ReelStopResultExtendStopByScreen resultExtend = (ReelStopResultExtendStopByScreen) reelCtrResult.getReelStopResultExtend();
        return ListUtil.copy2DimensionIntegerList(resultExtend.getScreenSymbol());
    }


    /* 計算破框 */

    // 計算破框結果
    public DampCtrResult calculateDampCtrResult(FrameResult frameResult, ReelCtrResult reelCtrResult) {
        // 1. 取出對應額外設定
        FrameResultExtendFix extendFrameResult = (FrameResultExtendFix) frameResult.getFrameResultExtend();

        // 2. 計算上破框資訊
        List<List<Integer>> upperDampSymbolIdList = new ArrayList<>();
        if (this.config.getDampConfig().getUpperDampType().equals(ConstMathSlot.DampType.ONE_DAMP)) {
            upperDampSymbolIdList = this.calculateDampOneList(extendFrameResult.getScreenRowList(), reelCtrResult.getReelId());
        }

        // 3. 計算下破框資訊
        List<List<Integer>> lowerDampSymbolIdList = new ArrayList<>();
        if (this.config.getDampConfig().getUpperDampType().equals(ConstMathSlot.DampType.ONE_DAMP)) {
            lowerDampSymbolIdList = this.calculateDampOneList(extendFrameResult.getScreenRowList(), reelCtrResult.getReelId());
        }

        // 4. 回傳
        return new DampCtrResult(upperDampSymbolIdList, lowerDampSymbolIdList);
    }

    // 計算破框資訊
    private List<List<Integer>> calculateDampOneList(List<Integer> screenRowList, int reelId) {
        // 1. 宣告變數
        List<List<Integer>> result = new ArrayList<>();

        // 2. 產出破框資訊
        for (int columnIndex = 0; columnIndex < screenRowList.size(); columnIndex++) {
            result.add(List.of(this.generateSymbolId(reelId, columnIndex)));
        }

        // 3. 回傳
        return result;
    }

    // 給定輪帶指標，產標誌
    private int generateSymbolId(int reelId, int columnIndex) {
        List<Integer> symbolWeightListPerColumn = ((ReelConfigExtendNonReelWeight)this.reelStripBoxList.get(reelId).getReelConfigExtend()).getSymbolWeightList().get(columnIndex);
        return this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(symbolWeightListPerColumn, ConstMathCommon.AccuracyType.MILLION);
    }


    //* 消除相關 *//

    // 計算消除後畫面 TODO 由上往下生成
    @Override
    public ReelCtrResult calculateCascadeReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, ScreenGtrResult beforeScreenGtrResult, EliminateCtrResult eliminateCtrResult) {
        // 1. 篩選符合此高低表的表
        List<ReelStripBox> satisfyReelStripBoxList = this.reelStripBoxList.stream().filter(reelStripBox -> reelStripBox.getReelRtpType().equals(reelRtpType)).collect(Collectors.toList());

        // 2. 隨機取得目標表
        int randomId = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(satisfyReelStripBoxList.stream().map(ReelStripBox::getWeight).collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
        ReelStripBox targetReelStripBox = satisfyReelStripBoxList.get(randomId);

        // 3. 取得消除後畫面
        List<List<Integer>> screenSymbolAfterCascade = beforeScreenGtrResult.calculateScreenSymbolAfterCascade(eliminateCtrResult);

        // 4. 計算新產出畫面
        ReelStopResultExtend resultExtend = this.calculateCascadeReelStopResultExtendWithAddSymbolNormal(targetReelStripBox, screenSymbolAfterCascade, beforeScreenGtrResult);

        // 5. 計算新結果
        return new ReelCtrResult(targetReelStripBox.getReelId(),
                targetReelStripBox.getReelStopType(),
                resultExtend);
    }

    @Override
    public ReelCtrResult calculateNoDampCascadeReelCtrResult(ConstMathSlot.ReelRtpType reelRtpType, ScreenGtrResult beforeScreenGtrResult, EliminateCtrResult eliminateCtrResult) {
        // 1. 篩選符合此高低表的表
        List<ReelStripBox> satisfyReelStripBoxList = this.reelStripBoxList.stream().filter(reelStripBox -> reelStripBox.getReelRtpType().equals(reelRtpType)).collect(Collectors.toList());

        // 2. 隨機取得目標表
        int randomId = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(satisfyReelStripBoxList.stream().map(ReelStripBox::getWeight).collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION);
        ReelStripBox targetReelStripBox = satisfyReelStripBoxList.get(randomId);

        // 3. 取得消除後畫面
        List<List<Integer>> screenSymbolAfterCascade = beforeScreenGtrResult.calculateScreenSymbolAfterCascade(eliminateCtrResult);

        // 4. 計算新產出畫面(Damp標誌不掉落)
        ReelStopResultExtend resultExtend = this.calculateNoDampCascadeReelStopResultExtendWithAddSymbolNormal(targetReelStripBox, screenSymbolAfterCascade, beforeScreenGtrResult);

        // 5. 計算新結果
        return new ReelCtrResult(targetReelStripBox.getReelId(),
                targetReelStripBox.getReelStopType(),
                resultExtend);
    }

    // 計算消除後破框畫面
    public DampCtrResult calculateCascadeDampCtrResult(ScreenGtrResult beforeScreenGtrResult, AddSymbolCtrResult addSymbolCtrResult) {
        // 1. 若無設定，不做事
        if (this.config.getDampConfig().getUpperDampType().equals(ConstMathSlot.DampType.NO_DAMP) && this.config.getDampConfig().getLowerDampType().equals(ConstMathSlot.DampType.NO_DAMP)) {
            return new DampCtrResult();
        }

        // 2. 傳入資訊錯誤，不做事
        List<List<Integer>> addSymbolList = addSymbolCtrResult.getAddSymbolList();
        List<Integer> usedSymbolCountOnScreen = addSymbolCtrResult.getUsedSymbolCountOnScreen();
        if (addSymbolList.isEmpty()) {
            return new DampCtrResult();
        }

        // 3. 計算上破框資訊
        DampCtrResult beforeDampCtrResult = beforeScreenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult();
        List<List<Integer>> upperDampSymbolIdList = this.calculateCascadeUpperDampCtrResult(beforeDampCtrResult, addSymbolList, usedSymbolCountOnScreen);

        // 4. 回傳
        return new DampCtrResult(upperDampSymbolIdList, ListUtil.copy2DimensionIntegerList(beforeDampCtrResult.getLowerDampSymbolIdList()));
    }

    // 計算上破框資訊 TODO 使用 config
    private List<List<Integer>> calculateCascadeUpperDampCtrResult(DampCtrResult beforeDampCtrResult, List<List<Integer>> addSymbolList, List<Integer> usedSymbolCountOnScreen) {
        List<List<Integer>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < addSymbolList.size(); columnIndex++) {
            List<Integer> dampSymbolIdListPerCol = new ArrayList<>();
            int dampCount = addSymbolList.get(columnIndex).size() - usedSymbolCountOnScreen.get(columnIndex); // 新增標誌數 - 可視範圍使用數 = 上破框剩餘個數

            if (dampCount == 0) {
                if (beforeDampCtrResult.isExistUpperDamp()) {
                    dampSymbolIdListPerCol = ListUtil.copy1DimensionIntegerList(beforeDampCtrResult.getUpperDampSymbolIdList().get(columnIndex));
                }

            }else {
                for (int dampIndex = 0; dampIndex < dampCount; dampIndex++) {
                    dampSymbolIdListPerCol.add(addSymbolList.get(columnIndex).get(dampIndex));
                }
            }

            result.add(dampSymbolIdListPerCol);
        }

        return result;
    }

    // 計算消除後畫面額外資訊
    private ReelStopResultExtend calculateCascadeReelStopResultExtendWithAddSymbolNormal(ReelStripBox targetReelStripBox, List<List<Integer>> screenSymbolAfterCascade, ScreenGtrResult beforeScreenGtrResult) {
        // 1. 定義變數
        List<List<Integer>> screenSymbol = ListUtil.copy2DimensionIntegerList(screenSymbolAfterCascade);
        List<Integer> usedAddSymbolCountList = new ArrayList<>();

        // 2. 計算新增標誌
        List<List<Integer>> addSymbolList = this.calculateAddSymbolList(targetReelStripBox, screenSymbolAfterCascade);

        // 3. 先由破框畫面 [由最下列往上補]
        this.adjustScreenSymbolByDampCtrResult(beforeScreenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult(), screenSymbol);

        // 4. 剩餘未補畫面，再進行補
        this.adjustScreenSymbolByAddSymbolList(screenSymbol, addSymbolList, usedAddSymbolCountList);

        // 5. 包裝新增標誌資訊
        AddSymbolCtrResult addSymbolCtrResult = new AddSymbolCtrResult(addSymbolList, usedAddSymbolCountList);

        // 6. 計算破框資訊
        DampCtrResult dampCtrResult = this.calculateCascadeDampCtrResult(beforeScreenGtrResult, addSymbolCtrResult);

        // 7. 回傳
        return new ReelStopResultExtendStopByScreen(dampCtrResult, screenSymbol, addSymbolCtrResult);
    }

    // 計算消除後畫面額外資訊(無damp標誌掉落) todo 跟客端討論作法是否可行
    private ReelStopResultExtend calculateNoDampCascadeReelStopResultExtendWithAddSymbolNormal(ReelStripBox targetReelStripBox, List<List<Integer>> screenSymbolAfterCascade, ScreenGtrResult beforeScreenGtrResult) {
        // 1. 定義變數
        List<List<Integer>> screenSymbol = ListUtil.copy2DimensionIntegerList(screenSymbolAfterCascade);
        List<Integer> usedAddSymbolCountList = new ArrayList<>();

        // 2. 計算新增標誌
        List<List<Integer>> addSymbolList = this.calculateAddSymbolList(targetReelStripBox, screenSymbolAfterCascade);

        // 3. 剩餘未補畫面，再進行補
        this.adjustScreenSymbolByAddSymbolList(screenSymbol, addSymbolList, usedAddSymbolCountList);

        // 4. 包裝新增標誌資訊
        AddSymbolCtrResult addSymbolCtrResult = new AddSymbolCtrResult(addSymbolList, usedAddSymbolCountList);

        // 5. 計算破框資訊
        DampCtrResult dampCtrResult = this.calculateCascadeDampCtrResult(beforeScreenGtrResult, addSymbolCtrResult);

        // 6. 回傳
        return new ReelStopResultExtendStopByScreen(dampCtrResult, screenSymbol, addSymbolCtrResult);
    }

    // 計算新增標誌
    private List<List<Integer>> calculateAddSymbolList(ReelStripBox targetReelStripBox, List<List<Integer>> screenSymbolAfterCascade) {
        // 1. 宣告空間
        List<List<Integer>> addSymbolList = new ArrayList<>();

        // 2. 計算新增標誌
        for (int columnIndex = 0; columnIndex < screenSymbolAfterCascade.size(); columnIndex++) {
            List<Integer> addSymbolPerColumn = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < screenSymbolAfterCascade.get(columnIndex).size(); rowIndex++) {
                if (screenSymbolAfterCascade.get(columnIndex).get(rowIndex) == -1) {
                    int symbolId = this.generateSymbolId(targetReelStripBox.getReelId(), columnIndex);
                    addSymbolPerColumn.add(symbolId);
                }
                else {
                    break;
                }
            }

            addSymbolList.add(addSymbolPerColumn);
        }

        // 3. 回傳
        return addSymbolList;
    }

    // 使用破框資訊填補畫面
    private void adjustScreenSymbolByDampCtrResult(DampCtrResult dampCtrResult, List<List<Integer>> screenSymbol) {
        if (dampCtrResult.getUpperDampSymbolIdList().isEmpty()) {
            return;
        }

        // 遍歷每軸
        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            // 從破框資訊，由最後面開始填補
            for (int dampIndex = dampCtrResult.getUpperDampSymbolIdList().get(columnIndex).size() - 1; dampIndex >= 0; dampIndex--) {
                // 畫面由下開始填補
                for (int rowIndex = screenSymbol.get(columnIndex).size() - 1; rowIndex >= 0; rowIndex--) {
                    if (screenSymbol.get(columnIndex).get(rowIndex) == -1) {
                        int symbolId = dampCtrResult.getUpperDampSymbolIdList().get(columnIndex).get(dampIndex);
                        screenSymbol.get(columnIndex).set(rowIndex, symbolId);
                        break;
                    }
                }
            }
        }
    }

    // 使用新增標誌填補畫面
    private void adjustScreenSymbolByAddSymbolList(List<List<Integer>> screenSymbol, List<List<Integer>> addSymbolList, List<Integer> usedAddSymbolCountList) {
        // 遍歷每軸
        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            // 1. 新增預設使用次數
            usedAddSymbolCountList.add(0);

            // 2. 最後新增標誌最先補
            for (int addSymbolIndex = addSymbolList.get(columnIndex).size() - 1; addSymbolIndex >= 0; addSymbolIndex--) {

                // 3. 由畫面下方開始補
                for (int rowIndex = screenSymbol.get(columnIndex).size() - 1; rowIndex >= 0; rowIndex--) {
                    if (screenSymbol.get(columnIndex).get(rowIndex) == -1) {
                        int symbolId = addSymbolList.get(columnIndex).get(addSymbolIndex);
                        screenSymbol.get(columnIndex).set(rowIndex, symbolId);
                        usedAddSymbolCountList.set(columnIndex, usedAddSymbolCountList.get(columnIndex) + 1);
                        break;
                    }
                }
            }
        }
    }
}
