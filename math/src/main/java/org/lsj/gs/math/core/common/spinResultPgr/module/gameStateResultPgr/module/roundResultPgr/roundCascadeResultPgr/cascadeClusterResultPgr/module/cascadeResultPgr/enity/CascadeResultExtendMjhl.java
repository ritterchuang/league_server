package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;

import java.util.List;

// 消除額外結果 麻將胡了
public class CascadeResultExtendMjhl extends CascadeResultExtend{
    private final List<List<Integer>> beforeCascadeGoldenPosition; // 消除前黃金位置 [column][index] = positionId
    private final List<List<Integer>> afterCascadeGoldenPosition; // 消除後黃金位置 [column][index] = positionId
    private final List<List<Integer>> changeToWildPosition; // 消除後，變Wild位置 [column][index] = positionId
    private final int multiplier; // 畫面倍數

    @JsonIgnore
    private final List<List<SymbolBox>> screenSymbolBoxAfterEliminate; // 消除後，畫面標誌

    public CascadeResultExtendMjhl(double totalWin, List<List<Integer>> beforeCascadeGoldenPosition, List<List<Integer>> afterCascadeGoldenPosition, List<List<Integer>> changeToWildPosition, int multiplier, List<List<SymbolBox>> screenSymbolBoxAfterEliminate) {
        super(totalWin);
        this.beforeCascadeGoldenPosition = beforeCascadeGoldenPosition;
        this.afterCascadeGoldenPosition = afterCascadeGoldenPosition;
        this.changeToWildPosition = changeToWildPosition;
        this.multiplier = multiplier;
        this.screenSymbolBoxAfterEliminate = screenSymbolBoxAfterEliminate;
    }

    public List<List<Integer>> getBeforeCascadeGoldenPosition() {
        return beforeCascadeGoldenPosition;
    }

    public List<List<Integer>> getAfterCascadeGoldenPosition() {
        return afterCascadeGoldenPosition;
    }

    public List<List<Integer>> getChangeToWildPosition() {
        return changeToWildPosition;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public List<List<SymbolBox>> getScreenSymbolBoxAfterEliminate() {
        return screenSymbolBoxAfterEliminate;
    }
}
