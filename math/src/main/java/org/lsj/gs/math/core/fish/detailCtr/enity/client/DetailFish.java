package org.lsj.gs.math.core.fish.detailCtr.enity.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 捕魚詳細記錄
public class DetailFish implements DetailAggregatedCalculate {
    private final String fishKind; // 魚名稱
    private final double fishMul; // 魚價值
    private int fishCount; // 數目
    private final String bulletKind; // 子彈名稱
    private final double bulletMul; // 子彈價值
    private final double bulletCost; // 子彈成本

    public DetailFish(String fishKind, double fishMul, int fishCount, String bulletKind, double bulletMul, double bulletCost) {
        this.fishKind = fishKind;
        this.fishMul = fishMul;
        this.fishCount = fishCount;
        this.bulletKind = bulletKind;
        this.bulletMul = bulletMul;
        this.bulletCost = bulletCost;
    }

    public String getFishKind() {
        return fishKind;
    }

    public double getFishMul() {
        return fishMul;
    }

    public int getFishCount() {
        return fishCount;
    }

    public String getBulletKind() {
        return bulletKind;
    }

    public double getBulletMul() {
        return bulletMul;
    }

    public double getBulletCost() {
        return bulletCost;
    }

    @JsonIgnore
    @Override
    public String getAggregatedKey() {
        return DetailFish.class.getSimpleName() + "_" + fishKind + "_" + fishMul + "_" + bulletKind + "_" + bulletMul;
    }

    @JsonIgnore
    @Override
    public int getCount() {
        return fishCount;
    }

    @Override
    public void sumAggregatedCount(int count) {
        this.fishCount += count;
    }
}
