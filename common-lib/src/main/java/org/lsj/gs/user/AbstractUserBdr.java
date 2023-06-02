package org.lsj.gs.user;

import javax.websocket.Session;

public abstract class AbstractUserBdr {
    protected int uid; // 玩家識別碼
    protected String phone; // 電話號碼
    protected String ip; // IP位址
    protected int robot; // 是否機器
    protected String alipay; // 阿里支付
    protected String nickName; // 暱稱
    protected String payAccount; // 支付帳號
    protected String account; // 帳號
    protected String headImgUrl; // 頭像
    protected int interfaceType; // 介面類型
    protected String thirdAccount; // 三方帳號
    protected int regTime; // 註冊時間
    protected String directAgent; // 代理
    protected int platform; // 平台
    protected int sex; // 性別
    protected int thirdAccountId; // 三方帳號識別碼
    protected int vipState; // VIP狀態
    protected int vipLevel; // VIP等級
    protected String realName; // 真實名稱
    protected int companyId; // 公司識別碼
    protected int baseAgencyId; // 代理識別碼
    protected int promoteState; // 促銷狀態
    protected int isTest; // 測試帳號
    protected int table; // 牌桌號
    protected int gameid; // 遊戲識別碼
    protected int accountType; // 帳戶類型
    protected int boxid; // 包廂識別碼
    protected String gsid; // 服務器識別碼
    protected int roomCode; // 房間?
    protected int role; // 角色
    protected int chair; // 座位
    protected int state; // 狀態
    protected int changeTime; // 變更時間
    protected String loginVersion; // 登入版本號
    protected int fid; // 房間識別碼
    protected String personControlLog; // 舊版個人控紀錄
    protected boolean afterBybNeedUpdateMoney; // TODO 因應博一博新增
    protected double balance; // 會根據不同錢包類型, 取得來源不同
    protected Session session; // websocket session

    public AbstractUserBdr setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public AbstractUserBdr setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public AbstractUserBdr setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public AbstractUserBdr setRobot(int robot) {
        this.robot = robot;
        return this;
    }

    public AbstractUserBdr setAlipay(String alipay) {
        this.alipay = alipay;
        return this;
    }

    public AbstractUserBdr setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public AbstractUserBdr setPayAccount(String payAccount) {
        this.payAccount = payAccount;
        return this;
    }

    public AbstractUserBdr setAccount(String account) {
        this.account = account;
        return this;
    }

    public AbstractUserBdr setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
        return this;
    }

    public AbstractUserBdr setInterfaceType(int interfaceType) {
        this.interfaceType = interfaceType;
        return this;
    }

    public AbstractUserBdr setThirdAccount(String thirdAccount) {
        this.thirdAccount = thirdAccount;
        return this;
    }

    public AbstractUserBdr setRegTime(int regTime) {
        this.regTime = regTime;
        return this;
    }

    public AbstractUserBdr setDirectAgent(String directAgent) {
        this.directAgent = directAgent;
        return this;
    }

    public AbstractUserBdr setPlatform(int platform) {
        this.platform = platform;
        return this;
    }

    public AbstractUserBdr setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public AbstractUserBdr setThirdAccountId(int thirdAccountId) {
        this.thirdAccountId = thirdAccountId;
        return this;
    }

    public AbstractUserBdr setVipState(int vipState) {
        this.vipState = vipState;
        return this;
    }

    public AbstractUserBdr setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
        return this;
    }

    public AbstractUserBdr setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public AbstractUserBdr setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public AbstractUserBdr setBaseAgencyId(int baseAgencyId) {
        this.baseAgencyId = baseAgencyId;
        return this;
    }

    public AbstractUserBdr setPromoteState(int promoteState) {
        this.promoteState = promoteState;
        return this;
    }

    public AbstractUserBdr setIsTest(int isTest) {
        this.isTest = isTest;
        return this;
    }

    public AbstractUserBdr setTable(int table) {
        this.table = table;
        return this;
    }

    public AbstractUserBdr setGameid(int gameid) {
        this.gameid = gameid;
        return this;
    }

    public AbstractUserBdr setAccountType(int accountType) {
        this.accountType = accountType;
        return this;
    }

    public AbstractUserBdr setBoxid(int boxid) {
        this.boxid = boxid;
        return this;
    }

    public AbstractUserBdr setGsid(String gsid) {
        this.gsid = gsid;
        return this;
    }

    public AbstractUserBdr setRoomCode(int roomCode) {
        this.roomCode = roomCode;
        return this;
    }

    public AbstractUserBdr setRole(int role) {
        this.role = role;
        return this;
    }

    public AbstractUserBdr setChair(int chair) {
        this.chair = chair;
        return this;
    }

    public AbstractUserBdr setState(int state) {
        this.state = state;
        return this;
    }

    public AbstractUserBdr setChangeTime(int changeTime) {
        this.changeTime = changeTime;
        return this;
    }

    public AbstractUserBdr setLoginVersion(String loginVersion) {
        this.loginVersion = loginVersion;
        return this;
    }

    public AbstractUserBdr setFid(int fid) {
        this.fid = fid;
        return this;
    }

    public AbstractUserBdr setPersonControlLog(String personControlLog) {
        this.personControlLog = personControlLog;
        return this;
    }

    public AbstractUserBdr setAfterBybNeedUpdateMoney(boolean afterBybNeedUpdateMoney) {
        this.afterBybNeedUpdateMoney = afterBybNeedUpdateMoney;
        return this;
    }

    public AbstractUserBdr setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public AbstractUserBdr setSession(Session session) {
        this.session = session;
        return this;
    }

    public abstract IUser createUser();
}
