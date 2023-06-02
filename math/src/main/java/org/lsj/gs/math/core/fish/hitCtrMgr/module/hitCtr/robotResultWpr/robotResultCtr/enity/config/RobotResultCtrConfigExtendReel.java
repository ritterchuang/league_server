package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendReel;

import java.util.List;

// 機器人打擊結果計算者設定滾輪
public class RobotResultCtrConfigExtendReel extends RobotResultCtrConfigExtend{
    private final List<Integer> initialValue; // 初始畫面設定
    private final List<int[]> oddsRangeList; // 倍數區間列表
    private final List<Integer> oddsRangeWeightList; // 倍數區間權重

    public RobotResultCtrConfigExtendReel(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendReel hitTypeConfigExtendReel = (HitTypeConfigExtendReel)HitTypeConfigExtend;
        this.initialValue = hitTypeConfigExtendReel.getInitialValue();
        this.oddsRangeList = hitTypeConfigExtendReel.getOddsRangeList();
        this.oddsRangeWeightList = hitTypeConfigExtendReel.getOddsRangeWeightList();
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
