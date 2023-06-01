package org.lsj.gs.math.games.lznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 下注結果訊息
public class StcBetResultLznnJava extends AbstractStcMessageLznnJava {
    @JsonIgnore public int[] rates; // 下注結果; 長度為最大玩家數; 若座位無人或莊家時，值為-1;

    public StcBetResultLznnJava(int[] rates) {
        super(StcBetResultLznnJava.class.getCanonicalName());
        this.rates = rates;
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
    }
}
