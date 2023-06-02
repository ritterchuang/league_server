package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtendNormal;

// 獎勵子彈生產者設定
public class AwardBulletGtrConfigExtendOneType extends AwardBulletGtrConfigExtend {
    private final ConstMathFish.AwardBulletCtrType awardBulletCtrType; // 獎勵子彈計算者類型
    private final AwardBulletCtrConfigExtendNormal config; // 獎勵子彈計算者設定
    private final int bulletIndex; // 獎勵子彈代碼
    private final int maxAmount; // 最大子彈數
    private final int[] amountArray; // 子彈出現數列表
    private final int[] amountWeightArray; // 子彈出現權重列表

    public AwardBulletGtrConfigExtendOneType(ConstMathFish.AwardBulletCtrType awardBulletCtrType, AwardBulletCtrConfigExtend awardBulletCtrConfigExtend, int bulletIndex, int maxAmount, int[] amountArray, int[] amountWeightArray) {
        this.awardBulletCtrType = awardBulletCtrType;
        this.config = (AwardBulletCtrConfigExtendNormal) awardBulletCtrConfigExtend;
        this.bulletIndex = bulletIndex;
        this.maxAmount = maxAmount;
        this.amountArray = amountArray;
        this.amountWeightArray = amountWeightArray;
    }

    public ConstMathFish.AwardBulletCtrType getAwardBulletCtrType() {
        return awardBulletCtrType;
    }

    public AwardBulletCtrConfigExtend getAwardBulletCtrConfigExtend() {
        return config;
    }

    public int getBulletIndex() {
        return bulletIndex;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public int[] getAmountArray() {
        return amountArray;
    }

    public int[] getAmountWeightArray() {
        return amountWeightArray;
    }
}
