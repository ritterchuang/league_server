package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity.ProgressResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.ReadyHandResultCluster;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity.CascadeClusterResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端局結果 消除
public class RoundResultCascade extends RoundResult {
    private final CascadeClusterResult cascadeClusterResult; // 消除集合結果
    private final ConstMathSlot.RoundCascadeGameType roundCascadeGameType; // 遊戲局狀態類型
    private final RoundResultExtend roundResultExtend; // 客製局結果額外資訊

    public RoundResultCascade(int roundIndex, double totalWin, SpecialFeatureResultCluster specialFeatureResultCluster, ProgressResult progressResult, ReadyHandResultCluster readyHandResultCluster, AccumulateWinResult accumulateWinResult, CascadeClusterResult cascadeClusterResult, ConstMathSlot.RoundCascadeGameType roundCascadeGameType, RoundResultExtend roundResultExtend) {
        super(roundIndex, totalWin, specialFeatureResultCluster, progressResult, readyHandResultCluster, accumulateWinResult);
        this.cascadeClusterResult = cascadeClusterResult;
        this.roundCascadeGameType = roundCascadeGameType;
        this.roundResultExtend = roundResultExtend;
    }

    public CascadeClusterResult getCascadeClusterResult() {
        return cascadeClusterResult;
    }

    public ConstMathSlot.RoundCascadeGameType getRoundCascadeGameType() {
        return roundCascadeGameType;
    }

    public RoundResultExtend getRoundResultExtend() {
        return roundResultExtend;
    }
}
