package com.lx.gs.math.games.brnn_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageBrnnJava extends AbstractStcMessage {
    public AbstractStcMessageBrnnJava(String subClassName){
        super(ConstMessageBrnnJava.MessageObjectEnumMap, subClassName);
    }
}
