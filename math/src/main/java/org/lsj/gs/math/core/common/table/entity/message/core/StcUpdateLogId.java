package org.lsj.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 更新對局日誌索引消息
public class StcUpdateLogId extends AbstractStcMessageCore {
    @JsonIgnore public String logid; // 局號

    public StcUpdateLogId(String logid){
        super(StcUpdateLogId.class.getCanonicalName());
        this.logid = logid;
        ((ObjectNode)super.data).put("logid", logid);
    }
}
