package org.lsj.gs.math.games.qznn_java.module.state;

import org.lsj.gs.math.core.common.state.stateQz.AbstractStateQz;
import org.lsj.gs.math.games.qznn_java.TableQznnJava;
import org.lsj.gs.math.games.qznn_java.module.gameAdjust.GameAdjustQznnJava;
import org.lsj.gs.math.games.qznn_java.module.logic.LogicQznnJava;
import org.lsj.gs.math.games.qznn_java.module.robotLogic.RobotLogicQznnJava;

// 抽象搶莊牛牛狀態
public abstract class AbstractStateQznnJava extends AbstractStateQz {
    protected TableQznnJava table; // 牌桌
    protected LogicQznnJava logic; // 邏輯
    protected RobotLogicQznnJava robotLogic; // 機器人邏輯
    protected GameAdjustQznnJava gameAdjust; // 遊戲調控

    public AbstractStateQznnJava(TableQznnJava table,
                                 LogicQznnJava logic,
                                 RobotLogicQznnJava robotLogic,
                                 GameAdjustQznnJava gameAdjust,
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
