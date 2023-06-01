package com.lx.gs.math.games.brnn_java.module.state;

import com.lx.gs.math.core.common.state.stateBaiRen.AbstractStateBaiRen;
import com.lx.gs.math.games.brnn_java.TableBrnnJava;
import com.lx.gs.math.games.brnn_java.module.gameAdjust.GameAdjustBrnnJava;
import com.lx.gs.math.games.brnn_java.module.logic.LogicBrnnJava;
import com.lx.gs.math.games.brnn_java.module.robotLogic.RobotLogicBrnnJava;

// 抽象新百人牛牛狀態
public abstract class AbstractStateBrnnJava extends AbstractStateBaiRen{
    protected TableBrnnJava table; // 牌桌
    protected LogicBrnnJava logic; // 邏輯
    protected RobotLogicBrnnJava robotLogic; // 機器人邏輯
    protected GameAdjustBrnnJava gameAdjust; // 遊戲調控

    public AbstractStateBrnnJava(TableBrnnJava table, LogicBrnnJava logic, RobotLogicBrnnJava robotLogic, GameAdjustBrnnJava gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
        this.table = table;
        this.logic = logic;
        this.robotLogic = robotLogic;
        this.gameAdjust = gameAdjust;
    }
}
