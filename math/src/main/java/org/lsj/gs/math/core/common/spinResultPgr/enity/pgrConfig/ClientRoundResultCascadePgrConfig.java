package org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.SpecialFeatureResultClusterPgrConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端局結果包裝者設定 消除
public class ClientRoundResultCascadePgrConfig extends ClientRoundResultPgrConfig {
    private final ConstMathSlot.RoundCascadeGameType roundCascadeGameType; // 局消除類型
    private final ClientSymbolHitConfig clientSymbolHitConfig; // 客端畫面打擊設定
    private final CascadeClusterResultPgrConfig cascadeClusterResultPgrConfig; // 消除集合結果包裝者設定

    public ClientRoundResultCascadePgrConfig(ConstMathSlot.RoundCascadeGameType roundCascadeGameType, ClientSymbolHitConfig clientSymbolHitConfig, CascadeClusterResultPgrConfig cascadeClusterResultPgrConfig) {
        this.roundCascadeGameType = roundCascadeGameType;
        this.clientSymbolHitConfig = clientSymbolHitConfig;
        this.cascadeClusterResultPgrConfig = cascadeClusterResultPgrConfig;
    }

    public SpecialFeatureResultClusterPgrConfig createSpecialFeatureResultClusterPgrConfig() {
        return new SpecialFeatureResultClusterPgrConfig(this.clientSymbolHitConfig);
    }

    public ConstMathSlot.RoundCascadeGameType getRoundCascadeGameType() {
        return roundCascadeGameType;
    }

    public ClientSymbolHitConfig getClientSymbolHitConfig() {
        return clientSymbolHitConfig;
    }

    public CascadeClusterResultPgrConfig getCascadeClusterResultPgrConfig() {
        return cascadeClusterResultPgrConfig;
    }
}
