package com.lx.gs.math.games.hhdz_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageHhdzJava extends AbstractStcMessage {
    public AbstractStcMessageHhdzJava(String subClassName){
        super(ConstMessageHhdzJava.MessageObjectEnumMap, subClassName);
    }
}
