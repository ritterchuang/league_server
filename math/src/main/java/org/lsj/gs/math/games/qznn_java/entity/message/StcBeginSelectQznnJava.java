package org.lsj.gs.math.games.qznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 開始選牌訊息
public class StcBeginSelectQznnJava extends AbstractStcMessageQznnJava {
    @JsonIgnore public double time; // 選牌時間

    public StcBeginSelectQznnJava(double time){
        super(StcBeginSelectQznnJava.class.getCanonicalName());
        this.time = time;
        ((ObjectNode)super.data).put("time", time);
    }
}
