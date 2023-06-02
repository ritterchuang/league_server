package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfigExtendDgry;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResultExtendDgry;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr.CascadeProgressHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeProgressHlr.ICascadeProgressHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeScreenGtr.CascadeScreenGtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.result.EliminateCtrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend.CascadeConfigExtendDgry;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 消除處理者 帝国榮耀
public class CascadeHlrDgry extends AbstractCascadeHlr implements ICascadeHlr{
    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final CascadeConfigExtendDgry configExtend; // 消除額外設定
    private final CascadeHlrConfigExtendDgry cascadeHlrConfigExtend; // 消除處理者額外設定
    private final CascadeScreenGtr cascadeScreenGtr; // 消除畫面產生器
    private final ICascadeProgressHlr cascadeProgressHlr; // 消除進度處理者
    private final CascadeHlrDgryUtil cascadeHlrDgryUtil; // 消除處理者工具包

    public CascadeHlrDgry(CascadeHlrConfig cascadeHlrConfig, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        super(cascadeHlrConfig, accumulateWinCtr, tableUtil);

        this.cascadeType = cascadeHlrConfig.getCascadeType();
        this.configExtend = (CascadeConfigExtendDgry) cascadeHlrConfig.getCascadeConfigExtend();
        this.cascadeHlrConfigExtend = (CascadeHlrConfigExtendDgry) cascadeHlrConfig.getCascadeHlrConfigExtend();

        this.cascadeScreenGtr = new CascadeScreenGtr(cascadeHlrConfigExtend.getCascadeScreenGtrConfig(), tableUtil);
        this.cascadeProgressHlr = new CascadeProgressHlr(cascadeHlrConfig.getCascadeProgressConfig(), tableUtil);

        this.cascadeHlrDgryUtil = new CascadeHlrDgryUtil(this.cascadeHlrConfigExtend.getSymbolConfig(), tableUtil);
    }

    // 計算消除處理者結果
    @Override
    public List<CascadeHlrResult> calculateCascadeHlrResultList(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        List<CascadeHlrResult> result = new ArrayList<>();

        for (int cascadeIndex = 0; cascadeIndex >= 0; cascadeIndex++) {

            // 1. 產出畫面結果
            ScreenGtrResult screenGtrResult = this.calculateScreenGtrResult(cascadeIndex, result, reelRtpType);

            // 2. 計算畫面倍數
            int screenMultiplier = this.calculateScreenMultiplier(this.configExtend.getScreenMultiplier(), this.configExtend.getScreenMultiplierWeight().get(reelRtpType));

            // 3. 算分
            GameCtrResult gameCtrResult = super.gameCtr.calculateGameCtrResultWithScreenMultiplier(spinRequest, screenGtrResult, screenMultiplier);

            // 4. 計算BonusGame Symbol位置
            List<List<Boolean>> bonusGameSymbolLocaiton = this.calculateFreeGameSymbolLocation(screenGtrResult.getScreenSymbol());

            // 5. 計算BonusGame Symbol位置 with Damp
            List<List<Boolean>> bonusGameSymbolLocaitonWithDamp = this.calculateFreeGameSymbolLocation(screenGtrResult.calculateScreenSymbolWithDamp());

            // 6. 計算一般標誌可能消除位置
            EliminateCtrResult normalSymbolEliminateCtrResult = super.eliminationCtr.calculateEliminationCtrResult(gameCtrResult, new SpecialFeatureHlrResultCluster(), screenGtrResult);

            // 7. 計算BonusGame Symbol可能消除位置
            EliminateCtrResult eliminateCtrResult = this.calculateEliminationCtrResult(gameCtrResult, normalSymbolEliminateCtrResult, bonusGameSymbolLocaiton, bonusGameSymbolLocaitonWithDamp);

            // 7. 計算消除額外結果
            CascadeHlrResultExtendDgry extendResult = this.calculateExtendResult(screenGtrResult, eliminateCtrResult, screenMultiplier);

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

        // 13. 回傳
        return result;
    }


    //* 計算畫面產出結果 *//

    // 產出畫面
    private ScreenGtrResult calculateScreenGtrResult(int cascadeIndex,  List<CascadeHlrResult> cascadeHlrResultList, ConstMathSlot.ReelRtpType reelRtpType) {

        // 1. 第一次消除，直接產
        if (cascadeIndex == 0) {
            return super.screenGtr.generateScreenGtrResult(reelRtpType);
        }

        // 2. 依照上次消除結果，產生畫面
        return this.calculateScreenGtrResultWithUpdateWild(reelRtpType, cascadeHlrResultList.get(cascadeIndex - 1));
    }

    // 修改畫面結果
    private ScreenGtrResult calculateScreenGtrResultWithUpdateWild(ConstMathSlot.ReelRtpType reelRtpType, CascadeHlrResult beforeCascadeHlrResult) {

        // 1. 計算此次畫面結果
        return this.cascadeScreenGtr.generateCascadeScreenGtrResult(
                reelRtpType, beforeCascadeHlrResult.getScreenGtrResult(), beforeCascadeHlrResult.getEliminateCtrResult());
    }

    //* 計算畫面倍數 *//

    // 計算畫面倍數
    private int calculateScreenMultiplier(List<Integer> multiplierList, List<Integer> multiplierListWeight) {
        int multiplierIndex = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(multiplierListWeight, ConstMathCommon.AccuracyType.A32768);

        return multiplierList.get(multiplierIndex);
    }

    //* 計算bonus game symbol 位置 *//

    private List<List<Boolean>> calculateFreeGameSymbolLocation(List<List<Integer>> screenSymbol) {
        List<List<Boolean>> result = new ArrayList<>();
        List<List<Boolean>> resultIfConditionMet = new ArrayList<>();
        int targetSymbolCount = 3;
        int counter = 0;

        for (int columnIndex = 0; columnIndex < screenSymbol.size(); columnIndex++) {
            List<Boolean> listPerColumn = new ArrayList<>();
            List<Boolean> listPerColumnIfConditionMet = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < screenSymbol.get(columnIndex).size(); rowIndex++) {
                if(this.cascadeHlrConfigExtend.getSymbolConfig().getSymbolAttributeTypeList().get(screenSymbol.get(columnIndex).get(rowIndex)) == ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL) {
                    listPerColumn.add(true);
                    listPerColumnIfConditionMet.add(false);
                    counter++;
                } else {
                    listPerColumn.add(false);
                    listPerColumnIfConditionMet.add(false);
                }
            }

            result.add(listPerColumn);
            resultIfConditionMet.add(listPerColumnIfConditionMet);
        }

        if(counter >= targetSymbolCount){
            return resultIfConditionMet;
        }else {
            return result;
        }
    }

    //* 計算客製結果 *//

    // 計算消除額外結果
    private CascadeHlrResultExtendDgry calculateExtendResult(
            ScreenGtrResult screenGtrResult,
            EliminateCtrResult eliminateCtrResult,
            int screenMultiplier) {


        // 5. 計算消除後剩餘畫面 [消掉的地方填 -1][screenGtr 擴充方法取得]
        List<List<Integer>> dampScreenSymbolAfterCascade = this.calculateDampScreenSymbolAfterCascade(screenGtrResult, eliminateCtrResult);

        // 6. 回傳
        return new CascadeHlrResultExtendDgry(
                dampScreenSymbolAfterCascade,
                screenMultiplier
        );
    }

    // 修改消除位置
    private EliminateCtrResult calculateEliminationCtrResult(GameCtrResult gameCtrResult, EliminateCtrResult normalSymbolEliminateCtrResult, List<List<Boolean>> bonusGameSymbolLocaiton, List<List<Boolean>> bonusGameSymbolLocaitonWithDamp){

        if(gameCtrResult.getTotalWin() > 0) {
            // 3. 計算一般消除結果(客製消除位置列表)
            List<List<Boolean>> eliminatePosition = this.calculateEliminatePosition(normalSymbolEliminateCtrResult.getEliminatePosition(), bonusGameSymbolLocaiton);

            // 4. 計算含破框資訊消除結果(客製消除位置列表)
            List<List<Boolean>> eliminatePositionDamp = this.calculateEliminatePosition(normalSymbolEliminateCtrResult.getEliminatePositionDamp(), bonusGameSymbolLocaitonWithDamp);

            return new EliminateCtrResult(ConstMathSlot.EliminationType.IF_HIT, eliminatePosition, eliminatePositionDamp);
        }

        return normalSymbolEliminateCtrResult;
    }

    // 計算消除位置
    private List<List<Boolean>> calculateEliminatePosition(List<List<Boolean>> targetArrayA, List<List<Boolean>> targetArrayB) {
        if (targetArrayA.isEmpty() && targetArrayB.isEmpty()) {
            return Collections.emptyList();
        }

        if (targetArrayA.isEmpty()) {
            return targetArrayB;
        }

        if (targetArrayB.isEmpty()) {
            return targetArrayA;
        }

        return ListUtil.unionSameSize2DimensionBooleanList(targetArrayA, targetArrayB);
    }



    //** 計算消除後剩餘畫面 **//

    // 計算消除後剩餘畫面
    private List<List<Integer>> calculateDampScreenSymbolAfterCascade(ScreenGtrResult screenGtrResult, EliminateCtrResult eliminateCtrResult) {
        // 1. 取得含破框畫面
        List<List<Integer>> result = screenGtrResult.calculateScreenSymbolWithDamp();
        List<List<Boolean>> eliminatePositionDamp = eliminateCtrResult.getEliminatePositionDamp();

        // 2. 防呆
        if (eliminatePositionDamp.isEmpty()) {
            return result;
        }

        // 3. 依照消除結果往下掉，將 -1 移到最上面
        for (int columnIndex = 0; columnIndex < result.size(); columnIndex++) {
            for (int rowIndex = 0; rowIndex < result.get(columnIndex).size(); rowIndex++) {
                if (eliminatePositionDamp.get(columnIndex).get(rowIndex)) {
                    result.get(columnIndex).set(rowIndex, -1);
                }
            }

            result.set(columnIndex, ListUtil.transNegativeNumberToArrayFirst(result.get(columnIndex)));
        }

        // 4. 回傳
        return result;
    }
}
