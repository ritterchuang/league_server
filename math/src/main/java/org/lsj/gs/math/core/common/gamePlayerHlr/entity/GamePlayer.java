package org.lsj.gs.math.core.common.gamePlayerHlr.entity;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.user.AbstractUser;
import org.lsj.gs.user.IUser;

import javax.websocket.Session;

// 遊戲玩家資訊
public class GamePlayer {
    private final AbstractUser user; // 使用者資訊
    private int chairIndex; // 座位索引
    private double beginMoney; // 起始金額
    private double afterMoney; // 剩餘金額


    public GamePlayer(AbstractUser user, int chairIndex, double beginMoney) {
        this.user = user.clone();
        this.chairIndex = chairIndex;
        this.beginMoney = beginMoney;
        this.afterMoney = beginMoney;
    }

    // 每局開始前更新玩家金額
    public void updateBeginMoneyBeforeGame() {
        this.beginMoney = this.afterMoney;
    }

    // 設定起始金額
    public void setBeginMoney(double beginMoney) {
        this.beginMoney = beginMoney;
    }

    // 設定遊戲後金額
    public void setAfterMoney(double afterMoney) {
        this.afterMoney = afterMoney;
    }

    public IUser getUser(){
        return this.user;
    }

    public void setChairIndex(int chairIndex) {
        this.chairIndex = chairIndex;
    }

    public void setSession(Session session) {
        this.user.setSession(session);
    }

    public Session getSession() {return this.user.getSession();}

    public int getChairIndex() {
        return this.chairIndex;
    }

    public boolean isRobot() {
        return this.user.getRobot() == ConstMathCommon.UserType.ROBOT.getCode();
    }

    public int getUid() {
        return this.user.getUid();
    }

    public double getBeginMoney() {
        return beginMoney;
    }

    public double getAfterMoney() {
        return afterMoney;
    }

    public String getAccount() {
        return this.user.getAccount();
    }

    public String getHeadImgUrl() {
        return this.user.getHeadImgUrl();
    }

    public String getIp() {
        return this.user.getIp();
    }

    public String getNickName() {
        return this.user.getNickName();
    }

    public int getRole() {
        return this.user.getRole();
    }

    public int getBoxid() {
        return this.user.getBoxid();
    }

    public int getSex() {
        return this.user.getSex();
    }

    public int getState() {
        return this.user.getState();
    }

    public int getVipLevel() {
        return this.user.getVipLevel();
    }
}
