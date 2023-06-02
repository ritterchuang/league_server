package org.lsj.gs.math.games.tbnn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 開始選牌訊息
public class StcBeginSelectTbnnJava extends AbstractStcMessageTbnnJava {
    @JsonIgnore public double time; // 選牌時間

    public StcBeginSelectTbnnJava(double time){
        super(StcBeginSelectTbnnJava.class.getCanonicalName());
        this.time = time;
        ((ObjectNode)super.data).put("time", time);
    }
}
