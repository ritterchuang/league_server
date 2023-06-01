package org.lsj.gs.math.core.slot.progressHlrMgr.enity.config;

// 遊戲進度額外設定 消除場次
public class ProgressConfigExtendCascade extends ProgressConfigExtend{
    private final int defaultCascadeTimes; // 初始場次
    private final int addCascadeTimes; // 增加場次
    private final int maxCascadeTimes; // 最大場次

    public ProgressConfigExtendCascade(int defaultCascadeTimes, int addCascadeTimes, int maxCascadeTimes) {
        this.defaultCascadeTimes = defaultCascadeTimes;
        this.addCascadeTimes = addCascadeTimes;
        this.maxCascadeTimes = maxCascadeTimes;
    }

    public int getDefaultCascadeTimes() {
        return defaultCascadeTimes;
    }

    public int getAddCascadeTimes() {
        return addCascadeTimes;
    }

    public int getMaxCascadeTimes() {
        return maxCascadeTimes;
    }
}
