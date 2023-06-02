package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeClusterConfigExtend.CascadeClusterConfigExtend;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.CascadeConfig;

// 消除集合設定
public class CascadeClusterConfig {
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 消除集合類型
    private final CascadeClusterConfigExtend cascadeClusterConfigExtend; // 消除集合額外設定
    private final CascadeConfig cascadeConfig; // 消除設定

    public CascadeClusterConfig(ConstMathSlot.CascadeClusterType cascadeClusterType, CascadeClusterConfigExtend cascadeClusterConfigExtend, CascadeConfig cascadeConfig) {
        this.cascadeClusterType = cascadeClusterType;
        this.cascadeClusterConfigExtend = cascadeClusterConfigExtend;
        this.cascadeConfig = cascadeConfig;
    }

    public ConstMathSlot.CascadeClusterType getCascadeClusterType() {
        return cascadeClusterType;
    }

    public CascadeClusterConfigExtend getCascadeClusterConfigExtend() {
        return cascadeClusterConfigExtend;
    }

    public CascadeConfig getCascadeConfig() {
        return cascadeConfig;
    }
}
