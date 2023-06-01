package org.lsj.gs.math.core.fish.bulletMgr.entity.client;

// 動態子彈設定
public class BulletConfigDynamic extends BulletConfigBase {
    private final double[] bulletCostArray; // 子彈成本陣列

    public BulletConfigDynamic(BulletConfigBase bulletConfigBase, double[] bulletCostArray) {
        super(  bulletConfigBase.getBulletType(),
                bulletConfigBase.getBulletConfigExtend(),
                bulletConfigBase.getBulletCostType(),
                bulletConfigBase.getBulletCostConfigExtend(),
                bulletConfigBase.getBulletAmountType(),
                bulletConfigBase.getBulletRtpUseType(),
                bulletConfigBase.getBulletRtpUseConfigExtend()
        );
        this.bulletCostArray = bulletCostArray;
    }

    public double[] getBulletCostArray() {
        return bulletCostArray;
    }
}
