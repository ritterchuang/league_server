package org.lsj.gs.math.core.common.table.module.tableEvent;

import org.lsj.gs.event.TableFinishedEvent;

import javax.enterprise.event.Event;

// 遊戲事件處理器介面
public interface ITableEvent {
    Event<TableFinishedEvent> getEvent(); // 取得遊戲桌事件
}
