package com.lx.gs.math.core.common.state.stateFish;

import com.lx.gs.math.core.common.gameAdjust.IGameAdjust;
import com.lx.gs.math.core.common.logic.ILogicFish;
import com.lx.gs.math.core.common.state.IState;
import com.lx.gs.math.core.common.table.TableCommandFish;

// 抽象魚機遊戲結束狀態
public abstract class AbstractStateGameEndFish extends AbstractStateFish implements IState {

    public AbstractStateGameEndFish(TableCommandFish tableCommandFish, ILogicFish logicFish, IGameAdjust gameAdjust, int stateId) {
        super(tableCommandFish, logicFish, gameAdjust, stateId);
    }
}
