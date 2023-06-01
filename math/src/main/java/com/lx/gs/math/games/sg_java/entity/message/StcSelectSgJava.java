package com.lx.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lx.utils.JsonUtil;

// 玩家選牌訊息
public class StcSelectSgJava extends AbstractStcMessageSgJava {
    @JsonIgnore public int chair; // 選牌玩家位置
    @JsonIgnore public int[] cards; // 玩家手牌
    @JsonIgnore public int type; // 玩家牌型

    public StcSelectSgJava(int chair,
                           int[] cards,
                           int type){
        super(StcSelectSgJava.class.getCanonicalName());
        this.chair = chair;
        this.cards = cards;
        this.type = type;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).put("type", type);
    }
}
