package org.lsj.gs.math.games.zjh_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 玩家手牌最大牌型
public class StcMaxCardTypeZjhJava extends AbstractStcMessageZjhJava {
    @JsonIgnore public int chair; // 選牌玩家位置
    @JsonIgnore public int type; // 玩家牌型

    public StcMaxCardTypeZjhJava(int chair, int type){
        super(StcMaxCardTypeZjhJava.class.getCanonicalName());
        this.chair = chair;
        this.type = type;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("type", type);
    }
}
