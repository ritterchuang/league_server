package org.lsj.db;

public class GameBetLogObjBuilder {
    private int id;
    private String roundId;
    private String orderId = "";
    private int uid;
    private String thirdAccount;
    private int thirdAccountId;
    private int companyId;
    private int platform;
    private String agent;
    private int gameId;
    private String gameName;
    private int fieldId;
    private String fieldName;
    private String gsId;
    private int tableId;
    private int chair;
    private double base;
    private double returnAward;
    private double bet;
    private double validBet;
    private double score;
    private double fee;
    private double beginMoney;
    private long beginTime;
    private long endTime;
    private long addTime;
    private String ip;
    private String adjustInfo;

    public GameBetLogObjBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public GameBetLogObjBuilder setRoundId(String roundId) {
        this.roundId = roundId;
        return this;
    }

    public GameBetLogObjBuilder setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public GameBetLogObjBuilder setUid(int uid) {
        this.uid = uid;
        return this;
    }

    public GameBetLogObjBuilder setThirdAccount(String thirdAccount) {
        this.thirdAccount = thirdAccount;
        return this;
    }

    public GameBetLogObjBuilder setThirdAccountId(int thirdAccountId) {
        this.thirdAccountId = thirdAccountId;
        return this;
    }

    public GameBetLogObjBuilder setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public GameBetLogObjBuilder setPlatform(int platform) {
        this.platform = platform;
        return this;
    }

    public GameBetLogObjBuilder setAgent(String agent) {
        this.agent = agent;
        return this;
    }

    public GameBetLogObjBuilder setGameId(int gameId) {
        this.gameId = gameId;
        return this;
    }

    public GameBetLogObjBuilder setGameName(String gameName) {
        this.gameName = gameName;
        return this;
    }

    public GameBetLogObjBuilder setFieldId(int fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public GameBetLogObjBuilder setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public GameBetLogObjBuilder setGsId(String gsId) {
        this.gsId = gsId;
        return this;
    }

    public GameBetLogObjBuilder setTableId(int tableId) {
        this.tableId = tableId;
        return this;
    }

    public GameBetLogObjBuilder setChair(int chair) {
        this.chair = chair;
        return this;
    }

    public GameBetLogObjBuilder setBase(double base) {
        this.base = base;
        return this;
    }

    public GameBetLogObjBuilder setReturnAward(double returnAward) {
        this.returnAward = returnAward;
        return this;
    }

    public GameBetLogObjBuilder setBet(double bet) {
        this.bet = bet;
        return this;
    }

    public GameBetLogObjBuilder setValidBet(double validBet) {
        this.validBet = validBet;
        return this;
    }

    public GameBetLogObjBuilder setScore(double score) {
        this.score = score;
        return this;
    }

    public GameBetLogObjBuilder setFee(double fee) {
        this.fee = fee;
        return this;
    }

    public GameBetLogObjBuilder setBeginMoney(double beginMoney) {
        this.beginMoney = beginMoney;
        return this;
    }

    public GameBetLogObjBuilder setBeginTime(long beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public GameBetLogObjBuilder setEndTime(long endTime) {
        this.endTime = endTime;
        return this;
    }

    public GameBetLogObjBuilder setAddTime(long addTime) {
        this.addTime = addTime;
        return this;
    }

    public GameBetLogObjBuilder setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public GameBetLogObjBuilder setAdjustInfo(String adjustInfo) {
        this.adjustInfo = adjustInfo;
        return this;
    }

    public GameBetLogObj createGameBetLogObj() {
        return new GameBetLogObj(id, roundId, orderId, uid, thirdAccount, thirdAccountId, companyId, platform, agent
                , gameId, gameName, fieldId, fieldName, gsId, tableId, chair, base, returnAward, bet, validBet, score
                , fee, beginMoney, beginTime, endTime, addTime, ip, adjustInfo);
    }
}
