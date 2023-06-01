package org.lsj.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 發牌訊息
public class StcDealCardSgJava extends AbstractStcMessageSgJava {
    @JsonIgnore public int[] cards; // 卡牌編碼

    public StcDealCardSgJava(int[] cards){
        super(StcDealCardSgJava.class.getCanonicalName());
        this.cards = cards;
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
    }
}
