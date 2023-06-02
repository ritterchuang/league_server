package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

// 雙重輪盤目標客製處理結果
public class HitResultExtendDoubleWheel extends HitResultExtend{
    private final int inSideShowOddsIndex; // 內圈表演倍數位置
    private final int outSideShowOddsIndex; // 內圈表演倍數位置
    private final int inSideShowOdds; // 內圈表演倍數
    private final int outSideShowOdds; // 內圈表演倍數

    public HitResultExtendDoubleWheel(int inSideShowOddsIndex, int outSideShowOddsIndex, int inSideShowOdds, int outSideShowOdds) {
        this.inSideShowOddsIndex = inSideShowOddsIndex;
        this.outSideShowOddsIndex = outSideShowOddsIndex;
        this.inSideShowOdds = inSideShowOdds;
        this.outSideShowOdds = outSideShowOdds;
    }

    public HitResultExtendDoubleWheel() {
        this.inSideShowOddsIndex = 0;
        this.outSideShowOddsIndex = 0;
        this.inSideShowOdds = 0;
        this.outSideShowOdds = 0;
    }

    public int getInSideShowOddsIndex() {
        return inSideShowOddsIndex;
    }

    public int getOutSideShowOddsIndex() {
        return outSideShowOddsIndex;
    }

    public int getInSideShowOdds() {
        return inSideShowOdds;
    }

    public int getOutSideShowOdds() {
        return outSideShowOdds;
    }

}
