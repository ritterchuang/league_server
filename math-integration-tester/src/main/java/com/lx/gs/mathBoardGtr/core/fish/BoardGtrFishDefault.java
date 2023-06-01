package com.lx.gs.mathBoardGtr.core.fish;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 預設魚機局產生器
public class BoardGtrFishDefault extends AbstractBoardGtrFish {
    public BoardGtrFishDefault(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        return null;
    }

    @Override
    public BoardGtrReturnResult getReturnResult() {
        return null;
    }

}
