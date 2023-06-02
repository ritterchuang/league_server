package org.lsj.gs.mathBoardGtr.config.entity;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.config.entity.PoolCtrConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;

// 局產生器設定
public class BoardGtrConfig {
    private final GamePlayerSimulation gamePlayer; // 模擬用遊戲玩家
    private final PoolCtrConfig poolCtrConfig; // 水池計算器設定
    private final PlayGameFieldConfig playGameFieldConfig; // 遊戲房間設定
    private final ControlAlgorithmConfig controlAlgorithmConfig; // 強控演算法設定

    public BoardGtrConfig(GamePlayerSimulation gamePlayer, PoolCtrConfig poolCtrConfig, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        this.gamePlayer = gamePlayer;
        this.poolCtrConfig = poolCtrConfig;
        this.playGameFieldConfig = playGameFieldConfig;
        this.controlAlgorithmConfig = controlAlgorithmConfig;
    }

    public GamePlayerSimulation getGamePlayer() {
        return gamePlayer;
    }

    public PoolCtrConfig getPoolCtrConfig() {
        return poolCtrConfig;
    }

    public PlayGameFieldConfig getPlayGameFieldConfig() {
        return playGameFieldConfig;
    }

    public ControlAlgorithmConfig getControlAlgorithmConfig() {
        return controlAlgorithmConfig;
    }
}
