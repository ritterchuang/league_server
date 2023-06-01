package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity;

import java.util.List;

// Damp 與 畫面資訊
public class DampScreenBox {
    private final List<Integer> upperDampSymbol; // 上破框標誌 [欄] = 標誌
    private final List<Integer> lowerDampSymbol; // 下破框標誌 [欄] = 標誌
    private final List<List<Integer>> screenSymbol; // 畫面標誌 [欄][列] = 標誌

    public DampScreenBox(List<Integer> upperDampSymbol, List<Integer> lowerDampSymbol, List<List<Integer>> screenSymbol) {
        this.upperDampSymbol = upperDampSymbol;
        this.lowerDampSymbol = lowerDampSymbol;
        this.screenSymbol = screenSymbol;
    }

    public List<Integer> getUpperDampSymbol() {
        return upperDampSymbol;
    }

    public List<Integer> getLowerDampSymbol() {
        return lowerDampSymbol;
    }

    public List<List<Integer>> getScreenSymbol() {
        return screenSymbol;
    }
}
