package org.lsj.gs.math.games.zjh_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 玩家選牌訊息
public class StcSelectZjhJava extends AbstractStcMessageZjhJava {
    @JsonIgnore public int chair; // 選牌玩家位置
    @JsonIgnore public int[] cards; // 玩家手牌
    @JsonIgnore public int[] lifts; // 提牌
    @JsonIgnore public int type; // 玩家牌型

    public StcSelectZjhJava(int chair,
                            int[] cards,
                            int[] lifts,
                            int type){
        super(StcSelectZjhJava.class.getCanonicalName());
        this.chair = chair;
        this.cards = cards;
        this.lifts = lifts;
        this.type = type;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("lifts").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(lifts));
        ((ObjectNode)super.data).put("type", type);
    }
}
