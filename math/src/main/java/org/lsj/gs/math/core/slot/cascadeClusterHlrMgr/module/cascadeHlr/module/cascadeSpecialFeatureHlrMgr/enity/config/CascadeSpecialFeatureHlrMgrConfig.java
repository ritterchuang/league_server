package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr.enity.config;

// 消除 特殊事件處理者管理器設定 TODO
public class CascadeSpecialFeatureHlrMgrConfig {
    private final CascadeSpecialFeatureHlrConfigCluster cascadeSpecialFeatureHlrConfigCluster; // 消除特殊事件處理者設定集合

    public CascadeSpecialFeatureHlrMgrConfig(CascadeSpecialFeatureHlrConfigCluster cascadeSpecialFeatureHlrConfigCluster) {
        this.cascadeSpecialFeatureHlrConfigCluster = cascadeSpecialFeatureHlrConfigCluster;
    }

    public CascadeSpecialFeatureHlrConfigCluster getCascadeSpecialFeatureHlrConfigCluster() {
        return cascadeSpecialFeatureHlrConfigCluster;
    }
}
