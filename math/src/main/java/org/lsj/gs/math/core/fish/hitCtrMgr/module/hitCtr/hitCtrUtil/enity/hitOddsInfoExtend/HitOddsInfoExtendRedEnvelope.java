package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend;

// 紅包額外倍數資訊
public class HitOddsInfoExtendRedEnvelope extends HitOddsInfoExtend{
    private final int awardCount; // 獲獎個數
    private final int[] showOddsArray; // 此次表演陣列

    public HitOddsInfoExtendRedEnvelope(int awardCount, int[] showOddsArray) {
        this.awardCount = awardCount;
        this.showOddsArray = showOddsArray;
    }

    public int getAwardCount() {
        return awardCount;
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }
}
