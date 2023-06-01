package org.lsj.gs.math.games.lznn_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.lznn_java.entity.message.data.StcSettlementDataLznnJava;
import org.lsj.utils.JsonUtil;

// 結算得分訊息
public class StcSettlementLznnJava extends AbstractStcMessageLznnJava {
    @JsonIgnore public StcSettlementDataLznnJava[] scores; // 所有玩家結算得分; 長度為玩家數

    public StcSettlementLznnJava(StcSettlementDataLznnJava[] scores){
        super(StcSettlementLznnJava.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
