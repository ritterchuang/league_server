package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity;

import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;

import java.util.Collections;
import java.util.List;

// 新增標誌結果
public class AddSymbolResult {
    private final List<List<SymbolBox>> addSymbolBoxList; // [column][index] = symbolBox

    public AddSymbolResult(List<List<SymbolBox>> addSymbolBoxList) {
        this.addSymbolBoxList = addSymbolBoxList;
    }

    public AddSymbolResult() {
        this.addSymbolBoxList = Collections.emptyList();
    }

    public List<List<SymbolBox>> getAddSymbolBoxList() {
        return addSymbolBoxList;
    }
}
