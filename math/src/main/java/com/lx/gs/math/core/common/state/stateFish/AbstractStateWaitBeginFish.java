package com.lx.gs.math.core.common.state.stateFish;

import com.lx.gs.math.core.common.gameAdjust.IGameAdjust;
import com.lx.gs.math.core.common.logic.ILogicFish;
import com.lx.gs.math.core.common.state.IState;
import com.lx.gs.math.core.common.table.TableCommandFish;

// 抽象魚機等待開始狀態
public abstract class AbstractStateWaitBeginFish extends AbstractStateFish implements IState {
    public AbstractStateWaitBeginFish(TableCommandFish tableCommandFish, ILogicFish logicFish, IGameAdjust gameAdjust, int stateId) {
        super(tableCommandFish, logicFish, gameAdjust, stateId);
    }
}
