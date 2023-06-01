package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;

// 更新玩家訊息
public class StcUpdateUserGame extends AbstractStcMessageCore {
    @JsonIgnore public int uid; // 玩家識別碼
    @JsonIgnore public int chair; // 位置
    @JsonIgnore public int role; // 角色
    @JsonIgnore public int state; // 狀態

    public StcUpdateUserGame(int uid, int chair, int role, int state) {
        super(StcUpdateUserGame.class.getCanonicalName());
        this.uid = uid;
        this.chair = chair;
        this.role = role;
        this.state = state;
        ((ObjectNode)super.data).put("uid", uid);
        ((ObjectNode)super.data).put("chair", chair);
        ((ObjectNode)super.data).put("role", role);
        ((ObjectNode)super.data).put("state", state);
    }
}
