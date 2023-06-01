package org.lsj.gs.math.config.resources.tableGameConfig.games._default;

import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigDefault;
import org.lsj.gs.math.config.resources.tableGameConfig.core.AbstractTableGameResourceCard;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 預設 牌桌遊戲設定
public class TableGameResourceDefault extends AbstractTableGameResourceCard {
    public TableGameConfigDefault create() {
        // 1. 計算遊戲調控設定
        GameAdjustConfig gameAdjustConfig = super.createGameAdjustConfig();

        // 2. 計算亂數產生器演算法設定
        RngAlgorithmConfig rngAlgorithmConfig = super.createRngAlgorithmConfig();

        return new TableGameConfigDefault(
                gameAdjustConfig,
                rngAlgorithmConfig
        );
    }
}
