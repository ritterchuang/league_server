package org.lsj.gs.math.games.hhdz_java.entity.message;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 回復訊息
public class StcCutReturnHhdzJava extends AbstractStcMessageHhdzJava {
    public int stage; // 當前狀態
    public double leftTime; // 剩餘時間秒數
    public int[] winAreas; // 得分區域陣列
    public int[] areas; // 區域所有押注金額
    public int[] luckyStarAreas; // 幸運星區域押注金額
    public int[] redCards; // 紅牌
    public int[] blackCards; // 黑牌
    public int[] types; // 牌型 [紅, 黑]

    public StcCutReturnHhdzJava(
            int stage,
            double leftTime,
            int[] winAreas, int[] areas,
            int[] luckyStarAreas,
            int[] redCards,
            int[] blackCards,
            int[] types) {
        super(StcCutReturnHhdzJava.class.getCanonicalName());
        this.stage = stage;
        this.leftTime = leftTime;
        this.winAreas = winAreas;
        this.areas = areas;
        this.luckyStarAreas = luckyStarAreas;
        this.redCards = redCards;
        this.blackCards = blackCards;
        this.types = types;
        ((ObjectNode)super.data).put("stage", stage);
        ((ObjectNode)super.data).put("leftTime", leftTime);
        ((ObjectNode)super.data).putArray("winAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(winAreas));
        ((ObjectNode)super.data).putArray("areas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(areas));
        ((ObjectNode)super.data).putArray("luckyStarAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarAreas));
        ((ObjectNode)super.data).putArray("redCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(redCards));
        ((ObjectNode)super.data).putArray("blackCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(blackCards));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(types));
    }
}
