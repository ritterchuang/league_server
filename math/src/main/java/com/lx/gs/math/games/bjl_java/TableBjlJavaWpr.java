package com.lx.gs.math.games.bjl_java;

import com.lx.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import com.lx.gs.math.core.common.poolCtr.entity.IPoolConfig;
import com.lx.gs.math.core.common.table.ISeverTableMessageBaiRen;
import com.lx.gs.state.BaiRenGameState;
import com.lx.gs.user.IUser;
import com.lx.websocket.ReceiveGameCommand;

import java.util.List;

// 新百家遊戲桌封裝器
public class TableBjlJavaWpr implements ISeverTableMessageBaiRen {
    private final TableBjlJava table; // 遊戲桌

    public TableBjlJavaWpr(TableBjlJava table) {
        this.table = table;
    }


    //* 流程相關 *//

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


    //* 牌桌設定檔相關 *//

    @Override
    public BaiRenMathConfig getMathConfig() {
        return this.table.getMathConfig();
    }


    //* 水池相關 *//

    @Override
    public void updatePoolConfig(IPoolConfig poolConfig) {
        this.table.updatePoolConfig(poolConfig);
    }


    //* 遊戲結果相關 *//

    @Override
    public IGameBetLogResultBaiRen getResult() {
        return this.table.getResult();
    }


    //* 傳輸協議相關 *//

    @Override
    public void receivedGameCommand(ReceiveGameCommand mathCommand) {
        this.table.receivedGameCommand(mathCommand);
    }


    //* 牌桌資訊 *//

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


    //* 亂數相關 *//

    @Override
    public void setRngData(String rngDataString) {
        this.table.setRngData(rngDataString);
    }
}
