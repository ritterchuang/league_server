package org.lsj.gs.math.games.tbnn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 斷線重連訊息
public class StcCutReturnTbnnJava extends AbstractStcMessageTbnnJava {
    // 共用參數
    @JsonIgnore public int stage; // 遊戲狀態
    @JsonIgnore public int leftTime; // 剩餘時間
    @JsonIgnore public int[] rates; // 各階段可執行倍數

    // 下注階段
    @JsonIgnore public int[] betRates; // 所有玩家下注倍數 [-1, -1, -1, -1]

    // 發牌階段
    @JsonIgnore public int[][] cards; // 玩家手牌  [[], [], [], []]

    // 選牌階段
    @JsonIgnore public int[][] lifts; // 所有玩家提起的牌
    @JsonIgnore public int[][] select; // 所有玩家選牌狀態
    @JsonIgnore public int maxtype; // 最大牌型

    // 比牌階段
    @JsonIgnore public int[] types; // 所有玩家牌型
    @JsonIgnore public double[] scores; // 所有玩家淨利

    public StcCutReturnTbnnJava(int stage,
                                int leftTime,
                                int[] rates,
                                int[] betRates,
                                int[][] cards,
                                int[][] lifts,
                                int[][] select,
                                int maxtype,
                                int[] types,
                                double[] scores) {
        super(StcCutReturnTbnnJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.rates = rates;
        this.betRates = betRates;
        this.cards = cards;
        this.lifts = lifts;
        this.select = select;
        this.maxtype = maxtype;
        this.types = types;
        this.scores = scores;
        ((ObjectNode)super.data).put("stage", stage);
        ((ObjectNode)super.data).put("leftTime", leftTime);
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
        ((ObjectNode)super.data).putArray("betRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(betRates));
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("lifts").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(lifts));
        ((ObjectNode)super.data).putArray("select").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(select));
        ((ObjectNode)super.data).put("maxtype", maxtype);
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
