package org.lsj.gs.math.core.fish.detailCtr.enity.client;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 魚機返還詳細記錄
public class DetailFishReturn implements DetailAggregatedCalculate {
    private final String returnAwardBulletKind; // 返還獎勵子彈名稱
    private int returnAwardBulletCount; // 返還獎勵子彈數目
    private final double returnAwardBulletMul; // 返還獎勵子彈價值
    private final double returnAwardBulletCost; // 返還獎勵子彈成本

    public DetailFishReturn(String returnAwardBulletKind, int returnAwardBulletCount, double returnAwardBulletMul, double returnAwardBulletCost) {
        this.returnAwardBulletKind = returnAwardBulletKind;
        this.returnAwardBulletCount = returnAwardBulletCount;
        this.returnAwardBulletMul = returnAwardBulletMul;
        this.returnAwardBulletCost = returnAwardBulletCost;
    }

    public String getReturnAwardBulletKind() {
        return returnAwardBulletKind;
    }

    public int getReturnAwardBulletCount() {
        return returnAwardBulletCount;
    }

    public double getReturnAwardBulletMul() {
        return returnAwardBulletMul;
    }

    public double getReturnAwardBulletCost() {
        return returnAwardBulletCost;
    }

    @JsonIgnore
    @Override
    public String getAggregatedKey() {
        return DetailFishReturn.class.getSimpleName() + "_" + returnAwardBulletKind + "_" + returnAwardBulletMul;
    }

    @JsonIgnore
    @Override
    public int getCount() {
        return returnAwardBulletCount;
    }

    @Override
    public void sumAggregatedCount(int count) {
        returnAwardBulletCount += count;
    }
}
