package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端消除集合設定
public class ClientCascadeClusterConfig {
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 客端消除集合類型
    private final ClientCascadeClusterConfigExtend clientCascadeClusterConfigExtend; // 客端消除集合額外設定
    private final ClientCascadeConfig clientCascadeConfig; // 客端消除設定

    public ClientCascadeClusterConfig(ConstMathSlot.CascadeClusterType cascadeClusterType, ClientCascadeClusterConfigExtend clientCascadeClusterConfigExtend, ClientCascadeConfig clientCascadeConfig) {
        this.cascadeClusterType = cascadeClusterType;
        this.clientCascadeClusterConfigExtend = clientCascadeClusterConfigExtend;
        this.clientCascadeConfig = clientCascadeConfig;
    }

    public ConstMathSlot.CascadeClusterType getCascadeClusterType() {
        return cascadeClusterType;
    }

    public ClientCascadeClusterConfigExtend getClientCascadeClusterConfigExtend() {
        return clientCascadeClusterConfigExtend;
    }

    public ClientCascadeConfig getClientCascadeConfig() {
        return clientCascadeConfig;
    }
}
