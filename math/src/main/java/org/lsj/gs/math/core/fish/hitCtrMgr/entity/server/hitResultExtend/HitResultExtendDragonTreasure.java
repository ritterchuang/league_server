package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

// 神龍尋珠目標客製處理結果
public class HitResultExtendDragonTreasure extends HitResultExtend{
    private final boolean isTriggerDragon; // 觸發神龍大獎
    private final int endBallCount; // 結束顆數
    private final int awardBallCountMultiplier; // 顆數獎勵倍數
    private final int dragonExtraMultiplier; // 神龍額外獎勵倍數
    private final int totalOdds; // 獲取總倍數
    private final int[] showOddsArray; // 表演列表

    public HitResultExtendDragonTreasure(boolean isTriggerDragon, int endBallCount, int awardBallCountMultiplier, int dragonExtraMultiplier, int totalOdds, int[] showOddsArray) {
        this.isTriggerDragon = isTriggerDragon;
        this.endBallCount = endBallCount;
        this.awardBallCountMultiplier = awardBallCountMultiplier;
        this.dragonExtraMultiplier = dragonExtraMultiplier;
        this.totalOdds = totalOdds;
        this.showOddsArray = showOddsArray;
    }

    public HitResultExtendDragonTreasure() {
        this.isTriggerDragon = false;
        this.endBallCount = 0;
        this.awardBallCountMultiplier = 0;
        this.dragonExtraMultiplier = 0;
        this.showOddsArray = new int[0];
        this.totalOdds = 0;
    }

    public boolean getIsTriggerDragon() {
        return isTriggerDragon;
    }

    public int getEndBallCount() {
        return endBallCount;
    }

    public int getAwardBallCountMultiplier() {
        return awardBallCountMultiplier;
    }

    public int getDragonExtraMultiplier() {
        return dragonExtraMultiplier;
    }

    public int getTotalOdds() {
        return totalOdds;
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }
}
