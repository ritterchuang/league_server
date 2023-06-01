package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;

// 客端局結果
public class RoundResult {
    private final int roundIndex; // 遊戲局號流水號
    private final double totalWin; // 遊戲局總贏分
    private final SpecialFeatureResultCluster specialFeatureResultCluster; // 特殊事件集合
    private final ProgressResult progressResult; // 遊戲進度結果
    private final ReadyHandResultCluster readyHandResultCluster; // 聽牌結果列表
    private final AccumulateWinResult accumulateWinResult; // 遊戲得分累積結果

    public RoundResult(int roundIndex, double totalWin, SpecialFeatureResultCluster specialFeatureResultCluster, ProgressResult progressResult, ReadyHandResultCluster readyHandResultCluster, AccumulateWinResult accumulateWinResult) {
        this.roundIndex = roundIndex;
        this.totalWin = totalWin;
        this.specialFeatureResultCluster = specialFeatureResultCluster;
        this.progressResult = progressResult;
        this.readyHandResultCluster = readyHandResultCluster;
        this.accumulateWinResult = accumulateWinResult;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public SpecialFeatureResultCluster getSpecialFeatureResultCluster() {
        return specialFeatureResultCluster;
    }

    public ProgressResult getProgressResult() {
        return progressResult;
    }

    public ReadyHandResultCluster getReadyHandResultCluster() {
        return readyHandResultCluster;
    }

    public AccumulateWinResult getAccumulateWinResult() {
        return accumulateWinResult;
    }
}
