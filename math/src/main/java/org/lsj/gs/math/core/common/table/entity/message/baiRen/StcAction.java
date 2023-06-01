package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;

// 動作訊息
public class StcAction extends AbstractStcMessageCore {
    @JsonIgnore public int action; // 動作識別碼
    @JsonIgnore public double time; // 動作時間秒數

    public StcAction(int action, double time) {
        super(StcAction.class.getCanonicalName());
        this.action = action;
        this.time = time;
        ((ObjectNode)super.data).put("action", action);
        ((ObjectNode)super.data).put("time", time);
    }
}
