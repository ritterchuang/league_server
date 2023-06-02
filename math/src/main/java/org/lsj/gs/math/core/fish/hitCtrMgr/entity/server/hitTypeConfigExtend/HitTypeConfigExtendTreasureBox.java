package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

import java.util.List;
import java.util.Map;

// 連點寶箱打擊設定
public class HitTypeConfigExtendTreasureBox extends HitTypeConfigExtend{
    private final List<int[]> oddsRangeList; // 倍數區間列表
    private final List<Integer> oddsRangeWeightList; // 倍數區間權重
    private final Map<Integer, int[]> lowOddsToCombinationMap; // 低倍數切割對應表
    private final Map<Integer, int[]> mediumOddsToCombinationMap; // 中倍數切割對應表
    private final List<int[]> highOddsCombinationList; // 高倍數切割列表
    private final List<Integer> highOddsCombinationWeightList; // 高倍數切割權重列表

    public HitTypeConfigExtendTreasureBox(
            List<int[]> oddsRangeList,
            List<Integer> oddsRangeWeightList,
            Map<Integer, int[]> lowOddsToCombinationMap,
            Map<Integer, int[]> mediumOddsToCombinationMap,
            List<int[]> highOddsCombinationList,
            List<Integer> highOddsCombinationWeightList) {
        this.oddsRangeList = oddsRangeList;
        this.oddsRangeWeightList = oddsRangeWeightList;
        this.lowOddsToCombinationMap = lowOddsToCombinationMap;
        this.mediumOddsToCombinationMap = mediumOddsToCombinationMap;
        this.highOddsCombinationList = highOddsCombinationList;
        this.highOddsCombinationWeightList = highOddsCombinationWeightList;
    }

    public List<int[]> getOddsRangeList() {
        return oddsRangeList;
    }

    public List<Integer> getOddsRangeWeightList() {
        return oddsRangeWeightList;
    }

    public Map<Integer, int[]> getLowOddsToCombinationMap() {
        return lowOddsToCombinationMap;
    }

    public Map<Integer, int[]> getMediumOddsToCombinationMap() {
        return mediumOddsToCombinationMap;
    }

    public List<int[]> getHighOddsCombinationList() {
        return highOddsCombinationList;
    }

    public List<Integer> getHighOddsCombinationWeightList() {
        return highOddsCombinationWeightList;
    }
}
