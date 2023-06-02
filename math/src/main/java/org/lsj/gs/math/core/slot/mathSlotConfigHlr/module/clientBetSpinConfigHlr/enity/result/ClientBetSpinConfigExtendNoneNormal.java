package org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.CreditBetBox;

import java.util.List;

// 客端押注玩法額外設定一般
public class ClientBetSpinConfigExtendNoneNormal extends ClientBetSpinConfigExtend {
    private final List<CreditBetBox> creditBetBoxList; // 押注分數成本列表

    public ClientBetSpinConfigExtendNoneNormal(List<CreditBetBox> creditBetBoxList) {
        this.creditBetBoxList = creditBetBoxList;
    }

    public List<CreditBetBox> getCreditBetBoxList() {
        return creditBetBoxList;
    }
}
