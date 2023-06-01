package com.lx.gs.mathBoardGtr.core.slot;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendSlot;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 老虎局產生器工廠
public class BoardGtrFactorySlot {

    public IBoardGtrSlot create(GamePlayerSimulation gamePlayerSimulation,
                                PoolCtr poolCtr,
                                PlayGameFieldConfig playGameFieldConfig,
                                ControlAlgorithmConfig controlAlgorithmConfig){
        // 1. 取出老虎機額外設定
        GameTypeConfigExtendSlot gameTypeConfigExtendSlot = (GameTypeConfigExtendSlot) playGameFieldConfig.getGameTypeConfigExtend();

        // 2. 依照設定產局產生器
        switch (gameTypeConfigExtendSlot.getPlayTypeSlot()) {
            default: return this.createBoardGtrNormal(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        }
    }

    // 生成一般模式局產生器
    private IBoardGtrSlot createBoardGtrNormal(GamePlayerSimulation player,
                                               PoolCtr poolCtr,
                                               PlayGameFieldConfig playGameFieldConfig,
                                               ControlAlgorithmConfig controlAlgorithmConfig){
        return new BoardGtrSlotNormal(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }
}
