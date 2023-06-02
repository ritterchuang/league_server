package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr.enity;

// 消除聽牌事件處理者管理器設定 TODO
public class CascadeReadyHandHlrMgrConfig {
    private final CascadeReadyHandHlrConfigCluster cascadeReadyHandHlrConfigCluster; // 消除聽牌處理設定集合

    public CascadeReadyHandHlrMgrConfig(CascadeReadyHandHlrConfigCluster cascadeReadyHandHlrConfigCluster) {
        this.cascadeReadyHandHlrConfigCluster = cascadeReadyHandHlrConfigCluster;
    }

    public CascadeReadyHandHlrConfigCluster getCascadeReadyHandHlrConfigCluster() {
        return cascadeReadyHandHlrConfigCluster;
    }
}
