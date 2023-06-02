package org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.IClientBetSpinConfigHlr;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.config.ClientBetSpinConfigHlrConfig;

// 客端押注玩法設定處理者工廠
public class ClientBetSpinConfigHlrFactory {

    // 創建客端押注玩法設定處理者 TODO Default
    public IClientBetSpinConfigHlr create(ClientBetSpinConfigHlrConfig clientBetSpinConfigHlrConfig, ITableUtil tableUtil) {
        switch (clientBetSpinConfigHlrConfig.getBetSpinConfig().getBetSpinType()) {
            default:  return new ClientBetSpinConfigHlrNoneNormal(clientBetSpinConfigHlrConfig, tableUtil);
        }
    }
}
