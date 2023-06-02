package org.lsj.gs.math.core.fish.bulletMgr.entity.client;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendInvalid;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendInvalid;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;

// 客端子彈訊息
public class ClientBullet {
    private final int bulletIndex; // 子彈編碼
    private final ConstMathFish.BulletType bulletType; // 子彈類型
    private final BulletTypeExtend bulletTypeExtend; // 客製子彈類型訊息
    private final ConstMathFish.BulletCostType bulletCostType; // 子彈成本類型
    private final BulletCostExtend bulletCostExtend; // 客製子彈成本訊息

    // 原始建構子提供JSON解析用
    public ClientBullet() {
        this.bulletIndex = -1;
        this.bulletType = ConstMathFish.BulletType.INVALID;
        this.bulletTypeExtend = new BulletTypeExtendInvalid();
        this.bulletCostType = ConstMathFish.BulletCostType.INVALID;
        this.bulletCostExtend = new BulletCostExtendInvalid();
    }

    public ClientBullet(int bulletIndex, ConstMathFish.BulletType bulletType, BulletTypeExtend bulletTypeExtend, ConstMathFish.BulletCostType bulletCostType, BulletCostExtend bulletCostExtend) {
        this.bulletIndex = bulletIndex;
        this.bulletType = bulletType;
        this.bulletTypeExtend = bulletTypeExtend;
        this.bulletCostType = bulletCostType;
        this.bulletCostExtend = bulletCostExtend;
    }

    public int getBulletIndex() {
        return bulletIndex;
    }

    public ConstMathFish.BulletType getBulletType() {
        return bulletType;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "bulletType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = BulletTypeExtendInvalid.class, name = "INVALID"),
            @JsonSubTypes.Type(value = BulletTypeExtendNormal.class, name = "NORMAL")
    })
    public BulletTypeExtend getBulletTypeExtend() {
        return bulletTypeExtend;
    }

    public ConstMathFish.BulletCostType getBulletCostType() {
        return bulletCostType;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "bulletCostType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = BulletCostExtendInvalid.class, name = "INVALID"),
            @JsonSubTypes.Type(value = BulletCostExtendRatio.class, name = "RATIO"),
            @JsonSubTypes.Type(value = BulletCostExtendFree.class, name = "FREE")
    })
    public BulletCostExtend getBulletCostExtend() {
        return bulletCostExtend;
    }
}
