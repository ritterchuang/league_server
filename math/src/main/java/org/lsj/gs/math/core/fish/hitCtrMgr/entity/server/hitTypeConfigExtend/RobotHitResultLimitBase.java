package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

// 機器人打擊結果上下限倍數
public class RobotHitResultLimitBase {
    private final int lowerLimitBase; // 機器人打擊結果下限倍數
    private final int upperLimitBase; // 機器人打擊結果上限倍數

    public RobotHitResultLimitBase(int lowerLimitBase, int upperLimitBase) {
        this.lowerLimitBase = lowerLimitBase;
        this.upperLimitBase = upperLimitBase;
    }

    public int getLowerLimitBase() {
        return lowerLimitBase;
    }

    public int getUpperLimitBase() {
        return upperLimitBase;
    }
}
