package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.ClientRoundConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端局設定 消除
public class ClientRoundConfigCascade extends ClientRoundConfig {
    private final ConstMathSlot.RoundCascadeGameType roundCascadeGameType; // 局消除遊戲類型
    private final ClientSymbolHitConfig clientSymbolHitConfig; // 客端打擊設定
    private final ClientCascadeClusterConfig clientCascadeClusterConfig; // 客端消除集合設定

    public ClientRoundConfigCascade(ConstMathSlot.RoundCascadeGameType roundCascadeGameType, ClientSymbolHitConfig clientSymbolHitConfig, ClientCascadeClusterConfig clientCascadeClusterConfig) {
        this.roundCascadeGameType = roundCascadeGameType;
        this.clientSymbolHitConfig = clientSymbolHitConfig;
        this.clientCascadeClusterConfig = clientCascadeClusterConfig;
    }

    public ConstMathSlot.RoundCascadeGameType getRoundCascadeGameType() {
        return roundCascadeGameType;
    }

    public ClientSymbolHitConfig getClientSymbolHitConfig() {
        return clientSymbolHitConfig;
    }

    public ClientCascadeClusterConfig getClientCascadeClusterConfig() {
        return clientCascadeClusterConfig;
    }
}
