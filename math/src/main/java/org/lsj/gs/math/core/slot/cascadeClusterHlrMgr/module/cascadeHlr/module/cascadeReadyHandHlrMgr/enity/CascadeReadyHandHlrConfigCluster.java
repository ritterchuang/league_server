package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr.enity;

import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;

import java.util.Collections;
import java.util.List;

// 聽牌處理器設定集合
public class CascadeReadyHandHlrConfigCluster {
    private final List<ReadyHandHlrConfig> readyHandHlrConfigList; // 聽牌處理器設定列表

    public CascadeReadyHandHlrConfigCluster(List<ReadyHandHlrConfig> readyHandHlrConfigList) {
        this.readyHandHlrConfigList = readyHandHlrConfigList;
    }

    public CascadeReadyHandHlrConfigCluster() {
        this.readyHandHlrConfigList = Collections.emptyList();
    }

    public List<ReadyHandHlrConfig> getReadyHandHlrConfigList() {
        return readyHandHlrConfigList;
    }
}
