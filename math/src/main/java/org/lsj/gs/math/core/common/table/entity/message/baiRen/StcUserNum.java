package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;

// 玩家人數消息
public class StcUserNum extends AbstractStcMessageCore {
    @JsonIgnore public int num; // 玩家人數

    public StcUserNum(int num){
        super(StcUserNum.class.getCanonicalName());
        this.num = num;
        ((ObjectNode)super.data).put("num", num);
    }
}
