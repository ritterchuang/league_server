package org.lsj.gs.math.games.qznn_ksz_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageQznnKszJava extends AbstractStcMessage {
    public AbstractStcMessageQznnKszJava(String subClassName){
        super(ConstMessageQznnKszJava.MessageObjectEnumMap, subClassName);
    }
}
