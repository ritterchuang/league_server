package com.lx.gs.math.games.ebg_java.module.state;

import com.lx.gs.math.core.common.state.stateQz.AbstractStateQz;
import com.lx.gs.math.games.ebg_java.TableEbgJava;
import com.lx.gs.math.games.ebg_java.module.gameAdjust.GameAdjustEbgJava;
import com.lx.gs.math.games.ebg_java.module.logic.LogicEbgJava;
import com.lx.gs.math.games.ebg_java.module.robotLogic.RobotLogicEbgJava;

// 抽象搶莊二八槓狀態
public abstract class AbstractStateEbgJava extends AbstractStateQz {
    protected TableEbgJava table; // 牌桌
    protected LogicEbgJava logic; // 邏輯
    protected RobotLogicEbgJava robotLogic; // 機器人邏輯
    protected GameAdjustEbgJava gameAdjust; // 遊戲調控

    public AbstractStateEbgJava(TableEbgJava table,
                                LogicEbgJava logic,
                                RobotLogicEbgJava robotLogic,
                                GameAdjustEbgJava gameAdjust,
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
