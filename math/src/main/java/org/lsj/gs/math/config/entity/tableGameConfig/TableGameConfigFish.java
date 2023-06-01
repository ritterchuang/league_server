package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.BulletConfigBase;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server.RobotBeginMoneyLimitBase;

import java.util.Map;

// 金蟾捕魚牌桌遊戲設定
public class TableGameConfigFish extends AbstractTableGameConfig {
    private final Map<Integer, Map<Integer, BulletConfigBase>> playToBulletConfigMap; // 遊戲類型子彈設定對應表 <playType, <bulletId, config>>
    private final Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap; // 打擊設定對應表
    private final RobotBeginMoneyLimitBase robotBeginMoneyLimitBase; // 機器人起始金額上下限倍數

    public TableGameConfigFish(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            Map<Integer, Map<Integer, BulletConfigBase>> playToBulletConfigMap,
            Map<Integer, Map<Integer, HitCtrConfig>> hitCtrConfigMap,
            RobotBeginMoneyLimitBase robotBeginMoneyLimitBase) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.playToBulletConfigMap = playToBulletConfigMap;
        this.hitCtrConfigMap = hitCtrConfigMap;
        this.robotBeginMoneyLimitBase = robotBeginMoneyLimitBase;
    }

    public Map<Integer, Map<Integer, BulletConfigBase>> getPlayToBulletConfigMap() {
        return playToBulletConfigMap;
    }

    public Map<Integer, Map<Integer, HitCtrConfig>> getHitCtrConfigMap() {
        return hitCtrConfigMap;
    }

    public RobotBeginMoneyLimitBase getRobotBeginMoneyLimitBase() {
        return robotBeginMoneyLimitBase;
    }
}
