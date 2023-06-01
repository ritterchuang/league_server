package org.lsj.gs.math.games.qzpj_java.module.state;

import org.lsj.gs.math.core.common.state.stateQz.AbstractStateQz;
import org.lsj.gs.math.games.qzpj_java.TableQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.gameAdjust.GameAdjustQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.logic.LogicQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.robotLogic.RobotLogicQzpjJava;

// 抽象搶莊牌九狀態
public abstract class AbstractStateQzpjJava extends AbstractStateQz {
    protected TableQzpjJava table; // 牌桌
    protected LogicQzpjJava logic; // 邏輯
    protected RobotLogicQzpjJava robotLogic; // 機器人邏輯
    protected GameAdjustQzpjJava gameAdjust; // 遊戲調控

    public AbstractStateQzpjJava(TableQzpjJava table,
                                 LogicQzpjJava logic,
                                 RobotLogicQzpjJava robotLogic,
                                 GameAdjustQzpjJava gameAdjust,
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
