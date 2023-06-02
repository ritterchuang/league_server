package org.lsj.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;

// 更新玩家分數消息
public class StcUpdateScores extends AbstractStcMessageCore {
    @JsonIgnore public double[] scores; // 更新玩家分數消息; 長度為最大玩家數

    public StcUpdateScores(double[] scores){
        super(StcUpdateScores.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
