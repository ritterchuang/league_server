package org.lsj.gs.math.core.fish.bulletMgr.entity.client;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtend;

// 子彈設定
public class BulletConfigBase {
    private final ConstMathFish.BulletType bulletType; // 子彈類型
    private final BulletConfigExtend bulletConfigExtend; // 客製子彈類型設定
    private final ConstMathFish.BulletCostType bulletCostType; // 子彈成本類型
    private final BulletCostConfigExtend bulletCostConfigExtend; // 客製子彈成本類型設定
    private final ConstMathFish.BulletAmountType bulletAmountType; // 子彈數量類型
    private final ConstMathFish.BulletRtpUseType bulletRtpUseType; // 子彈Rtp類型
    private final BulletRtpUseConfigExtend bulletRtpUseConfigExtend; // 子彈Rtp設定

    // 原始建構子提供JSON解析用
    public BulletConfigBase() {
        this.bulletType = ConstMathFish.BulletType.INVALID;
        this.bulletConfigExtend = null;
        this.bulletCostType = ConstMathFish.BulletCostType.INVALID;
        this.bulletCostConfigExtend = null;
        this.bulletAmountType = ConstMathFish.BulletAmountType.INVALID;
        this.bulletRtpUseType = ConstMathFish.BulletRtpUseType.INVALID;
        this.bulletRtpUseConfigExtend = null;
    }

    public BulletConfigBase(ConstMathFish.BulletType bulletType,
                            BulletConfigExtend bulletConfigExtend,
                            ConstMathFish.BulletCostType bulletCostType,
                            BulletCostConfigExtend bulletCostConfigExtend,
                            ConstMathFish.BulletAmountType bulletAmountType,
                            ConstMathFish.BulletRtpUseType bulletRtpUseType,
                            BulletRtpUseConfigExtend bulletRtpUseConfigExtend
                            ) {
        this.bulletType = bulletType;
        this.bulletConfigExtend = bulletConfigExtend;
        this.bulletCostType = bulletCostType;
        this.bulletCostConfigExtend = bulletCostConfigExtend;
        this.bulletAmountType = bulletAmountType;
        this.bulletRtpUseType = bulletRtpUseType;
        this.bulletRtpUseConfigExtend = bulletRtpUseConfigExtend;
    }

    public ConstMathFish.BulletType getBulletType() {
        return bulletType;
    }

    public BulletConfigExtend getBulletConfigExtend() {
        return bulletConfigExtend;
    }

    public ConstMathFish.BulletCostType getBulletCostType() {
        return bulletCostType;
    }

    public BulletCostConfigExtend getBulletCostConfigExtend() {
        return bulletCostConfigExtend;
    }

    public ConstMathFish.BulletAmountType getBulletAmountType() {
        return bulletAmountType;
    }

    public ConstMathFish.BulletRtpUseType getBulletRtpUseType() {
        return bulletRtpUseType;
    }

    public BulletRtpUseConfigExtend getBulletRtpUseConfigExtend() {
        return bulletRtpUseConfigExtend;
    }
}
