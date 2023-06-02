package org.lsj.gs.math.games.brnn_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageBrnnJava extends AbstractStcMessage {
    public AbstractStcMessageBrnnJava(String subClassName){
        super(ConstMessageBrnnJava.MessageObjectEnumMap, subClassName);
    }
}
