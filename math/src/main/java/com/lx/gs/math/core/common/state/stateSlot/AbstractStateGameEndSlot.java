package com.lx.gs.math.core.common.state.stateSlot;

import com.lx.gs.math.core.common.gameAdjust.IGameAdjust;
import com.lx.gs.math.core.common.logic.logicSlot.ILogicSlot;
import com.lx.gs.math.core.common.state.IState;
import com.lx.gs.math.core.common.table.TableCommandSlot;

// 抽象虎機遊戲結束狀態
public abstract class AbstractStateGameEndSlot extends AbstractStateSlot implements IState {

    public AbstractStateGameEndSlot(TableCommandSlot table, ILogicSlot logicSlot, IGameAdjust gameAdjust, int stateId) {
        super(table, logicSlot, gameAdjust, stateId);
    }
}
