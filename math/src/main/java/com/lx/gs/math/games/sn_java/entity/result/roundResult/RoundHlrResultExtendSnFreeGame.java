package com.lx.gs.math.games.sn_java.entity.result.roundResult;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;

import java.util.List;

// 客製遊戲局額外結果水牛免費遊戲
public class RoundHlrResultExtendSnFreeGame extends RoundHlrResultExtend {
    private final List<List<Integer>> multiplierMatrix; // 倍數矩陣 [空的填 0] [column][row] = 倍數

    public RoundHlrResultExtendSnFreeGame(double totalWin, List<List<Integer>> multiplierMatrix) {
        super(totalWin);
        this.multiplierMatrix = multiplierMatrix;
    }

    public RoundHlrResultExtendSnFreeGame(List<List<Integer>> multiplierMatrix) {
        super(0.0);
        this.multiplierMatrix = multiplierMatrix;
    }

    public List<List<Integer>> getMultiplierMatrix() {
        return multiplierMatrix;
    }
}
