package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 標誌設定
public class SymbolConfig {
    private final List<ConstMathSlot.SymbolAttribute> symbolAttributeList; // 標誌列表
    @JsonIgnore
    private final List<ConstMathSlot.SymbolAttributeType> symbolAttributeTypeList; // 標誌類型列表

    public SymbolConfig(List<ConstMathSlot.SymbolAttribute> symbolAttributeList, List<ConstMathSlot.SymbolAttributeType> symbolAttributeTypeList) {
        this.symbolAttributeList = symbolAttributeList;
        this.symbolAttributeTypeList = symbolAttributeTypeList;
    }

    // 判斷是否為 Wild
    public boolean isWildSymbol(int symbolId) {
        if (symbolId < 0 || symbolId > this.symbolAttributeList.size()) {
            return false;
        }

        return this.symbolAttributeTypeList.get(symbolId).equals(ConstMathSlot.SymbolAttributeType.WILD_SYMBOL);
    }

    // 判斷是否為 Scatter
    public boolean isScatterSymbol(int symbolId) {
        return this.symbolAttributeTypeList.get(symbolId).equals(ConstMathSlot.SymbolAttributeType.BONUS_GAME_SYMBOL)
                || this.symbolAttributeTypeList.get(symbolId).equals(ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL);
    }

    // 取得指定標誌ID
    public int getTargetSymbolId(ConstMathSlot.SymbolAttribute targetSymbolAttribute) {
        for (int symbolIndex = 0; symbolIndex < this.symbolAttributeList.size(); symbolIndex++) {
            if (this.symbolAttributeList.get(symbolIndex).equals(targetSymbolAttribute) == true) {
                return symbolIndex;
            }
        }

        return -1;
    }

    public List<ConstMathSlot.SymbolAttribute> getSymbolAttributeList() {
        return symbolAttributeList;
    }

    public List<ConstMathSlot.SymbolAttributeType> getSymbolAttributeTypeList() {
        return symbolAttributeTypeList;
    }
}
