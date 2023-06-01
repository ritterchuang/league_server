package org.lsj.gs.math.games.sg_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.sg_java.entity.message.data.StcSettlementDataSgJava;
import org.lsj.utils.JsonUtil;

// 結算得分訊息
public class StcSettlementSgJava extends AbstractStcMessageSgJava {
    @JsonIgnore public StcSettlementDataSgJava[] scores; // 所有玩家結算得分; 長度為玩家數

    public StcSettlementSgJava(StcSettlementDataSgJava[] scores){
        super(StcSettlementSgJava.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
