package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

import java.util.List;

// 滾輪打擊設定
public class HitTypeConfigExtendReel extends HitTypeConfigExtend{
    private final List<Integer> initialValue; // 初始畫面設定
    private final List<int[]> oddsRangeList; // 倍數區間列表
    private final List<Integer> oddsRangeWeightList; // 倍數區間權重

    public HitTypeConfigExtendReel(List<Integer> initialValue, List<int[]> oddsRangeList, List<Integer> oddsRangeWeightList) {
        this.initialValue = initialValue;
        this.oddsRangeList = oddsRangeList;
        this.oddsRangeWeightList = oddsRangeWeightList;
    }

    public List<Integer> getInitialValue() {
        return initialValue;
    }

    public List<int[]> getOddsRangeList() {
        return oddsRangeList;
    }

    public List<Integer> getOddsRangeWeightList() {
        return oddsRangeWeightList;
    }
}
