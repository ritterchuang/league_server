package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDragonTreasure;
import org.lsj.utils.MathUtil;

// 客製表演神龍尋珠
public class DisplayResultExtendDragonTreasure extends AbstractDisplayResultExtendValue{
    private final boolean isTriggerDragon; // 觸發神龍大獎
    private final int endBallCount; // 結束顆數
    private final int awardBallCountMultiplier; // 顆數獎勵倍數
    private final int dragonExtraMultiplier; // 神龍額外獎勵倍數
    private final int totalOdds; // 獲取總倍數
    private final int[] showOddsArray; // 表演列表

    public DisplayResultExtendDragonTreasure(int killCount, double basicWin, HitResultExtendDragonTreasure hitResultExtendDragonTreasure) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.isTriggerDragon = hitResultExtendDragonTreasure.getIsTriggerDragon();
        this.endBallCount = hitResultExtendDragonTreasure.getEndBallCount();
        this.awardBallCountMultiplier = hitResultExtendDragonTreasure.getAwardBallCountMultiplier();
        this.totalOdds = hitResultExtendDragonTreasure.getTotalOdds();
        this.dragonExtraMultiplier = hitResultExtendDragonTreasure.getDragonExtraMultiplier();
        this.showOddsArray = hitResultExtendDragonTreasure.getShowOddsArray();
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
