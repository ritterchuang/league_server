package org.lsj.gs.math.core.slot.readyHandHlrMgr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

import java.util.ArrayList;
import java.util.List;

// 抽象聽牌處理者
public abstract class AbstractReadyHandHlr {
    protected final FrameConfig frameConfig; // 畫面設定
    protected final SymbolConfig symbolConfig; // 標誌設定

    public AbstractReadyHandHlr(FrameConfig frameConfig, SymbolConfig symbolConfig) {
        this.frameConfig = frameConfig;
        this.symbolConfig = symbolConfig;
    }

    // 計算目標欄的目標標誌個數
    protected int calculateTargetSymbolCountPerColumn(List<List<Integer>> screenSymbol, ConstMathSlot.SymbolAttribute targetSymbol, int targetColumnIndex) {
        List<Integer> screenSymbolPerColumn = screenSymbol.get(targetColumnIndex);

        int targetSymbolCount = 0;
        for (int rowIndex = 0; rowIndex < screenSymbolPerColumn.size(); rowIndex++) {
            if (screenSymbolPerColumn.get(rowIndex) == this.symbolConfig.getTargetSymbolId(targetSymbol)) {
                targetSymbolCount++;
            }
        }

        return targetSymbolCount;
    }

    // 計算目標欄的目標標誌個數
    protected int calculateTargetSymbolCountPerColumn(List<List<Integer>> screenSymbol, List<ConstMathSlot.SymbolAttribute> targetSymbolList, int targetColumnIndex) {
        List<Integer> screenSymbolPerColumn = screenSymbol.get(targetColumnIndex);
        List<Integer> targetSymbolIdList = this.calculateTargetSymbolIdList(targetSymbolList);

        int targetSymbolCount = 0;
        for (int rowIndex = 0; rowIndex < screenSymbolPerColumn.size(); rowIndex++) {
            int currentSymbolId = screenSymbolPerColumn.get(rowIndex);
            if (targetSymbolIdList.contains(currentSymbolId)) {
                targetSymbolCount++;
            }
        }

        return targetSymbolCount;
    }

    // 計算標誌ID列表
    protected List<Integer> calculateTargetSymbolIdList(List<ConstMathSlot.SymbolAttribute> symbolAttributeList) {
        List<Integer> result = new ArrayList<>();

        symbolAttributeList.stream().forEach(symbolAttribute -> result.add(this.symbolConfig.getTargetSymbolId(symbolAttribute)));

        return result;
    }
}
