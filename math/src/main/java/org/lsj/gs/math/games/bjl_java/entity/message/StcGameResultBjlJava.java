package org.lsj.gs.math.games.bjl_java.entity.message;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.bjl_java.entity.data.StcGameResultDataMyScoreBjlJava;
import org.lsj.gs.math.games.bjl_java.entity.data.StcGameResultDataRichScoreBjlJava;
import org.lsj.utils.JsonUtil;

// 遊戲結果訊息
public class StcGameResultBjlJava extends AbstractStcMessageBjlJava {
    public int[] winAreas; // 得分區域陣列
    public int[] returnAreas; // 返分區域陣列
    public double winScore; // 總得分
    public StcGameResultDataMyScoreBjlJava myScore; // 真人玩家得分
    public StcGameResultDataRichScoreBjlJava[] richScores; // 榜單玩家得分陣列
    public StcGameResultDataRichScoreBjlJava[] luckyStarScores; // 幸運星玩家得分陣列
    public int[] playerCards; // 閒牌
    public int[] bankerCards; // 庄牌
    public int[][] compareScore; // <閒、庄>依序開牌 有三張牌就分別開  [0, 0+1, 0+1+2]
    public double time; // 超時時間

    public StcGameResultBjlJava(int[] winAreas, int[] returnAreas,
                                double winScore,
                                StcGameResultDataMyScoreBjlJava myScore,
                                StcGameResultDataRichScoreBjlJava[] richScores,
                                StcGameResultDataRichScoreBjlJava[] luckyStarScores,
                                int[] playerCards,
                                int[] bankerCards,
                                int[][] compareScore,
                                double time) {
        super(StcGameResultBjlJava.class.getCanonicalName());
        this.winAreas = winAreas;
        this.returnAreas = returnAreas;
        this.winScore = winScore;
        this.myScore = myScore;
        this.richScores = richScores;
        this.luckyStarScores = luckyStarScores;
        this.playerCards = playerCards;
        this.bankerCards = bankerCards;
        this.compareScore = compareScore;
        this.time = time;
        ((ObjectNode)super.data).putArray("winAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(winAreas));
        ((ObjectNode)super.data).putArray("returnAreas").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(returnAreas));
        ((ObjectNode)super.data).put("winScore", winScore);
        ((ObjectNode)super.data).set("myScore", JsonUtil.getInstance().getObjectMapper().valueToTree(myScore));
        ((ObjectNode)super.data).putArray("richScores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(richScores));
        ((ObjectNode)super.data).putArray("luckyStarScores").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(luckyStarScores));
        ((ObjectNode)super.data).putArray("playerCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(playerCards));
        ((ObjectNode)super.data).putArray("bankerCards").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(bankerCards));
        ((ObjectNode)super.data).putArray("compareScore").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(compareScore));
        ((ObjectNode)super.data).put("time", time);
    }
}
