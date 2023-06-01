package org.lsj.gs.math.core.slot.progressHlrMgr.enity.config;

// 遊戲進度額外設定局
public class ProgressConfigExtendRound extends ProgressConfigExtend{
    private final int defaultRound; // 初始場次
    private final int addRound; // 增加場次
    private final int maxRound; // 最大場次

    public ProgressConfigExtendRound(int defaultRound, int addRound, int maxRound) {
        this.defaultRound = defaultRound;
        this.addRound = addRound;
        this.maxRound = maxRound;
    }

    public int getDefaultRound() {
        return defaultRound;
    }

    public int getAddRound() {
        return addRound;
    }

    public int getMaxRound() {
        return maxRound;
    }
}
