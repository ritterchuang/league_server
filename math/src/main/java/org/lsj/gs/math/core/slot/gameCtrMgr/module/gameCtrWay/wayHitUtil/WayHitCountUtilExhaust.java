package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Way堆疊計算工具
public class WayHitCountUtilExhaust extends AbstractWayUtil {

    public WayHitCountUtilExhaust(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        super(symbolConfig, payTableConfig);
    }

    // 計算堆疊數
    public int calculateHitCount(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList, int hitNumber, int screenMultiplier, Map<Integer, Integer> symbolIdToMultiplierMap, List<List<Integer>> multiplierMatrix) {
        // 1. 計算簡化版畫面
        List<List<Integer>> simplifyHitScreen = super.calculateSimplifyHitScreen(hitScreen, hitNumber);

        // 2. 計算簡化版倍數矩陣
        List<List<Integer>> simplifyMultiplierMatrix = this.calculateSimplifyMultiplierMatrix(hitScreen, multiplierMatrix);

        // 3. 計算總堆疊數可能
        int totalHitCount = super.calculateTotalHitCount(simplifyHitScreen, hitNumber);

        // 4. 窮舉所有可能
        int hitCount = 0;
        for (int comboIndex = 0; comboIndex < totalHitCount; comboIndex++) {
            // 加總路數
            hitCount = hitCount + this.calculateSpecifyWayHitCountWithMultiplier(
                                                comboIndex,
                                                simplifyHitScreen,
                                                payComboTargetSymbolIdList,
                                                screenMultiplier,
                                                symbolIdToMultiplierMap,
                                                simplifyMultiplierMatrix);
        }

        // 5. 回傳
        return hitCount;
    }

    // 計算單條路堆疊
    private int calculateSpecifyWayHitCountWithMultiplier(int comboIndex, List<List<Integer>> simplifyHitScreen, List<Integer> payComboTargetSymbolIdList, int screenMultiplier, Map<Integer, Integer> symbolIdToMultiplierMap, List<List<Integer>> simplifyMultiplierMatrix) {
        //1. 計算中獎每列位置 [columnIndex] = rowIndex
        List<Integer> simplifyHitPositionIndexList = this.calculateSimplifyHitPositionIndexList(comboIndex, simplifyHitScreen);

        //2. 依照中獎每列位置取得畫面
        List<Integer> simplifyHitScreenPerWay = this.calculateScreenSymbolPerWay(simplifyHitPositionIndexList, simplifyHitScreen);

        //3. 判斷是否有中獎
        if (this.isWayWin(simplifyHitScreenPerWay, payComboTargetSymbolIdList) == true) {
            int hitCount = 1;

            // 3.1 乘上畫面倍數
            hitCount = (screenMultiplier == 0) ? hitCount : (hitCount * screenMultiplier);

            // 3.2 乘上貫過標誌固定倍數堆疊
            hitCount = hitCount * this.calculateSingleMultiplier(simplifyHitScreenPerWay, symbolIdToMultiplierMap);

            // 3.3 乘上貫過標誌倍數堆疊
            hitCount = hitCount * this.calculateMatrixMultiplier(simplifyHitPositionIndexList, simplifyMultiplierMatrix);

            // 3.4 回傳
            return hitCount;
        }

        return 0;
    }


    //* 計算倍數 *//

    // 計算貫過特殊標誌單一倍數
    private int calculateSingleMultiplier(List<Integer> simplifyHitScreenPerWay, Map<Integer, Integer> symbolIdToMultiplierMap) {
        // 1. 定義變數
        int result = 1;

        // 2. 防呆
        if (symbolIdToMultiplierMap.size() == 0) {
            return result;
        }

        // 3. 計算相異標誌
        List<Integer> distinctSymbolIdList = simplifyHitScreenPerWay.stream().distinct().collect(Collectors.toList());

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

    // 計算簡易版倍數位置陣列 [欄][列] = 倍數
    private List<List<Integer>> calculateSimplifyMultiplierMatrix(List<List<Integer>> hitScreen, List<List<Integer>> multiplierMatrix) {
        // 1. 檢查資料
        if (multiplierMatrix.isEmpty() || hitScreen.isEmpty()) {
            return new ArrayList<>();
        }

        // 2. 該位置有擊中，則記錄對應倍數
        List<List<Integer>> result = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < hitScreen.size(); columnIndex++) {
            List<Integer> multiplierPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < hitScreen.get(columnIndex).size(); rowIndex++) {
                if (hitScreen.get(columnIndex).get(rowIndex) >= 0) {
                    multiplierPerColumn.add(multiplierMatrix.get(columnIndex).get(rowIndex));
                }
            }

            result.add(multiplierPerColumn);
        }

        // 3. 回傳
        return result;
    }

    // 依照簡易版倍數位置陣列、中獎位置計算倍數
    private int calculateMatrixMultiplier(List<Integer> simplifyHitPositionIndexList, List<List<Integer>> simplifyMultiplierMatrix) {
        // 1. 定義初始值
        int result = 1;

        // 2. 空陣列回傳出使值
        if (simplifyMultiplierMatrix.isEmpty()) {
            return result;
        }

        // 3. 依照擊中位置，計算倍數
        for (int columnIndex = 0; columnIndex < simplifyHitPositionIndexList.size(); columnIndex++) {
            // 3.1 取得座標位置
            int positionIndex = simplifyHitPositionIndexList.get(columnIndex);

            // 3.2 計算倍數
            int multiplier = simplifyMultiplierMatrix.get(columnIndex).get(positionIndex);

            // 3.3 連乘
            result = (multiplier == 0) ? result : result * multiplier;
        }

        // 4. 回傳
        return result;
    }


    //* 通用方法 *//

    // 依照是否含 Wild 計算相異標誌列表 [index] = 畫面標誌
    private List<Integer> calculateDistinctSymbolList(List<Integer> screenSymbolPerWay, List<Integer> payComboTargetSymbolIdList) {
        // 1. 若目標有 wild，則不須省略
        if (payComboTargetSymbolIdList.stream().allMatch(super.symbolConfig::isWildSymbol)) {
            return screenSymbolPerWay.stream().distinct().collect(Collectors.toList());
        }

        // 2. 目標無 wild，需省略 wild
        return screenSymbolPerWay.stream()
                .distinct()
                .filter(symbol -> super.symbolConfig.isWildSymbol(symbol) == false)
                .collect(Collectors.toList());
    }


    // 計算中獎畫面 [欄] = 標誌
    private List<Integer> calculateScreenSymbolPerWay(List<Integer> simplifyHitPositionIndexList, List<List<Integer>> simplifyHitScreen) {
        // 1. 宣告空間
        List<Integer> result = new ArrayList<>();

        // 2. 依照中獎位置取得標誌
        for (int columnIndex = 0; columnIndex < simplifyHitPositionIndexList.size(); columnIndex++) {
            int positionIndex = simplifyHitPositionIndexList.get(columnIndex);

            result.add(simplifyHitScreen.get(columnIndex).get(positionIndex));
        }

        // 3. 回傳
        return result;
    }


    //* 條件判斷 *//

    // 判斷是否 路中獎
    private boolean isWayWin(List<Integer> simplifyHitScreenPerWay, List<Integer> payComboTargetSymbolIdList) {
        // 1. 依照目標計算畫面相異標誌列表
        List<Integer> distinctSymbolList = this.calculateDistinctSymbolList(simplifyHitScreenPerWay, payComboTargetSymbolIdList);

        // 2. 判斷是否符合贏分
        return ListUtil.isSameListWithoutOrder(distinctSymbolList, payComboTargetSymbolIdList);
    }

    // 計算中獎位置 [欄] = 列
    private List<Integer> calculateSimplifyHitPositionIndexList(int comboIndex, List<List<Integer>> simplifyHitScreen) {
        // 1. 宣告空間
        List<Integer> result = new ArrayList<>();

        // 2. 計算中獎位置
        for (int columnIndex = 0; columnIndex < simplifyHitScreen.size(); columnIndex++) {
            int symbolCountPerColumn = simplifyHitScreen.get(columnIndex).size();

            result.add(comboIndex % symbolCountPerColumn);

            comboIndex = comboIndex / symbolCountPerColumn;
        }

        // 3. 回傳
        return result;
    }
}
