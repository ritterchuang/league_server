package org.lsj.gs.math.core.fish.hitCtrMgr.entity.client;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.*;

// 客端打擊資訊
public class ClientHit {
    private final ConstMathFish.HitType hitType; // 打擊類型
    private final HitTypeExtend hitTypeExtend; // 客製目標訊息

    // 原始建構子提供JSON解析用
    public ClientHit(){
        this.hitType = ConstMathFish.HitType.INVALID;
        this.hitTypeExtend = new HitTypeExtend();
    }

    public ClientHit(ConstMathFish.HitType hitType, HitTypeExtend hitTypeExtend) {
        this.hitType = hitType;
        this.hitTypeExtend = hitTypeExtend;
    }

    public ConstMathFish.HitType getHitType() {
        return hitType;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "hitType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = HitTypeExtendFixedOdds.class, name = "FIXED_ODDS"),
            @JsonSubTypes.Type(value = HitTypeExtendRandomOdds.class, name = "RANDOM_ODDS"),
            @JsonSubTypes.Type(value = HitTypeExtendMultiTarget.class, name = "MULTI_TARGET"),
            @JsonSubTypes.Type(value = HitTypeExtendWheel.class, name = "WHEEL"),
            @JsonSubTypes.Type(value = HitTypeExtendRedEnvelope.class, name = "RED_ENVELOPE"),
            @JsonSubTypes.Type(value = HitTypeExtendDragonTreasure.class, name = "DRAGON_TREASURE"),
            @JsonSubTypes.Type(value = HitTypeExtendDoubleWheel.class, name = "DOUBLE_WHEEL"),
            @JsonSubTypes.Type(value = HitTypeExtendTreasureBox.class, name = "TREASURE_BOX"),
            @JsonSubTypes.Type(value = HitTypeExtendReel.class, name = "REEL"),
            @JsonSubTypes.Type(value = HitTypeExtendTripleWheel.class, name = "TRIPLE_WHEEL"),
    })
    public HitTypeExtend getHitTypeExtend() {
        return hitTypeExtend;
    }
}
