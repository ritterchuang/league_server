package org.lsj.gs.math.core.common.table.entity.message.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 更新遊戲階段消息
public class StcUpdateStage extends AbstractStcMessageCore {
    @JsonIgnore public int stage; // 遊戲階段

    public StcUpdateStage(int stage){
        super(StcUpdateStage.class.getCanonicalName());
        this.stage = stage;
        ((ObjectNode)super.data).put("stage", stage);
    }
}
