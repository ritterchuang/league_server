package org.lsj.gs.math.core.common.table.module.tableEvent;

import org.lsj.gs.event.TableFinishedEvent;

import javax.enterprise.event.Event;

// 牌桌事件處理器
public class TableEventHlr {
    private Event<TableFinishedEvent> event; // 牌桌事件

    public TableEventHlr(Event<TableFinishedEvent> event) {
        this.event = event;
    }

    // 取得牌桌事件
    public Event<TableFinishedEvent> getEvent() {
        return this.event;
    }
}
