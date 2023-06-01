package org.lsj.gs.math.games.dydb_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;

import java.util.List;

// 客製遊戲局額外結果大運奪寶免費遊戲
public class RoundHlrResultExtendDydbFreeGame extends RoundHlrResultExtend {
    private final List<List<Integer>> multiplierMatrix; // 倍數矩陣 [空的填 0] [column][row] = 倍數

    public RoundHlrResultExtendDydbFreeGame(double totalWin, List<List<Integer>> multiplierMatrix) {
        super(totalWin);
        this.multiplierMatrix = multiplierMatrix;
    }

    public RoundHlrResultExtendDydbFreeGame(List<List<Integer>> multiplierMatrix) {
        super(0.0);
        this.multiplierMatrix = multiplierMatrix;
    }

    public List<List<Integer>> getMultiplierMatrix() {
        return multiplierMatrix;
    }
}
