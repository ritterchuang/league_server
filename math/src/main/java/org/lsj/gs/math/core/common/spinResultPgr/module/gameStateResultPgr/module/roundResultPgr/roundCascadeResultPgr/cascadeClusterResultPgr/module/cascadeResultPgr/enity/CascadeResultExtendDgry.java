package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;

import java.util.List;

// 消除額外結果 帝国榮耀
public class CascadeResultExtendDgry extends CascadeResultExtend{

    @JsonIgnore
    private final List<List<SymbolBox>> screenSymbolBoxAfterEliminate; // 消除後，畫面標誌

    public CascadeResultExtendDgry(double totalWin, List<List<SymbolBox>> screenSymbolBoxAfterEliminate) {
        super(totalWin);
        this.screenSymbolBoxAfterEliminate = screenSymbolBoxAfterEliminate;
    }

    public List<List<SymbolBox>> getScreenSymbolBoxAfterEliminate() {
        return screenSymbolBoxAfterEliminate;
    }
}
