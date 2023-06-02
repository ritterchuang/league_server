package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult;

import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

// 遊戲局結果
public class RoundHlrResult {
    private final int roundIndex; // 遊戲局號流水號
    private final double totalWin; // 遊戲局總贏分
    private final SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster; // 特殊事件集合
    private final ProgressHlrResult progressHlrResult; // 遊戲進度結果
    private final ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster; // 遊戲聽牌處理結果
    private final AccumulateWinCtrResult accumulateWinCtrResult; // 遊戲得分累積結果

    public RoundHlrResult(int roundIndex, double totalWin, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster, ProgressHlrResult progressHlrResult, ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster, AccumulateWinCtrResult accumulateWinCtrResult) {
        this.roundIndex = roundIndex;
        this.totalWin = totalWin;
        this.specialFeatureHlrResultCluster = specialFeatureHlrResultCluster;
        this.progressHlrResult = progressHlrResult;
        this.readyHandHlrResultUnionCluster = readyHandHlrResultUnionCluster;
        this.accumulateWinCtrResult = accumulateWinCtrResult;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public SpecialFeatureHlrResultCluster getSpecialFeatureHlrResultCluster() {
        return specialFeatureHlrResultCluster;
    }

    public ProgressHlrResult getProgressHlrResult() {
        return progressHlrResult;
    }

    public ReadyHandHlrResultUnionCluster getReadyHandHlrResultUnionCluster() {
        return readyHandHlrResultUnionCluster;
    }

    public AccumulateWinCtrResult getAccumulateWinCtrResult() {
        return accumulateWinCtrResult;
    }
}
