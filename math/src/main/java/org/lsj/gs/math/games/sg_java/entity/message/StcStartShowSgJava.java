package org.lsj.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 開始選牌訊息
public class StcStartShowSgJava extends AbstractStcMessageSgJava {
    @JsonIgnore public double time; // 選牌時間

    public StcStartShowSgJava(double time){
        super(StcStartShowSgJava.class.getCanonicalName());
        this.time = time;

        ((ObjectNode)super.data).put("time", time);
    }
}
