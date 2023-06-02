package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;

// 消除集合處理者設定 TODO
public class CascadeClusterHlrConfig {
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 消除集合類型
    private final CascadeClusterHlrConfigExtend hlrConfigExtend;
    private final CascadeHlrConfig cascadeHlrConfig; // 消除處理者設定

    public CascadeClusterHlrConfig(
            ConstMathSlot.CascadeClusterType cascadeClusterType,
            CascadeClusterHlrConfigExtend hlrConfigExtend,
            CascadeHlrConfig cascadeHlrConfig) {
        this.cascadeClusterType = cascadeClusterType;
        this.hlrConfigExtend = hlrConfigExtend;
        this.cascadeHlrConfig = cascadeHlrConfig;
    }

    public ConstMathSlot.CascadeClusterType getCascadeClusterType() {
        return cascadeClusterType;
    }

    public CascadeClusterHlrConfigExtend getHlrConfigExtend() {
        return hlrConfigExtend;
    }

    public CascadeHlrConfig getCascadeHlrConfig() {
        return cascadeHlrConfig;
    }
}
