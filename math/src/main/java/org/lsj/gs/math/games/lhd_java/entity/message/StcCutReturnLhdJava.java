package org.lsj.gs.math.games.lhd_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 回復訊息
public class StcCutReturnLhdJava extends AbstractStcMessageLhdJava {
    @JsonIgnore public int stage; // 當前狀態
    @JsonIgnore public double leftTime; // 剩餘時間秒數
    @JsonIgnore public int[] areas; // 區域所有押注金額
    @JsonIgnore public int[] luckyStarAreas; // 幸運星區域押注金額
    @JsonIgnore public int[] cards; // 卡牌; [龍, 虎]
    @JsonIgnore public int[] compareScore; // 比分; [龍, 虎]

    public StcCutReturnLhdJava(int stage, double leftTime, int[] areas, int[] luckyStarAreas, int[] cards, int[] compareScore) {
        super(StcCutReturnLhdJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.areas = areas;
        this.luckyStarAreas = luckyStarAreas;
        this.cards = cards;
        this.compareScore = compareScore;
        ((ObjectNode)super.data).put("stage", stage);
        ((ObjectNode)super.data).put("leftTime", leftTime);
        ((ObjectNode)super.data).putArray("areas").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(areas));
        ((ObjectNode)super.data).putArray("luckyStarAreas").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarAreas));
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("compareScore").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(compareScore));
    }
}
