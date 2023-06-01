package com.lx.gs.math.games.lhd_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageLhdJava extends AbstractStcMessage {
    public AbstractStcMessageLhdJava(String subClassName){
        super(ConstMessageLhdJava.MessageObjectEnumMap, subClassName);
    }
}
