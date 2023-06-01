package org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientHitGtr;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.*;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

import java.util.HashMap;

// 客製打擊產生器
public class ClientHitGtr implements IClientHitGtr {

    @Override
    public ClientHit calculateClientHit(MathFishConfig mathFishConfig, int bulletIndex, int targetIndex) {
        // 1. 取得此次打擊類型
        ConstMathFish.HitType hitType = mathFishConfig.getClientHitConfig().getHitConfigMap().get(bulletIndex).get(targetIndex).getHitType();

        // 2. 計算客製打擊資訊
        HitTypeExtend hitTypeExtend = this.calculateHitTypeExtend(hitType, targetIndex);

        // 3. 封裝
        return new ClientHit(hitType, hitTypeExtend);
    }

    private HitTypeExtend calculateHitTypeExtend(ConstMathFish.HitType hitType, int targetIndex) {
        switch (hitType) {
            case FIXED_ODDS: return new HitTypeExtendFixedOdds();
            case RANDOM_ODDS: return new HitTypeExtendRandomOdds();
            case MULTI_TARGET: return new HitTypeExtendMultiTarget(new HashMap<>(){{
                put(1, 1);
                put(5, 3);
                put(7, 12);
                put(17, 1);
                put(19, 4);
                put(targetIndex, 2);
            }});
            case WHEEL: return new HitTypeExtendWheel();
            case RED_ENVELOPE: return new HitTypeExtendRedEnvelope();
            case DRAGON_TREASURE: return new HitTypeExtendDragonTreasure();
            case DOUBLE_WHEEL: return new HitTypeExtendDoubleWheel();
            case TREASURE_BOX: return new HitTypeExtendTreasureBox();
            case REEL: return new HitTypeExtendReel();
            case TRIPLE_WHEEL: return new HitTypeExtendTripleWheel();
            default: return new HitTypeExtendInvalid();
        }
    }
}
