package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeClusterConfigExtend;

import java.util.List;

// 消除集合處理者額外設定 超級王牌
public class CascadeClusterConfigExtendCjwp extends CascadeClusterConfigExtend {
    private final List<List<Integer>> defaultGoldenPositionIdList; // 預設黃金位置列表

    public CascadeClusterConfigExtendCjwp(List<List<Integer>> defaultGoldenPositionIdList) {
        this.defaultGoldenPositionIdList = defaultGoldenPositionIdList;
    }

    public List<List<Integer>> getDefaultGoldenPositionIdList() {
        return defaultGoldenPositionIdList;
    }
}
