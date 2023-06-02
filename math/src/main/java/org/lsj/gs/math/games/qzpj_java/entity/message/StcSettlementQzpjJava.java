package org.lsj.gs.math.games.qzpj_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.games.qzpj_java.entity.message.data.StcSettlementDataQzpjJava;
import org.lsj.utils.JsonUtil;

// 結算得分訊息
public class StcSettlementQzpjJava extends AbstractStcMessageQzpjJava {
    @JsonIgnore public StcSettlementDataQzpjJava[] scores; // 所有玩家結算得分; 長度為玩家數

    public StcSettlementQzpjJava(StcSettlementDataQzpjJava[] scores){
        super(StcSettlementQzpjJava.class.getCanonicalName());
        this.scores = scores;
        ((ObjectNode)super.data).putArray("scores").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(scores));
    }
}
