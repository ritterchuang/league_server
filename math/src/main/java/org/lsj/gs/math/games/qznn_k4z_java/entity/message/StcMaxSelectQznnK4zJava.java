package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 最大選牌(自定義)
public class StcMaxSelectQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public int chair; // 選牌玩家座位
    @JsonIgnore public int code; // 玩家牌型

    public StcMaxSelectQznnK4zJava(int chair, int code){
        super(StcMaxSelectQznnK4zJava.class.getCanonicalName());
        this.chair = chair;
        this.code = code;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("code", code);
    }
}
