package org.lsj.gs.math.games.lhd_java.module.state;

import org.lsj.gs.math.core.common.state.stateBaiRen.AbstractStateBaiRen;
import org.lsj.gs.math.games.lhd_java.TableLhdJava;
import org.lsj.gs.math.games.lhd_java.module.gameAdjust.GameAdjustLhdJava;
import org.lsj.gs.math.games.lhd_java.module.logic.LogicLhdJava;
import org.lsj.gs.math.games.lhd_java.module.robotLogic.RobotLogicLhdJava;

// 抽象新龍虎鬥狀態
public abstract class AbstractStateLhdJava extends AbstractStateBaiRen{
    protected TableLhdJava table; // 牌桌
    protected LogicLhdJava logic; // 邏輯
    protected RobotLogicLhdJava robotLogic; // 機器人邏輯
    protected GameAdjustLhdJava gameAdjust; // 遊戲調控

    public AbstractStateLhdJava(TableLhdJava table, LogicLhdJava logic, RobotLogicLhdJava robotLogic, GameAdjustLhdJava gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
        this.table = table;
        this.logic = logic;
        this.robotLogic = robotLogic;
        this.gameAdjust = gameAdjust;
    }
}
