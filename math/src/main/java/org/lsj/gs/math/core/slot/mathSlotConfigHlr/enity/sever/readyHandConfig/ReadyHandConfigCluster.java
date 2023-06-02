package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig;

import java.util.Collections;
import java.util.List;

// 聽牌設定集合
public class ReadyHandConfigCluster {
    private final List<ReadyHandConfig> readyHandConfigList; // 聽牌設定列表

    public ReadyHandConfigCluster(List<ReadyHandConfig> readyHandConfigList) {
        this.readyHandConfigList = readyHandConfigList;
    }

    public ReadyHandConfigCluster() {
        this.readyHandConfigList = Collections.emptyList();
    }

    public List<ReadyHandConfig> getReadyHandConfigList() {
        return readyHandConfigList;
    }
}
