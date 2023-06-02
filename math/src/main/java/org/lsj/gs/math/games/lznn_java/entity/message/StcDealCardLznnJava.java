package org.lsj.gs.math.games.lznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 發牌訊息
public class StcDealCardLznnJava extends AbstractStcMessageLznnJava {
    @JsonIgnore public int[] cards; // 卡牌編碼

    public StcDealCardLznnJava(int[] cards){
        super(StcDealCardLznnJava.class.getCanonicalName());
        this.cards = cards;
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
    }
}
