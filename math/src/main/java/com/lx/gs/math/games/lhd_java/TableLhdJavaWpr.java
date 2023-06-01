package com.lx.gs.math.games.lhd_java;

import com.lx.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import com.lx.gs.math.core.common.poolCtr.entity.IPoolConfig;
import com.lx.gs.math.core.common.table.ISeverTableMessageBaiRen;
import com.lx.gs.state.BaiRenGameState;
import com.lx.gs.user.IUser;
import com.lx.websocket.ReceiveGameCommand;

import java.util.List;

// 新龍虎鬥遊戲桌封裝器
public class TableLhdJavaWpr implements ISeverTableMessageBaiRen {
    private final TableLhdJava table; // 遊戲桌

    public TableLhdJavaWpr(TableLhdJava table) {
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
