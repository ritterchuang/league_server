package com.lx.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lx.utils.JsonUtil;

// 搶莊結果訊息
public class StcVieResultSgJava extends AbstractStcMessageSgJava {
    @JsonIgnore public int banker; // 莊家位置
    @JsonIgnore public int bankerRate; // 莊家倍數
    @JsonIgnore public int[] viers; // 競爭莊家位置

    public StcVieResultSgJava(int banker, int bankerRate, int[] viers) {
        super(StcVieResultSgJava.class.getCanonicalName());
        this.banker = banker;
        this.bankerRate = bankerRate;
        this.viers = viers;
        ((ObjectNode)super.data).put("banker", banker);
        ((ObjectNode)super.data).put("bankerRate", bankerRate);
        ((ObjectNode)super.data).putArray("viers").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(viers));
    }
}
