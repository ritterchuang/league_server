package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity;

import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtend;

// 打擊倍數資訊
public class HitOddsInfo {
    private final int basicOdds; // 基礎倍數
    private final HitOddsInfoExtend hitOddsInfoExtend; // 基礎倍數額外資訊

    public HitOddsInfo(int basicOdds, HitOddsInfoExtend hitOddsInfoExtend) {
        this.basicOdds = basicOdds;
        this.hitOddsInfoExtend = hitOddsInfoExtend;
    }

    public HitOddsInfo() {
        this.basicOdds = 0;
        this.hitOddsInfoExtend = new HitOddsInfoExtend();
    }

    public int getBasicOdds() {
        return basicOdds;
    }

    public HitOddsInfoExtend getHitOddsInfoExtend() {
        return hitOddsInfoExtend;
    }
}
