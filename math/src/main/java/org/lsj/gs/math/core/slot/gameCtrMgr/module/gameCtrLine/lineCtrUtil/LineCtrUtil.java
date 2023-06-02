package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineCtrUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.LineCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil.LineCtrCommonUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil.LineHitNumberUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil.LineHitScreenUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil.LineMultiplierUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.utils.MathUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// 線計算者 工具包
public class LineCtrUtil {
    private final LineCtrCommonUtil lineCtrCommonUtil; // 線計算通用工具包
    private final LineHitScreenUtil lineHitScreenUtil; // 線連線畫面工具包
    private final LineHitNumberUtil lineHitNumberUtil; // 線連線數工具包
    private final LineMultiplierUtil lineMultiplierUtil; // 線額外倍數工具包

    public LineCtrUtil(SymbolConfig symbolConfig, PayTableConfig payTableConfig, List<List<Integer>> linePositionIndexList) {
        this.lineCtrCommonUtil = new LineCtrCommonUtil(symbolConfig, payTableConfig, linePositionIndexList);
        this.lineHitScreenUtil = new LineHitScreenUtil(symbolConfig);
        this.lineHitNumberUtil = new LineHitNumberUtil(symbolConfig);
        this.lineMultiplierUtil = new LineMultiplierUtil(linePositionIndexList);
    }

    // 給定線ID 計算畫面
    public List<Integer> calculateScreenSymbolPerLine(int lineId, List<List<Integer>> screenSymbol) {
        return this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenSymbol);
    }

    // 計算 線結果 單一 wild
    public Optional<LineCtrWinResult> calculateSingleWildSymbolLineCtrWinResult(
            int payComboId,
            int lineId,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix,
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 取得賠率 標誌列表
        List<Integer> payComboTargetSymbolIdList = this.lineCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 取得 單線畫面
        List<Integer> screenSymbolPerLine = this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenGtrResult.getScreenSymbol());

        // 3. 計算打擊畫面
        List<Integer> hitScreenPerLine = this.lineHitScreenUtil.calculateWildSymbolHitScreenPerLine(screenSymbolPerLine, payComboTargetSymbolIdList);

        // 4. 計算連線數
        int hitNumber = this.lineHitNumberUtil.calculateWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 5. 計算倍數
        int hitOdds = this.lineCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 6. 計算額外倍數
        int multiplier = this.lineMultiplierUtil.calculateMultiplier(lineId, hitNumber, hitScreenPerLine, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);

        // 7. 計算總得分
        double totalWin = MathUtil.multiply(playerCreditBase, MathUtil.multiply(hitOdds, multiplier));

        // 8. 計算中獎畫面
        List<List<Boolean>> screenHitPosition = this.lineCtrCommonUtil.calculateScreenHitPosition(screenGtrResult.getScreenSymbol(), lineId, hitNumber, gameHitDirectionType);

        // 9. 計算破框中獎畫面
        List<List<Boolean>> dampScreenHitPosition = this.lineCtrCommonUtil.calculateDampScreenHitPosition(screenHitPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 10. 回傳
        return Optional.of(new LineCtrWinResult(payComboId, lineId, hitNumber, hitOdds, multiplier, totalWin, screenHitPosition, dampScreenHitPosition));
    }

    // 計算 線結果 混 wild
    public Optional<LineCtrWinResult> calculateMixWildSymbolLineCtrWinResult(
            int payComboId,
            int lineId,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix,
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 取得賠率 標誌列表
        List<Integer> payComboTargetSymbolIdList = this.lineCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 取得 單線畫面
        List<Integer> screenSymbolPerLine = this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenGtrResult.getScreenSymbol());

        // 3. 計算打擊畫面
        List<Integer> hitScreenPerLine = this.lineHitScreenUtil.calculateWildSymbolHitScreenPerLine(screenSymbolPerLine, payComboTargetSymbolIdList);

        // 4. 計算連線數
        int hitNumber = this.lineHitNumberUtil.calculateWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 5. 計算倍數
        int hitOdds = this.lineCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 6. 計算額外倍數
        int multiplier = this.lineMultiplierUtil.calculateMultiplier(lineId, hitNumber, hitScreenPerLine, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);

        // 7. 計算總得分
        double totalWin = MathUtil.multiply(playerCreditBase, MathUtil.multiply(hitOdds, multiplier));

        // 8. 計算中獎畫面
        List<List<Boolean>> screenHitPosition = this.lineCtrCommonUtil.calculateScreenHitPosition(screenGtrResult.getScreenSymbol(), lineId, hitNumber, gameHitDirectionType);

        // 9. 計算破框中獎畫面
        List<List<Boolean>> dampScreenHitPosition = this.lineCtrCommonUtil.calculateDampScreenHitPosition(screenHitPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 10. 回傳
        return Optional.of(new LineCtrWinResult(payComboId, lineId, hitNumber, hitOdds, multiplier, totalWin, screenHitPosition, dampScreenHitPosition));
    }

    // 計算 線結果 單一標誌
    public Optional<LineCtrWinResult> calculateSingleNormalSymbolAndWildSymbolLineCtrWinResult(
            int payComboId,
            int lineId,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix,
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 取得賠率 標誌列表
        List<Integer> payComboTargetSymbolIdList = this.lineCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 取得 單線畫面
        List<Integer> screenSymbolPerLine = this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenGtrResult.getScreenSymbol());

        // 3. 計算打擊畫面
        List<Integer> hitScreenPerLine = this.lineHitScreenUtil.calculateWildSymbolAndNormalSymbolHitScreenPerLine(screenSymbolPerLine, payComboTargetSymbolIdList);

        // 4. 計算連線數
        int hitNumber = this.lineHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 5. 計算倍數
        int hitOdds = this.lineCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 6. 計算額外倍數
        int multiplier = this.lineMultiplierUtil.calculateMultiplier(lineId, hitNumber, hitScreenPerLine, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);

        // 7. 計算總得分
        double totalWin = MathUtil.multiply(playerCreditBase, MathUtil.multiply(hitOdds, multiplier));

        // 8. 計算中獎畫面
        List<List<Boolean>> screenHitPosition = this.lineCtrCommonUtil.calculateScreenHitPosition(screenGtrResult.getScreenSymbol(), lineId, hitNumber, gameHitDirectionType);

        // 9. 計算破框中獎畫面
        List<List<Boolean>> dampScreenHitPosition = this.lineCtrCommonUtil.calculateDampScreenHitPosition(screenHitPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 10. 回傳
        return Optional.of(new LineCtrWinResult(payComboId, lineId, hitNumber, hitOdds, multiplier, totalWin, screenHitPosition, dampScreenHitPosition));
    }

    // 計算 線結果 單一標誌
    public Optional<LineCtrWinResult> calculateMixNormalSymbolAndWildSymbolLineCtrWinResult(
            int payComboId,
            int lineId,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix,
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 取得賠率 標誌列表
        List<Integer> payComboTargetSymbolIdList = this.lineCtrCommonUtil.getPayComboTargetSymbolIdList(payComboId);

        // 2. 取得 單線畫面
        List<Integer> screenSymbolPerLine = this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenGtrResult.getScreenSymbol());

        // 3. 計算打擊畫面
        List<Integer> hitScreenPerLine = this.lineHitScreenUtil.calculateWildSymbolAndNormalSymbolHitScreenPerLine(screenSymbolPerLine, payComboTargetSymbolIdList);

        // 4. 計算連線數
        int hitNumber = this.lineHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        if (hitNumber == 0) {
            return Optional.empty();
        }

        // 5. 計算倍數
        int hitOdds = this.lineCtrCommonUtil.calculateHitOdds(payComboId, hitNumber);
        if (hitOdds == 0) {
            return Optional.empty();
        }

        // 6. 計算額外倍數
        int multiplier = this.lineMultiplierUtil.calculateMultiplier(lineId, hitNumber, hitScreenPerLine, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);

        // 7. 計算總得分
        double totalWin = MathUtil.multiply(playerCreditBase, MathUtil.multiply(hitOdds, multiplier));

        // 8. 計算中獎畫面
        List<List<Boolean>> screenHitPosition = this.lineCtrCommonUtil.calculateScreenHitPosition(screenGtrResult.getScreenSymbol(), lineId, hitNumber, gameHitDirectionType);

        // 9. 計算破框中獎畫面
        List<List<Boolean>> dampScreenHitPosition = this.lineCtrCommonUtil.calculateDampScreenHitPosition(screenHitPosition, screenGtrResult.getReelCtrResult().getReelStopResultExtend().getDampCtrResult());

        // 10. 回傳
        return Optional.of(new LineCtrWinResult(payComboId, lineId, hitNumber, hitOdds, multiplier, totalWin, screenHitPosition, dampScreenHitPosition));
    }
}
