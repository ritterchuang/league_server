package org.lsj.gs.math.core.common.state.stateTb;

import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.logic.ILogicCard;
import org.lsj.gs.math.core.common.state.AbstractState;
import org.lsj.gs.math.core.common.state.stateQz.IStateQz;
import org.lsj.gs.math.core.common.table.AbstractTableMessageEventCard;

// 抽象通比狀態
public abstract class AbstractStateTb extends AbstractState implements IStateQz {
    protected final AbstractTableMessageEventCard table; // 遊戲桌
    protected final ILogicCard logic; // 邏輯介面
    protected final IGameAdjust gameAdjust; // 遊戲調控

    public AbstractStateTb(AbstractTableMessageEventCard table,
                           ILogicCard logic,
                           IGameAdjust gameAdjust,
                           int stateId){
        super(table, stateId);
        this.table = table;
        this.logic = logic;
        this.gameAdjust = gameAdjust;
    }

    // 進入狀態
    @Override
    public abstract void onEnter();
}
