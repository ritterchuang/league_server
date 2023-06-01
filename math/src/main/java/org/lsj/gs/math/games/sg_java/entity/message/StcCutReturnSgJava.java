package org.lsj.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 斷線重連訊息
public class StcCutReturnSgJava extends AbstractStcMessageSgJava {
    // 共用參數
    @JsonIgnore public int stage; // 遊戲狀態
    @JsonIgnore public int leftTime; // 剩餘時間

    // 搶莊階段
    @JsonIgnore public int banker; // 莊家
    @JsonIgnore public int[] robbankers; // 所有玩家搶莊倍數 [3, 0, 2, 2]

    // 下注階段
    @JsonIgnore public int[] betRates; // 所有玩家下注倍數 [-1, -1, -1, -1]

    // 發牌階段
    @JsonIgnore public int[][] cards; // 玩家手牌  [[], [], [], []]

    // 比牌階段
    @JsonIgnore public int[] types; // 所有玩家牌型

    public StcCutReturnSgJava(int stage,
                              int leftTime,
                              int banker,
                              int[] robbankers,
                              int[] betRates,
                              int[][] cards,
                              int[] types) {
        super(StcCutReturnSgJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.banker = banker;
        this.robbankers = robbankers;
        this.betRates = betRates;
        this.cards = cards;
        this.types = types;
        ((ObjectNode)super.data).put("stage", stage);
        ((ObjectNode)super.data).put("leftTime", leftTime);
        ((ObjectNode)super.data).put("banker", banker);
        ((ObjectNode)super.data).putArray("robbankers").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(robbankers));
        ((ObjectNode)super.data).putArray("betRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(betRates));
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(types));
    }
}
