package org.lsj.gs.math.games.wl_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;

import java.util.List;

// 客端客製遊戲局額外結果 旺來免費遊戲
public class RoundResultExtendWlFreeGame extends RoundResultExtend {
    private final List<List<int[]>> positionIdToMultiplierMatrix; // 倍數矩陣 [空的填 0] [column][index] = [positionId, multiplier]

    public RoundResultExtendWlFreeGame(double totalWin, List<List<int[]>> positionIdToMultiplierMatrix) {
        super(totalWin);
        this.positionIdToMultiplierMatrix = positionIdToMultiplierMatrix;
    }

    public RoundResultExtendWlFreeGame(List<List<int[]>> positionIdToMultiplierMatrix) {
        super(0.0);
        this.positionIdToMultiplierMatrix = positionIdToMultiplierMatrix;
    }

    public List<List<int[]>> getPositionIdToMultiplierMatrix() {
        return positionIdToMultiplierMatrix;
    }
}
