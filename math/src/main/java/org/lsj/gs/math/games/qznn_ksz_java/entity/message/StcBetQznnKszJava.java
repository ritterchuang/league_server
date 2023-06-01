package org.lsj.gs.math.games.qznn_ksz_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 玩家下注訊息
public class StcBetQznnKszJava extends AbstractStcMessageQznnKszJava {
    @JsonIgnore public int chair; // 座位
    @JsonIgnore public int rate; // 倍數

    public StcBetQznnKszJava(int chair, int rate) {
        super(StcBetQznnKszJava.class.getCanonicalName());
        this.chair = chair;
        this.rate = rate;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("rate", rate);
    }
}
