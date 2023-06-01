package org.lsj.gs.math.games.cjnn_java.entity.message;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 回復訊息
public class StcCutReturnCjnnJava extends AbstractStcMessageCjnnJava {
    public int stage; // 當前狀態
    public double leftTime; // 剩餘時間秒數
    public int[] areas; // 區域所有押注金額
    public int[] luckyStarAreas; // 幸運星區域押注金額
    public int[] types; // 牌型 [天，地，玄，黃，莊]
    public int[][] cards; // 所有玩家牌 [天，地，玄，黃，莊]
    public int[][] lifts; // 所有玩家提起手牌 [天，地，玄，黃，莊]
    public int[] result; // 各押注區輸贏

    public StcCutReturnCjnnJava(
            int stage,
            double leftTime,
            int[] areas,
            int[] luckyStarAreas,
            int[] types,
            int[][] cards,
            int[][] lifts,
            int[] result
            ) {
        super(StcCutReturnCjnnJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.areas = areas;
        this.luckyStarAreas = luckyStarAreas;
        this.types = types;
        this.cards = cards;
        this.lifts = lifts;
        this.result = result;
        ((ObjectNode)super.data).put("stage", stage);
        ((ObjectNode)super.data).put("leftTime", leftTime);
        ((ObjectNode)super.data).putArray("areas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(areas));
        ((ObjectNode)super.data).putArray("luckyStarAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarAreas));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("lifts").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(lifts));
        ((ObjectNode)super.data).putArray("result").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(result));
    }
}
