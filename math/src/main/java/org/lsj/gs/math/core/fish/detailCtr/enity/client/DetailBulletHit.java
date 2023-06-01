package org.lsj.gs.math.core.fish.detailCtr.enity.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 捕魚發射詳細記錄
public class DetailBulletHit implements DetailAggregatedCalculate {
    private final String bulletKind; // 子彈名稱
    private int bulletCount; // 數目
    private final double bulletMul; // 子彈價值
    private final double bulletCost; // 子彈成本

    public DetailBulletHit(String bulletKind, int bulletCount, double bulletMul, double bulletCost) {
        this.bulletKind = bulletKind;
        this.bulletCount = bulletCount;
        this.bulletMul = bulletMul;
        this.bulletCost = bulletCost;
    }

    @JsonIgnore
    @Override
    public String getAggregatedKey() {
        return DetailBulletHit.class.getSimpleName() + "_" + bulletKind + "_" + bulletMul + "_" + bulletCost;
    }

    @JsonIgnore
    @Override
    public int getCount() {
        return bulletCount;
    }

    @Override
    public void sumAggregatedCount(int count) {
        bulletCount += count;
    }
}
