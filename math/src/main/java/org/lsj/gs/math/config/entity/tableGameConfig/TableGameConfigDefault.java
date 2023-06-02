package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 預設牌桌遊戲設定
public class TableGameConfigDefault extends AbstractTableGameConfig {

    public TableGameConfigDefault(GameAdjustConfig gameAdjustConfig, RngAlgorithmConfig rngAlgorithmConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig);
    }
}
