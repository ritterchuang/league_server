package org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend;

import java.util.HashMap;
import java.util.Map;

// 多重目標訊息
public class HitTypeExtendMultiTarget extends HitTypeExtend {
    private final Map<Integer, Integer> targetCountMap; // 目標數量對應表 <目標索引, 數量>

    // 原始建構子提供JSON解析用
    public HitTypeExtendMultiTarget() { this.targetCountMap = new HashMap<>(); }

    public HitTypeExtendMultiTarget(Map<Integer, Integer> targetCountMap) {
        this.targetCountMap = targetCountMap;
    }

    public Map<Integer, Integer> getTargetCountMap() {
        return targetCountMap;
    }
}
