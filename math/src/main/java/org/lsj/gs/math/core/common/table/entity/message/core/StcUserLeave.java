package org.lsj.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 玩家列表訊息
public class StcUserLeave extends AbstractStcMessageCore {
    @JsonIgnore public int uid; // 玩家識別碼
    @JsonIgnore public int chair; // 選牌玩家位置
    @JsonIgnore public int reason; // 離開原因

    public StcUserLeave(int uid, int chair, int reason){
        super(StcUserLeave.class.getCanonicalName());
        this.uid = uid;
        this.chair = chair;
        this.reason = reason;
        ((ObjectNode)super.data).put("uid", uid);
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("reason", reason);
    }
}
