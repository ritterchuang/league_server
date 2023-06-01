package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeClusterConfigExtend.CascadeClusterConfigExtend;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

// 消除集合處理者設定 麻將胡了 TODO
public class CascadeClusterHlrConfigExtendMjhl extends CascadeClusterHlrConfigExtend{
    private final CascadeClusterConfigExtend configExtend; // 消除集合處理者額外設定
    private final ReelConfig reelConfig; // 輪帶表設定

    public CascadeClusterHlrConfigExtendMjhl(CascadeClusterConfigExtend configExtend, ReelConfig reelConfig) {
        this.configExtend = configExtend;
        this.reelConfig = reelConfig;
    }

    public CascadeClusterConfigExtend getConfigExtend() {
        return configExtend;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }
}
