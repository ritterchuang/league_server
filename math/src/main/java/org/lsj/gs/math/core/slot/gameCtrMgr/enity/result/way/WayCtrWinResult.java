package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way;

import java.util.List;

// 路得分結果
public class WayCtrWinResult {
    private final int payComboId; // 賠率組合識別碼
    private final int hitNumber; // 連線數
    private final int hitCount; // 堆疊數
    private final int hitOdds; // 獎項倍數
    private final double totalWin; // 總得分
    private final List<List<Boolean>> screenHitPosition; // 中獎位置
    private final List<List<Boolean>> dampScreenHitPosition; // 破框中獎位置

    public WayCtrWinResult(int payComboId, int hitNumber, int hitCount, int hitOdds, double totalWin, List<List<Boolean>> screenHitPosition, List<List<Boolean>> dampScreenHitPosition) {
        this.payComboId = payComboId;
        this.hitNumber = hitNumber;
        this.hitCount = hitCount;
        this.hitOdds = hitOdds;
        this.totalWin = totalWin;
        this.screenHitPosition = screenHitPosition;
        this.dampScreenHitPosition = dampScreenHitPosition;
    }

    public int getPayComboId() {
        return payComboId;
    }

    public int getHitNumber() {
        return hitNumber;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getHitOdds() {
        return hitOdds;
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
