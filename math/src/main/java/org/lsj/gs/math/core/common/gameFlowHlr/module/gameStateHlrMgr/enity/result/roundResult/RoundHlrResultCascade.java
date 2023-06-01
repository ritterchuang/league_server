package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

// 局結果消除類
public class RoundHlrResultCascade extends RoundHlrResult {
    private final CascadeClusterHlrResult cascadeClusterHlrResult;
    private final ConstMathSlot.RoundCascadeGameType roundCascadeGameType; // 遊戲局狀態類型
    private final RoundHlrResultExtend roundHlrResultExtend; // 客製局結果額外資訊

    public RoundHlrResultCascade(
            int roundIndex,
            double totalWin,
            SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster,
            ProgressHlrResult progressHlrResult,
            ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster,
            AccumulateWinCtrResult accumulateWinCtrResult,
            CascadeClusterHlrResult cascadeClusterHlrResult,
            ConstMathSlot.RoundCascadeGameType roundCascadeGameType,
            RoundHlrResultExtend roundHlrResultExtend) {
        super(roundIndex, totalWin, specialFeatureHlrResultCluster, progressHlrResult, readyHandHlrResultUnionCluster, accumulateWinCtrResult);
        this.cascadeClusterHlrResult = cascadeClusterHlrResult;
        this.roundCascadeGameType = roundCascadeGameType;
        this.roundHlrResultExtend = roundHlrResultExtend;
    }

    public CascadeClusterHlrResult getCascadeClusterHlrResult() {
        return cascadeClusterHlrResult;
    }

    public ConstMathSlot.RoundCascadeGameType getRoundCascadeGameType() {
        return roundCascadeGameType;
    }

    public RoundHlrResultExtend getRoundHlrResultExtend() {
        return roundHlrResultExtend;
    }
}
