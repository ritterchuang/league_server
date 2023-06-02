package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;
import org.lsj.gs.math.core.common.table.entity.message.core.data.StcMoneyData;
import org.lsj.utils.JsonUtil;

// 百人玩家餘額訊息
public class StcMoney extends AbstractStcMessageCore {
    @JsonIgnore public StcMoneyData[] lst; // 真人、榜單餘額列表

    public StcMoney(StcMoneyData[] lst) {
        super(StcMoney.class.getCanonicalName());
        this.lst = lst;
        ((ObjectNode)super.data).putArray("lst").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(lst));
    }
}
