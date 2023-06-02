package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfigExtendCjwp;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendCjwp;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr.CascadeProgressHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr.ICascadeProgressHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeScreenGtr.CascadeScreenGtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendCjwp;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByScreen;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.utils.ListUtil;

import java.util.*;
import java.util.stream.Collectors;

// 消除處理者 超級王牌
public class CascadeHlrCjwp extends AbstractCascadeHlr implements ICascadeHlr{
    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final CascadeConfigExtendCjwp configExtend; // 消除額外設定
    private final CascadeHlrConfigExtendCjwp cascadeHlrConfigExtend; // 消除處理者額外設定
    private final CascadeScreenGtr cascadeScreenGtr; // 消除畫面產生器
    private final ICascadeProgressHlr cascadeProgressHlr; // 消除進度處理者
    private final CascadeHlrCjwpUtil cascadeHlrCjwpUtil; // 消除處理者工具包

    public CascadeHlrCjwp(CascadeHlrConfig cascadeHlrConfig, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        super(cascadeHlrConfig, accumulateWinCtr, tableUtil);

        this.cascadeType = cascadeHlrConfig.getCascadeType();
        this.configExtend = (CascadeConfigExtendCjwp) cascadeHlrConfig.getCascadeConfigExtend();
        this.cascadeHlrConfigExtend = (CascadeHlrConfigExtendCjwp) cascadeHlrConfig.getCascadeHlrConfigExtend();

        this.cascadeScreenGtr = new CascadeScreenGtr(cascadeHlrConfigExtend.getCascadeScreenGtrConfig(), tableUtil);
        this.cascadeProgressHlr = new CascadeProgressHlr(cascadeHlrConfig.getCascadeProgressConfig(), tableUtil);

        this.cascadeHlrCjwpUtil = new CascadeHlrCjwpUtil(this.cascadeHlrConfigExtend.getSymbolConfig(), tableUtil);
    }

    // 計算消除處理者結果
    @Override
    public List<CascadeHlrResult> calculateCascadeHlrResultList(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        List<CascadeHlrResult> result = new ArrayList<>();

        for (int cascadeIndex = 0; cascadeIndex >= 0; cascadeIndex++) {

            // 1. 產出畫面結果 [Random Wild 觸發前]
            ScreenGtrResult screenGtrResult = this.calculateScreenGtrResult(cascadeIndex, result, reelRtpType);

            // 2. 計算Random Wild列表
            Map<Integer, Map<Integer, List<List<Boolean>>>> bigWildTransformPosition = this.calculateBigWildTransformPosition(cascadeIndex, result, screenGtrResult);

            // 3. 產出畫面結果 [Random Wild 觸發後]
            ScreenGtrResult screenGtrResultAfterRW = this.calculateScreenGtrResultAfterRW(cascadeIndex, screenGtrResult, bigWildTransformPosition, this.cascadeHlrConfigExtend.getSymbolConfig().getTargetSymbolId(this.configExtend.getTransSymbolAttribute()[0]));

            // 4. 計算消除倍數
            int cascadeMultiplier = this.calculateCascadeMultiplier(cascadeIndex);

            // 5. 算分
            GameCtrResult gameCtrResult = super.gameCtr.calculateGameCtrResultWithScreenMultiplier(spinRequest, screenGtrResultAfterRW, cascadeMultiplier);

            // 6. 計算可能消除位置
            EliminateCtrResult eliminateCtrResult = super.eliminationCtr.calculateEliminationCtrResult(gameCtrResult, new SpecialFeatureHlrResultCluster(), screenGtrResultAfterRW);

            // 7. 計算消除額外結果
            CascadeHlrResultExtendCjwp extendResult = this.calculateExtendResult(cascadeIndex, cascadeMultiplier, result, screenGtrResultAfterRW, gameCtrResult, eliminateCtrResult, bigWildTransformPosition);

            // 8. 計算總得分
            double totalWin = gameCtrResult.getTotalWin();

            // 9. 計算消除進度結果
            ProgressHlrResult progressHlrResult = this.cascadeProgressHlr.calculateProgressHlrResult(cascadeIndex, result, eliminateCtrResult);

            // 10. 計算消除累積得分
            AccumulateWinCtrResult accumulateWinCtrResult = super.accumulateWinCtr.calculateAccumulateWinCtrResultCascade(cascadeIndex, totalWin, result);

            // 11. 封裝此次消除結果
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

            // 12. 判斷是否繼續消除
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

    private ScreenGtrResult calculateScreenGtrResultAfterRW(int cascadeIndex, ScreenGtrResult screenGtrResult, Map<Integer, Map<Integer,List<List<Boolean>>>> bigWildTransformPosition, int bigWildId) {
        // 1. 第一次消除，不做事
        if (cascadeIndex == 0) {
            return screenGtrResult;
        }

        // 2. 依照 BigWild 結果與 RandomWild 結果，修改對應位置為 BigWild
        return this.calculateScreenGtrResultWithUpdateRandomWild(screenGtrResult, bigWildTransformPosition, bigWildId);
    }

    // 修改畫面結果
    private ScreenGtrResult calculateScreenGtrResultWithUpdateWild(ConstMathSlot.ReelRtpType reelRtpType, CascadeHlrResult beforeCascadeHlrResult) {
        // 1. 取得上一次消除後，算分範圍畫面
        List<List<Integer>> screenWithScoreAfterLastCascade = this.calculateScreenWithScoreAfterLastCascade(beforeCascadeHlrResult);

        // 2. 計算此次畫面結果
        ScreenGtrResult result = this.cascadeScreenGtr.generateCascadeScreenGtrResult(
                reelRtpType, beforeCascadeHlrResult.getScreenGtrResult(), beforeCascadeHlrResult.getEliminateCtrResult());

        // 3. 修改畫面標誌
        this.cascadeHlrCjwpUtil.updateScreenGtrResultWithLastCascadeViewScreen(result, screenWithScoreAfterLastCascade);

        // 4. 回傳
        return result;
    }

    private ScreenGtrResult calculateScreenGtrResultWithUpdateRandomWild(ScreenGtrResult screenGtrResult, Map<Integer, Map<Integer,List<List<Boolean>>>> bigWildTransformPosition, int bigWildId) {
        // 複製一份screenSymbol
        List<List<Integer>> screenSymbolCopy = ListUtil.copy2DimensionIntegerList(screenGtrResult.getScreenSymbol());

        ScreenGtrResult result = new ScreenGtrResult(screenSymbolCopy, screenGtrResult.getFrameResult(), screenGtrResult.getReelCtrResult()); //todo
        // 修改畫面標誌
        this.cascadeHlrCjwpUtil.updateScreenGtrResultWithRandomWild(result, bigWildTransformPosition, bigWildId);

        // 回傳
        return result;
    }

    // 計算上次消除後，可視範圍畫面
    private List<List<Integer>> calculateScreenWithScoreAfterLastCascade(CascadeHlrResult beforeCascadeHlrResult) {
        // 1. 取得上一次消除後畫面資訊 copy
        List<List<Integer>> result = ListUtil.copy2DimensionIntegerList(((CascadeHlrResultExtendCjwp)beforeCascadeHlrResult.getCascadeHlrResultExtend()).getDampScreenSymbolAfterCascade());

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
    private CascadeHlrResultExtendCjwp calculateExtendResult(
            int cascadeIndex,
            int cascadeMultiplier,
            List<CascadeHlrResult> cascadeHlrResultList,
            ScreenGtrResult screenGtrResult,
            GameCtrResult gameCtrResult,
            EliminateCtrResult eliminateCtrResult,
            Map<Integer, Map<Integer, List<List<Boolean>>>> bigWildTransformPosition) {

        // 1. 計算消除前 GoldPosition[6*5]
        List<List<Boolean>> beforeCascadeGoldenPosition = this.calculateGoldenPositionBeforeCascade(cascadeIndex, screenGtrResult, cascadeHlrResultList, bigWildTransformPosition);

        // 2. 計算 wild 轉變 position[6*5]
        List<List<Boolean>> changeToWildPosition= this.calculateChangeToWildPosition(gameCtrResult, beforeCascadeGoldenPosition);

        // 3. 計算 大wild 轉變 position[6*5]
        List<List<Boolean>> changeToBigWildPosition = this.calculateChangeToBigWildPosition(changeToWildPosition);

        // 4. 計算 小wild 轉變 position[6*5]
        List<List<Boolean>> changeToSmallWildPosition =  this.calculateChangeToSmallWildPosition(changeToWildPosition, changeToBigWildPosition);

        // 5. 修改消除位置 [6*5]
        this.adjustEliminateCtrResult(screenGtrResult, eliminateCtrResult, changeToWildPosition);

        // 6. 計算消除後 GoldPosition[6*5]
        List<List<Boolean>> afterCascadeGoldenPosition = this.calculateGoldenPositionAfterCascade(eliminateCtrResult, changeToWildPosition, beforeCascadeGoldenPosition);

        // 7. 計算消除後剩餘畫面 [消掉的地方填 -1][screenGtr 擴充方法取得]
        List<List<Integer>> dampScreenSymbolAfterCascade = this.calculateDampScreenSymbolAfterCascade(screenGtrResult, changeToBigWildPosition, changeToSmallWildPosition, eliminateCtrResult);

        // 8. RandomWild 位置加上damp[6*5]
        Map<Integer, Map<Integer, List<List<Boolean>>>> bigWildTransformPositionWithDamp = this.calculateRandomWildDamp(bigWildTransformPosition, screenGtrResult);

        // 9. 回傳
        return new CascadeHlrResultExtendCjwp(
                beforeCascadeGoldenPosition,
                afterCascadeGoldenPosition,
                changeToBigWildPosition,
                changeToSmallWildPosition,
                bigWildTransformPositionWithDamp,
                dampScreenSymbolAfterCascade,
                cascadeMultiplier
        );
    }


    //** 計算消除前黃金位置 **//

    // 計算消除前黃金位置
    private List<List<Boolean>> calculateGoldenPositionBeforeCascade(int cascadeIndex, ScreenGtrResult cascadeScreenGtrResult, List<CascadeHlrResult> cascadeHlrResultList, Map<Integer, Map<Integer, List<List<Boolean>>>> bigWildTransformPosition) {
        // 1. 第一場處理
        if (cascadeIndex == 0) {
            // 依照畫面、破框來計算 那些位置為 金
            return this.calculateGoldenPositionByScreenAndDamp(cascadeScreenGtrResult);
        }

        // 2. 取得上一次消除後 金 位置 [6*5]
        List<List<Boolean>> goldPositionAfterCascadeLastTime = ((CascadeHlrResultExtendCjwp)cascadeHlrResultList.get(cascadeIndex - 1).getCascadeHlrResultExtend()).getAfterCascadeGoldenPosition();

        // 3. 取得此次新增標誌結果，判斷哪些位置為金
        List<List<Integer>> addSymbolList = ((ReelStopResultExtendStopByScreen)cascadeScreenGtrResult.getReelCtrResult().getReelStopResultExtend()).getAddSymbolCtrResult().getAddSymbolList();

        // 4. 計算哪些位置為金
        List<List<Boolean>> goldenPositionForAddSymbolList = this.calculateGoldenPositionForAddSymbolList(addSymbolList);

        // 5. 回傳 哪些位置為金 [index = 0, 為最上面]
        return this.cascadeHlrCjwpUtil.unionGoldenPositionAddSymbolAndGoldPositionAfterCascadeLastTime(goldenPositionForAddSymbolList, goldPositionAfterCascadeLastTime);
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
            if (!this.cascadeHlrConfigExtend.getSymbolConfig().isScatterSymbol(symbolId) && !this.cascadeHlrConfigExtend.getSymbolConfig().isWildSymbol(symbolId)) {
                goldenPositionPerColumn.add(super.tableUtil.getMainRandomUtil().isHitWithAccuracy(goldenProbabilityPerColumn, ConstMathCommon.AccuracyType.A32768));
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
            List<Boolean> changeWildPositionPerColumn = this.cascadeHlrCjwpUtil.calculateChangeWildPerColumn(hitPositionWithDamp.get(columnIndex), beforeCascadeGoldenPosition.get(columnIndex));
            result.add(changeWildPositionPerColumn);
        }

        // 4. 回傳
        return result;
    }

    // 計算得分後，變成BigWild的位置
    private List<List<Boolean>> calculateChangeToBigWildPosition(List<List<Boolean>> changeToWildPosition) {

        // 1. 建立空結果List
        List<List<Boolean>> result = new ArrayList<>();

        // 2. 針對每個變成wild位置決定是否中 BigWild
        for (List<Boolean> rowWildPosition : changeToWildPosition) {

            List<Boolean> rowList = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < rowWildPosition.size(); rowIndex++) {
                // 2.1 建立預設結果(沒中)
                rowList.add(false);

                // 2.2 用設定檔中的權重決定是否中 BigWild
                if (rowWildPosition.get(rowIndex) && (this.configExtend.getTransSymbolAttribute()[this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.configExtend.getTransSymbolWeight()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.A32768)] == ConstMathSlot.SymbolAttribute.WILD_01)) {

                    // 2.3 如果中 BigWild 則該位置設定為true
                    rowList.set(rowIndex, true);
                }
            }
            // 2.4 添加結果
            result.add(rowList);
        }
        // 3. 回傳
        return result;
    }

    // 計算得分後，變成SmallWild的位置
    private List<List<Boolean>> calculateChangeToSmallWildPosition(List<List<Boolean>> changeToWildPosition, List<List<Boolean>> changeToBigWildPosition) {

        // 1. 建立空結果List
        List<List<Boolean>> result = new ArrayList<>();

        // 2. 針對每個變成Wild位置決定是否中 SmallWild
        for (int columnIndex = 0; columnIndex < changeToWildPosition.size(); columnIndex++) {

            List<Boolean> rowList = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < changeToWildPosition.get(columnIndex).size(); rowIndex++) {
                // 2.1 建立預設結果(沒中)
                rowList.add(false);

                // 2.2 檢查是否該位置沒中 BigWild，如果沒中 BigWild 則該位置設定為 true
                if(changeToWildPosition.get(columnIndex).get(rowIndex) && !changeToBigWildPosition.get(columnIndex).get(rowIndex)){

                    // 2.3 如果這個位置沒中 BigWild 則該位置設定為 true
                    rowList.set(rowIndex, true);
                }

            }
            // 2.4 添加結果
            result.add(rowList);
        }
        // 3. 回傳
        return result;
    }

    // 計算RandomWild的位置
    private Map<Integer, Map<Integer,List<List<Boolean>>>> calculateBigWildTransformPosition(int cascadeIndex, List<CascadeHlrResult> cascadeHlrResultList, ScreenGtrResult screenGtrResult) {

        // 1. 第一次消除，不做事
        if(cascadeIndex == 0){
            return new HashMap<>();
        }
        // 2. 取得上一次消除的gold位置
        List<List<Boolean>> lastRoundGoldPosition = ((CascadeHlrResultExtendCjwp)(cascadeHlrResultList.get(cascadeIndex - 1).getCascadeHlrResultExtend())).getAfterCascadeGoldenPosition();

        // 3. 取得上一次消除的 BigWild 位置
        List<List<Boolean>> changeToBigWildPosition = ((CascadeHlrResultExtendCjwp)(cascadeHlrResultList.get(cascadeIndex - 1).getCascadeHlrResultExtend())).getChangeToBigWildPosition();

        // 4. 建立檢查用參數(以防重複給同一個位置 RandomWild，預設所有位置為false)
        List<List<Boolean>> bigWildTransformCheckList = ListUtil.create2DimensionBooleanList(cascadeHlrResultList.get(cascadeIndex - 1).getScreenGtrResult().getScreenSymbol(), Boolean.FALSE);

        // 5. 建立結果MAP
        Map<Integer, Map<Integer,List<List<Boolean>>>> result = new HashMap<>();

        // 6. 針對每一個 BigWild 位置產生 RandomWild
        for (int columnIndex = 0; columnIndex < changeToBigWildPosition.size(); columnIndex++) {

            Map<Integer,List<List<Boolean>>> bigWildTransformRowResult = new HashMap<>();
            for (int rowIndex = 0; rowIndex < changeToBigWildPosition.get(columnIndex).size(); rowIndex++) {

                // 5.1 針對每個位置，檢查是否有中 BigWild
                if(changeToBigWildPosition.get(columnIndex).get(rowIndex)){

                    // 5.2 建立結果List並塞入預設結果(false)
                    List<List<Boolean>> randomWildResult = ListUtil.create2DimensionBooleanList(cascadeHlrResultList.get(cascadeIndex - 1).getScreenGtrResult().getScreenSymbol(), Boolean.FALSE);

                    // 5.3 計算Random Wild
                    this.calculateUpToFourRandomWild(screenGtrResult, randomWildResult, bigWildTransformCheckList, lastRoundGoldPosition);

                    // 5.4 添加結果
                    bigWildTransformRowResult.put(rowIndex, randomWildResult);
                    result.put(columnIndex, bigWildTransformRowResult);
                }
            }
        }
        // 6. 回傳
        return result;
    }

    // 檢查指定位置是否可以分派 Random Wild
    private boolean checkIfSlotIsAvailable(ScreenGtrResult screenGtrResult, List<List<Boolean>> bigWildTransformCheckList, int columnIndex, int rowIndex, List<List<Boolean>> lastRoundGoldPosition){
        // 1. RandomWild 只能出現在設定檔有設定的軸
        boolean isAvailableColumn = Arrays.stream(this.configExtend.getRandomWildAppearColumn()).boxed().collect(Collectors.toList()).contains(columnIndex);
        // 2. 檢查該位置上是否是 WILD SYMBOL
        boolean locationIsWild = this.cascadeHlrConfigExtend.getSymbolConfig().getSymbolAttributeTypeList().get(screenGtrResult.getScreenSymbol().get(columnIndex).get(rowIndex)) == ConstMathSlot.SymbolAttributeType.WILD_SYMBOL;
        // 3. 檢查該位置上是否是 FREE GAME SYMBOL
        boolean locationIsFreeGame = this.cascadeHlrConfigExtend.getSymbolConfig().getSymbolAttributeTypeList().get(screenGtrResult.getScreenSymbol().get(columnIndex).get(rowIndex)) == ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL;
        // 4. 檢查該位置上是否已被分配 RandomWild (需計算damp位置)
        boolean isAlreadyRandomWild = bigWildTransformCheckList.get(columnIndex).get(rowIndex);
        // 5. 檢查該位置上是否已是 Gold
        boolean isGold = lastRoundGoldPosition.get(columnIndex).get(rowIndex);

        return isAvailableColumn && !locationIsWild && !locationIsFreeGame && !isAlreadyRandomWild && !isGold;
    }

    // todo random wild 所出現的位置也可在設定檔做權重設定

    // 計算 RandomWild
    private void calculateUpToFourRandomWild(ScreenGtrResult screenGtrResult, List<List<Boolean>> randomWildResult, List<List<Boolean>> bigWildTransformCheckList, List<List<Boolean>> lastRoundGoldPosition) {
        // 1. 從設定檔中取得這次的 RandomWild 數量
        int randomWildCountLimit = this.configExtend.getRandomWildCount()[this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(Arrays.stream(this.configExtend.getRandomWildCountWeight()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.A32768)];

        // 2. [防呆] 確保可用位置足夠
        // 2.1 計算可用位置
        List<List<Integer>> availableSlot= new ArrayList<>();

        for (int columnIndex = 0; columnIndex < screenGtrResult.getScreenSymbol().size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < screenGtrResult.getScreenSymbol().get(columnIndex).size(); rowIndex++) {

                // 2.2 檢查位置是否可放 RandomWild
                if (this.checkIfSlotIsAvailable(screenGtrResult, bigWildTransformCheckList, columnIndex, rowIndex, lastRoundGoldPosition)) {
                    availableSlot.add(List.of(columnIndex, rowIndex));
                }
            }
        }

        // 2.4 如可用位置比 RandomWild 數量少，則將 RandomWild 數量設定成等同可用位置數量
        if(availableSlot.size() < randomWildCountLimit){
            randomWildCountLimit = availableSlot.size();
        }

        // 3. 隨機 shuffle 可用位置
        this.tableUtil.getMainRandomUtil().shuffleList(availableSlot);

        // 4. 挑出符合數量的位置並設定Random Wild
        for(int wildCount = 0; wildCount < randomWildCountLimit; wildCount++) {
            // 4.1 將位置加入 Random Wild 列表中
            randomWildResult.get(availableSlot.get(wildCount).get(0)).set(availableSlot.get(wildCount).get(1), true);
            // 4.2 將位置加入檢查表中
            bigWildTransformCheckList.get(availableSlot.get(wildCount).get(0)).set(availableSlot.get(wildCount).get(1), true);
        }
    }

    //** 修改消除可能結果至實際結果 **//

    // 修改消除位置
    private void adjustEliminateCtrResult(ScreenGtrResult screenGtrResult, EliminateCtrResult eliminateCtrResult, List<List<Boolean>> changeToWildPosition) {
        // 1. 防呆
        if (eliminateCtrResult.getEliminatePosition().isEmpty() || eliminateCtrResult.getEliminatePositionDamp().isEmpty()) {
            return;
        }

        // 2. 修改含破框消除位置
        this.cascadeHlrCjwpUtil.adjustEliminatePositionWithDamp(eliminateCtrResult, changeToWildPosition);

        // 3. 修改純算分消除位置
        this.cascadeHlrCjwpUtil.adjustEliminatePositionWithScore(eliminateCtrResult, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());
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
    private List<List<Integer>> calculateDampScreenSymbolAfterCascade(ScreenGtrResult screenGtrResult, List<List<Boolean>> changeToBigWildPosition, List<List<Boolean>> changeToSmallWildPosition, EliminateCtrResult eliminateCtrResult) {
        // 1. 取得含破框畫面
        List<List<Integer>> result = screenGtrResult.calculateScreenSymbolWithDamp();
        List<List<Boolean>> eliminatePositionDamp = eliminateCtrResult.getEliminatePositionDamp();

        // 2. 防呆
        if (eliminatePositionDamp.isEmpty()) {
            return result;
        }

        // 3. 修改畫面[將金位置變成 大wild]
        for (int columnIndex = 0; columnIndex < changeToBigWildPosition.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < changeToBigWildPosition.get(columnIndex).size(); rowIndex++) {
                if (changeToBigWildPosition.get(columnIndex).get(rowIndex)) {
                    result.get(columnIndex).set(rowIndex, this.cascadeHlrConfigExtend.getSymbolConfig().getTargetSymbolId(this.configExtend.getTransSymbolAttribute()[0]));
                }
            }
        }

        // 4. 修改畫面[將金位置變成 小wild]
        for (int columnIndex = 0; columnIndex < changeToSmallWildPosition.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < changeToSmallWildPosition.get(columnIndex).size(); rowIndex++) {
                if (changeToSmallWildPosition.get(columnIndex).get(rowIndex)) {
                    result.get(columnIndex).set(rowIndex, this.cascadeHlrConfigExtend.getSymbolConfig().getTargetSymbolId(this.configExtend.getTransSymbolAttribute()[1]));
                }
            }
        }

        // 5. 依照消除結果往下掉，將 -1 移到最上面
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


    //** 計算含 damp 的 RandomWild 畫面 **//

    // 計算含 damp 的 RandomWild 畫面
    private Map<Integer, Map<Integer, List<List<Boolean>>>> calculateRandomWildDamp(Map<Integer, Map<Integer, List<List<Boolean>>>> bigWildTransformPosition, ScreenGtrResult screenGtrResult) {
        Map<Integer, Map<Integer, List<List<Boolean>>>> result = new HashMap<>();

        bigWildTransformPosition.forEach((columnId, bigWildColumnPosition) ->{
            Map<Integer, List<List<Boolean>>> rowResult = new HashMap<>();

            bigWildColumnPosition.forEach((rowId, bigWildRowPosition) -> {
                List<List<Boolean>> transformPositionResult = new ArrayList<>();

                // 1. 針對每個BigWild所產生的RandomWild分布進行加上damp的動作
                for (int columnIndex = 0; columnIndex < bigWildRowPosition.size(); columnIndex++) {

                    List<Boolean> transformPositionRowResult = new ArrayList<>();

                    // 1.1 計算該軸的上下damp
                    int upperDamp = screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getUpperDampSymbolIdList().get(columnIndex).size();
                    int lowerDamp = screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult().getLowerDampSymbolIdList().get(columnIndex).size();

                    // 1.2 添加上damp
                    for (int rowIndex = 0; rowIndex < upperDamp; rowIndex++) {
                        transformPositionRowResult.add(false);
                    }

                    // 1.3 添加得分畫面
                    transformPositionRowResult.addAll(bigWildRowPosition.get(columnIndex));

                    // 1.4 添加下damp
                    for (int rowIndex = 0; rowIndex < lowerDamp; rowIndex++) {
                        transformPositionRowResult.add(false);
                    }
                    transformPositionResult.add(transformPositionRowResult);
                }
                rowResult.put(rowId, transformPositionResult);
            });
            result.put(columnId, rowResult);
        });

        // 2. 回傳
        return result;
    }
}
