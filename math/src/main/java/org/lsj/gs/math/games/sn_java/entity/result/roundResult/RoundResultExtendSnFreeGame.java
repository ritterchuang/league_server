package org.lsj.gs.math.games.sn_java.entity.result.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;

import java.util.List;

// 客端客製遊戲局額外結果水牛免費遊戲
public class RoundResultExtendSnFreeGame extends RoundResultExtend {
    private final List<List<int[]>> positionIdToMultiplierMatrix; // 倍數矩陣 [空的填 0] [column][index] = [positionId, multiplier]

    public RoundResultExtendSnFreeGame(double totalWin, List<List<int[]>> positionIdToMultiplierMatrix) {
        super(totalWin);
        this.positionIdToMultiplierMatrix = positionIdToMultiplierMatrix;
    }

    public RoundResultExtendSnFreeGame(List<List<int[]>> positionIdToMultiplierMatrix) {
        super(0.0);
        this.positionIdToMultiplierMatrix = positionIdToMultiplierMatrix;
    }

    public List<List<int[]>> getPositionIdToMultiplierMatrix() {
        return positionIdToMultiplierMatrix;
    }
}
