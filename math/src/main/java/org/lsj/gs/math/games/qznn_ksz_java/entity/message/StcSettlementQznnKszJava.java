package org.lsj.gs.math.games.qznn_ksz_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.data.StcSettlementDataQznnKszJava;
import org.lsj.utils.JsonUtil;

// 結算得分訊息
public class StcSettlementQznnKszJava extends AbstractStcMessageQznnKszJava {
    @JsonIgnore public StcSettlementDataQznnKszJava[] scores; // 所有玩家結算得分; 長度為玩家數

    public StcSettlementQznnKszJava(StcSettlementDataQznnKszJava[] scores){
        super(StcSettlementQznnKszJava.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
