package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.connect;

import java.util.List;

// 連接得分結果
public class ConnectCtrWinResult implements Comparable<ConnectCtrWinResult>{
    private final int payComboId; // 賠率組合識別碼
    private final int hitNumber; // 連線數
    private final int hitOdds; // 獎項倍數
    private final int multiplier; // 額外倍數
    private final double totalWin; // 總得分
    private final List<List<Boolean>> screenHitPosition; // 中獎位置
    private final List<List<Boolean>> dampScreenHitPosition; // 破框中獎位置

    public ConnectCtrWinResult(int payComboId, int hitNumber, int hitOdds, int multiplier, double totalWin, List<List<Boolean>> screenHitPosition, List<List<Boolean>> dampScreenHitPosition) {
        this.payComboId = payComboId;
        this.hitNumber = hitNumber;
        this.hitOdds = hitOdds;
        this.multiplier = multiplier;
        this.totalWin = totalWin;
        this.screenHitPosition = screenHitPosition;
        this.dampScreenHitPosition = dampScreenHitPosition;
    }

    // 自定義排序 [依照 倍數大、連線數大、賠率標誌小]
    @Override
    public int compareTo(ConnectCtrWinResult connectCtrWinResult) {
        // 1. 倍數高，排序在前
        if (this.hitOdds * this.multiplier > connectCtrWinResult.hitOdds * connectCtrWinResult.multiplier) {
            return -1;
        }

        if (this.hitOdds * this.multiplier < connectCtrWinResult.hitOdds * connectCtrWinResult.multiplier) {
            return 1;
        }

        // 2. 連線數高，排序在前
        if (this.hitNumber > connectCtrWinResult.hitNumber) {
            return -1;
        }

        if (this.hitNumber < connectCtrWinResult.hitNumber) {
            return 1;
        }

        // 3. 賠率標誌低，排序在前
        if (this.payComboId < connectCtrWinResult.payComboId) {
            return -1;
        }

        if (this.payComboId > connectCtrWinResult.payComboId) {
            return 1;
        }

        return 0;
    }

    public int getPayComboId() {
        return payComboId;
    }

    public int getHitNumber() {
        return hitNumber;
    }

    public int getHitOdds() {
        return hitOdds;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public List<List<Boolean>> getScreenHitPosition() {
        return screenHitPosition;
    }

    public List<List<Boolean>> getDampScreenHitPosition() {
        return dampScreenHitPosition;
    }
}
