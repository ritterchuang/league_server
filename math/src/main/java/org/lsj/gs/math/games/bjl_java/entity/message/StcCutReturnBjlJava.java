package org.lsj.gs.math.games.bjl_java.entity.message;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 回復訊息
public class StcCutReturnBjlJava extends AbstractStcMessageBjlJava {
    public int stage; // 當前狀態
    public int[] areas; // 區域所有押注金額
    public double leftTime; // 剩餘時間秒數
    public int[] luckyStarAreas; // 幸運星區域押注金額
    public int[] bankCards; // 莊牌
    public int[] playCards; // 閒牌
    public int[] compareScore; // 分數 [閒， 莊]

    public StcCutReturnBjlJava(
            int stage,
            double leftTime,
            int[] areas,
            int[] luckyStarAreas,
            int[] bankCards,
            int[] playCards,
            int[] compareScore) {
        super(StcCutReturnBjlJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.areas = areas;
        this.luckyStarAreas = luckyStarAreas;
        this.bankCards = bankCards;
        this.playCards = playCards;
        this.compareScore = compareScore;
        ((ObjectNode)super.data).put("stage", stage);
        ((ObjectNode)super.data).put("leftTime", leftTime);
        ((ObjectNode)super.data).putArray("areas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(areas));
        ((ObjectNode)super.data).putArray("luckyStarAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarAreas));
        ((ObjectNode)super.data).putArray("bankCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(bankCards));
        ((ObjectNode)super.data).putArray("playCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(playCards));
        ((ObjectNode)super.data).putArray("compareScore").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(compareScore));
    }
}
