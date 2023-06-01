package org.lsj.gs.math.core.common.table.entity.message.core;

// 抽象服務端傳客戶端共用訊息
public abstract class AbstractStcMessageCore extends AbstractStcMessage{
    public AbstractStcMessageCore(String subClassName){
        super(ConstMessageCore.MessageObjectEnumMap, subClassName);
    }
}
