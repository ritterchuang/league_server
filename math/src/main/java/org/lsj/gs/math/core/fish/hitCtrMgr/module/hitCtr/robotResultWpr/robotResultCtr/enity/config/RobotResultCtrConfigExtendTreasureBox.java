package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendTreasureBox;

import java.util.List;
import java.util.Map;

// 機器人打擊結果計算者設定連點寶箱
public class RobotResultCtrConfigExtendTreasureBox extends RobotResultCtrConfigExtend{
    private final List<int[]> oddsRangeList; // 倍數區間列表
    private final List<Integer> oddsRangeWeightList; // 倍數區間權重
    private final Map<Integer, int[]> lowOddsToCombinationMap; // 低倍數切割對應表
    private final Map<Integer, int[]> mediumOddsToCombinationMap; // 中倍數切割對應表
    private final List<int[]> highOddsCombinationList; // 高倍數切割列表
    private final List<Integer> highOddsCombinationWeightList; // 高倍數切割權重列表

    public RobotResultCtrConfigExtendTreasureBox(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendTreasureBox hitTypeConfigExtendTreasureBox = (HitTypeConfigExtendTreasureBox)HitTypeConfigExtend;
        this.oddsRangeList = hitTypeConfigExtendTreasureBox.getOddsRangeList();
        this.oddsRangeWeightList = hitTypeConfigExtendTreasureBox.getOddsRangeWeightList();
        this.lowOddsToCombinationMap = hitTypeConfigExtendTreasureBox.getLowOddsToCombinationMap();
        this.mediumOddsToCombinationMap = hitTypeConfigExtendTreasureBox.getMediumOddsToCombinationMap();
        this.highOddsCombinationList = hitTypeConfigExtendTreasureBox.getHighOddsCombinationList();
        this.highOddsCombinationWeightList = hitTypeConfigExtendTreasureBox.getHighOddsCombinationWeightList();
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
