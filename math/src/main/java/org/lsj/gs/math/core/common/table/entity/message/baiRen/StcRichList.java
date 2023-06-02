package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;
import org.lsj.utils.JsonUtil;

// 榜單訊息
public class StcRichList extends AbstractStcMessageCore {
    @JsonIgnore public StcRichData[] lst; // 榜單

    public StcRichList(StcRichData[] lst) {
        super(StcRichList.class.getCanonicalName());
        this.lst = lst;
        ((ObjectNode)super.data).putArray("lst").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(lst));
    }
}
