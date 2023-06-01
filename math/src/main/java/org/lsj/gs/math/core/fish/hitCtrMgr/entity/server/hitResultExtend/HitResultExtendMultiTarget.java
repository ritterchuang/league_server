package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

import java.util.Collections;
import java.util.Map;

// 多重目標客製處理結果
public class HitResultExtendMultiTarget extends HitResultExtend{
    private final Map<Integer, Double> targetWinMap; // 目標得分對應表 <目標索引, 得分>

    public HitResultExtendMultiTarget(Map<Integer, Double> targetWinMap) {
        this.targetWinMap = targetWinMap;
    }

    // 未獲得獎項
    public HitResultExtendMultiTarget () {
        this.targetWinMap = Collections.emptyMap();
    }


    public Map<Integer, Double> getTargetWinMap() {
        return targetWinMap;
    }
}
