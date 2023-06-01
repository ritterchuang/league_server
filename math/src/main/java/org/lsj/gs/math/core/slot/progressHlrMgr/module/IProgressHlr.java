package org.lsj.gs.math.core.slot.progressHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.List;

// 遊戲進度處理器介面
public interface IProgressHlr {

    // 計算遊戲場次進度
    ProgressHlrResult calculateProgressHlrResult(int roundIndex, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> preRoundHlrResultList, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster);

    // 取得預設場次
    int getDefaultRound(GameStateHlrResult beforeGameStateHlrResult);
}
