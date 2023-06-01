package org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer;

import java.util.*;

// 牌桌定時器
public class TableTimer implements ITableTimer {
    private Timer timer; // 計時器
    private final Map<Integer, Long> timeIndexExpectRunTimeMap; // 定時器索引與與期望執行時間

    public TableTimer() {
        this.timer = new Timer();
        this.timeIndexExpectRunTimeMap = new HashMap<>();
    }

    // 設定指定索引定時器
    @Override
    public void schedule(TimerTask task, int timerIndex, long delay) {
        // 1. 設定預期觸發時間
        this.timeIndexExpectRunTimeMap.put(timerIndex,  delay + System.currentTimeMillis());

        // 2. 安排行程
        this.timer.schedule(task, delay);
    }

    // 設定定時器
    @Override
    public void schedule(TimerTask task, long delay) {
        // 1. 安排行程
        this.timer.schedule(task, delay);
    }

    // 清空計時器行程
    @Override
    public void cancel() {
        // 1. 清空定時器
        this.timer.cancel();

        // 2. 清空定時器
        this.timeIndexExpectRunTimeMap.clear();

        // 3. 重新創建
        this.timer = new Timer();
    }

    // 取得剩餘秒數
    @Override
    public int getLeftSecond(int timerIndex) {
        if (Objects.isNull(this.timeIndexExpectRunTimeMap.get(timerIndex))) {
            return -1;
        }

        long leftMilliSecond = this.timeIndexExpectRunTimeMap.get(timerIndex) - System.currentTimeMillis();

        return (int) (leftMilliSecond / 1000);
    }
}
