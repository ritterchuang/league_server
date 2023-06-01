package com.lx.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.lx.gs.math.core.common.table.entity.message.core.data.StcUserListData;
import com.lx.utils.JsonUtil;

// 玩家列表訊息
public class StcUserList extends AbstractStcMessageCore {
    @JsonIgnore public StcUserListData[] stcUserListData;

    public StcUserList(StcUserListData[] stcUserListData){
        super(StcUserList.class.getCanonicalName());
        this.stcUserListData = stcUserListData;
        super.data = JsonUtil.getInstance().getObjectMapper().createArrayNode();
        ((ArrayNode)super.data).addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(stcUserListData));
    }
}
