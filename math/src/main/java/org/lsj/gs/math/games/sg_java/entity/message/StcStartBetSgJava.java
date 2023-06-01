package org.lsj.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 開始下注
public class StcStartBetSgJava extends AbstractStcMessageSgJava {
    @JsonIgnore public double time; // 可操作時間
    @JsonIgnore public int[] rates; // 玩家搶莊倍數列表

    public StcStartBetSgJava(double time,
                             int[] rates){
        super(StcStartBetSgJava.class.getCanonicalName());
        this.time = time;
        this.rates = rates;
        ((ObjectNode)super.data).put("time", time);
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
    }
}
