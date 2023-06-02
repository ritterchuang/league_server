package org.lsj.gs.math.games.qznn_k4z_java.module.state;

import org.lsj.gs.math.core.common.state.stateQz.AbstractStateQz;
import org.lsj.gs.math.games.qznn_k4z_java.TableQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.gameAdjust.GameAdjustQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.logic.LogicQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.robotLogic.RobotLogicQznnK4zJava;

// 抽象新看四張搶莊牛牛狀態
public abstract class AbstractStateQznnK4zJava extends AbstractStateQz {
    protected TableQznnK4zJava table; // 牌桌
    protected LogicQznnK4zJava logic; // 邏輯
    protected RobotLogicQznnK4zJava robotLogic; // 機器人邏輯
    protected GameAdjustQznnK4zJava gameAdjust; // 遊戲調控

    public AbstractStateQznnK4zJava(TableQznnK4zJava table,
                                    LogicQznnK4zJava logic,
                                    RobotLogicQznnK4zJava robotLogic,
                                    GameAdjustQznnK4zJava gameAdjust,
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
