package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.LineCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.PayComboClassifier;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineCtrUtil.LineCtrUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.*;

// 線 計算器
public class LineCtr {
    private final List<List<Integer>> linePositionIndexList; // 線中獎位置 [lineId][columnIndex] = rowIndex
    private final PayComboClassifier payComboClassifier; // 賠率表分類器
    private final LineCtrUtil lineCtrUtil; // 線計算器工具包

    public LineCtr(SymbolConfig symbolConfig, PayTableConfig payTableConfig, List<List<Integer>> linePositionIndexList) {
        this.linePositionIndexList = linePositionIndexList;
        this.payComboClassifier = new PayComboClassifier(symbolConfig, payTableConfig);
        this.lineCtrUtil = new LineCtrUtil(symbolConfig, payTableConfig, linePositionIndexList);
    }

    // 計算一般線得分列表
    public List<LineCtrWinResult> calculateLineCtrWinResultList(
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 宣告空間
        List<LineCtrWinResult> result = new ArrayList<>();

        // 2. 遍歷所有結果
        for (int lineIndex = 0; lineIndex < this.linePositionIndexList.size(); lineIndex++) {

            // 3. 取得結果
            Optional<LineCtrWinResult> lineCtrWinResult = this.calculateLineCtrWinResultWithMultiplierPerLine(
                    lineIndex,
                   0,
                    new HashMap<>(),
                    new ArrayList<>(),
                    playerCreditBase,
                    screenGtrResult,
                    gameHitDirectionType);

            // 4. 添加結果
            lineCtrWinResult.ifPresent(result::add);
        }

        // 5. 回傳
        return result;
    }

    // 計算線得分全屏倍數列表
    public List<LineCtrWinResult> calculateLineCtrWinResultListWithScreenMultiplier(
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 宣告空間
        List<LineCtrWinResult> result = new ArrayList<>();

        // 2. 遍歷所有結果
        for (int lineIndex = 0; lineIndex < this.linePositionIndexList.size(); lineIndex++) {

            // 3. 取得結果
            Optional<LineCtrWinResult> lineCtrWinResult = this.calculateLineCtrWinResultWithMultiplierPerLine(
                    lineIndex,
                    screenMultiplier,
                    new HashMap<>(),
                    new ArrayList<>(),
                    playerCreditBase,
                    screenGtrResult,
                    gameHitDirectionType);

            // 4. 添加結果
            lineCtrWinResult.ifPresent(result::add);
        }

        // 5. 回傳
        return result;
    }

    // 計算多種倍數線得分列表
    public List<LineCtrWinResult> calculateLineCtrWinResultListWithMultiplier(
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {

        // 1. 宣告空間
        List<LineCtrWinResult> result = new ArrayList<>();

        // 2. 遍歷所有結果
        for (int lineIndex = 0; lineIndex < this.linePositionIndexList.size(); lineIndex++) {

            // 3. 取得結果
            Optional<LineCtrWinResult> lineCtrWinResult = this.calculateLineCtrWinResultWithMultiplierPerLine(
                    lineIndex,
                    screenMultiplier,
                    symbolIdToMultiplierMap,
                    multiplierMatrix,
                    playerCreditBase,
                    screenGtrResult,
                    gameHitDirectionType);

            // 4. 添加結果
            lineCtrWinResult.ifPresent(result::add);
        }

        // 5. 回傳
        return result;
    }

    // 計算結果
    private Optional<LineCtrWinResult> calculateLineCtrWinResultWithMultiplierPerLine(
            int lineId,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix,
            double playerCreditBase,
            ScreenGtrResult screenGtrResult,
            ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        // 1. 創建空間
        List<LineCtrWinResult> lineCtrWinResultList = new ArrayList<>();

        // 2. 計算可能賠率列表
        Map<ConstMathSlot.GameCalculateType, List<Integer>> calculateTypeToPayComboIdListMap = this.payComboClassifier.calculateGameCalculateTypeToPayComboIdListMapByScreenSymbolPerLine(this.lineCtrUtil.calculateScreenSymbolPerLine(lineId, screenGtrResult.getScreenSymbol()));

        // 3. 添加 單一Wild
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.SINGLE_WILD).forEach(
                payComboId -> this.lineCtrUtil.calculateSingleWildSymbolLineCtrWinResult(payComboId, lineId, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, playerCreditBase, screenGtrResult, gameHitDirectionType).ifPresent(lineCtrWinResultList::add)
        );

        // 4. 添加 混Wild
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.MIX_WILD).forEach(
                payComboId -> this.lineCtrUtil.calculateMixWildSymbolLineCtrWinResult(payComboId, lineId, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, playerCreditBase, screenGtrResult, gameHitDirectionType).ifPresent(lineCtrWinResultList::add)
        );

        // 5. 添加 單一標誌
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.SINGLE_NORMAL).forEach(
                payComboId -> this.lineCtrUtil.calculateSingleNormalSymbolAndWildSymbolLineCtrWinResult(payComboId, lineId, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, playerCreditBase, screenGtrResult, gameHitDirectionType).ifPresent(lineCtrWinResultList::add)
        );

        // 6. 添加 混標誌
        calculateTypeToPayComboIdListMap.get(ConstMathSlot.GameCalculateType.MIX_NORMAL).forEach(
                payComboId -> this.lineCtrUtil.calculateMixNormalSymbolAndWildSymbolLineCtrWinResult(payComboId, lineId, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, playerCreditBase, screenGtrResult, gameHitDirectionType).ifPresent(lineCtrWinResultList::add)
        );

        // 7. 若空回傳 empty
        if (lineCtrWinResultList.isEmpty() == true) {
            return Optional.empty();
        }

        // 8. 依照 odds, hitNumber, payComboId 排序資料
        Collections.sort(lineCtrWinResultList);

        // 9. 回傳排序最高結果
        return Optional.of(lineCtrWinResultList.get(0));
    }
}
