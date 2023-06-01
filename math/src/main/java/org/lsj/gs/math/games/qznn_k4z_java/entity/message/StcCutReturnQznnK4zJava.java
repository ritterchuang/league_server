package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 斷線重連訊息
public class StcCutReturnQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    // 共用參數
    @JsonIgnore public int stage; // 遊戲狀態
    @JsonIgnore public int leftTime; // 剩餘時間
    @JsonIgnore public int[] rates; // 各階段可執行倍數

    // 搶莊階段
    @JsonIgnore public int banker; // 莊家
    @JsonIgnore public int bankerRate; // 莊家倍數
    @JsonIgnore public int maxRate; // 最大搶莊倍數
    @JsonIgnore public int[] vieRates; // 所有玩家搶莊倍數 [3, 0, 2, 2]

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

    public StcCutReturnQznnK4zJava(int stage,
                                   int leftTime,
                                   int[] rates,
                                   int banker,
                                   int bankerRate,
                                   int maxRate,
                                   int[] vieRates,
                                   int[] betRates,
                                   int[][] cards,
                                   int[][] lifts,
                                   int[][] select,
                                   int maxtype,
                                   int[] types,
                                   double[] scores) {
        super(StcCutReturnQznnK4zJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.rates = rates;
        this.banker = banker;
        this.bankerRate = bankerRate;
        this.maxRate = maxRate;
        this.vieRates = vieRates;
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
        ((ObjectNode)super.data).put("banker", banker);
        ((ObjectNode)super.data).put("bankerRate", bankerRate);
        ((ObjectNode)super.data).put("maxRate", maxRate);
        ((ObjectNode)super.data).putArray("vieRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(vieRates));
        ((ObjectNode)super.data).putArray("betRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(betRates));
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("lifts").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(lifts));
        ((ObjectNode)super.data).putArray("select").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(select));
        ((ObjectNode)super.data).put("maxtype", maxtype);
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
