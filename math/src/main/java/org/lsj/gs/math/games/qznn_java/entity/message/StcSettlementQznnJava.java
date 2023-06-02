package org.lsj.gs.math.games.qznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.qznn_java.entity.message.data.StcSettlementDataQznnJava;
import org.lsj.utils.JsonUtil;

// 結算得分訊息
public class StcSettlementQznnJava extends AbstractStcMessageQznnJava {
    @JsonIgnore public StcSettlementDataQznnJava[] scores; // 所有玩家結算得分; 長度為玩家數

    public StcSettlementQznnJava(StcSettlementDataQznnJava[] scores){
        super(StcSettlementQznnJava.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
