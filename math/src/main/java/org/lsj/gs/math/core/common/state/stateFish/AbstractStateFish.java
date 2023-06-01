package org.lsj.gs.math.core.common.state.stateFish;

import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.logic.ILogicFish;
import org.lsj.gs.math.core.common.state.AbstractState;
import org.lsj.gs.math.core.common.table.TableCommandFish;

// 抽象魚機狀態
public abstract class AbstractStateFish extends AbstractState implements IStateFish {
    protected final TableCommandFish table; // 牌桌
    protected final ILogicFish logic; // 魚機邏輯介面
    protected final IGameAdjust gameAdjust; // 遊戲調控

    public AbstractStateFish(TableCommandFish tableCommandFish, ILogicFish logicFish, IGameAdjust gameAdjust, int stateId){
        super(tableCommandFish, stateId);
        this.table = tableCommandFish;
        this.logic = logicFish;
        this.gameAdjust = gameAdjust;
    }
}
