package com.lx.gs.math.games.qznn_ksz_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lx.utils.JsonUtil;

// 下注結果訊息
public class StcBetResultQznnKszJava extends AbstractStcMessageQznnKszJava {
    @JsonIgnore public int[] rates; // 下注結果; 長度為最大玩家數; 若座位無人或莊家時，值為-1;

    public StcBetResultQznnKszJava(int[] rates) {
        super(StcBetResultQznnKszJava.class.getCanonicalName());
        this.rates = rates;
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
    }
}
