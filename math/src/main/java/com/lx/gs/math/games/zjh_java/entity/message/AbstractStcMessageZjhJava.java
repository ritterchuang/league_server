package com.lx.gs.math.games.zjh_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageZjhJava extends AbstractStcMessage {
    public AbstractStcMessageZjhJava(String subClassName){
        super(ConstMessageZjhJava.MessageObjectEnumMap, subClassName);
    }
}
