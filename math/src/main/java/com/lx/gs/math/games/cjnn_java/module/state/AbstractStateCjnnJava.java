package com.lx.gs.math.games.cjnn_java.module.state;

import com.lx.gs.math.core.common.state.stateBaiRen.AbstractStateBaiRen;
import com.lx.gs.math.games.cjnn_java.TableCjnnJava;
import com.lx.gs.math.games.cjnn_java.module.gameAdjust.GameAdjustCjnnJava;
import com.lx.gs.math.games.cjnn_java.module.logic.LogicCjnnJava;
import com.lx.gs.math.games.cjnn_java.module.robotLogic.RobotLogicCjnnJava;

// 抽象新超級牛牛狀態
public abstract class AbstractStateCjnnJava extends AbstractStateBaiRen{
    protected TableCjnnJava table; // 牌桌
    protected LogicCjnnJava logic; // 邏輯
    protected RobotLogicCjnnJava robotLogic; // 機器人邏輯
    protected GameAdjustCjnnJava gameAdjust; // 遊戲調控

    public AbstractStateCjnnJava(TableCjnnJava table, LogicCjnnJava logic, RobotLogicCjnnJava robotLogic, GameAdjustCjnnJava gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
        this.table = table;
        this.logic = logic;
        this.robotLogic = robotLogic;
        this.gameAdjust = gameAdjust;
    }
}
