package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend;

// 輪盤額外倍數資訊
public class HitOddsInfoExtendWheel extends HitOddsInfoExtend{
    private final int oddsIndex; // 倍數位置

    public HitOddsInfoExtendWheel(int oddsIndex) {
        this.oddsIndex = oddsIndex;
    }

    public int getOddsIndex() {
        return oddsIndex;
    }
}
