package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.utils.MathUtil;

// 客製表演紅包
public class DisplayResultExtendRedEnvelope extends AbstractDisplayResultExtendValue{
    private final int awardCount; // 獲獎個數
    private final double[] showWinArray; // 表演分數陣列
    private final int[] showOddsArray; // 表演倍數陣列

    public DisplayResultExtendRedEnvelope(SpecialFeatureResultExtendRedEnvelope specialFeatureResultExtendRedEnvelope) {
        super(specialFeatureResultExtendRedEnvelope.getKillCount(), specialFeatureResultExtendRedEnvelope.getBasicWin(), specialFeatureResultExtendRedEnvelope.getTotalWin());
        this.awardCount = specialFeatureResultExtendRedEnvelope.getAwardCount();
        this.showWinArray = specialFeatureResultExtendRedEnvelope.getShowWinArray();
        this.showOddsArray = specialFeatureResultExtendRedEnvelope.getShowOddsArray();
    }


    public DisplayResultExtendRedEnvelope(int killCount, double basicWin, HitResultExtendRedEnvelope hitResultExtendRedEnvelope) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.awardCount = hitResultExtendRedEnvelope.getAwardCount();
        this.showWinArray = hitResultExtendRedEnvelope.getShowWinArray();
        this.showOddsArray = hitResultExtendRedEnvelope.getShowOddsArray();
    }

    public int getAwardCount() {
        return awardCount;
    }

    public double[] getShowWinArray() {
        return showWinArray;
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }
}
