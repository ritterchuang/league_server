package org.lsj.gs.math.games.ebg_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageEbgJava extends AbstractStcMessage {
    public AbstractStcMessageEbgJava(String subClassName){
        super(ConstMessageEbgJava.MessageObjectEnumMap, subClassName);
    }
}
