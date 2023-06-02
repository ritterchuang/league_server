package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster;

import java.util.ArrayList;
import java.util.List;

// 客端特殊事件結果集合
public class SpecialFeatureResultCluster {
    private final double totalWin; // 總得分
    private final List<SpecialFeatureResult> specialFeatureResultList; // 客端特殊事件結果列表

    public SpecialFeatureResultCluster() {
        this.totalWin = 0.0;
        this.specialFeatureResultList = new ArrayList<>();
    }

    public SpecialFeatureResultCluster(double totalWin, List<SpecialFeatureResult> specialFeatureResultList) {
        this.totalWin = totalWin;
        this.specialFeatureResultList = specialFeatureResultList;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public List<SpecialFeatureResult> getSpecialFeatureResultList() {
        return specialFeatureResultList;
    }
}
