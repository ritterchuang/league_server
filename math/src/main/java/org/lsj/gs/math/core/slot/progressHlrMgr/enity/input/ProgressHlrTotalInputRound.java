package org.lsj.gs.math.core.slot.progressHlrMgr.enity.input;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.List;

// 遊戲進度處理者 場次 總場次 輸入參數
public class ProgressHlrTotalInputRound extends AbstractProgressHlrTotalInput{
    private final GameStateHlrResult beforeGameStateHlrResult; // 前一狀態處理者結果
    private final List<RoundHlrResult> preRoundHlrResultList; // 之前場次處理者結果列表
    private final SpecialFeatureHlrResultCluster currentSpecialFeatureHlrResultCluster; // 當前特殊事件處理者結果集合

    public ProgressHlrTotalInputRound(int processIndex, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> preRoundHlrResultList, SpecialFeatureHlrResultCluster currentSpecialFeatureHlrResultCluster) {
        super(processIndex);
        this.beforeGameStateHlrResult = beforeGameStateHlrResult;
        this.preRoundHlrResultList = preRoundHlrResultList;
        this.currentSpecialFeatureHlrResultCluster = currentSpecialFeatureHlrResultCluster;
    }

    public GameStateHlrResult getBeforeGameStateHlrResult() {
        return beforeGameStateHlrResult;
    }

    public List<RoundHlrResult> getPreRoundHlrResultList() {
        return preRoundHlrResultList;
    }

    public SpecialFeatureHlrResultCluster getCurrentSpecialFeatureHlrResultCluster() {
        return currentSpecialFeatureHlrResultCluster;
    }
}
