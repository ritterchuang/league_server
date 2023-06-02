package org.lsj.gs.math.games.hhdz_java.entity.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;

// 更新玩家訊息
public class StcUpdateUserHhdzJava extends AbstractStcMessageHhdzJava {
    @JsonIgnore public int uid; // 玩家識別碼
    @JsonIgnore public int chair; // 位置
    @JsonIgnore public int role; // 角色
    @JsonIgnore public int state; // 狀態

    public StcUpdateUserHhdzJava(int uid, int chair, int role, int state) {
        super(StcUpdateUserHhdzJava.class.getCanonicalName());
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
