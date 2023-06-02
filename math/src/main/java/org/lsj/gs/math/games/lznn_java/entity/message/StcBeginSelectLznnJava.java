package org.lsj.gs.math.games.lznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 開始選牌訊息
public class StcBeginSelectLznnJava extends AbstractStcMessageLznnJava {
    @JsonIgnore public double time; // 選牌時間

    public StcBeginSelectLznnJava(double time){
        super(StcBeginSelectLznnJava.class.getCanonicalName());
        this.time = time;
        ((ObjectNode)super.data).put("time", time);
    }
}
