package org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.roundCascade;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;

// 客端消除額外設定 超級王牌
public class ClientCascadeConfigExtendCjwp extends ClientCascadeConfigExtend{
    private final ClientScreenConfig cascadeClientScreenConfig; // 消除畫面設定

    public ClientCascadeConfigExtendCjwp(ClientScreenConfig cascadeClientScreenConfig) {
        this.cascadeClientScreenConfig = cascadeClientScreenConfig;
    }

    public ClientScreenConfig getCascadeClientScreenConfig() {
        return cascadeClientScreenConfig;
    }
}
