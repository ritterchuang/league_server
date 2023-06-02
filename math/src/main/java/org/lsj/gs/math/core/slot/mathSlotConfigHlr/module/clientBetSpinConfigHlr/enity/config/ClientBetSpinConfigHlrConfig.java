package org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.config;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfig;

// 客端押注玩法設定處理者設定
public class ClientBetSpinConfigHlrConfig {
    private final BetSpinConfig betSpinConfig; // 押注玩法設定

    public ClientBetSpinConfigHlrConfig(BetSpinConfig betSpinConfig) {
        this.betSpinConfig = betSpinConfig;
    }

    public BetSpinConfig getBetSpinConfig() {
        return betSpinConfig;
    }
}
