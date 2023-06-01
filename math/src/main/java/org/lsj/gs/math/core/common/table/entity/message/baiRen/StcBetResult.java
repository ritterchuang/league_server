package org.lsj.gs.math.core.common.table.entity.message.baiRen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.table.entity.message.core.AbstractStcMessageCore;
import org.lsj.utils.JsonUtil;

// 下注結果訊息
public class StcBetResult extends AbstractStcMessageCore {
    @JsonIgnore public int code; // 狀態碼; 0: 成功; 非0: 失敗;
    @JsonIgnore public int area; // 區域識別碼
    @JsonIgnore public int bet; // 押注金額
    @JsonIgnore public int[] bets; // 所有押注區域
    @JsonIgnore public double money; // 玩家最新餘額
    @JsonIgnore public String msg; // 中文消息

    public StcBetResult(int code, int area, int bet, int[] bets, double money, String msg) {
        super(StcBetResult.class.getCanonicalName());
        this.code = code;
        this.area = area;
        this.bet = bet;
        this.bets = bets;
        this.money = money;
        this.msg = msg;
        ((ObjectNode)super.data).put("code", code);
        ((ObjectNode)super.data).put("area", area);
        ((ObjectNode)super.data).put("bet", bet);
        ((ObjectNode)super.data).putArray("bets").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(bets));
        ((ObjectNode)super.data).put("money", money);
        ((ObjectNode)super.data).put("msg", msg);
    }

    public StcBetResult(int code){
        super(StcBetResult.class.getCanonicalName());
        this.code = code;
        this.area = -1;
        this.bet = 0;
        this.bets = new int[]{};
        this.money = 0;
        this.msg = "";
        ((ObjectNode)super.data).put("code", code);
        ((ObjectNode)super.data).put("area", area);
        ((ObjectNode)super.data).put("bet", bet);
        ((ObjectNode)super.data).putArray("bets").addAll((ArrayNode) JsonUtil.getInstance().getObjectMapper().valueToTree(bets));
        ((ObjectNode)super.data).put("money", money);
        ((ObjectNode)super.data).put("msg", msg);
    }
}
