package org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer;

import java.util.TimerTask;

// 定時器介面
public interface ITableTimer {
    // 設定指定索引定時器
    void schedule(TimerTask task, int timerIndex, long delay);

    // 設定定時器
    void schedule(TimerTask task, long delay);

    // 取消定時器
    void cancel();

    // 取得剩餘秒數
    int getLeftSecond(int timerIndex);
}
