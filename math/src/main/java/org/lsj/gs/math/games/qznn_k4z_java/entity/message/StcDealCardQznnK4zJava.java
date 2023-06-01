package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 發牌訊息
public class StcDealCardQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public int[] cards; // 卡牌編碼

    public StcDealCardQznnK4zJava(int[] cards){
        super(StcDealCardQznnK4zJava.class.getCanonicalName());
        this.cards = cards;
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
    }
}
