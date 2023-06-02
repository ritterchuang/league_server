package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.SymbolBox;

import java.util.List;
import java.util.Map;

// 消除額外結果 超級王牌
public class CascadeResultExtendCjwp extends CascadeResultExtend{

    private final List<List<Integer>> beforeCascadeGoldenPosition; // 消除前黃金位置 [column][index] = positionId
    private final List<List<Integer>> afterCascadeGoldenPosition; // 消除後黃金位置 [column][index] = positionId
    private final List<List<Integer>> changeToBigWildPosition; // 消除後，變大Wild位置 [column][index] = positionId
    private final List<List<Integer>> changeToSmallWildPosition; // 消除後，變小Wild位置 [column][index] = positionId

    private final Map<Integer, Map<Integer, List<List<Integer>>>> bigWildTransformPosition; // 消除後，大wild觸發random wild 位置 [column][index] = positionId
    private final int multiplier; // 畫面倍數

    @JsonIgnore
    private final List<List<SymbolBox>> screenSymbolBoxAfterEliminate; // 消除後，畫面標誌

    public CascadeResultExtendCjwp(double totalWin, List<List<Integer>> beforeCascadeGoldenPosition, List<List<Integer>> afterCascadeGoldenPosition, List<List<Integer>> changeToBigWildPosition, List<List<Integer>> changeToSmallWildPosition, Map<Integer, Map<Integer, List<List<Integer>>>> bigWildTransformPosition, int multiplier, List<List<SymbolBox>> screenSymbolBoxAfterEliminate) {
        super(totalWin);
        this.beforeCascadeGoldenPosition = beforeCascadeGoldenPosition;
        this.afterCascadeGoldenPosition = afterCascadeGoldenPosition;
        this.changeToBigWildPosition = changeToBigWildPosition;
        this.changeToSmallWildPosition = changeToSmallWildPosition;
        this.bigWildTransformPosition = bigWildTransformPosition;
        this.multiplier = multiplier;
        this.screenSymbolBoxAfterEliminate = screenSymbolBoxAfterEliminate;
    }

    public List<List<Integer>> getBeforeCascadeGoldenPosition() {
        return beforeCascadeGoldenPosition;
    }

    public List<List<Integer>> getAfterCascadeGoldenPosition() {
        return afterCascadeGoldenPosition;
    }

    public List<List<Integer>> getChangeToBigWildPosition() {
        return changeToBigWildPosition;
    }

    public List<List<Integer>> getChangeToSmallWildPosition() {
        return changeToSmallWildPosition;
    }

    public Map<Integer, Map<Integer, List<List<Integer>>>> getBigWildTransformPosition() {
        return bigWildTransformPosition;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public List<List<SymbolBox>> getScreenSymbolBoxAfterEliminate() {
        return screenSymbolBoxAfterEliminate;
    }
}
