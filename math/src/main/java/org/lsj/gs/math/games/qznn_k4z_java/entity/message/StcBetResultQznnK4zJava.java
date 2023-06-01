package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 下注結果訊息
public class StcBetResultQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public int[] rates; // 下注結果; 長度為最大玩家數; 若座位無人或莊家時，值為-1;

    public StcBetResultQznnK4zJava(int[] rates) {
        super(StcBetResultQznnK4zJava.class.getCanonicalName());
        this.rates = rates;
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode)JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
    }
}
