package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

import java.util.ArrayList;
import java.util.List;

// 計算線擊中結果
public class LineHitScreenUtil {
    private final SymbolConfig symbolConfig; // 標誌設定

    public LineHitScreenUtil(SymbolConfig symbolConfig) {
        this.symbolConfig = symbolConfig;
    }

    // 計算 僅 wild 畫面打擊結果 [欄][列] = 符合標誌
    public List<Integer> calculateWildSymbolHitScreenPerLine(List<Integer> screenSymbolPerLine, List<Integer> payComboTargetSymbolIdList) {
        List<Integer> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < screenSymbolPerLine.size(); columnIndex++) {
            int symbolId = screenSymbolPerLine.get(columnIndex);

            if (payComboTargetSymbolIdList.contains(symbolId)) {
                result.add(symbolId);
            }else {
                result.add(-1);
            }
        }

        return result;
    }

    // 計算 一般標誌、wild 畫面打擊結果 [欄][列] = 符合標誌
    public List<Integer> calculateWildSymbolAndNormalSymbolHitScreenPerLine(List<Integer> screenSymbolPerLine, List<Integer> payComboTargetSymbolIdList) {
        List<Integer> result = new ArrayList<>();

        for (int columnIndex = 0; columnIndex < screenSymbolPerLine.size(); columnIndex++) {
            int symbolId = screenSymbolPerLine.get(columnIndex);

            if (payComboTargetSymbolIdList.contains(symbolId) || this.symbolConfig.isWildSymbol(symbolId)) {
                result.add(symbolId);
            }else {
                result.add(-1);
            }
        }

        return result;
    }

}
