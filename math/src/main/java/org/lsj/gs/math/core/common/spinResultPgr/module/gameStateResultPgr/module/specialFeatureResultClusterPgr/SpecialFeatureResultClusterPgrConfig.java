package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;

// 特殊事件結果集合包裝者設定
public class SpecialFeatureResultClusterPgrConfig {
    private final ClientSymbolHitConfig clientSymbolHitConfig; // 客端打擊設定

    public SpecialFeatureResultClusterPgrConfig(ClientSymbolHitConfig clientSymbolHitConfig) {
        this.clientSymbolHitConfig = clientSymbolHitConfig;
    }

    public ClientSymbolHitConfig getClientSymbolHitConfig() {
        return clientSymbolHitConfig;
    }
}
