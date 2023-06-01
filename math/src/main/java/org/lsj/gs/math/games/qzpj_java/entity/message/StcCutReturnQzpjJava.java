package org.lsj.gs.math.games.qzpj_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 斷線重連訊息
public class StcCutReturnQzpjJava extends AbstractStcMessageQzpjJava {
    // 共用參數
    @JsonIgnore public int stage; // 遊戲狀態
    @JsonIgnore public int leftTime; // 剩餘時間
    @JsonIgnore public int[] opRates; // 各階段可執行倍數

    // 搶莊階段
    @JsonIgnore public int banker; // 莊家
    @JsonIgnore public int bankerRate; // 莊家倍數
    @JsonIgnore public int[] vieRates; // 所有玩家搶莊倍數 [3, 0, 2, 2]

    // 下注階段
    @JsonIgnore public int[] betRates; // 所有玩家下注倍數 [-1, -1, -1, -1]
    @JsonIgnore public double ownBet; // 真人下注額
    @JsonIgnore public double totalBet; // 牌桌下注總和

    // 發牌階段
    @JsonIgnore public int[][] cards; // 玩家手牌  [[], [], [], []]

    // 比牌階段
    @JsonIgnore public int[] types; // 所有玩家牌型
    @JsonIgnore public double[] scores; // 所有玩家淨利

    public StcCutReturnQzpjJava(int stage,
                                int leftTime,
                                int[] opRates,
                                int banker,
                                int bankerRate,
                                int[] vieRates,
                                int[] betRates,
                                double ownBet,
                                double totalBet,
                                int[][] cards,
                                int[] types,
                                double[] scores) {
        super(StcCutReturnQzpjJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.opRates = opRates;
        this.banker = banker;
        this.bankerRate = bankerRate;
        this.vieRates = vieRates;
        this.betRates = betRates;
        this.ownBet = ownBet;
        this.totalBet = totalBet;
        this.cards = cards;
        this.types = types;
        this.scores = scores;
        ((ObjectNode)super.data).put("stage", stage);
        ((ObjectNode)super.data).put("leftTime", leftTime);
        ((ObjectNode)super.data).putArray("opRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(opRates));
        ((ObjectNode)super.data).put("banker", banker);
        ((ObjectNode)super.data).put("bankerRate", bankerRate);
        ((ObjectNode)super.data).putArray("vieRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(vieRates));
        ((ObjectNode)super.data).putArray("betRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(betRates));
        ((ObjectNode)super.data).put("ownBet", ownBet);
        ((ObjectNode)super.data).put("totalBet", totalBet);
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
