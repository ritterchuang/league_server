package org.lsj.gs.math.games.hhdz_java.entity.message;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.hhdz_java.entity.data.StcGameResultDataMyScoreHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.data.StcGameResultDataRichScoreHhdzJava;
import org.lsj.utils.JsonUtil;

// 遊戲結果訊息
public class StcGameResultHhdzJava extends AbstractStcMessageHhdzJava {
    public int[] winAreas; // 得分區域陣列
    public double winScore; // 總得分
    public StcGameResultDataMyScoreHhdzJava myScore; // 真人玩家得分
    public StcGameResultDataRichScoreHhdzJava[] richScores; // 榜單玩家得分陣列
    public StcGameResultDataRichScoreHhdzJava[] luckyStarScores; // 幸運星玩家得分陣列
    public int[] redCards; // 紅牌
    public int[] blackCards; // 黑牌
    public int[] types; // 牌型 [紅, 黑]
    public double time; // 超時時間

    public StcGameResultHhdzJava(int[] winAreas, double winScore,
                                 StcGameResultDataMyScoreHhdzJava myScore,
                                 StcGameResultDataRichScoreHhdzJava[] richScores,
                                 StcGameResultDataRichScoreHhdzJava[] luckyStarScores,
                                 int[] redCards,
                                 int[] blackCards,
                                 int[] types,
                                 double time) {
        super(StcGameResultHhdzJava.class.getCanonicalName());
        this.winAreas = winAreas;
        this.winScore = winScore;
        this.myScore = myScore;
        this.richScores = richScores;
        this.luckyStarScores = luckyStarScores;
        this.blackCards = blackCards;
        this.redCards = redCards;
        this.types = types;
        this.time = time;
        ((ObjectNode)super.data).putArray("winAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(winAreas));
        ((ObjectNode)super.data).put("winScore", winScore);
        ((ObjectNode)super.data).set("myScore", JsonUtil.getInstance().getObjectMapper().valueToTree(myScore));
        ((ObjectNode)super.data).putArray("richScores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(richScores));
        ((ObjectNode)super.data).putArray("luckyStarScores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarScores));
        ((ObjectNode)super.data).putArray("redCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(redCards));
        ((ObjectNode)super.data).putArray("blackCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(blackCards));
        ((ObjectNode)super.data).putArray("types").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(types));
        ((ObjectNode)super.data).put("time", time);
    }
}
