package org.lsj.gs.math.core.common.state.stateSlot;

import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.table.TableCommandSlot;

// 抽象虎機等待開始狀態
public abstract class AbstractStateWaitBeginSlot extends AbstractStateSlot {
    public AbstractStateWaitBeginSlot(TableCommandSlot table, ILogicSlot logicSlot, IGameAdjust gameAdjust, int stateId) {
        super(table, logicSlot, gameAdjust, stateId);
    }
}
