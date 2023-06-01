package org.lsj.gs.math.games.bjl_java.module.state;

import org.lsj.gs.math.core.common.state.stateBaiRen.AbstractStateBaiRen;
import org.lsj.gs.math.games.bjl_java.TableBjlJava;
import org.lsj.gs.math.games.bjl_java.module.gameAdjust.GameAdjustBjlJava;
import org.lsj.gs.math.games.bjl_java.module.logic.LogicBjlJava;
import org.lsj.gs.math.games.bjl_java.module.robotLogic.RobotLogicBjlJava;

// 抽象新百家樂狀態
public abstract class AbstractStateBjlJava extends AbstractStateBaiRen{
    protected TableBjlJava table; // 牌桌
    protected LogicBjlJava logic; // 邏輯
    protected RobotLogicBjlJava robotLogic; // 機器人邏輯
    protected GameAdjustBjlJava gameAdjust; // 遊戲調控

    public AbstractStateBjlJava(TableBjlJava table, LogicBjlJava logic, RobotLogicBjlJava robotLogic, GameAdjustBjlJava gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
        this.table = table;
        this.logic = logic;
        this.robotLogic = robotLogic;
        this.gameAdjust = gameAdjust;
    }
}
