package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config;

import java.util.List;

// 聽牌處理器設定集合
public class ReadyHandHlrConfigCluster {
    private final List<ReadyHandHlrConfig> readyHandHlrConfigList; // 聽牌處理器設定列表

    public ReadyHandHlrConfigCluster(List<ReadyHandHlrConfig> readyHandHlrConfigList) {
        this.readyHandHlrConfigList = readyHandHlrConfigList;
    }

    public List<ReadyHandHlrConfig> getReadyHandHlrConfigList() {
        return readyHandHlrConfigList;
    }
}
