package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeScreenGtr.CascadeScreenGtrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 消除處理者額外設定 超級王牌
public class CascadeHlrConfigExtendCjwp extends CascadeHlrConfigExtend{
    private final CascadeScreenGtrConfig cascadeScreenGtrConfig; // 消除畫面生產器設定
    private final SymbolConfig symbolConfig; // 標誌設定檔

    public CascadeHlrConfigExtendCjwp(CascadeScreenGtrConfig cascadeScreenGtrConfig, SymbolConfig symbolConfig) {
        this.cascadeScreenGtrConfig = cascadeScreenGtrConfig;
        this.symbolConfig = symbolConfig;
    }

    public CascadeScreenGtrConfig getCascadeScreenGtrConfig() {
        return cascadeScreenGtrConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }
}
