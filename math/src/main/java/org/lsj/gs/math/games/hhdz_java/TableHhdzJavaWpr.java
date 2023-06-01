package org.lsj.gs.math.games.hhdz_java;

import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.ISeverTableMessageBaiRen;
import org.lsj.gs.state.BaiRenGameState;
import org.lsj.gs.user.IUser;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;

// 新紅黑大戰遊戲桌封裝器
public class TableHhdzJavaWpr implements ISeverTableMessageBaiRen {
    private final TableHhdzJava table; // 遊戲桌

    public TableHhdzJavaWpr(TableHhdzJava table) {
        this.table = table;
    }

    @Override
    public void enterTable(IUser user, double leftTimeSec) {
        this.table.enterTable(user, leftTimeSec);
    }

    @Override
    public boolean leaveTable() {
        return this.table.leaveTable();
    }

    @Override
    public double updateGameState(BaiRenGameState gameState, double leftTimeSec) {
        return this.table.updateGameState(gameState, leftTimeSec);
    }

    @Override
    public void switchBg(double leftTimeSec) {
        this.table.switchBg(leftTimeSec);
    }

    @Override
    public BaiRenMathConfig getMathConfig() {
        return this.table.getMathConfig();
    }

    @Override
    public void updatePoolConfig(IPoolConfig poolConfig) {
        this.table.updatePoolConfig(poolConfig);
    }

    @Override
    public IGameBetLogResultBaiRen getResult() {
        return this.table.getResult();
    }

    @Override
    public void receivedGameCommand(ReceiveGameCommand mathCommand) {
        this.table.receivedGameCommand(mathCommand);
    }

    @Override
    public int getTableId() {
        return this.table.getTableId();
    }

    @Override
    public List<Object> getChartList() {
        return this.table.getChartList();
    }

    @Override
    public int getPlayerNum() {
        return this.table.getPlayerNum();
    }

    @Override
    public boolean isHumanBet() {
        return this.table.isHumanBet();
    }

    @Override
    public void updateUser(IUser user) { this.table.updateUser(user);}

    @Override
    public void setRngData(String rngDataString) {
        this.table.setRngData(rngDataString);
    }
}
