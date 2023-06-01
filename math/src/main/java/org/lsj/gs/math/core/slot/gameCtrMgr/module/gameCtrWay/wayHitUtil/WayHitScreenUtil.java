package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

import java.util.ArrayList;
import java.util.List;

// 計算畫面擊中結果
public class WayHitScreenUtil {
    private final SymbolConfig symbolConfig; // 標誌設定

    public WayHitScreenUtil(SymbolConfig symbolConfig) {
        this.symbolConfig = symbolConfig;
    }

    // 計算 僅 wild 畫面打擊結果 [欄][列] = 符合標誌
    public List<List<Integer>> calculateWildSymbolHitScreen(List<List<Integer>> hitScreenSymbol, List<Integer> payComboTargetSymbolIdList) {
        List<List<Integer>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < hitScreenSymbol.size(); columnIndex++) {
            List<Integer> hitScreenPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < hitScreenSymbol.get(columnIndex).size(); rowIndex++) {
                int symbolId = hitScreenSymbol.get(columnIndex).get(rowIndex);

                if (payComboTargetSymbolIdList.contains(symbolId)) {
                    hitScreenPerColumn.add(symbolId);
                }else {
                    hitScreenPerColumn.add(-1);
                }
            }

            result.add(hitScreenPerColumn);
        }

        return result;
    }

    // 計算 一般標誌、wild 畫面打擊結果 [欄][列] = 符合標誌
    public List<List<Integer>> calculateWildSymbolAndNormalSymbolHitScreen(List<List<Integer>> hitScreenSymbol, List<Integer> payComboTargetSymbolIdList) {
        List<List<Integer>> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < hitScreenSymbol.size(); columnIndex++) {
            List<Integer> hitScreenPerColumn = new ArrayList<>();

            for (int rowIndex = 0; rowIndex < hitScreenSymbol.get(columnIndex).size(); rowIndex++) {
                int symbolId = hitScreenSymbol.get(columnIndex).get(rowIndex);

                if (payComboTargetSymbolIdList.contains(symbolId) || this.symbolConfig.isWildSymbol(symbolId)) {
                    hitScreenPerColumn.add(symbolId);
                }else {
                    hitScreenPerColumn.add(-1);
                }

            }

            result.add(hitScreenPerColumn);
        }

        return result;
    }

}
