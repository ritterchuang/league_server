package com.lx.gs.math.games.lznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 玩家下注訊息
public class StcBetLznnJava extends AbstractStcMessageLznnJava {
    @JsonIgnore public int chair; // 座位
    @JsonIgnore public int rate; // 倍數

    public StcBetLznnJava(int chair, int rate) {
        super(StcBetLznnJava.class.getCanonicalName());
        this.chair = chair;
        this.rate = rate;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("rate", rate);
    }
}
