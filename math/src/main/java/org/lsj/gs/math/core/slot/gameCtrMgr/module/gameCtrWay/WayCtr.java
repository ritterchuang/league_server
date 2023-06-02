package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.PayComboClassifier;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayCtrUtil.WayCtrUtilEfficient;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayCtrUtil.WayCtrUtilExhaust;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.utils.ListUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// 路計算器
public class WayCtr {
    private final PayComboClassifier payComboClassifier; // 賠率表分類者
    private final WayCtrUtilEfficient wayCtrUtilEfficient; // 路計算器效率
    private final WayCtrUtilExhaust wayCtrUtilExhaust; // 路計算器窮舉

    public WayCtr(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        this.payComboClassifier = new PayComboClassifier(symbolConfig, payTableConfig);
        this.wayCtrUtilEfficient = new WayCtrUtilEfficient(symbolConfig, payTableConfig);
        this.wayCtrUtilExhaust = new WayCtrUtilExhaust(symbolConfig, payTableConfig);
    }

    // 計算一般路得分列表 [使用效率]
    public List<WayCtrWinResult> calculateWayCtrWinResultList(
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 宣告變數
        List<WayCtrWinResult> result = new ArrayList<>();

        // 2. 計算要的 payComboId 列表
        Map<ConstMathSlot.GameCalculateType, List<Integer>> calculateTypeToPayComboIdListMap = this.payComboClassifier.calculateGameCalculateTypeToPayComboIdListMapByScreenSymbol(screenGtrResult.getScreenSymbol());

        // 3. 添加 單一Wild 效率結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.SINGLE_WILD).forEach(
                payComboId -> this.wayCtrUtilEfficient.calculateSingleWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, gameHitDirectionType).ifPresent(result::add)
        );

        // 4. 添加 混Wild 效率結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.MIX_WILD).forEach(
                payComboId -> this.wayCtrUtilEfficient.calculateMixWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, result, gameHitDirectionType).ifPresent(result::add)
        );

        // 5. 添加 單一標誌 效率結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.SINGLE_NORMAL).forEach(
                payComboId -> this.wayCtrUtilEfficient.calculateSingleNormalSymbolAndWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, result, gameHitDirectionType).ifPresent(result::add)
        );

        // 6. 添加 混標誌 效率結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.MIX_NORMAL).forEach(
                payComboId -> this.wayCtrUtilEfficient.calculateMixNormalSymbolAndWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, result, gameHitDirectionType).ifPresent(result::add)
        );

        // 7. 回傳
        return result;
    }

    // 計算全畫面倍數 路得分列表 [使用效率]
    public List<WayCtrWinResult> calculateWayCtrWinResultListWithScreenMultiplier(
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 計算一般路得分結果
        List<WayCtrWinResult> wayCtrWinResultList = this.calculateWayCtrWinResultList(playerCreditBase, screenGtrResult, gameHitDirectionType);

        // 2. 回傳複製一分乘上倍數結果
        return this.calculateWayCtrWinResultListWithMultiplier(screenMultiplier, wayCtrWinResultList);

    }

    // 計算倍數路得分列表 [使用窮舉]
    public List<WayCtrWinResult> calculateWayCtrWinResultListWithMultiplier(
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 宣告變數
        List<WayCtrWinResult> result = new ArrayList<>();

        // 2. 計算分類後 payComboId 對應表 [key: 算分類型、value: [index] = payComboId]
        Map<ConstMathSlot.GameCalculateType, List<Integer>> calculateTypeToPayComboIdListMap = this.payComboClassifier.calculateGameCalculateTypeToPayComboIdListMapByScreenSymbol(screenGtrResult.getScreenSymbol());

        // 3. 添加 單一Wild 窮舉結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.SINGLE_WILD).forEach(
                payComboId -> this.wayCtrUtilExhaust.calculateSingleWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, gameHitDirectionType).ifPresent(result::add)
        );

        // 4. 添加 混Wild 窮舉結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.MIX_WILD).forEach(
                payComboId -> this.wayCtrUtilExhaust.calculateMixWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, gameHitDirectionType).ifPresent(result::add)
        );

        // 5. 添加 單一標誌 窮舉結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.SINGLE_NORMAL).forEach(
                payComboId -> this.wayCtrUtilExhaust.calculateSingleNormalSymbolAndWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, gameHitDirectionType).ifPresent(result::add)
        );

        // 6. 添加 混標誌 窮舉結果
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.MIX_NORMAL).forEach(
                payComboId -> this.wayCtrUtilExhaust.calculateMixNormalSymbolAndWildSymbolWayCtrWinResult(payComboId, playerCreditBase, screenGtrResult, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, gameHitDirectionType).ifPresent(result::add)
        );

        // 7. 回傳
        return result;
    }


    // 計算乘上倍數的路結果列表
    private List<WayCtrWinResult> calculateWayCtrWinResultListWithMultiplier(int screenMultiplier, List<WayCtrWinResult> wayCtrWinResultList) {
        // 1. 空陣列回傳空
        if (wayCtrWinResultList.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 創立空間
        List<WayCtrWinResult> result = new ArrayList<>();

        // 3. 將每筆結果乘上倍數
        wayCtrWinResultList.forEach(ctrResult -> result.add(this.calculateMultipleWayCtrWinResult(screenMultiplier, ctrResult)));

        // 4. 回傳
        return result;
    }

    // 計算乘上倍數的路結果
    private WayCtrWinResult calculateMultipleWayCtrWinResult(int screenMultiplier, WayCtrWinResult wayCtrWinResult) {
        return new WayCtrWinResult(
                wayCtrWinResult.getPayComboId(),
                wayCtrWinResult.getHitNumber(),
                wayCtrWinResult.getHitCount() * screenMultiplier,
                wayCtrWinResult.getHitOdds(),
                MathUtil.multiply(screenMultiplier, wayCtrWinResult.getTotalWin()),
                ListUtil.copy2DimensionBooleanList(wayCtrWinResult.getScreenHitPosition()),
                ListUtil.copy2DimensionBooleanList(wayCtrWinResult.getDampScreenHitPosition())
        );
    }
}
