package com.lx.gs.math.games.qznn_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageQznnJava extends AbstractStcMessage {
    public AbstractStcMessageQznnJava(String subClassName){
        super(ConstMessageQznnJava.MessageObjectEnumMap, subClassName);
    }
}
