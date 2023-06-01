package org.lsj.gs.math.games.bjl_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageBjlJava extends AbstractStcMessage {
    public AbstractStcMessageBjlJava(String subClassName){
        super(ConstMessageBjlJava.MessageObjectEnumMap, subClassName);
    }
}
