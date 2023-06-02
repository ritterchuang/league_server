package org.lsj.gs.math.core.common.gameAdjust.entity;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;

// 捕魚預遊戲結果
public class PreGameAdjustResultFish extends PreGameAdjustResult {
    private final HitResult hitResult; // 打擊結果

    public PreGameAdjustResultFish(HitResult hitResult) {
        super();
        this.hitResult = hitResult;
    }

    public HitResult getHitResult() {
        return hitResult;
    }
}
