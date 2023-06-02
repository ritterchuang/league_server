package org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade.ClientCascadeClusterConfigExtend;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 消除集合結果包裝者設定
public class CascadeClusterResultPgrConfig {
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 消除集合類型
    private final ClientCascadeClusterConfigExtend clientCascadeClusterConfigExtend; // 消除集合額外設定
    private final CascadeResultPgrConfig cascadeResultPgrConfig; // 消除結果包裝者設定

    public CascadeClusterResultPgrConfig(ConstMathSlot.CascadeClusterType cascadeClusterType, ClientCascadeClusterConfigExtend clientCascadeClusterConfigExtend, CascadeResultPgrConfig cascadeResultPgrConfig) {
        this.cascadeClusterType = cascadeClusterType;
        this.clientCascadeClusterConfigExtend = clientCascadeClusterConfigExtend;
        this.cascadeResultPgrConfig = cascadeResultPgrConfig;
    }

    public ConstMathSlot.CascadeClusterType getCascadeClusterType() {
        return cascadeClusterType;
    }

    public ClientCascadeClusterConfigExtend getClientCascadeClusterConfigExtend() {
        return clientCascadeClusterConfigExtend;
    }

    public CascadeResultPgrConfig getCascadeResultPgrConfig() {
        return cascadeResultPgrConfig;
    }
}
