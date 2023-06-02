package org.lsj.gs.math.games.hjxb_java.entity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;

import java.util.List;

// 客製遊戲局額外結果黃金西部免費遊戲
public class RoundHlrResultExtendHjxbFreeGame extends RoundHlrResultExtend {
    private final List<List<Integer>> multiplierMatrix; // 倍數矩陣 [空的填 0] [column][row] = 倍數

    public RoundHlrResultExtendHjxbFreeGame(double totalWin, List<List<Integer>> multiplierMatrix) {
        super(totalWin);
        this.multiplierMatrix = multiplierMatrix;
    }

    public RoundHlrResultExtendHjxbFreeGame(List<List<Integer>> multiplierMatrix) {
        super(0.0);
        this.multiplierMatrix = multiplierMatrix;
    }

    public List<List<Integer>> getMultiplierMatrix() {
        return multiplierMatrix;
    }
}
