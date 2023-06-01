package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;

// 新增標誌包裝者設定
public class AddSymbolResultPgrConfig {
    private final ConstPgrSlot.AddSymbolOrderType addSymbolOrderType; // 新增標誌類型
    private final DampConfig dampConfig; // 破框設定

    public AddSymbolResultPgrConfig(ConstPgrSlot.AddSymbolOrderType addSymbolOrderType, DampConfig dampConfig) {
        this.addSymbolOrderType = addSymbolOrderType;
        this.dampConfig = dampConfig;
    }

    public ConstPgrSlot.AddSymbolOrderType getAddSymbolOrderType() {
        return addSymbolOrderType;
    }

    public DampConfig getDampConfig() {
        return dampConfig;
    }
}
