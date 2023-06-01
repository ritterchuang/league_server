package org.lsj.gs.math.games.zjh_java.module.state;

import org.lsj.gs.math.core.common.state.stateQz.AbstractStateQz;
import org.lsj.gs.math.games.zjh_java.TableZjhJava;
import org.lsj.gs.math.games.zjh_java.module.gameAdjust.GameAdjustZjhJava;
import org.lsj.gs.math.games.zjh_java.module.logic.LogicZjhJava;
import org.lsj.gs.math.games.zjh_java.module.robotLogic.RobotLogicZjhJava;

// 抽象炸金花狀態
public abstract class AbstractStateZjhJava extends AbstractStateQz {
    protected TableZjhJava table; // 牌桌
    protected LogicZjhJava logic; // 邏輯
    protected RobotLogicZjhJava robotLogic; // 機器人邏輯
    protected GameAdjustZjhJava gameAdjust; // 遊戲調控

    public AbstractStateZjhJava(TableZjhJava table,
                                LogicZjhJava logic,
                                RobotLogicZjhJava robotLogic,
                                GameAdjustZjhJava gameAdjust,
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
