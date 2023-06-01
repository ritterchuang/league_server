package com.lx.gs.math.games.sg_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageSgJava extends AbstractStcMessage {
    public AbstractStcMessageSgJava(String subClassName){
        super(ConstMessageSgJava.MessageObjectEnumMap, subClassName);
    }
}
