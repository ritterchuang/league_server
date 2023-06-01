package org.lsj.gs.math.core.fish.detailCtr.enity.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 魚機特殊事件詳細記錄
public class DetailSpecialFeature implements DetailAggregatedCalculate {
    private final String specialFeatureKind; // 特殊事件名稱
    private int specialFeatureCount; // 特殊事件次數
    private final double specialFeatureMul; // 特殊事件價值
    private final String bulletKind; // 子彈名稱
    private final double bulletMul; // 子彈價值
    private final double bulletCost; // 子彈成本

    public DetailSpecialFeature(String specialFeatureKind, int specialFeatureCount, double specialFeatureMul, String bulletKind, double bulletMul, double bulletCost) {
        this.specialFeatureKind = specialFeatureKind;
        this.specialFeatureCount = specialFeatureCount;
        this.specialFeatureMul = specialFeatureMul;
        this.bulletKind = bulletKind;
        this.bulletMul = bulletMul;
        this.bulletCost = bulletCost;
    }

    public String getSpecialFeatureKind() {
        return specialFeatureKind;
    }

    public int getSpecialFeatureCount() {
        return specialFeatureCount;
    }

    public double getSpecialFeatureMul() {
        return specialFeatureMul;
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
        return DetailSpecialFeature.class.getSimpleName() + "_" + specialFeatureKind + "_" + specialFeatureMul + "_" + bulletKind + "_" + bulletMul;
    }

    @JsonIgnore
    @Override
    public int getCount() {
        return specialFeatureCount;
    }

    @Override
    public void sumAggregatedCount(int count) {
        specialFeatureCount += count;
    }
}
