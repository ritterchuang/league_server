package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 開始選牌訊息
public class StcBeginSelectQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public int[] cards; // 末兩張卡牌編碼
    @JsonIgnore public double time; // 選牌時間
    @JsonIgnore public int[] betRates; // 所有玩家押注倍數

    public StcBeginSelectQznnK4zJava(int[] cards, double time, int[] betRates){
        super(StcBeginSelectQznnK4zJava.class.getCanonicalName());
        this.cards = cards;
        this.time = time;
        this.betRates = betRates;

        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).put("time", time);
        ((ObjectNode)super.data).putArray("betRates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(betRates));
    }
}
