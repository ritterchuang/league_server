package org.lsj.gs.math.core.fish.detailCtr.enity.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 捕魚詳細記錄
public class DetailAwardBullet implements DetailAggregatedCalculate {
    private final String awardBulletKind; // 獎勵子彈名稱
    private int awardBulletCount; // 獎勵子彈個數
    private final double awardBulletMul; // 獎勵子彈價值

    public DetailAwardBullet(String awardBulletKind, int awardBulletCount, double awardBulletMul) {
        this.awardBulletKind = awardBulletKind;
        this.awardBulletCount = awardBulletCount;
        this.awardBulletMul = awardBulletMul;
    }

    public String getAwardBulletKind() {
        return awardBulletKind;
    }

    public int getAwardBulletCount() {
        return awardBulletCount;
    }

    public double getAwardBulletMul() {
        return awardBulletMul;
    }

    @JsonIgnore
    @Override
    public String getAggregatedKey() {
        return DetailAwardBullet.class.getSimpleName() + "_" + awardBulletKind + "_" + awardBulletMul;
    }

    @JsonIgnore
    @Override
    public int getCount() {
        return awardBulletCount;
    }

    @Override
    public void sumAggregatedCount(int count) {
        awardBulletCount += count;
    }
}
