package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

// 固定倍數打擊設定
public class HitTypeConfigExtendFixedOdds extends HitTypeConfigExtend{
    private final int odds; // 固定倍數

    public HitTypeConfigExtendFixedOdds(int odds) {
        this.odds = odds;
    }

    public int getOdds() {
        return odds;
    }
}
