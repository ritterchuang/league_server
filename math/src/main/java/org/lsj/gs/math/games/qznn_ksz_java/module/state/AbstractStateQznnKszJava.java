package org.lsj.gs.math.games.qznn_ksz_java.module.state;

import org.lsj.gs.math.core.common.state.stateQz.AbstractStateQz;
import org.lsj.gs.math.games.qznn_ksz_java.TableQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.gameAdjust.GameAdjustQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.logic.LogicQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.robotLogic.RobotLogicQznnKszJava;

// 抽象看三張搶莊牛牛狀態
public abstract class AbstractStateQznnKszJava extends AbstractStateQz {
    protected TableQznnKszJava table; // 牌桌
    protected LogicQznnKszJava logic; // 邏輯
    protected RobotLogicQznnKszJava robotLogic; // 機器人邏輯
    protected GameAdjustQznnKszJava gameAdjust; // 遊戲調控

    public AbstractStateQznnKszJava(TableQznnKszJava table,
                                    LogicQznnKszJava logic,
                                    RobotLogicQznnKszJava robotLogic,
                                    GameAdjustQznnKszJava gameAdjust,
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
