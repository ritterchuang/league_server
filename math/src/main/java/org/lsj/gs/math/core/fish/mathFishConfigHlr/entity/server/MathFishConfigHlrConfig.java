package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server;

import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;

import java.util.Map;

// 數值魚機設定處理器的設定
public class MathFishConfigHlrConfig {
    private final RobotBeginMoneyLimitBase robotBeginMoneyLimitBase; // 機器人起始金額上下限倍數設定
    private final Map<Integer, BulletConfigBase> bulletConfigMap; // 子彈設定對應表 <子彈索引, 子彈設定>
    private final Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap; // 打擊計算器對應表 <子彈索引, <目標索引, 打擊設定>>
    private final double base; // 底注

    public MathFishConfigHlrConfig(RobotBeginMoneyLimitBase robotBeginMoneyLimitBase, Map<Integer, BulletConfigBase> bulletConfigMap, Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap, double base) {
        this.robotBeginMoneyLimitBase = robotBeginMoneyLimitBase;
        this.bulletConfigMap = bulletConfigMap;
        this.hitCtrConfigMap = hitCtrConfigMap;
        this.base = base;
    }

    public RobotBeginMoneyLimitBase getRobotBeginMoneyLimitBase() {
        return robotBeginMoneyLimitBase;
    }

    public Map<Integer, BulletConfigBase> getBulletConfigMap() {
        return bulletConfigMap;
    }

    public Map<Integer, Map<Integer, HitCtrConfig>> getHitCtrConfigMap() {
        return hitCtrConfigMap;
    }

    public double getBase() {
        return base;
    }
}
