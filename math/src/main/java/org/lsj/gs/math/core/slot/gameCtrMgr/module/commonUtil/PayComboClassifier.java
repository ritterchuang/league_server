package org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.ListUtil;

import java.util.*;
import java.util.stream.Collectors;

// 路得分賠率分類者
public class PayComboClassifier {
    private final SymbolConfig symbolConfig; // 標誌設定
    private final PayTableConfig payTableConfig; // 賠率設定

    public PayComboClassifier(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        this.symbolConfig = symbolConfig;
        this.payTableConfig = payTableConfig;
    }


    //* wayGame *//

    // 計算可能滿足畫面標誌的賠率分類 < 算分類型, payComboIdList >; [算分類型][index] = payComboId
    public Map<ConstMathSlot.GameCalculateType, List<Integer>> calculateGameCalculateTypeToPayComboIdListMapByScreenSymbol(List<List<Integer>> screenSymbol) {
        // 1. 創建空殼
        Map<ConstMathSlot.GameCalculateType, List<Integer>> result = new HashMap<>();

        // 2. 列出所有相異標誌
        List<Integer> distinctSymbolIdList = screenSymbol.stream().flatMap(List::stream).distinct().collect(Collectors.toList());

        // 3. 計算 滿足畫面上單一百搭的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.SINGLE_WILD, this.calculateSatisfySingleWildSymbolPayComboIdList(distinctSymbolIdList));

        // 4. 計算 滿足畫面上混百搭的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.MIX_WILD, this.calculateSatisfyMixWildSymbolPayComboIdList(distinctSymbolIdList));

        // 5. 計算 滿足畫面上單一標誌的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.SINGLE_NORMAL, this.calculateSatisfySingleNormalSymbolPayComboIdList(distinctSymbolIdList));

        // 6. 計算 滿足畫面上混搭標誌的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.MIX_NORMAL, this.calculateSatisfyMixNormalSymbolPayComboIdList(distinctSymbolIdList));

        // 7. 回傳
        return result;
    }


    //* lineGame *//

    // 計算可能滿足畫面標誌的賠率分類 < 算分類型, payComboIdList >; [算分類型][index] = payComboId
    public Map<ConstMathSlot.GameCalculateType, List<Integer>> calculateGameCalculateTypeToPayComboIdListMapByScreenSymbolPerLine(List<Integer> screenSymbolPerLine) {
        // 1. 創建空殼
        Map<ConstMathSlot.GameCalculateType, List<Integer>> result = new HashMap<>();

        // 2. 列出所有相異標誌
        List<Integer> distinctSymbolIdList = screenSymbolPerLine.stream().distinct().collect(Collectors.toList());

        // 3. 計算 滿足畫面上單一百搭的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.SINGLE_WILD, this.calculateSatisfySingleWildSymbolPayComboIdList(distinctSymbolIdList));

        // 4. 計算 滿足畫面上混百搭的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.MIX_WILD, this.calculateSatisfyMixWildSymbolPayComboIdList(distinctSymbolIdList));

        // 5. 計算 滿足畫面上單一標誌的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.SINGLE_NORMAL, this.calculateSatisfySingleNormalSymbolPayComboIdList(distinctSymbolIdList));

        // 6. 計算 滿足畫面上混搭標誌的賠率表ID列表
        result.put(ConstMathSlot.GameCalculateType.MIX_NORMAL, this.calculateSatisfyMixNormalSymbolPayComboIdList(distinctSymbolIdList));

        // 7. 回傳
        return result;
    }


    // 計算 滿足畫面上單一百搭的賠率表ID列表 [index] = payComboId
    private List<Integer> calculateSatisfySingleWildSymbolPayComboIdList(List<Integer> distinctSymbolIdList) {
        // 1. 畫面無百搭，回傳空
        if (distinctSymbolIdList.stream().noneMatch(this.symbolConfig::isWildSymbol)) {
            return Collections.emptyList();
        }

        // 2. 創建空殼
        Map<Integer, PayCombo> idToPayComboMap = new HashMap<>();

        // 3. 取出賠率列表
        List<PayCombo> payComboList = this.payTableConfig.getPayComboList();

        // 4. 判斷是否符合條件
        for (int payComboIndex = 0; payComboIndex < payComboList.size(); payComboIndex++) {
            // 4.1 取出賠率
            PayCombo payCombo = payComboList.get(payComboIndex);

            // 4.2 條件判斷
            if (payCombo.isMix() == false // 判斷是否單一百搭
            && payCombo.getSymbolIdList().stream().allMatch(this.symbolConfig::isWildSymbol) // 判斷賠率表是否全為 wild
            && ListUtil.isSubList(distinctSymbolIdList, payCombo.getSymbolIdList()) // 判斷畫面相異標誌包含賠率表標誌列表
            ) {
                idToPayComboMap.put(payComboIndex, payCombo);
            }
        }

        // 5. 回傳
        return new ArrayList<>(idToPayComboMap.keySet());
    }

    // 計算 滿足畫面上混百搭的賠率表ID列表 [index] = payComboId
    private List<Integer> calculateSatisfyMixWildSymbolPayComboIdList(List<Integer> distinctSymbolIdList) {
        // 1. 畫面無百搭，回傳空
        if (distinctSymbolIdList.stream().noneMatch(this.symbolConfig::isWildSymbol)) {
            return Collections.emptyList();
        }

        // 2. 創建空殼
        Map<Integer, PayCombo> idToPayComboMap = new HashMap<>();

        // 3. 取出賠率列表
        List<PayCombo> payComboList = this.payTableConfig.getPayComboList();

        // 4. 判斷是否符合條件
        for (int payComboIndex = 0; payComboIndex < payComboList.size(); payComboIndex++) {
            // 4.1 取出賠率
            PayCombo payCombo = payComboList.get(payComboIndex);

            // 4.2 條件判斷
            if (payCombo.isMix() // 是否混搭
            && payCombo.getSymbolIdList().stream().allMatch(this.symbolConfig::isWildSymbol) // 判斷賠率表是否全為 wild
            && ListUtil.isSubList(distinctSymbolIdList, payCombo.getSymbolIdList()) // 判斷畫面相異標誌包含賠率表標誌列表
            ) {
                idToPayComboMap.put(payComboIndex, payCombo);
            }
        }

        // 5. 排序混搭標誌個數少至大
        return this.calculateSortKeySetByValueSet(idToPayComboMap);
    }

    // 計算 滿足畫面上單一標誌的賠率表ID列表 [index] = payComboId
    private List<Integer> calculateSatisfySingleNormalSymbolPayComboIdList(List<Integer> distinctSymbolIdList) {
        // 1. 創建空殼
        Map<Integer, PayCombo> idToPayComboMap = new HashMap<>();

        // 2. 取出賠率列表
        List<PayCombo> payComboList = this.payTableConfig.getPayComboList();

        // 3. 判斷是否符合條件
        for (int payComboIndex = 0; payComboIndex < payComboList.size(); payComboIndex++) {
            // 3.1 取出賠率
            PayCombo payCombo = payComboList.get(payComboIndex);

            // 3.2 條件判斷
            if (payCombo.isMix() == false // 判斷是否單一標誌
            && payCombo.getSymbolIdList().stream().noneMatch(this.symbolConfig::isWildSymbol) // 標誌列表皆無 wild
            && ListUtil.isSubList(distinctSymbolIdList, payCombo.getSymbolIdList()) // 判斷畫面相異標誌包含賠率表標誌列表
            ) {
                idToPayComboMap.put(payComboIndex, payCombo);
            }
        }

        // 4. 回傳
        return new ArrayList<>(idToPayComboMap.keySet());
    }

    // 計算 滿足畫面上混搭標誌的賠率表ID列表 [index] = payComboId
    private List<Integer> calculateSatisfyMixNormalSymbolPayComboIdList(List<Integer> distinctSymbolIdList) {
        // 1. 創建空殼
        Map<Integer, PayCombo> idToPayComboMap = new HashMap<>();

        // 2. 取出賠率列表
        List<PayCombo> payComboList = this.payTableConfig.getPayComboList();

        // 3. 判斷是否符合條件
        for (int payComboIndex = 0; payComboIndex < payComboList.size(); payComboIndex++) {
            // 3.1 取出賠率
            PayCombo payCombo = payComboList.get(payComboIndex);

            // 3.2 條件判斷
            if (payCombo.isMix() // 判斷是否混搭
            && payCombo.getSymbolIdList().stream().noneMatch(this.symbolConfig::isWildSymbol) // 標誌列表皆無 wild
            && ListUtil.isSubList(distinctSymbolIdList, payCombo.getSymbolIdList()) // 判斷畫面相異標誌包含賠率表標誌列表
            ) {
                idToPayComboMap.put(payComboIndex, payCombo);
            }
        }

        // 4. 排序混搭標誌個數少至大
        return this.calculateSortKeySetByValueSet(idToPayComboMap);
    }

    // 將value 依照標誌個數排序後，輸出 key list [個數少至個數多]
    private List<Integer> calculateSortKeySetByValueSet(Map<Integer, PayCombo> idToPayComboList) {
        return idToPayComboList.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getSymbolIdList().size())) // 針對賠率標誌列表長度排序
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
