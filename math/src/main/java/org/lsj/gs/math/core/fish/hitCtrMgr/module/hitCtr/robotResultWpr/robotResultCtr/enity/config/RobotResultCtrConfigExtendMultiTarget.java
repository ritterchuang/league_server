package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.RobotHitResultLimitBase;

import java.util.Map;

// 機器人打擊結果計算者設定多重打擊
public class RobotResultCtrConfigExtendMultiTarget extends RobotResultCtrConfigExtend{
    private final Map<Integer, Integer> targetOddsMap; // 目標倍數對應表 <目標索引, 目標倍數>
    private final RobotHitResultLimitBase robotHitResultLimitBase; // 機器人打擊結果上下限倍數

    public RobotResultCtrConfigExtendMultiTarget(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendMultiTarget hitTypeConfigExtendMultiTarget = (HitTypeConfigExtendMultiTarget)HitTypeConfigExtend;
        this.targetOddsMap = hitTypeConfigExtendMultiTarget.getTargetOddsMap();
        this.robotHitResultLimitBase = hitTypeConfigExtendMultiTarget.getRobotHitResultLimitBase();
    }

    public Map<Integer, Integer> getTargetOddsMap() {
        return targetOddsMap;
    }

    public RobotHitResultLimitBase getRobotHitResultLimitBase() {
        return robotHitResultLimitBase;
    }
}
