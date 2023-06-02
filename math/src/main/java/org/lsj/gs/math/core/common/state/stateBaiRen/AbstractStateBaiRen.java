package org.lsj.gs.math.core.common.state.stateBaiRen;

import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.logic.logicBaiRen.ILogicBaiRen;
import org.lsj.gs.math.core.common.state.AbstractState;
import org.lsj.gs.math.core.common.table.AbstractTableMessageBaiRen;

// 抽象百人狀態
public abstract class AbstractStateBaiRen extends AbstractState implements IStateBaiRen {
    protected final AbstractTableMessageBaiRen table; // 牌桌
    protected final ILogicBaiRen logic; // 邏輯介面
    protected final IGameAdjust gameAdjust; // 遊戲調控

    public AbstractStateBaiRen(AbstractTableMessageBaiRen table,
                               ILogicBaiRen logic,
                               IGameAdjust gameAdjust,
                               int stateId){
        super(table, stateId);
        this.table = table;
        this.logic = logic;
        this.gameAdjust = gameAdjust;
    }

    // 進入狀態
    @Override
    public void onEnter(){}

    // 新增真人遊戲開始日誌
    public void addHumanLogGameBegin(){
        if(super.table.getGamePlayerHlr().isHumanEnterTable()){
            super.addLogGameBegin(this.logic);
        }
    }
}
