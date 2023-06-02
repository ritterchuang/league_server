package org.lsj.gs.math.games.qznn_k4z_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.data.StcSettlementDataQznnK4zJava;
import org.lsj.utils.JsonUtil;

// 結算得分訊息
public class StcSettlementQznnK4zJava extends AbstractStcMessageQznnK4zJava {
    @JsonIgnore public StcSettlementDataQznnK4zJava[] scores; // 所有玩家結算得分; 長度為玩家數

    public StcSettlementQznnK4zJava(StcSettlementDataQznnK4zJava[] scores){
        super(StcSettlementQznnK4zJava.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
