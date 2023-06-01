package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageQznnK4zJava extends AbstractStcMessage {
    public AbstractStcMessageQznnK4zJava(String subClassName){
        super(ConstMessageQznnK4zJava.MessageObjectEnumMap, subClassName);
    }
}
