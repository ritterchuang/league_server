package org.lsj.gs.math.games.mybjl_java;

import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.ISeverTableMessageBaiRen;
import org.lsj.gs.state.BaiRenGameState;
import org.lsj.gs.user.IUser;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;

// 新免傭百家遊戲桌封裝器
public class TableMybjlJavaWpr implements ISeverTableMessageBaiRen {
    private final TableMybjlJava table; // 遊戲桌

    public TableMybjlJavaWpr(TableMybjlJava table) {
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
