package org.lsj.db;

import org.lsj.utils.DBUtil;

// 下注紀錄物件
public class GameBetLogObj {
    private final int id; // 流水號

    // 格式: ${hash}_${fid}_${timestamp}_${tableId}
    // 透過 mq 送到後台的注單需再加上 chair: ${hash}_${fid}_${timestamp}_${tableId}_${chair}
    private final String roundId; // 局號

    private final String orderId; // 訂單號
    private final int uid; // 玩家辨識碼
    private final String thirdAccount; // 三方帳號
    private final int thirdAccountId; // 三方帳號辨識碼
    private final int companyId; // 公司編碼
    private final int platform; // 平台
    private final String agent; // 代理
    private int gameId; // 遊戲編碼
    private final String gameName; // 遊戲名稱
    private int fieldId; // 房間編碼
    private final String fieldName; // 房間名稱
    private final String gsId; // 服務器編碼
    private final int tableId; // 牌桌編碼
    private final int chair; // 座位
    private final double base; // 底分
    private double returnAward; // 得分
    private double bet; // 押注
    private double validBet; // 有效押注
    private double score; // 玩家淨利
    private double fee; // 手續費
    private double beginMoney; // 起始金額
    private long beginTime; // 起始時間
    private long endTime; // 結束時間
    private long addTime; // 添加紀錄時間
    private final String ip; // IP位置
    private final String adjustInfo; // 調控資訊

    public GameBetLogObj(int id, String roundId, String orderId, int uid, String thirdAccount, int thirdAccountId
            , int companyId, int platform, String agent, int gameId, String gameName, int fieldId, String fieldName
            , String gsId, int tableId, int chair, double base, double returnAward, double bet, double validBet
            , double score, double fee, double beginMoney, long beginTime, long endTime, long addTime, String ip
            , String adjustInfo) {
        this.id = id;
        this.roundId = roundId;
        this.orderId = orderId;
        this.uid = uid;
        this.thirdAccount = thirdAccount;
        this.thirdAccountId = thirdAccountId;
        this.companyId = companyId;
        this.platform = platform;
        this.agent = agent;
        this.gameId = gameId;
        this.gameName = gameName;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.gsId = gsId;
        this.tableId = tableId;
        this.chair = chair;
        this.base = base;
        this.returnAward = returnAward;
        this.bet = bet;
        this.validBet = validBet;
        this.score = score;
        this.fee = fee;
        this.beginMoney = beginMoney;
        this.beginTime = DBUtil.convertMillisecondToZero(beginTime);    // mysql datetime 只支援到秒, 將毫秒歸 0
        this.endTime = DBUtil.convertMillisecondToZero(endTime);        // mysql datetime 只支援到秒, 將毫秒歸 0
        this.addTime = addTime;
        this.ip = ip;
        this.adjustInfo = adjustInfo;
    }

    public int getId() {
        return id;
    }

    public String getRoundId() {
        return roundId;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getUid() {
        return uid;
    }

    public String getThirdAccount() {
        return thirdAccount;
    }

    public int getThirdAccountId() {
        return thirdAccountId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getPlatform() {
        return platform;
    }

    public String getAgent() {
        return agent;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getGsId() {
        return gsId;
    }

    public int getTableId() {
        return tableId;
    }

    public int getChair() {
        return chair;
    }

    public double getBase() {
        return base;
    }

    public double getReturnAward() {
        return returnAward;
    }

    public double getBet() {
        return bet;
    }

    public double getValidBet() {
        return validBet;
    }

    public double getScore() {
        return score;
    }

    public double getFee() {
        return fee;
    }

    public double getBeginMoney() {
        return beginMoney;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getAddTime() {
        return addTime;
    }

    public String getIp() {
        return ip;
    }

    public String getAdjustInfo() {
        return adjustInfo;
    }

    public void setReturnAward(double returnAward) {
        this.returnAward = returnAward;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public void setValidBet(double validBet) {
        this.validBet = validBet;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setBeginMoney(double beginMoney) {
        this.beginMoney = beginMoney;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = DBUtil.convertMillisecondToZero(beginTime);    // mysql datetime 只支援到秒, 將毫秒歸 0
    }

    public void setEndTime(long endTime) {
        this.endTime = DBUtil.convertMillisecondToZero(endTime);    // mysql datetime 只支援到秒, 將毫秒歸 0
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }
}
