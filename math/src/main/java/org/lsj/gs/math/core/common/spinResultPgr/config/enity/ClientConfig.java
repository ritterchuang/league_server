package org.lsj.gs.math.core.common.spinResultPgr.config.enity;

import java.util.List;

// 客戶端顯示設定
public class ClientConfig {
    private final List<ClientGameStateConfig> clientGameStateConfigList; // 客端遊戲狀態設定

    public ClientConfig(List<ClientGameStateConfig> clientGameStateConfigList) {
        this.clientGameStateConfigList = clientGameStateConfigList;
    }

    public List<ClientGameStateConfig> getClientGameStateConfigList() {
        return clientGameStateConfigList;
    }
}
