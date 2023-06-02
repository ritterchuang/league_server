package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend;

// 神龍尋珠額外倍數資訊
public class HitOddsInfoExtendDragonTreasure extends HitOddsInfoExtend{
    private final int[] showOddsArray; // 此次表演陣列
    private final int ballCountAwardMultiplier; // 顆數獎勵倍數
    private final int dragonExtraAwardMultiplier; // 神龍額外獎勵倍數

    public HitOddsInfoExtendDragonTreasure(int[] showOddsArray, int ballCountAwardMultiplier, int dragonExtraAwardMultiplier) {
        this.showOddsArray = showOddsArray;
        this.ballCountAwardMultiplier = ballCountAwardMultiplier;
        this.dragonExtraAwardMultiplier = dragonExtraAwardMultiplier;
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }

    public int getBallCountAwardMultiplier() {
        return ballCountAwardMultiplier;
    }

    public int getDragonExtraAwardMultiplier() {
        return dragonExtraAwardMultiplier;
    }
}
