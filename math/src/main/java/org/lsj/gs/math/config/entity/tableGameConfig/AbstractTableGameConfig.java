package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 抽象牌桌遊戲設定
public class AbstractTableGameConfig {
    private final GameAdjustConfig gameAdjustConfig; // 遊戲調控設定
    private final RngAlgorithmConfig rngAlgorithmConfig; // 亂數產生器演算法設定

    public AbstractTableGameConfig(GameAdjustConfig gameAdjustConfig, RngAlgorithmConfig rngAlgorithmConfig) {
        this.gameAdjustConfig = gameAdjustConfig;
        this.rngAlgorithmConfig = rngAlgorithmConfig;
    }

    public GameAdjustConfig getGameAdjustConfig() {
        return gameAdjustConfig;
    }

    public RngAlgorithmConfig getRngAlgorithmConfig() {
        return rngAlgorithmConfig;
    }
}
