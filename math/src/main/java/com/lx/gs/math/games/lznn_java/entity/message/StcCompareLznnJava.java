package com.lx.gs.math.games.lznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lx.utils.JsonUtil;

// 比牌訊息
public class StcCompareLznnJava extends AbstractStcMessageLznnJava {
    @JsonIgnore public int[][] cards; // 所有玩家手牌; 無人給空
    @JsonIgnore public int[][] lifts; // 所有玩家提牌; 無人給空
    @JsonIgnore public int[] types; // 所有玩家牌型; 無人則-1
    @JsonIgnore public double[] scores; // 所有玩家得分, 無人則0

    public StcCompareLznnJava(int[][] cards, int[][] lifts, int[] types, double[] scores){
        super(StcCompareLznnJava.class.getCanonicalName());
        this.cards = cards;
        this.lifts = lifts;
        this.types = types;
        this.scores = scores;
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("lifts").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(lifts));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
