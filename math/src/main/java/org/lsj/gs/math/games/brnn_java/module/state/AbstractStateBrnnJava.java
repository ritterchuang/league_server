package org.lsj.gs.math.games.brnn_java.module.state;

import org.lsj.gs.math.core.common.state.stateBaiRen.AbstractStateBaiRen;
import org.lsj.gs.math.games.brnn_java.TableBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.gameAdjust.GameAdjustBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.logic.LogicBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.robotLogic.RobotLogicBrnnJava;

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
