package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;

// 新增標誌包裝者設定
public class CascadeScreenResultPgrConfig {
    private final ClientScreenConfig clientScreenConfig; // 客端畫面設定
    private final ConstPgrSlot.AddSymbolOrderType addSymbolOrderType; // 新增標誌類型
    private final DampConfig dampConfig; // 破框設定

    public CascadeScreenResultPgrConfig(ClientScreenConfig clientScreenConfig, ConstPgrSlot.AddSymbolOrderType addSymbolOrderType, DampConfig dampConfig) {
        this.clientScreenConfig = clientScreenConfig;
        this.addSymbolOrderType = addSymbolOrderType;
        this.dampConfig = dampConfig;
    }

    public AddSymbolResultPgrConfig createAddSymbolResultPgrConfig() {
        return new AddSymbolResultPgrConfig(this.addSymbolOrderType, dampConfig);
    }

    public ClientScreenConfig getClientScreenConfig() {
        return clientScreenConfig;
    }

    public ConstPgrSlot.AddSymbolOrderType getAddSymbolOrderType() {
        return addSymbolOrderType;
    }

    public DampConfig getDampConfig() {
        return dampConfig;
    }
}
