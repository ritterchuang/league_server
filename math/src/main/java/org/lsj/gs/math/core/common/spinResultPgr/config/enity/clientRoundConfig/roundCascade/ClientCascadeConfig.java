package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端消除設定
public class ClientCascadeConfig {
    private final ConstMathSlot.CascadeType cascadeType; // 消除類型
    private final ClientCascadeConfigExtend clientCascadeConfigExtend; // 客端消除額外設定
    private final ConstPgrSlot.AddSymbolOrderType addSymbolOrderType; // 新增標誌順序類型
    private final ClientScreenConfig clientScreenConfig; // 客端畫面設定
    private final ClientSymbolHitConfig clientSymbolHitConfig; // 客端打擊設定


    public ClientCascadeConfig(ConstMathSlot.CascadeType cascadeType, ClientCascadeConfigExtend clientCascadeConfigExtend, ConstPgrSlot.AddSymbolOrderType addSymbolOrderType, ClientScreenConfig clientScreenConfig, ClientSymbolHitConfig clientSymbolHitConfig) {
        this.cascadeType = cascadeType;
        this.clientCascadeConfigExtend = clientCascadeConfigExtend;
        this.addSymbolOrderType = addSymbolOrderType;
        this.clientScreenConfig = clientScreenConfig;
        this.clientSymbolHitConfig = clientSymbolHitConfig;
    }

    public ConstMathSlot.CascadeType getCascadeType() {
        return cascadeType;
    }

    public ClientCascadeConfigExtend getClientCascadeConfigExtend() {
        return clientCascadeConfigExtend;
    }

    public ConstPgrSlot.AddSymbolOrderType getAddSymbolOrderType() {
        return addSymbolOrderType;
    }

    public ClientScreenConfig getClientScreenConfig() {
        return clientScreenConfig;
    }

    public ClientSymbolHitConfig getClientSymbolHitConfig() {
        return clientSymbolHitConfig;
    }
}
