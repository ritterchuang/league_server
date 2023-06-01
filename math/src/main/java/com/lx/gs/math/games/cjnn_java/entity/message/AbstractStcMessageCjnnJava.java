package com.lx.gs.math.games.cjnn_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageCjnnJava extends AbstractStcMessage {
    public AbstractStcMessageCjnnJava(String subClassName){
        super(ConstMessageCjnnJava.MessageObjectEnumMap, subClassName);
    }
}
