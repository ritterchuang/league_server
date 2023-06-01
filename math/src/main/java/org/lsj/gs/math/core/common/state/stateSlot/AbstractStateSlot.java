package org.lsj.gs.math.core.common.state.stateSlot;

import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.state.AbstractState;
import org.lsj.gs.math.core.common.table.TableCommandSlot;

// 抽象虎機狀態
public abstract class AbstractStateSlot extends AbstractState implements IStateSlot {
    protected final TableCommandSlot table; // 牌桌
    protected final ILogicSlot logic; // 虎機邏輯介面
    protected final IGameAdjust gameAdjust; // 遊戲調控

    public AbstractStateSlot(TableCommandSlot table, ILogicSlot logicSlot, IGameAdjust gameAdjust, int stateId){
        super(table, stateId);
        this.table = table;
        this.logic = logicSlot;
        this.gameAdjust = gameAdjust;
    }

    // 進入狀態
    public void onEnter(double leftTimeSec){}


}
