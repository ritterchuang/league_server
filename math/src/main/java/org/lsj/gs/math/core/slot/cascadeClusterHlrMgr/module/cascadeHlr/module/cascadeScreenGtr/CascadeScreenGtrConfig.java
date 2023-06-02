package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeScreenGtr;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrConfig;

// 消除畫面產生器
public class CascadeScreenGtrConfig extends ScreenGtrConfig {

    public CascadeScreenGtrConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, ReelConfig reelConfig, DampConfig dampConfig) {
        super(frameConfig, symbolConfig, reelConfig, dampConfig);
    }
}
