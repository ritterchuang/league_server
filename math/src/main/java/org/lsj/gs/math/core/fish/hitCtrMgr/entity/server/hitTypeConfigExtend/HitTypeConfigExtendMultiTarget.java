package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

import java.util.Map;

// 多重目標打擊設定
public class HitTypeConfigExtendMultiTarget extends HitTypeConfigExtend{
    private final Map<Integer, Integer> targetOddsMap; // 目標倍數對應表 <目標索引, 目標倍數>
    private final RobotHitResultLimitBase robotHitResultLimitBase; // 機器人打擊結果上下限倍數

    public HitTypeConfigExtendMultiTarget(Map<Integer, Integer> targetOddsMap, RobotHitResultLimitBase robotHitResultLimitBase) {
        this.targetOddsMap = targetOddsMap;
        this.robotHitResultLimitBase = robotHitResultLimitBase;
    }

    public Map<Integer, Integer> getTargetOddsMap() {
        return targetOddsMap;
    }

    public RobotHitResultLimitBase getRobotHitResultLimitBase() {
        return robotHitResultLimitBase;
    }
}
