package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;

// 客端畫面打擊設定
public class ClientSymbolHitConfig {
    private final ConstPgrSlot.ClientSymbolHitType symbolHitType; // 畫面打擊設定
    private final ClientSymbolHitConfigExtend configExtend; // 客端打擊額外設定

    public ClientSymbolHitConfig(ConstPgrSlot.ClientSymbolHitType clientSymbolHitType, ClientSymbolHitConfigExtend configExtend) {
        this.symbolHitType = clientSymbolHitType;
        this.configExtend = configExtend;
    }

    public ConstPgrSlot.ClientSymbolHitType getSymbolHitType() {
        return symbolHitType;
    }

    public ClientSymbolHitConfigExtend getConfigExtend() {
        return configExtend;
    }
}
