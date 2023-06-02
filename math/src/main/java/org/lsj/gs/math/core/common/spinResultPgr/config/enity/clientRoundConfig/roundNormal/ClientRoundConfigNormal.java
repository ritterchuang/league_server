package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundNormal;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.ClientRoundConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端局設定 一般
public class ClientRoundConfigNormal extends ClientRoundConfig {
    private final ConstMathSlot.RoundNormalGameType roundNormalGameType; // 一般局 遊戲類型
    private final ClientScreenConfig clientScreenConfig; // 客端畫面設定
    private final ClientSymbolHitConfig clientSymbolHitConfig; // 客端打擊設定

    public ClientRoundConfigNormal(ConstMathSlot.RoundNormalGameType roundNormalGameType, ClientScreenConfig clientScreenConfig, ClientSymbolHitConfig clientSymbolHitConfig) {
        this.roundNormalGameType = roundNormalGameType;
        this.clientScreenConfig = clientScreenConfig;
        this.clientSymbolHitConfig = clientSymbolHitConfig;
    }

    public ConstMathSlot.RoundNormalGameType getRoundNormalGameType() {
        return roundNormalGameType;
    }

    public ClientScreenConfig getClientScreenConfig() {
        return clientScreenConfig;
    }

    public ClientSymbolHitConfig getClientSymbolHitConfig() {
        return clientSymbolHitConfig;
    }
}
