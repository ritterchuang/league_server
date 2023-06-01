package org.lsj.gs.math.games.tbnn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 玩家選牌訊息
public class StcSelectTbnnJava extends AbstractStcMessageTbnnJava {
    @JsonIgnore public int chair; // 選牌玩家位置
    @JsonIgnore public int[] cards; // 玩家手牌
    @JsonIgnore public int[] lifts; // 提牌
    @JsonIgnore public int nType; // 玩家牌型
    @JsonIgnore public boolean isNiu; // 是否有牛

    public StcSelectTbnnJava(int chair,
                             int[] cards,
                             int[] lifts,
                             int nType,
                             boolean isNiu){
        super(StcSelectTbnnJava.class.getCanonicalName());
        this.chair = chair;
        this.cards = cards;
        this.lifts = lifts;
        this.nType = nType;
        this.isNiu = isNiu;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("lifts").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(lifts));
        ((ObjectNode)super.data).put("nType", nType);
        ((ObjectNode)super.data).put("isNiu", isNiu);
    }
}
