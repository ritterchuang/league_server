package com.lx.gs.math.games.tbnn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lx.gs.math.games.tbnn_java.entity.message.data.StcSettlementDataTbnnJava;
import com.lx.utils.JsonUtil;

// 結算得分訊息
public class StcSettlementTbnnJava extends AbstractStcMessageTbnnJava {
    @JsonIgnore public StcSettlementDataTbnnJava[] scores; // 所有玩家結算得分; 長度為玩家數

    public StcSettlementTbnnJava(StcSettlementDataTbnnJava[] scores){
        super(StcSettlementTbnnJava.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
