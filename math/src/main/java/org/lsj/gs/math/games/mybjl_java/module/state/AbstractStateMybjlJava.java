package org.lsj.gs.math.games.mybjl_java.module.state;

import org.lsj.gs.math.core.common.state.stateBaiRen.AbstractStateBaiRen;
import org.lsj.gs.math.games.mybjl_java.TableMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.gameAdjust.GameAdjustMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.logic.LogicMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.robotLogic.RobotLogicMybjlJava;

// 抽象新免傭百家樂狀態
public abstract class AbstractStateMybjlJava extends AbstractStateBaiRen{
    protected TableMybjlJava table; // 牌桌
    protected LogicMybjlJava logic; // 邏輯
    protected RobotLogicMybjlJava robotLogic; // 機器人邏輯
    protected GameAdjustMybjlJava gameAdjust; // 遊戲調控

    public AbstractStateMybjlJava(TableMybjlJava table, LogicMybjlJava logic, RobotLogicMybjlJava robotLogic, GameAdjustMybjlJava gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
        this.table = table;
        this.logic = logic;
        this.robotLogic = robotLogic;
        this.gameAdjust = gameAdjust;
    }
}
