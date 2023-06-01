package com.lx.gs.mathBoardGtr.core.fish;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendFish;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 魚機局產生器工廠
public class BoardGtrFishFactory {

    public IBoardGtrFish create(GamePlayerSimulation gamePlayerSimulation,
                                PoolCtr poolCtr,
                                PlayGameFieldConfig playGameFieldConfig,
                                ControlAlgorithmConfig controlAlgorithmConfig){
        // 1. 取出額外設定
        GameTypeConfigExtendFish gameTypeConfigExtend = (GameTypeConfigExtendFish) playGameFieldConfig.getGameTypeConfigExtend();

        // 2. 依照設定產局產生器
        switch (gameTypeConfigExtend.getPlayTypeFish()) {
            case RANDOM_TARGET: return new BoardGtrFishRandomTarget(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
            default: return new BoardGtrFishDefault(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }
}
