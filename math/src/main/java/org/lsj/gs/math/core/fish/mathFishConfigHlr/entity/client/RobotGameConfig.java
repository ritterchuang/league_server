package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client;

import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotHitResult;

import java.util.Map;

// 機器人遊戲設定
public class RobotGameConfig {
    private final Map<Integer, Map<Integer, RobotHitResult>> robotHitResultMap; // 機器人子彈擊殺目標結果對應表 <bulletIndex, targetIndex, robotKillResult>

    public RobotGameConfig(Map<Integer, Map<Integer, RobotHitResult>> robotHitResultMap) {
        this.robotHitResultMap = robotHitResultMap;
    }

    public Map<Integer, Map<Integer, RobotHitResult>> getRobotHitResultMap() {
        return robotHitResultMap;
    }
}
