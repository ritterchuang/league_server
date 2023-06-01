package com.lx.gs.math.games.tbnn_java;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import com.lx.gs.math.core.common.poolCtr.entity.IPoolConfig;
import com.lx.gs.math.core.common.table.ISeverTableMessageCard;
import com.lx.gs.math.core.common.tableConfigMgr.TableConfigMgr;
import com.lx.gs.user.IUser;
import com.lx.websocket.ReceiveGameCommand;

import javax.websocket.Session;

// 通比牛牛遊戲桌封裝器
public class TableTbnnJavaWpr implements ISeverTableMessageCard {
    private final TableTbnnJava table; // 遊戲桌

    public TableTbnnJavaWpr(TableTbnnJava table) {
        this.table = table;
    }

    @Override
    public void startBeforeGameBegin() {
        this.table.startBeforeGameBegin();
    }

    @Override
    public void startAfterGameEnd() {
        this.table.startAfterGameEnd();
    }

    @Override
    public void startForceStop() {
        this.table.startForceStop();
    }

    @Override
    public void updateUser(IUser user) { this.table.updateUser(user);}

    @Override
    public void sendCutReturn(Session session) {
        this.table.sendCutReturn(session);
    }

    @Override
    public void receivedGameCommand(ReceiveGameCommand mathCommand) {
        this.table.receivedGameCommand(mathCommand);
    }

    @Override
    public void sendUserList() {
        this.table.sendUserList();
    }

    @Override
    public void sendHumanUpdateScore() {
        this.table.sendHumanUpdateScore();
    }

    @Override
    public void sendRoundLogId() {
        this.table.sendRoundLogId();
    }

    @Override
    public TableConfigMgr getTableConfigMgr() {
        return this.table.getTableConfigMgr();
    }

    @Override
    public IPoolConfig getPoolConfig() {
        return this.table.getPoolConfig();
    }

    @Override
    public IGameBetLogResultCard getResult() {
        return this.table.getResult();
    }

    @Override
    public IUser getUser() {
        return this.table.getUser();
    }

    @Override
    public int getGameId() {
        return this.table.getGameId();
    }

    @Override
    public String getRoundId() {
        return this.table.getRoundId();
    }
}
