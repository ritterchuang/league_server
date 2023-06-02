package org.lsj.gs.user;

import javax.websocket.Session;

// 使用者介面
public interface IUser {
    int getUid();

    String getPhone();

    String getIp();

    int getRobot();

    String getAlipay();

    String getNickName();

    String getPayAccount();

    String getAccount();

    String getHeadImgUrl();

    int getInterfaceType();

    String getThirdAccount();

    int getRegTime();

    String getDirectAgent();

    int getPlatform();

    int getSex();

    int getThirdAccountId();

    int getVipState();

    int getVipLevel();

    String getRealName();

    int getCompanyId();

    int getBaseAgencyId();

    int getPromoteState();

    int getIsTest();

    boolean isTestUser();

    int getTable();

    int getGameid();

    int getAccountType();

    int getBoxid();

    String getGsid();

    int getRoomCode();

    int getRole();

    int getChair();

    int getState();

    int getChangeTime();

    String getLoginVersion();

    int getFid();

    String getPersonControlLog();

    int getTransferState();

    boolean isAfterBybNeedUpdateMoney();

    double getBalance();

    void setBalance(double balance);

    Session getSession();

    void setSession(Session session);
}
