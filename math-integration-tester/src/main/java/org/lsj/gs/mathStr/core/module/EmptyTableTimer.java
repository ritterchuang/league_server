package org.lsj.gs.mathStr.core.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;

import java.util.TimerTask;

// 空的牌桌定時器
public class EmptyTableTimer implements ITableTimer {

    // 設定指定索引定時器
    @Override
    public void schedule(TimerTask task, int timerIndex, long delay) {}

    // 設定定時器
    @Override
    public void schedule(TimerTask task, long delay) {}

    // 取消定時器
    @Override
    public void cancel() {}

    // 取得剩餘秒數
    @Override
    public int getLeftSecond(int timerIndex) { return 0; }
}
