package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity;

import java.util.List;

// 特殊事件處理者設定集合
public class SpecialFeatureHlrConfigCluster {
    private final List<SpecialFeatureHlrConfig> specialFeatureHlrConfigList; // 特殊事件處理者設定列表

    public SpecialFeatureHlrConfigCluster(List<SpecialFeatureHlrConfig> specialFeatureHlrConfigList) {
        this.specialFeatureHlrConfigList = specialFeatureHlrConfigList;
    }

    public List<SpecialFeatureHlrConfig> getSpecialFeatureHlrConfigList() {
        return specialFeatureHlrConfigList;
    }
}
