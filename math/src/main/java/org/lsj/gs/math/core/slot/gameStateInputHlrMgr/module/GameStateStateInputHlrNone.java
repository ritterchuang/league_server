package org.lsj.gs.math.core.slot.gameStateInputHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfig;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfigExtendNone;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResultExtendNone;

import java.util.List;

// 遊戲輸入處理者None
public class GameStateStateInputHlrNone implements IGameStateInputHlr {
    private final ConstMathSlot.GameStateInputType gameStateInputType;
    private final GameStateInputConfigExtendNone configExtend;
    private ITableUtil tableUtil;

    public GameStateStateInputHlrNone(GameStateInputConfig gameStateInputConfig, ITableUtil tableUtil) {
        this.gameStateInputType = gameStateInputConfig.getGameStateInputType();
        this.configExtend = (GameStateInputConfigExtendNone) gameStateInputConfig.getGameStateInputConfigExtend();
        this.tableUtil = tableUtil;
    }

    @Override
    public GameStateInputResult calculateGameStateInputResult(List<RoundHlrResult> roundHlrResultList) {
        return new GameStateInputResult(this.gameStateInputType, new GameStateInputResultExtendNone());
    }
}
