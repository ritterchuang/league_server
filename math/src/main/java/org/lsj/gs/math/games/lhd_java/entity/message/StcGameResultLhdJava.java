package org.lsj.gs.math.games.lhd_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.lhd_java.entity.data.StcGameResultDataMyScoreLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.data.StcGameResultDataRichScoreLhdJava;
import org.lsj.utils.JsonUtil;

// 遊戲結果訊息
public class StcGameResultLhdJava extends AbstractStcMessageLhdJava {
    @JsonIgnore public int winAreaID; // 得分區域
    @JsonIgnore public double winScore; // 總得分
    @JsonIgnore public StcGameResultDataMyScoreLhdJava myScore; // 真人玩家得分
    @JsonIgnore public StcGameResultDataRichScoreLhdJava[] richScores; // 榜單玩家得分陣列
    @JsonIgnore public StcGameResultDataRichScoreLhdJava[] luckyStarScores; // 幸運星玩家得分陣列
    @JsonIgnore public int[] cards; // 卡牌; [龍, 虎]
    @JsonIgnore public int[] compareScore; // 比分; [龍, 虎]
    @JsonIgnore public double time; // 超時時間

    public StcGameResultLhdJava(int winAreaID,
                                double winScore,
                                StcGameResultDataMyScoreLhdJava myScore,
                                StcGameResultDataRichScoreLhdJava[] richScores,
                                StcGameResultDataRichScoreLhdJava[] luckyStarScores,
                                int[] cards,
                                int[] compareScore,
                                double time) {
        super(StcGameResultLhdJava.class.getCanonicalName());
        this.winAreaID = winAreaID;
        this.winScore = winScore;
        this.myScore = myScore;
        this.richScores = richScores;
        this.luckyStarScores = luckyStarScores;
        this.cards = cards;
        this.compareScore = compareScore;
        this.time = time;
        ((ObjectNode)super.data).put("winAreaID", winAreaID);
        ((ObjectNode)super.data).put("winScore", winScore);
        ((ObjectNode)super.data).set("myScore", JsonUtil.getInstance().getObjectMapper().valueToTree(myScore));
        ((ObjectNode)super.data).putArray("richScores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(richScores));
        ((ObjectNode)super.data).putArray("luckyStarScores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarScores));
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("compareScore").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(compareScore));
        ((ObjectNode)super.data).put("time", time);
    }
}
