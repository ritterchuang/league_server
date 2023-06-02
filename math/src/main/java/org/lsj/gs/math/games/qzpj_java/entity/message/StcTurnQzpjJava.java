package org.lsj.gs.math.games.qzpj_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 操作位置訊息
public class StcTurnQzpjJava extends AbstractStcMessageQzpjJava {
    @JsonIgnore public int oper; // 操作定義; 1: 搶莊, 2: 下注
    @JsonIgnore public double time; // 可操作時間
    @JsonIgnore public int[] rates; // 玩家搶莊倍數列表

    public StcTurnQzpjJava(int oper,
                           double time,
                           int[] rates){
        super(StcTurnQzpjJava.class.getCanonicalName());
        this.oper = oper;
        this.time = time;
        this.rates = rates;
        ((ObjectNode)super.data).put("oper", oper);
        ((ObjectNode)super.data).put("time", time);
        ((ObjectNode)super.data).putArray("rates").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(rates));
    }
}
