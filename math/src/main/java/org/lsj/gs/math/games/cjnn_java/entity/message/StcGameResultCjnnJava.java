package org.lsj.gs.math.games.cjnn_java.entity.message;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.cjnn_java.entity.data.StcGameResultDataMyScoreCjnnJava;
import org.lsj.gs.math.games.cjnn_java.entity.data.StcGameResultDataRichScoreCjnnJava;
import org.lsj.utils.JsonUtil;

// 遊戲結果訊息
public class StcGameResultCjnnJava extends AbstractStcMessageCjnnJava {
    public int[] winAreas; // 得分區域陣列
    public double winScore; // 總得分
    public StcGameResultDataMyScoreCjnnJava myScore; // 真人玩家得分
    public StcGameResultDataRichScoreCjnnJava[] richScores; // 榜單玩家得分陣列
    public StcGameResultDataRichScoreCjnnJava[] luckyStarScores; // 幸運星玩家得分陣列
    public int[][] cards; // 區域牌 [天、地、玄、黃、莊]
    public int[][] lifts; // 提牌 [天、地、玄、黃、莊]
    public int[] types; // 牌型 [天、地、玄、黃、莊]
    public int[] result; // 輸贏結果
    public double time; // 超時時間

    public StcGameResultCjnnJava(int[] winAreas,
                                 double winScore,
                                 StcGameResultDataMyScoreCjnnJava myScore,
                                 StcGameResultDataRichScoreCjnnJava[] richScores,
                                 StcGameResultDataRichScoreCjnnJava[] luckyStarScores,
                                 int[][] cards,
                                 int[][] lifts,
                                 int[] types,
                                 int[] result,
                                 double time) {
        super(StcGameResultCjnnJava.class.getCanonicalName());
        this.winAreas = winAreas;
        this.winScore = winScore;
        this.myScore = myScore;
        this.richScores = richScores;
        this.luckyStarScores = luckyStarScores;
        this.cards = cards;
        this.lifts = lifts;
        this.types = types;
        this.result = result;
        this.time = time;
        ((ObjectNode)super.data).putArray("winAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(winAreas));
        ((ObjectNode)super.data).put("winScore", winScore);
        ((ObjectNode)super.data).set("myScore", JsonUtil.getInstance().getObjectMapper().valueToTree(myScore));
        ((ObjectNode)super.data).putArray("richScores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(richScores));
        ((ObjectNode)super.data).putArray("luckyStarScores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarScores));
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("lifts").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(lifts));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).putArray("result").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(result));
        ((ObjectNode)super.data).put("time", time);
    }
}
