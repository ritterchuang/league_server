package com.lx.gs.math.games.qzpj_java.entity.message;

import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageQzpjJava extends AbstractStcMessage {
    public AbstractStcMessageQzpjJava(String subClassName){
        super(ConstMessageQzpjJava.MessageObjectEnumMap, subClassName);
    }
}
