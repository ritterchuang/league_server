package com.lx.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lx.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;
import com.lx.utils.JsonUtil;

// 押注區金額訊息
public class StcArea extends AbstractStcMessageCore {
    @JsonIgnore public int[] lst; // 所有押注區域的押注金額

    public StcArea(int[] lst) {
        super(StcArea.class.getCanonicalName());
        this.lst = lst;
        ((ObjectNode)super.data).putArray("lst").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(lst));
    }
}