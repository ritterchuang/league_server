package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfigExtendMjhl;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendMjhl;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr.CascadeProgressHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr.ICascadeProgressHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeScreenGtr.CascadeScreenGtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendMjhl;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByScreen;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 消除處理者 麻將無雙
public class CascadeHlrMjhl extends AbstractCascadeHlr implements ICascadeHlr{
    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final CascadeConfigExtendMjhl configExtend; // 消除額外設定
    private final CascadeHlrConfigExtendMjhl cascadeHlrConfigExtend; // 消除處理者額外設定
    private final CascadeScreenGtr cascadeScreenGtr; // 消除畫面產生器
    private final ICascadeProgressHlr cascadeProgressHlr; // 消除進度處理者
    private final CascadeHlrMjhlUtil cascadeHlrMjhlUtil; // 消除處理者工具包

    public CascadeHlrMjhl(CascadeHlrConfig cascadeHlrConfig, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        super(cascadeHlrConfig, accumulateWinCtr, tableUtil);

        this.cascadeType = cascadeHlrConfig.getCascadeType();
        this.configExtend = (CascadeConfigExtendMjhl) cascadeHlrConfig.getCascadeConfigExtend();
        this.cascadeHlrConfigExtend = (CascadeHlrConfigExtendMjhl) cascadeHlrConfig.getCascadeHlrConfigExtend();

        this.cascadeScreenGtr = new CascadeScreenGtr(cascadeHlrConfigExtend.getCascadeScreenGtrConfig(), tableUtil);
        this.cascadeProgressHlr = new CascadeProgressHlr(cascadeHlrConfig.getCascadeProgressConfig(), tableUtil);

        this.cascadeHlrMjhlUtil = new CascadeHlrMjhlUtil(this.cascadeHlrConfigExtend.getSymbolConfig(), tableUtil);
    }

    // 計算消除處理者結果
    @Override
    public List<CascadeHlrResult> calculateCascadeHlrResultList(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        List<CascadeHlrResult> result = new ArrayList<>();

        for (int cascadeIndex = 0; cascadeIndex >= 0; cascadeIndex++) {
            // 1. 產出畫面結果
            ScreenGtrResult screenGtrResult = this.calculateScreenGtrResult(cascadeIndex, result, reelRtpType);

            // 2. 計算消除倍數
            int cascadeMultiplier = this.calculateCascadeMultiplier(cascadeIndex);

            // 3. 算分
            GameCtrResult gameCtrResult = super.gameCtr.calculateGameCtrResultWithScreenMultiplier(spinRequest, screenGtrResult, cascadeMultiplier);

            // 4. 計算可能消除位置
            EliminateCtrResult eliminateCtrResult = super.eliminationCtr.calculateEliminationCtrResult(gameCtrResult, new SpecialFeatureHlrResultCluster(), screenGtrResult);

            // 5. 計算消除額外結果
            CascadeHlrResultExtendMjhl extendResult = this.calculateExtendResult(cascadeIndex, cascadeMultiplier, result, screenGtrResult, gameCtrResult, eliminateCtrResult);

            // 6. 計算總得分
            double totalWin = gameCtrResult.getTotalWin();

            // 7. 計算消除進度結果
            ProgressHlrResult progressHlrResult = this.cascadeProgressHlr.calculateProgressHlrResult(cascadeIndex, result, eliminateCtrResult);

            // 8. 計算消除累積得分
            AccumulateWinCtrResult accumulateWinCtrResult = super.accumulateWinCtr.calculateAccumulateWinCtrResultCascade(cascadeIndex, totalWin, result);

            // 9. 封裝此次消除結果
            result.add(
                    new CascadeHlrResult(
                            totalWin,
                            this.cascadeType,
                            extendResult,
                            screenGtrResult,
                            gameCtrResult,
                            progressHlrResult,
                            accumulateWinCtrResult,
                            new SpecialFeatureHlrResultCluster(),
                            new ReadyHandHlrResultUnionCluster(),
                            eliminateCtrResult));

            // 10. 判斷是否繼續消除
            if (super.isLastCascadeTime(progressHlrResult)) {
                break;
            }
        }

        // 11. 回傳
        return result;
    }


    //* 計算畫面產出結果 *//

    // 產出畫面
    private ScreenGtrResult calculateScreenGtrResult(int cascadeIndex,  List<CascadeHlrResult> cascadeHlrResultList, ConstMathSlot.ReelRtpType reelRtpType) {
        // 1. 第一次消除，直接產
        if (cascadeIndex == 0) {
            return super.screenGtr.generateScreenGtrResult(reelRtpType);
        }

        // 2. 依照上次消除結果，產生畫面，並修改對應位置為 wild
        return this.calculateScreenGtrResultWithUpdateWild(reelRtpType, cascadeHlrResultList.get(cascadeIndex - 1));
    }

    // 修改畫面結果
    private ScreenGtrResult calculateScreenGtrResultWithUpdateWild(ConstMathSlot.ReelRtpType reelRtpType, CascadeHlrResult beforeCascadeHlrResult) {
        // 1. 取得上一次消除後，算分範圍畫面
        List<List<Integer>> screenWithScoreAfterLastCascade = this.calculateScreenWithScoreAfterLastCascade(beforeCascadeHlrResult);

        // 2. 計算此次畫面結果
        ScreenGtrResult result = this.cascadeScreenGtr.generateCascadeScreenGtrResult(
                reelRtpType, beforeCascadeHlrResult.getScreenGtrResult(), beforeCascadeHlrResult.getEliminateCtrResult());

        // 3. 修改畫面標誌
        this.cascadeHlrMjhlUtil.updateScreenGtrResultWithLastCascadeViewScreen(result, screenWithScoreAfterLastCascade);

        // 4. 回傳
        return result;
    }

    // 計算上次消除後，可視範圍畫面
    private List<List<Integer>> calculateScreenWithScoreAfterLastCascade(CascadeHlrResult beforeCascadeHlrResult) {
        // 1. 取得上一次消除後畫面資訊 copy
        List<List<Integer>> result = ListUtil.copy2DimensionIntegerList(((CascadeHlrResultExtendMjhl)beforeCascadeHlrResult.getCascadeHlrResultExtend()).getDampScreenSymbolAfterCascade());

        // 2. 取得上一次破框資訊
        DampCtrResult dampCtrResult = beforeCascadeHlrResult.getScreenGtrResult().getReelCtrResult().getReelStopResultExtend().getDampCtrResult();

        // 3. 依照破框資訊修改畫面
        for (int columnIndex = 0; columnIndex < result.size(); columnIndex++) {
            List<Integer> dampScreenPerColumn = result.get(columnIndex);

            if (dampCtrResult.isExistUpperDamp()) {
                for (int upperDampIndex = 0; upperDampIndex < dampCtrResult.getUpperDampSymbolIdList().get(columnIndex).size(); upperDampIndex++) {
                    dampScreenPerColumn.remove(0);
                }
            }

            if (dampCtrResult.isExistLowerDamp()) {
                for (int lowerDampIndex = 0; lowerDampIndex < dampCtrResult.getLowerDampSymbolIdList().get(columnIndex).size(); lowerDampIndex++) {
                    dampScreenPerColumn.remove(dampScreenPerColumn.size() - 1);
                }
            }
        }

        // 4. 回傳
        return result;
    }


    //* 計算消除倍數 *//

    // 計算消除倍數
    private int calculateCascadeMultiplier(int cascadeIndex) {
        if (cascadeIndex >= this.configExtend.getMultiplierList().size()) {
            return this.configExtend.getMultiplierList().get(this.configExtend.getMultiplierList().size() - 1);
        }

        return this.configExtend.getMultiplierList().get(cascadeIndex);
    }


    //* 計算客製結果 *//

    // 計算消除額外結果
    private CascadeHlrResultExtendMjhl calculateExtendResult(
            int cascadeIndex,
            int cascadeMultiplier,
            List<CascadeHlrResult> cascadeHlrResultList,
            ScreenGtrResult screenGtrResult,
            GameCtrResult gameCtrResult,
            EliminateCtrResult eliminateCtrResult) {

        // 1. 計算消除前 GoldPosition[6*5]
        List<List<Boolean>> beforeCascadeGoldenPosition = this.calculateGoldenPositionBeforeCascade(cascadeIndex, screenGtrResult, cascadeHlrResultList);

        // 2. 計算 wild 轉變 position[6*5]
        List<List<Boolean>> changeToWildPosition= this.calculateChangeToWildPosition(gameCtrResult, beforeCascadeGoldenPosition);

        // 3. 修改消除位置 [6*5]
        this.adjustEliminateCtrResult(screenGtrResult, eliminateCtrResult, changeToWildPosition);

        // 4. 計算消除後 GoldPosition[6*5]
        List<List<Boolean>> afterCascadeGoldenPosition = this.calculateGoldenPositionAfterCascade(eliminateCtrResult, changeToWildPosition, beforeCascadeGoldenPosition);

        // 5. 計算消除後剩餘畫面 [消掉的地方填 -1][screenGtr 擴充方法取得]
        List<List<Integer>> dampScreenSymbolAfterCascade = this.calculateDampScreenSymbolAfterCascade(screenGtrResult, changeToWildPosition, eliminateCtrResult);

        // 6. 回傳
        return new CascadeHlrResultExtendMjhl(
                beforeCascadeGoldenPosition,
                afterCascadeGoldenPosition, changeToWildPosition,
                dampScreenSymbolAfterCascade,
                cascadeMultiplier
        );
    }


    //** 計算消除前黃金位置 **//

    // 計算消除前黃金位置
    private List<List<Boolean>> calculateGoldenPositionBeforeCascade(int cascadeIndex, ScreenGtrResult cascadeScreenGtrResult, List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 第一場處理
        if (cascadeIndex == 0) {
            // 依照畫面、破框來計算 那些位置為 金
            return this.calculateGoldenPositionByScreenAndDamp(cascadeScreenGtrResult);
        }

        // 2. 取得上一次消除後 金 位置 [6*5]
        List<List<Boolean>> goldPositionAfterCascadeLastTime = ((CascadeHlrResultExtendMjhl)cascadeHlrResultList.get(cascadeIndex - 1).getCascadeHlrResultExtend()).getAfterCascadeGoldenPosition();

        // 3. 取得此次新增標誌結果，判斷哪些位置為金
        List<List<Integer>> addSymbolList = ((ReelStopResultExtendStopByScreen)cascadeScreenGtrResult.getReelCtrResult().getReelStopResultExtend()).getAddSymbolCtrResult().getAddSymbolList();
        List<List<Boolean>> goldenPositionForAddSymbolList = this.calculateGoldenPositionForAddSymbolList(addSymbolList);

        // 4. 回傳 哪些位置為金 [index = 0, 為最上面]
        return this.cascadeHlrMjhlUtil.unionGoldenPositionAddSymbolAndGoldPositionAfterCascadeLastTime(goldenPositionForAddSymbolList, goldPositionAfterCascadeLastTime);
    }

    // 計算新增標誌那些為黃金
    private List<List<Boolean>> calculateGoldenPositionForAddSymbolList(List<List<Integer>> addSymbolList) {
        List<List<Boolean>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < addSymbolList.size(); columnIndex++) {
            double goldenProbabilityPerColumn = this.configExtend.getGoldenPositionProbList().get(columnIndex);

            List<Boolean> goldenPositionPerColumn = new ArrayList<>();
            this.updateGoldenPositionPerColumn(goldenPositionPerColumn, addSymbolList.get(columnIndex), goldenProbabilityPerColumn);

            result.add(goldenPositionPerColumn);
        }

        return result;
    }

    // 依照破框畫面，計算那些位置為黃金
    private List<List<Boolean>> calculateGoldenPositionByScreenAndDamp(ScreenGtrResult screenGtrResult) {
        List<List<Boolean>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < screenGtrResult.getScreenSymbol().size(); columnIndex++) {
            double goldenProbabilityPerColumn = this.configExtend.getGoldenPositionProbList().get(columnIndex);
            List<Boolean> goldenPositionPerColumn = new ArrayList<>();

            // 計算上破框黃金位置
            if (screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().isExistUpperDamp()) {
                this.updateGoldenPositionPerColumn(goldenPositionPerColumn, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().get(columnIndex), goldenProbabilityPerColumn);
            }

            // 計算可視區黃金位置
            this.updateGoldenPositionPerColumn(goldenPositionPerColumn, screenGtrResult.getScreenSymbol().get(columnIndex), goldenProbabilityPerColumn);

            // 計算下破框黃金位置
            if (screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().isExistLowerDamp()) {
                this.updateGoldenPositionPerColumn(goldenPositionPerColumn, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().get(columnIndex), goldenProbabilityPerColumn);
            }

            // 添加黃金位置
            result.add(goldenPositionPerColumn);
        }

        // 回傳
        return result;
    }

    // 更新特定軸黃金位置
    private void updateGoldenPositionPerColumn(List<Boolean> goldenPositionPerColumn, List<Integer> targetSymbolIdList, double goldenProbabilityPerColumn) {
        for (int symbolId : targetSymbolIdList) {
            if (!this.cascadeHlrConfigExtend.getSymbolConfig().isScatterSymbol(symbolId)) {
                goldenPositionPerColumn.add(super.tableUtil.getMainRandomUtil().isHitWithAccuracy(goldenProbabilityPerColumn, ConstMathCommon.AccuracyType.MILLION));
            } else {
                goldenPositionPerColumn.add(false);
            }
        }
    }


    //** 計算Wild轉變位置 **//

    // 計算得分後，變成Wild的位置
    private List<List<Boolean>> calculateChangeToWildPosition(GameCtrResult gameCtrResult, List<List<Boolean>> beforeCascadeGoldenPosition) {
        // 1. 從算分結果，取得包含破框的得分位置
        List<List<Boolean>> hitPositionWithDamp = gameCtrResult.getIntegralDampHitPosition();

        // 2. 若無得分，則不需做
        if (hitPositionWithDamp.isEmpty()) {
            return Collections.emptyList();
        }

        // 3. 計算轉變 wild 位置
        List<List<Boolean>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < beforeCascadeGoldenPosition.size(); columnIndex++) {
            List<Boolean> changeWildPositionPerColumn = this.cascadeHlrMjhlUtil.calculateChangeWildPerColumn(hitPositionWithDamp.get(columnIndex), beforeCascadeGoldenPosition.get(columnIndex));
            result.add(changeWildPositionPerColumn);
        }

        // 4. 回傳
        return result;
    }


    //** 修改消除可能結果至實際結果 **//

    // 修改消除位置
    private void adjustEliminateCtrResult(ScreenGtrResult screenGtrResult, EliminateCtrResult eliminateCtrResult, List<List<Boolean>> changeToWildPosition) {
        // 1. 防呆
        if (eliminateCtrResult.getEliminatePosition().isEmpty() || eliminateCtrResult.getEliminatePositionDamp().isEmpty()) {
            return;
        }

        // 2. 修改含破框消除位置
        this.cascadeHlrMjhlUtil.adjustEliminatePositionWithDamp(eliminateCtrResult, changeToWildPosition);

        // 3. 修改純算分消除位置
        this.cascadeHlrMjhlUtil.adjustEliminatePositionWithScore(eliminateCtrResult, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());
    }


    //** 計算消除後 GoldPosition **//

    // 計算消除後黃金位置
    private List<List<Boolean>> calculateGoldenPositionAfterCascade(EliminateCtrResult eliminateCtrResult, List<List<Boolean>> changeToWildPosition, List<List<Boolean>> beforeCascadeGoldPosition) {
        // 1. 複製消除前黃金位置
        List<List<Boolean>> result = ListUtil.copy2DimensionBooleanList(beforeCascadeGoldPosition);
        List<List<Boolean>> eliminatePositionDamp = eliminateCtrResult.getEliminatePositionDamp();

        // 2. 防呆
        if (eliminatePositionDamp.isEmpty()) {
            return result;
        }

        // 3. 先將消除前黃金位置，移除 變成wild的位置
        for (int columnIndex = 0; columnIndex < result.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < result.get(columnIndex).size(); rowIndex++) {
                if (changeToWildPosition.get(columnIndex).get(rowIndex)) {
                    result.get(columnIndex).set(rowIndex, false);
                }
            }
        }

        // 4. 依照消除結果進行下落動作
        for (int columnIndex = 0; columnIndex < result.size(); columnIndex++) {
            for (int rowIndex = result.get(columnIndex).size() - 1; rowIndex >= 0; rowIndex--) {
                if (eliminatePositionDamp.get(columnIndex).get(rowIndex)) {
                    result.get(columnIndex).remove(rowIndex);
                }
            }
        }

        // 5. 補齊消除後，空的位置 [預設非黃金]
        for (int columnIndex = 0; columnIndex < result.size(); columnIndex++) {
            int fitCount = eliminatePositionDamp.get(columnIndex).size() - result.get(columnIndex).size();
            for (int fitIndex = 0; fitIndex < fitCount; fitIndex++) {
                result.get(columnIndex).add(0, false);
            }
        }

        // 6. 回傳
        return result;
    }


    //** 計算消除後剩餘畫面 **//

    // 計算消除後剩餘畫面
    private List<List<Integer>> calculateDampScreenSymbolAfterCascade(ScreenGtrResult screenGtrResult, List<List<Boolean>> changeToWildPosition, EliminateCtrResult eliminateCtrResult) {
        // 1. 取得含破框畫面
        List<List<Integer>> result = screenGtrResult.calculateScreenSymbolWithDamp();
        List<List<Boolean>> eliminatePositionDamp = eliminateCtrResult.getEliminatePositionDamp();

        // 2. 防呆
        if (eliminatePositionDamp.isEmpty()) {
            return result;
        }

        // 3. 修改畫面[將金位置變成 wild ]
        for (int columnIndex = 0; columnIndex < changeToWildPosition.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < changeToWildPosition.get(columnIndex).size(); rowIndex++) {
                if (changeToWildPosition.get(columnIndex).get(rowIndex)) {
                    result.get(columnIndex).set(rowIndex, this.cascadeHlrConfigExtend.getSymbolConfig().getTargetSymbolId(this.configExtend.getTransSymbolAttribute()));
                }
            }
        }

        // 4. 依照消除結果往下掉，將 -1 移到最上面
        for (int columnIndex = 0; columnIndex < result.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < result.get(columnIndex).size(); rowIndex++) {
                if (eliminatePositionDamp.get(columnIndex).get(rowIndex)) {
                    result.get(columnIndex).set(rowIndex, -1);
                }
            }

            result.set(columnIndex, ListUtil.transNegativeNumberToArrayFirst(result.get(columnIndex)));
        }

        // 5. 回傳
        return result;
    }
}
