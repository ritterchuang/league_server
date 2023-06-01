package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 玩家下注訊息
public class StcBetQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public int chair; // 座位
    @JsonIgnore public int rate; // 倍數

    public StcBetQznnK4zJava(int chair, int rate) {
        super(StcBetQznnK4zJava.class.getCanonicalName());
        this.chair = chair;
        this.rate = rate;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("rate", rate);
    }
}
