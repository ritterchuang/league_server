package org.lsj.gs.math.games.ebg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 發牌訊息
public class StcDealCardEbgJava extends AbstractStcMessageEbgJava {
    @JsonIgnore public int[] cards; // 卡牌編碼
    @JsonIgnore public int[] dices; // 骰子編碼

    public StcDealCardEbgJava(int[] cards, int[] dices){
        super(StcDealCardEbgJava.class.getCanonicalName());
        this.cards = cards;
        this.dices = dices;
        ((ObjectNode)super.data).putArray("cards").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(cards));
        ((ObjectNode)super.data).putArray("dices").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(dices));
    }
}
