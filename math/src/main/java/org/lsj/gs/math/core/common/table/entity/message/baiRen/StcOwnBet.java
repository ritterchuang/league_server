package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;
import org.lsj.utils.JsonUtil;

// 真人下注訊息
public class StcOwnBet extends AbstractStcMessageCore {
    @JsonIgnore public int area; // 區域識別碼
    @JsonIgnore public int code; // 狀態碼; 0: 成功; 1: 下注限紅;
    @JsonIgnore public int[] bets; // 真人所有押注區域的下注金額

    public StcOwnBet(int area, int code, int[] bets) {
        super(StcOwnBet.class.getCanonicalName());
        this.area = area;
        this.code = code;
        this.bets = bets;
        ((ObjectNode)super.data).put("area", area);
        ((ObjectNode)super.data).put("code", code);
        ((ObjectNode)super.data).putArray("bets").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(bets));
    }
}
