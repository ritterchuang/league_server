package org.lsj.gs.math.games.mybjl_java.entity.message;

import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessage;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageMybjlJava extends AbstractStcMessage {
    public AbstractStcMessageMybjlJava(String subClassName){
        super(ConstMessageMybjlJava.MessageObjectEnumMap, subClassName);
    }
}
