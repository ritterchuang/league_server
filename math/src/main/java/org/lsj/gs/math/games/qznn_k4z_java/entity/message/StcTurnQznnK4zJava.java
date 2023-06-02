package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 操作位置訊息
public class StcTurnQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public int oper; // 操作定義; 1: 搶莊, 2: 下注
    @JsonIgnore public double time; // 可操作時間
    @JsonIgnore public int maxRate; // 最大倍数
    @JsonIgnore public int[] rates; // 玩家搶莊倍數列表

    public StcTurnQznnK4zJava(int oper,
                              double time,
                              int maxRate,
                              int[] rates){
        super(StcTurnQznnK4zJava.class.getCanonicalName());
        this.oper = oper;
        this.time = time;
        this.maxRate = maxRate;
        this.rates = rates;
        ((ObjectNode)super.data).put("oper", oper);
        ((ObjectNode)super.data).put("time", time);
        ((ObjectNode)super.data).put("maxRate", maxRate);
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
    }
}
