package org.lsj.gs.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.websocket.Session;

// 玩家資訊
@RegisterForReflection
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractUser implements IUser, Cloneable {
    private int uid; // 玩家識別碼
    private String phone; // 電話號碼
    private String ip; // IP位址
    private int robot; // 是否機器
    private String alipay; // 阿里支付
    private String nickName; // 暱稱
    private String payAccount; // 支付帳號
    private String account; // 帳號
    private String headImgUrl; // 頭像
    private int interfaceType; // 介面類型
    private String thirdAccount; // 三方帳號
    private int regTime; // 註冊時間
    private String directAgent; // 代理
    private int platform; // 平台
    private int sex; // 性別
    private int thirdAccountId; // 三方帳號識別碼
    private int vipState; // VIP狀態
    private int vipLevel; // VIP等級
    private String realName; // 真實名稱
    private int companyId; // 公司識別碼
    private int baseAgencyId; // 代理識別碼
    private int promoteState; // 促銷狀態
    private int isTest; // 測試帳號
    private int table; // 牌桌號
    private int gameid; // 遊戲識別碼
    private int accountType; // 帳戶類型
    private int boxid; // 包廂識別碼
    private String gsid; // 服務器識別碼
    private int roomCode; // 房間?
    private int role; // 角色
    private int chair; // 座位
    private int state; // 狀態
    private int changeTime; // 變更時間
    private String loginVersion; // 登入版本號
    private int fid; // 房間識別碼
    private String personControlLog; // 個人控紀錄
    private boolean afterBybNeedUpdateMoney; //TODO 因應博一博新增
    private int transferState;
    private double balance; // 會根據不同錢包類型, 取得來源不同
    private Session session; // websocket session

    public AbstractUser() {
    }

    public AbstractUser(int uid, String phone, String ip, int robot, String alipay, String nickName, String payAccount, String account, String headImgUrl, int interfaceType, String thirdAccount, int regTime, String directAgent, int platform, int sex, int thirdAccountId, int vipState, int vipLevel, String realName, int companyId, int baseAgencyId, int promoteState, int isTest, int table, int gameid, int accountType, int boxid, String gsid, int roomCode, int role, int chair, int state, int changeTime, String loginVersion, int fid, String personControlLog, boolean afterBybNeedUpdateMoney, double balance, Session session) {
        this.uid = uid;
        this.phone = phone;
        this.ip = ip;
        this.robot = robot;
        this.alipay = alipay;
        this.nickName = nickName;
        this.payAccount = payAccount;
        this.account = account;
        this.headImgUrl = headImgUrl;
        this.interfaceType = interfaceType;
        this.thirdAccount = thirdAccount;
        this.regTime = regTime;
        this.directAgent = directAgent;
        this.platform = platform;
        this.sex = sex;
        this.thirdAccountId = thirdAccountId;
        this.vipState = vipState;
        this.vipLevel = vipLevel;
        this.realName = realName;
        this.companyId = companyId;
        this.baseAgencyId = baseAgencyId;
        this.promoteState = promoteState;
        this.isTest = isTest;
        this.table = table;
        this.gameid = gameid;
        this.accountType = accountType;
        this.boxid = boxid;
        this.gsid = gsid;
        this.roomCode = roomCode;
        this.role = role;
        this.chair = chair;
        this.state = state;
        this.changeTime = changeTime;
        this.loginVersion = loginVersion;
        this.fid = fid;
        this.personControlLog = personControlLog;
        this.afterBybNeedUpdateMoney = afterBybNeedUpdateMoney;
        this.balance = balance;
        this.session = session;
    }

    // 複製物件
    public AbstractUser clone() {
        try {
            return (AbstractUser) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public int getRobot() {
        return robot;
    }

    @Override
    public String getAlipay() {
        return alipay;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    @Override
    public String getPayAccount() {
        return payAccount;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    @Override
    public int getInterfaceType() {
        return interfaceType;
    }

    @Override
    public String getThirdAccount() {
        return thirdAccount;
    }

    @Override
    public int getRegTime() {
        return regTime;
    }

    @Override
    public String getDirectAgent() {
        return directAgent;
    }

    @Override
    public int getPlatform() {
        return platform;
    }

    @Override
    public int getSex() {
        return sex;
    }

    @Override
    public int getThirdAccountId() {
        return thirdAccountId;
    }

    @Override
    public int getVipState() {
        return vipState;
    }

    @Override
    public int getVipLevel() {
        return vipLevel;
    }

    @Override
    public String getRealName() {
        return realName;
    }

    @Override
    public int getCompanyId() {
        return companyId;
    }

    @Override
    public int getBaseAgencyId() {
        return baseAgencyId;
    }

    @Override
    public int getPromoteState() {
        return promoteState;
    }

    @Override
    public int getIsTest() {
        return isTest;
    }

    @Override
    public boolean isTestUser() {
        return isTest == 1;
    }

    @Override
    public int getTable() {
        return table;
    }

    @Override
    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    @Override
    public int getAccountType() {
        return accountType;
    }

    @Override
    public int getBoxid() {
        return boxid;
    }

    @Override
    public String getGsid() {
        return gsid;
    }

    public void setGsid(String gsid) {
        this.gsid = gsid;
    }

    @Override
    public int getRoomCode() {
        return roomCode;
    }

    @Override
    public int getRole() {
        return role;
    }

    @Override
    public int getChair() {
        return chair;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public int getChangeTime() {
        return changeTime;
    }

    @Override
    public String getLoginVersion() {
        return loginVersion;
    }

    @Override
    public int getFid() {
        return fid;
    }

    @Override
    public String getPersonControlLog() {
        return personControlLog;
    }

    @Override
    public int getTransferState() {
        return transferState;
    }

    public void setTransferState(int transferState) {
        this.transferState = transferState;
    }

    @Override
    public boolean isAfterBybNeedUpdateMoney() {
        return afterBybNeedUpdateMoney;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
