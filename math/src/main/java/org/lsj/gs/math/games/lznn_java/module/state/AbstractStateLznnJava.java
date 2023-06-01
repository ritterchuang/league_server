package org.lsj.gs.math.games.lznn_java.module.state;

import org.lsj.gs.math.core.common.state.stateQz.AbstractStateQz;
import org.lsj.gs.math.games.lznn_java.TableLznnJava;
import org.lsj.gs.math.games.lznn_java.module.gameAdjust.GameAdjustLznnJava;
import org.lsj.gs.math.games.lznn_java.module.logic.LogicLzNnJava;
import org.lsj.gs.math.games.lznn_java.module.robotLogic.RobotLogicLznnJava;

// 抽象賴子牛牛狀態
public abstract class AbstractStateLznnJava extends AbstractStateQz {
    protected TableLznnJava table; // 牌桌
    protected LogicLzNnJava logic; // 邏輯
    protected RobotLogicLznnJava robotLogic; // 機器人邏輯
    protected GameAdjustLznnJava gameAdjust; // 遊戲調控

    public AbstractStateLznnJava(TableLznnJava table,
                                 LogicLzNnJava logic,
                                 RobotLogicLznnJava robotLogic,
                                 GameAdjustLznnJava gameAdjust,
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
