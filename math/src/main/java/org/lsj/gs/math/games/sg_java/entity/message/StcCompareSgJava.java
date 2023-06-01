package org.lsj.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 比牌訊息
public class StcCompareSgJava extends AbstractStcMessageSgJava {
    @JsonIgnore public int[][] cards; // 所有玩家手牌; 無人給空
    @JsonIgnore public int[] types; // 所有玩家牌型; 無人則-1
    @JsonIgnore public int[] rates; // 所有玩家提牌; 無人給空
    @JsonIgnore public int[] winners; // 所有贏家
    @JsonIgnore public double[] scores; // 所有玩家得分, 無人則0

    public StcCompareSgJava(int[][] cards, int[] types, int[] rates, int[] winners, double[] scores){
        super(StcCompareSgJava.class.getCanonicalName());
        this.cards = cards;
        this.types = types;
        this.rates = rates;
        this.winners = winners;
        this.scores = scores;
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
        ((ObjectNode)super.data).putArray("winners").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(winners));
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
