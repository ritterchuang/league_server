package org.lsj.gs.math.games.hhdz_java.module.state;

import org.lsj.gs.math.core.common.state.stateBaiRen.AbstractStateBaiRen;
import org.lsj.gs.math.games.hhdz_java.TableHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.gameAdjust.GameAdjustHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.logic.LogicHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.robotLogic.RobotLogicHhdzJava;

// 抽象新紅黑大戰狀態
public abstract class AbstractStateHhdzJava extends AbstractStateBaiRen{
    protected TableHhdzJava table; // 牌桌
    protected LogicHhdzJava logic; // 邏輯
    protected RobotLogicHhdzJava robotLogic; // 機器人邏輯
    protected GameAdjustHhdzJava gameAdjust; // 遊戲調控

    public AbstractStateHhdzJava(TableHhdzJava table, LogicHhdzJava logic, RobotLogicHhdzJava robotLogic, GameAdjustHhdzJava gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
        this.table = table;
        this.logic = logic;
        this.robotLogic = robotLogic;
        this.gameAdjust = gameAdjust;
    }
}
