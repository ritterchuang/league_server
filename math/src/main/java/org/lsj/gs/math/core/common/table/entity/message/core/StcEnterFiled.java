package org.lsj.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.data.StcEnterFieldMsgData;
import org.lsj.utils.JsonUtil;

// 進入房間消息
public class StcEnterFiled extends AbstractStcMessageCore {
    @JsonIgnore public int code; // 編碼
    @JsonIgnore public StcEnterFieldMsgData msg; // 進入房間消息

    public StcEnterFiled(int code, StcEnterFieldMsgData msg){
        super(StcEnterFiled.class.getCanonicalName());
        this.code = code;
        this.msg = msg;
        ((ObjectNode)super.data).put("code", code);
        ((ObjectNode)super.data).set("msg", JsonUtil.getInstance().getObjectMapper().valueToTree(msg));
    }
}
