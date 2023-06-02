package org.lsj.gs.math.core.common.table.module.tableEvent;


import org.lsj.gs.event.TableFinishedEvent;

import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.concurrent.CompletionStage;

// 空的牌桌事件
public class EmptyTableFinishedEvent implements Event<TableFinishedEvent> {
    @Override
    public void fire(TableFinishedEvent tableFinishedEvent) { }

    @Override
    public <U extends TableFinishedEvent> CompletionStage<U> fireAsync(U u) {
        return null;
    }

    @Override
    public <U extends TableFinishedEvent> CompletionStage<U> fireAsync(U u, NotificationOptions notificationOptions) {return null; }

    @Override
    public Event<TableFinishedEvent> select(Annotation... annotations) {
        return null;
    }

    @Override
    public <U extends TableFinishedEvent> Event<U> select(Class<U> aClass, Annotation... annotations) {
        return null;
    }

    @Override
    public <U extends TableFinishedEvent> Event<U> select(TypeLiteral<U> typeLiteral, Annotation... annotations) { return null; }
}
