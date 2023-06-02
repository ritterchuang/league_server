package org.lsj.gs.math.core.slot.progressHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.List;

// 遊戲進度處理者場次非法
public class ProgressHlrInvalid implements IProgressHlr{

    public ProgressHlrInvalid() {

    }

    // 計算遊戲進度處理者結果
    @Override
    public ProgressHlrResult calculateProgressHlrResult(int roundIndex, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> preRoundHlrResultList, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        return new ProgressHlrResult();
    }

    // 依照前遊戲處理結果計算初始場次
    @Override
    public int getDefaultRound(GameStateHlrResult beforeGameStateHlrResult) {
        return 0;
    }
}
