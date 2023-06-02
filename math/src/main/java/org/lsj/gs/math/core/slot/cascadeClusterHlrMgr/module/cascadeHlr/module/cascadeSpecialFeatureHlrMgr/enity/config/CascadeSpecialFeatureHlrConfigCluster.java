package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr.enity.config;

import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;

import java.util.Collections;
import java.util.List;

// 消除特殊事件處理者設定集合
public class CascadeSpecialFeatureHlrConfigCluster {
    private final List<SpecialFeatureHlrConfig> specialFeatureHlrConfigList; // 消除特殊事件處理者設定列表

    public CascadeSpecialFeatureHlrConfigCluster(List<SpecialFeatureHlrConfig> specialFeatureHlrConfigList) {
        this.specialFeatureHlrConfigList = specialFeatureHlrConfigList;
    }

    public CascadeSpecialFeatureHlrConfigCluster() {
        this.specialFeatureHlrConfigList = Collections.emptyList();
    }

    public List<SpecialFeatureHlrConfig> getSpecialFeatureHlrConfigList() {
        return specialFeatureHlrConfigList;
    }
}
