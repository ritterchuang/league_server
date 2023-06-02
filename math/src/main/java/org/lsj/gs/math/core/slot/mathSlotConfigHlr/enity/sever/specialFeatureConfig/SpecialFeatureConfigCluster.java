package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig;

import java.util.Collections;
import java.util.List;

// 特殊事件集合
public class SpecialFeatureConfigCluster {
    private final List<SpecialFeatureConfig> specialFeatureConfigList; // 特殊事件設定列表

    public SpecialFeatureConfigCluster(List<SpecialFeatureConfig> specialFeatureConfigList) {
        this.specialFeatureConfigList = specialFeatureConfigList;
    }

    public SpecialFeatureConfigCluster() {
        this.specialFeatureConfigList = Collections.emptyList();
    }

    public List<SpecialFeatureConfig> getSpecialFeatureConfigList() {
        return specialFeatureConfigList;
    }
}
