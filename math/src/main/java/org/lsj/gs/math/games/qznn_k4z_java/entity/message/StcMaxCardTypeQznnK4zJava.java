package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 玩家手牌最大牌型
public class StcMaxCardTypeQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public int chair; // 選牌玩家位置
    @JsonIgnore public int type; // 玩家牌型

    public StcMaxCardTypeQznnK4zJava(int chair, int type){
        super(StcMaxCardTypeQznnK4zJava.class.getCanonicalName());
        this.chair = chair;
        this.type = type;
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("type", type);
    }
}
