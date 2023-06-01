package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfigExtendMjhl;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResultExtend;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResultExtendMjhl;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.CascadeHlrFactory;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.ICascadeHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendMjhl;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeClusterConfigExtend.CascadeClusterConfigExtendMjhl;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfigExtendReelDependent;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import org.lsj.utils.ListUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 消除集合處理者 麻將胡了
public class CascadeClusterHlrMjhl implements ICascadeClusterHlr{
    private final CascadeClusterHlrConfig config; // 消除集合處理者設定黨
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 消除集合類型
    private final CascadeClusterHlrConfigExtendMjhl configExtend; // 消除集合額外設定
    private final ICascadeHlr cascadeHlr; // 消除處理者
    private final ITableUtil tableUtil; // 牌桌工具包


    public CascadeClusterHlrMjhl(CascadeClusterHlrConfig config, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        this.config = config;
        this.cascadeClusterType = config.getCascadeClusterType();
        this.configExtend = (CascadeClusterHlrConfigExtendMjhl) config.getHlrConfigExtend();
        this.tableUtil = tableUtil;
        this.cascadeHlr = new CascadeHlrFactory().create(config.getCascadeHlrConfig(), accumulateWinCtr, tableUtil);
    }

    @Override
    public CascadeClusterHlrResult calculateCascadeClusterHlrResult(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 計算 消除結果 列表
        List<CascadeHlrResult> cascadeHlrResultList = this.cascadeHlr.calculateCascadeHlrResultList(spinRequest, reelRtpType);

        // 2. 計算修改後初始金標誌位置
        CascadeClusterHlrResultExtend clusterHlrResultExtend = this.calculateResultExtend(cascadeHlrResultList);

        // 3. 計算總得分
        double totalWin = this.calculateTotalWin(cascadeHlrResultList, clusterHlrResultExtend);

        // 4. 封裝回傳
        return new CascadeClusterHlrResult(MathUtil.moneyScale(totalWin), this.cascadeClusterType, clusterHlrResultExtend, cascadeHlrResultList);
    }

    // 計算總得分
    private double calculateTotalWin(List<CascadeHlrResult> cascadeHlrResultList, CascadeClusterHlrResultExtend clusterHlrResultExtend) {
        double result = 0.0;

        for (CascadeHlrResult cascadeHlrResult : cascadeHlrResultList) {
            result = MathUtil.add(result, cascadeHlrResult.getTotalWin());
        }

        return result;
    }

    // 計算消除處理者集合額外結果
    private CascadeClusterHlrResultExtend calculateResultExtend(List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 取得第一場畫面
        ScreenGtrResult screenGtrResult = cascadeHlrResultList.get(0).getScreenGtrResult();

        // 2. 取得消除前黃金位置
        List<List<Boolean>> beforeCascadeGoldenPosition = ((CascadeHlrResultExtendMjhl)cascadeHlrResultList.get(0).getCascadeHlrResultExtend()).getBeforeCascadeGoldenPosition();

        // 3. 複製設定檔
        CascadeClusterConfigExtendMjhl cascadeClusterConfigExtendMjhl = (CascadeClusterConfigExtendMjhl)this.configExtend.getConfigExtend();
        List<List<Integer>> reelStripList = ((ReelConfigExtendReelDependent)this.configExtend.getReelConfig().getReelStripBoxList().get(screenGtrResult.getReelCtrResult().getReelId()).getReelConfigExtend()).getReelStripList();
        List<List<Integer>> result = ListUtil.copy2DimensionIntegerList((cascadeClusterConfigExtendMjhl).getDefaultGoldenPositionIdList());

        // 4. 計算 Golden Position
        List<List<Integer>> currentGoldenPositionIdList = this.calculateGoldenPositionIdList(screenGtrResult, reelStripList);

        // 5. 計算哪些位置為黃金
        List<List<Integer>> goldenPositionIdList = new ArrayList<>();
        List<List<Integer>> normalPositionIdList = new ArrayList<>();
        this.calculateGoldenPositionAndNormalPositionIdList(beforeCascadeGoldenPosition, currentGoldenPositionIdList, goldenPositionIdList, normalPositionIdList);

        // 6. 修改預設位置
        this.addGoldenPositionAndRemoveNormalPosition(result, goldenPositionIdList, normalPositionIdList);

        // 7. 回傳
        return new CascadeClusterHlrResultExtendMjhl(result);
    }

    // 依照第一次消除結果，修改預設畫面黃金位置
    private void addGoldenPositionAndRemoveNormalPosition(List<List<Integer>> result, List<List<Integer>> goldenPositionIdList, List<List<Integer>> normalPositionIdList) {
        // 1. 移除非黃金
        for (int columnIndex = 0; columnIndex < normalPositionIdList.size(); columnIndex++) {
            for (int normalIndex = 0; normalIndex < normalPositionIdList.get(columnIndex).size(); normalIndex++) {
                if (result.get(columnIndex).contains(normalPositionIdList.get(columnIndex).get(normalIndex))) {
                    for (int defaultIndex = 0; defaultIndex < result.get(columnIndex).size(); defaultIndex++) {
                        if (result.get(columnIndex).get(defaultIndex) == normalPositionIdList.get(columnIndex).get(normalIndex)) {
                            result.get(columnIndex).remove(defaultIndex);
                            break;
                        }
                    }
                }
            }
        }

        // 2. 添加黃金
        for (int columnIndex = 0; columnIndex < goldenPositionIdList.size(); columnIndex++) {
            for (int goldenIndex = 0; goldenIndex < goldenPositionIdList.get(columnIndex).size(); goldenIndex++) {
                if (!result.get(columnIndex).contains(goldenPositionIdList.get(columnIndex).get(goldenIndex))) {
                    result.get(columnIndex).add(goldenPositionIdList.get(columnIndex).get(goldenIndex));
                }
            }
        }
    }

    // 計算畫面黃金、一般位置 ID 列表
    private void calculateGoldenPositionAndNormalPositionIdList(List<List<Boolean>> goldenPosition, List<List<Integer>> currentGoldenPositionIdList, List<List<Integer>> goldenPositionIdList, List<List<Integer>> normalPositionIdList) {
        for (int columnIndex = 0; columnIndex < currentGoldenPositionIdList.size(); columnIndex++) {
            List<Integer> goldenPositionIdPerColumn = new ArrayList<>();
            List<Integer> normalPositionIdPerColumn = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < currentGoldenPositionIdList.get(columnIndex).size(); rowIndex++) {
                if (goldenPosition.get(columnIndex).get(rowIndex)) {
                    goldenPositionIdPerColumn.add(currentGoldenPositionIdList.get(columnIndex).get(rowIndex));
                }else {
                    normalPositionIdPerColumn.add(currentGoldenPositionIdList.get(columnIndex).get(rowIndex));
                }
            }
            goldenPositionIdList.add(goldenPositionIdPerColumn);
            normalPositionIdList.add(normalPositionIdPerColumn);
        }
    }

    // 計算畫面ID位置列表
    private List<List<Integer>> calculateGoldenPositionIdList(ScreenGtrResult screenGtrResult, List<List<Integer>> reelStripList) {
        // 1. 計算參數
        List<Integer> stopIndexList = ((ReelStopResultExtendStopByDependentReelIndex) screenGtrResult.getReelCtrResult().getReelStopResultExtend()).getReelStopIndexList();
        List<Integer> upperDampCountList = screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().stream().map(List::size).collect(Collectors.toList());
        List<Integer> lowerDampCountList = screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().stream().map(List::size).collect(Collectors.toList());
        List<Integer> screenRowCountList = screenGtrResult.getScreenSymbol().stream().map(List::size).collect(Collectors.toList());

        // 2. 宣告空間
        List<List<Integer>> result = new ArrayList<>();

        // 3. 依照上破框、畫面、下破框添加黃金位置ID
        for (int columnIndex = 0; columnIndex < stopIndexList.size(); columnIndex++) {
            List<Integer> positionIdListPerColumn = new ArrayList<>();
            int stopPositionId = stopIndexList.get(columnIndex);
            int reelStripSize = reelStripList.get(columnIndex).size();

            if (!upperDampCountList.isEmpty()) {
                for (int upperDampIndex =  upperDampCountList.get(columnIndex); upperDampIndex > 0; upperDampIndex--) {
                    int upperDampPositionId = (stopPositionId - upperDampIndex + reelStripSize) % reelStripSize;
                    positionIdListPerColumn.add(upperDampPositionId);
                }
            }

            for (int rowIndex = 0; rowIndex < screenRowCountList.get(columnIndex); rowIndex++) {
                int screenSymbolPositionId = (stopPositionId + rowIndex + reelStripSize) % reelStripSize;
                positionIdListPerColumn.add(screenSymbolPositionId);
            }

            if (!lowerDampCountList.isEmpty()) {
                for (int lowerDampIndex = 1; lowerDampIndex <= lowerDampCountList.get(columnIndex); lowerDampIndex++) {
                    int lowerDampPositionId = (positionIdListPerColumn.get(positionIdListPerColumn.size() - 1) + lowerDampIndex + reelStripSize) % reelStripSize;
                    positionIdListPerColumn.add(lowerDampPositionId);
                }
            }

            result.add(positionIdListPerColumn);
        }

        // 4. 回傳
        return result;
    }
}
