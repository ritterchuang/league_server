package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 貫輪數工具包
public class WayHitNumberUtil {
    private SymbolConfig symbolConfig; // 標誌設定

    public WayHitNumberUtil(SymbolConfig symbolConfig) {
        this.symbolConfig = symbolConfig;
    }

    // 計算 單一Wild/混wild 貫輪數
    public int calculateWildSymbolHitNumber(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList) {
        // 1. 計算連線數
        int hitNumber = this.calculateWildHitNumber(hitScreen, payComboTargetSymbolIdList);

        // 2. 檢查是否符合
        if (this.isSatisfyPayComboTargetSymbolIdList(hitNumber, hitScreen, payComboTargetSymbolIdList)) {
            return hitNumber;
        }

        // 3. 不滿足
        return 0;
    }

    // 計算 一般標誌/混標誌 貫輪數
    public int calculateSymbolAndWildSymbolHitNumber(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList) {
        // 1. 計算連線數
        int hitNumber = this.calculateSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);

        // 2. 檢查是否符合
        if (this.isSatisfyPayComboTargetSymbolIdList(hitNumber, hitScreen, payComboTargetSymbolIdList)) {
            return hitNumber;
        }

        // 3. 不滿足
        return 0;
    }


    //* 檢查邏輯 *//

    // 檢查連線數是否滿足賠率標誌列表
    private boolean isSatisfyPayComboTargetSymbolIdList(int hitNumber, List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList) {
        // 1. 畫面標誌列表
        Map<Integer, Boolean> hitSymbolMap = payComboTargetSymbolIdList.stream().collect(Collectors.toMap(
                symbolId -> symbolId, symbolId -> false
        ));

        // 2. 確認標誌是否出現
        for (int columnIndex = 0; columnIndex < hitNumber; columnIndex++) {
            for (int hitCountIndex = 0; hitCountIndex < hitScreen.get(columnIndex).size(); hitCountIndex++) {
                int symbolId = hitScreen.get(columnIndex).get(hitCountIndex);
                if (payComboTargetSymbolIdList.contains(symbolId)) {
                    hitSymbolMap.put(symbolId, true);
                }
            }
        }

        // 3. 確認賠率表標誌全部出現過
        return hitSymbolMap.values().stream().allMatch(booleanValue -> booleanValue);
    }

    // 計算 wild 貫輪數
    private int calculateWildHitNumber(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList) {
        int result = 0;

        for (int columnIndex = 0; columnIndex < hitScreen.size(); columnIndex++) {
            if (hitScreen.get(columnIndex).stream().anyMatch(payComboTargetSymbolIdList::contains)) {
                result++;
            }else {
                break;
            }
        }

        return result;
    }

    // 計算 一般標誌 貫輪數
    private int calculateSymbolHitNumber(List<List<Integer>> hitScreen, List<Integer> payComboTargetSymbolIdList) {
        int result = 0;

        for (int columnIndex = 0; columnIndex < hitScreen.size(); columnIndex++) {
            if (hitScreen.get(columnIndex).stream().anyMatch(symbolId -> this.symbolConfig.isWildSymbol(symbolId) || payComboTargetSymbolIdList.contains(symbolId))) {
                result++;
            }else {
                break;
            }
        }

        return result;
    }
}
