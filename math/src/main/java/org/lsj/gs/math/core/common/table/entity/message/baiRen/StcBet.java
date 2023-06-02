package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;
import org.lsj.utils.JsonUtil;

// 玩家下注訊息
public class StcBet extends AbstractStcMessageCore {
    @JsonIgnore public int uid; // 玩家識別碼
    @JsonIgnore public int area; // 區域識別碼
    @JsonIgnore public int chips; // 押注金額
    @JsonIgnore public int[] betAreas; // 所有押注區域
    @JsonIgnore public double money; // 下注後的餘額

    public StcBet(int uid, int area, int chips, int[] betAreas, double money) {
        super(StcBet.class.getCanonicalName());
        this.uid = uid;
        this.area = area;
        this.chips = chips;
        this.betAreas = betAreas;
        this.money = money;
        ((ObjectNode)super.data).put("uid", uid);
        ((ObjectNode)super.data).put("area", area);
        ((ObjectNode)super.data).put("chips", chips);
        ((ObjectNode)super.data).putArray("betAreas").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(betAreas));
        ((ObjectNode)super.data).put("money", money);
    }
}
