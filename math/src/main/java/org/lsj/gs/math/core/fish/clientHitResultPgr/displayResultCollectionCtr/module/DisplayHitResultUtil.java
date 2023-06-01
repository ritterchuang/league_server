package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResult;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend.*;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.*;

import java.util.Collections;
import java.util.List;

// 客製表演打擊工具包
public class DisplayHitResultUtil {

    // 計算打擊事件表演結果
    public List<DisplayResult> calculateHitDisplayResultList(int killCount, double basicWin, ConstMathFish.HitType hitType, HitResultExtend hitResultExtend) {
        // 1. 無擊殺，不包裝
        if (killCount <= 0 || basicWin <= 0.0) {
            return Collections.emptyList();
        }

        // 2. 包裝表演結果列表
        switch (hitType) {
            case MULTI_TARGET: return List.of(new DisplayResult(ConstMathFish.DisplayType.MULTI_TARGET, new DisplayResultExtendMultiTarget(killCount, basicWin, (HitResultExtendMultiTarget) hitResultExtend)));
            case WHEEL: return List.of(new DisplayResult(ConstMathFish.DisplayType.WHEEL, new DisplayResultExtendWheel(killCount, basicWin, (HitResultExtendWheel) hitResultExtend)));
            case RED_ENVELOPE: return List.of(new DisplayResult(ConstMathFish.DisplayType.RED_ENVELOPE, new DisplayResultExtendRedEnvelope(killCount, basicWin, (HitResultExtendRedEnvelope) hitResultExtend)));
            case DRAGON_TREASURE: return List.of(new DisplayResult(ConstMathFish.DisplayType.DRAGON_TREASURE, new DisplayResultExtendDragonTreasure(killCount, basicWin, (HitResultExtendDragonTreasure) hitResultExtend)));
            case DOUBLE_WHEEL: return List.of(new DisplayResult(ConstMathFish.DisplayType.DOUBLE_WHEEL, new DisplayResultExtendDoubleWheel(killCount, basicWin, (HitResultExtendDoubleWheel) hitResultExtend)));
            case TREASURE_BOX: return List.of(new DisplayResult(ConstMathFish.DisplayType.TREASURE_BOX, new DisplayResultExtendTreasureBox(killCount, basicWin, (HitResultExtendTreasureBox) hitResultExtend)));
            case REEL: return List.of(new DisplayResult(ConstMathFish.DisplayType.REEL, new DisplayResultExtendReel(killCount, basicWin, (HitResultExtendReel) hitResultExtend)));
            case TRIPLE_WHEEL: return List.of(new DisplayResult(ConstMathFish.DisplayType.TRIPLE_WHEEL, new DisplayResultExtendTripleWheel(killCount, basicWin, (HitResultExtendTripleWheel) hitResultExtend)));
            default: return Collections.emptyList();
        }
    }
}
