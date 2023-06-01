package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend;

// 雙重輪盤額外倍數資訊
public class HitOddsInfoExtendDoubleWheel extends HitOddsInfoExtend{
    private final int inSideOddsIndex; // 內圈倍數位置
    private final int outSideOddsIndex; // 外圈倍數位置

    public HitOddsInfoExtendDoubleWheel(int inSideOddsIndex, int outSideOddsIndex) {
        this.inSideOddsIndex = inSideOddsIndex;
        this.outSideOddsIndex = outSideOddsIndex;
    }

    public int getInSideOddsIndex() {
        return inSideOddsIndex;
    }

    public int getOutSideOddsIndex() {
        return outSideOddsIndex;
    }
}
