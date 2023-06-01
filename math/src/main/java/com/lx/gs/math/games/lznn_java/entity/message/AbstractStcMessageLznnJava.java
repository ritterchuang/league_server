package com.lx.gs.math.games.lznn_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageLznnJava extends AbstractStcMessage {
    public AbstractStcMessageLznnJava(String subClassName){
        super(ConstMessageLznnJava.MessageObjectEnumMap, subClassName);
    }
}
