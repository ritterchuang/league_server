package org.lsj.gs.math.core.common.state.stateSlot;

import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.state.IState;
import org.lsj.gs.math.core.common.table.TableCommandSlot;

// 抽象虎機遊戲結束狀態
public abstract class AbstractStateGameEndSlot extends AbstractStateSlot implements IState {

    public AbstractStateGameEndSlot(TableCommandSlot table, ILogicSlot logicSlot, IGameAdjust gameAdjust, int stateId) {
        super(table, logicSlot, gameAdjust, stateId);
    }
}
