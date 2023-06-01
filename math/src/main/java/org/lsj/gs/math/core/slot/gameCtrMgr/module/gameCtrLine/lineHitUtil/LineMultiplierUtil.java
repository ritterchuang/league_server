package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 倍數計算工具包
public class LineMultiplierUtil {
    private final List<List<Integer>> linePositionIndexList; // 線中獎位置 [lineId][columnIndex] = rowIndex

    public LineMultiplierUtil(List<List<Integer>> linePositionIndexList) {
        this.linePositionIndexList = linePositionIndexList;
    }

    // 計算倍數
    public int calculateMultiplier(
            int lineId,
            int hitNumber,
            List<Integer> hitScreenPerLine,
            int screenMultiplier,
            Map<Integer, Integer> symbolIdToMultiplierMap,
            List<List<Integer>> multiplierMatrix) {

        // 1. 宣告空間
        int result = 1;

        // 2. 計算畫面倍數
        result = (screenMultiplier == 0) ? result : result * screenMultiplier;

        // 3. 乘上貫過標誌固定倍數
        result = result * this.calculateSingleMultiplier(hitScreenPerLine, symbolIdToMultiplierMap);

        // 4. 乘上貫過標誌
        result = result * this.calculateMatrixMultiplier(lineId, hitNumber, multiplierMatrix);

        // 5. 回傳
        return result;
    }

    // 計算 矩陣倍數
    private int calculateMatrixMultiplier(int lineId, int hitNumber, List<List<Integer>> multiplierMatrix) {
        // 1. 建立空間
        int result = 1;

        // 2. 檢查資料
        if (multiplierMatrix.isEmpty()) {
            return result;
        }

        // 3. 取得線位置
        List<Integer> linePosition = this.linePositionIndexList.get(lineId);

        // 4. 計算倍數
        for (int columnIndex = 0; columnIndex < hitNumber; columnIndex++) {
            int rowIndex = linePosition.get(columnIndex);

            int multiplier = multiplierMatrix.get(columnIndex).get(rowIndex);

            result = (multiplier == 0) ? result : result * multiplier;
        }

        // 5. 回傳
        return result;
    }

    // 計算 灌過標誌倍數
    private int calculateSingleMultiplier(List<Integer> hitScreenPerLine, Map<Integer, Integer> symbolIdToMultiplierMap) {
        // 1. 定義空間
        int result = 1;

        // 2. 防呆檢測
        if (hitScreenPerLine.isEmpty() || symbolIdToMultiplierMap.size() == 0) {
            return result;
        }

        // 3. 計算相異標誌列表
        List<Integer> distinctSymbolIdList = hitScreenPerLine.stream().distinct().collect(Collectors.toList());

        // 4. 計算倍數
        for (int symbolIndex = 0; symbolIndex < distinctSymbolIdList.size(); symbolIndex++) {
            if (symbolIdToMultiplierMap.containsKey(distinctSymbolIdList.get(symbolIndex))) {
                int multiplier = symbolIdToMultiplierMap.get(distinctSymbolIdList.get(symbolIndex));

                result = (multiplier == 0) ? result : result * multiplier;
            }
        }

        // 5. 回傳
        return result;
    }
}
