package org.lsj.gs.math.games.zjh_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 最大選牌(自定義)
public class StcMaxSelectZjhJava extends AbstractStcMessageZjhJava {
    @JsonIgnore public int chair; // 選牌玩家座位
    @JsonIgnore public int code; // 玩家牌型

    public StcMaxSelectZjhJava(int chair, int code){
        super(StcMaxSelectZjhJava.class.getCanonicalName());
        this.chair = chair;
        this.code = code;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("code", code);
    }
}
