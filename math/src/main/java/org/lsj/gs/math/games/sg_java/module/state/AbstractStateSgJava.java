package org.lsj.gs.math.games.sg_java.module.state;

import org.lsj.gs.math.core.common.state.stateQz.AbstractStateQz;
import org.lsj.gs.math.games.sg_java.TableSgJava;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;

// 抽象新三公狀態
public abstract class AbstractStateSgJava extends AbstractStateQz {
    protected TableSgJava table; // 牌桌
    protected LogicSgJava logic; // 邏輯
    protected RobotLogicSgJava robotLogic; // 機器人邏輯
    protected GameAdjustSgJava gameAdjust; // 遊戲調控

    public AbstractStateSgJava(TableSgJava table,
                               LogicSgJava logic,
                               RobotLogicSgJava robotLogic,
                               GameAdjustSgJava gameAdjust,
                               int stateId) {
        super(table, logic, gameAdjust, stateId);
        this.table = table;
        this.logic = logic;
        this.robotLogic = robotLogic;
        this.gameAdjust = gameAdjust;
    }

    // 進入狀態
    @Override
    public abstract void onEnter();

    // 離開狀態
    @Override
    public abstract void onLeave();

    // 狀態超時處理
    @Override
    public abstract void onTimeout();
}
