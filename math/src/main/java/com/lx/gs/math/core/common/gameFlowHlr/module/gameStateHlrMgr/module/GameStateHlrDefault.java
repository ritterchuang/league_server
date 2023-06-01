package com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 遊戲狀態計算者預設
public class GameStateHlrDefault extends AbstractGameStateHlr implements IGameStateHlr{

    public GameStateHlrDefault(int conditionId, int gameStateId, ITableUtilSlot tableUtil, GameStateConfig gameStateConfig) {
        super(conditionId, gameStateId, gameStateConfig, tableUtil);
    }

    @Override
    public GameStateHlrResult calculateGameStateHlrResult(int gameStateResultIndex, ConstMathSlot.ReelRtpType reelRtpType, SpinRequest spinRequest, GameStateHlrResult beforeGameStateHlrResult) {
        return new GameStateHlrResult();
    }
}
