package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayCtrUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil.WayHitCountUtilEfficient;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.utils.MathUtil;

import java.util.List;
import java.util.Optional;

// 路計算包效率 [混搭須包含所有子集，若 payCombo 有缺少部分子集，僅可用窮舉算]
public class WayCtrUtilEfficient extends AbstractWayCtrUtil{
    private final WayHitCountUtilEfficient wayHitCountUtilEfficient; // 路擊中數工具包效率

    public WayCtrUtilEfficient(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        super(symbolConfig, payTableConfig);
        this.wayHitCountUtilEfficient = new WayHitCountUtilEfficient(symbolConfig, payTableConfig);
    }

    // 計算單一 wild 標誌路得分
    public Optional<WayCtrWinResult> calculateSingleWildSymbolWayCtrWinResult(int payComboId, double playerCreditBase, ScreenGtrResult screenGtrResult, ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        // 1. 取得目標 標誌列表
        List<Integer> payComboTargetSymbolIdList = super.wayCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 計算打擊畫面
        List<List<Integer>> hitScreen = super.wayHitScreenUtil.calculateWildSymbolHitScreen(screenGtrResult.getScreenSymbol(), payComboTargetSymbolIdList);

        // 3. 計算連線數
        int hitNumber = super.wayHitNumberUtil.calculateWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 4. 計算倍數
        int hitOdds = super.wayCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 5. 計算打擊個數
        int hitCount = this.wayHitCountUtilEfficient.calculateSingleWildSymbolHitCount(hitScreen, hitNumber);
        if (hitCount == 0) {
            return Optional.empty();
        }

        // 6. 計算分數
        double totalWin = MathUtil.multiply(playerCreditBase, hitOdds * hitCount);

        // 7. 計算中獎畫面
        List<List<Boolean>> screenPosition = super.wayCtrCommonUtil.calculateScreenHitPosition(hitScreen, hitNumber, gameHitDirectionType);

        // 8. 計算破框中獎畫面
        List<List<Boolean>> dampScreenPosition = this.wayCtrCommonUtil.calculateDampScreenHitPosition(screenPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 9. 回傳
        return Optional.of(new WayCtrWinResult(payComboId, hitNumber, hitCount, hitOdds, MathUtil.moneyScale(totalWin), screenPosition, dampScreenPosition));
    }

    // 計算混 wild 標誌路得分
    public Optional<WayCtrWinResult> calculateMixWildSymbolWayCtrWinResult(int payComboId, double playerCreditBase, ScreenGtrResult screenGtrResult, List<WayCtrWinResult> wayCtrWinResultList, ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        // 1. 取得目標 標誌列表
        List<Integer> payComboTargetSymbolIdList = super.wayCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 計算打擊畫面
        List<List<Integer>> hitScreen = super.wayHitScreenUtil.calculateWildSymbolHitScreen(screenGtrResult.getScreenSymbol(), payComboTargetSymbolIdList);

        // 3. 計算連線數
        int hitNumber = super.wayHitNumberUtil.calculateWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 4. 計算倍數
        int hitOdds = super.wayCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 5. 計算打擊個數
        int hitCount = this.wayHitCountUtilEfficient.calculateMixWildSymbolHitCount(hitScreen, payComboTargetSymbolIdList, hitNumber, wayCtrWinResultList);
        if (hitCount == 0) {
            return Optional.empty();
        }

        // 6. 計算分數
        double totalWin = MathUtil.multiply(playerCreditBase, hitOdds * hitCount);

        // 7. 計算中獎畫面
        List<List<Boolean>> screenPosition = super.wayCtrCommonUtil.calculateScreenHitPosition(hitScreen, hitNumber, gameHitDirectionType);

        // 8. 計算破框中獎畫面
        List<List<Boolean>> dampScreenPosition = this.wayCtrCommonUtil.calculateDampScreenHitPosition(screenPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 9. 回傳
        return Optional.of(new WayCtrWinResult(payComboId, hitNumber, hitCount, hitOdds, MathUtil.moneyScale(totalWin), screenPosition, dampScreenPosition));
    }

    // 計算單一 標誌路得分
    public Optional<WayCtrWinResult> calculateSingleNormalSymbolAndWildSymbolWayCtrWinResult(int payComboId, double playerCreditBase, ScreenGtrResult screenGtrResult, List<WayCtrWinResult> wayCtrWinResultList, ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        // 1. 取得目標 標誌列表
        List<Integer> payComboTargetSymbolIdList = super.wayCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 計算打擊畫面
        List<List<Integer>> hitScreen = super.wayHitScreenUtil.calculateWildSymbolAndNormalSymbolHitScreen(screenGtrResult.getScreenSymbol(), payComboTargetSymbolIdList);

        // 3. 計算連線數
        int hitNumber = super.wayHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 4. 計算倍數
        int hitOdds = super.wayCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 5. 計算打擊個數
        int hitCount = this.wayHitCountUtilEfficient.calculateSingleNormalSymbolAndWildSymbolHitCount(hitScreen, payComboTargetSymbolIdList, hitNumber, wayCtrWinResultList);
        if (hitCount == 0) {
            return Optional.empty();
        }

        // 6. 計算分數
        double totalWin = MathUtil.multiply(playerCreditBase, hitOdds * hitCount);

        // 7. 計算中獎畫面
        List<List<Boolean>> screenPosition = super.wayCtrCommonUtil.calculateScreenHitPosition(hitScreen, hitNumber, gameHitDirectionType);

        // 8. 計算破框中獎畫面
        List<List<Boolean>> dampScreenPosition = this.wayCtrCommonUtil.calculateDampScreenHitPosition(screenPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 9. 回傳
        return Optional.of(new WayCtrWinResult(payComboId, hitNumber, hitCount, hitOdds, MathUtil.moneyScale(totalWin), screenPosition, dampScreenPosition));
    }

    // 計算混 標誌路得分
    public Optional<WayCtrWinResult> calculateMixNormalSymbolAndWildSymbolWayCtrWinResult(int payComboId, double playerCreditBase, ScreenGtrResult screenGtrResult, List<WayCtrWinResult> wayCtrWinResultList, ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        // 1. 取得目標 標誌列表
        List<Integer> payComboTargetSymbolIdList = super.wayCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 計算打擊畫面
        List<List<Integer>> hitScreen = super.wayHitScreenUtil.calculateWildSymbolAndNormalSymbolHitScreen(screenGtrResult.getScreenSymbol(), payComboTargetSymbolIdList);

        // 3. 計算連線數
        int hitNumber = super.wayHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 4. 計算倍數
        int hitOdds = super.wayCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 5. 計算打擊個數
        int hitCount = this.wayHitCountUtilEfficient.calculateMixNormalSymbolAndWildSymbolHitCount(hitScreen, payComboTargetSymbolIdList, hitNumber, wayCtrWinResultList);
        if (hitCount == 0) {
            return Optional.empty();
        }

        // 6. 計算分數
        double totalWin = MathUtil.multiply(playerCreditBase, hitOdds * hitCount);

        // 7. 計算中獎畫面
        List<List<Boolean>> screenPosition = super.wayCtrCommonUtil.calculateScreenHitPosition(hitScreen, hitNumber, gameHitDirectionType);

        // 8. 計算破框中獎畫面
        List<List<Boolean>> dampScreenPosition = this.wayCtrCommonUtil.calculateDampScreenHitPosition(screenPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 9. 回傳
        return Optional.of(new WayCtrWinResult(payComboId, hitNumber, hitCount, hitOdds, MathUtil.moneyScale(totalWin), screenPosition, dampScreenPosition));
    }
}
