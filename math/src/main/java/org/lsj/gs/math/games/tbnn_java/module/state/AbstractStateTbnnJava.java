package org.lsj.gs.math.games.tbnn_java.module.state;

import org.lsj.gs.math.core.common.state.stateTb.AbstractStateTb;
import org.lsj.gs.math.games.tbnn_java.TableTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.gameAdjust.GameAdjustTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.robotLogic.RobotLogicTbnnJava;

// 抽象搶莊牛牛狀態
public abstract class AbstractStateTbnnJava extends AbstractStateTb {
    protected TableTbnnJava table; // 牌桌
    protected LogicTbnnJava logic; // 邏輯
    protected RobotLogicTbnnJava robotLogic; // 機器人邏輯
    protected GameAdjustTbnnJava gameAdjust; // 遊戲調控

    public AbstractStateTbnnJava(TableTbnnJava table,
                                 LogicTbnnJava logic,
                                 RobotLogicTbnnJava robotLogic,
                                 GameAdjustTbnnJava gameAdjust,
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
