package org.lsj.gs.math.games.zjh_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 開始選牌訊息
public class StcBeginSelectZjhJava extends AbstractStcMessageZjhJava {
    @JsonIgnore public double time; // 選牌時間

    public StcBeginSelectZjhJava(double time){
        super(StcBeginSelectZjhJava.class.getCanonicalName());
        this.time = time;
        ((ObjectNode)super.data).put("time", time);
    }
}
