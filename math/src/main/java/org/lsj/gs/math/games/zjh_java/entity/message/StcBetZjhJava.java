package org.lsj.gs.math.games.zjh_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 玩家下注訊息
public class StcBetZjhJava extends AbstractStcMessageZjhJava {
    @JsonIgnore public int chair; // 座位
    @JsonIgnore public int rate; // 倍數

    public StcBetZjhJava(int chair, int rate) {
        super(StcBetZjhJava.class.getCanonicalName());
        this.chair = chair;
        this.rate = rate;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("rate", rate);
    }
}
