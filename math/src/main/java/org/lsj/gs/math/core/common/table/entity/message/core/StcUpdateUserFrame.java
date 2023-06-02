package org.lsj.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 更新玩家數據訊息
public class StcUpdateUserFrame extends AbstractStcMessageCore {
    @JsonIgnore public int uid; // 玩家識別碼
    @JsonIgnore public double dollar; // 玩家餘額

    public StcUpdateUserFrame(int uid, double dollar){
        super(StcUpdateUserFrame.class.getCanonicalName());
        this.uid = uid;
        this.dollar = dollar;
        ((ObjectNode)super.data).put("uid", uid);
        ((ObjectNode)super.data).put("dollar", dollar);
    }
}
