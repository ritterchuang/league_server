package com.lx.gs.mathStr.core.module.playStr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathBoardGtr.core.BoardGtrFactory;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 抽象遊戲模擬器
public abstract class AbstractPlayStr implements IPlayStr {
    protected final BoardGtrFactory boardGtrFactory; // 局產生器工廠

    public AbstractPlayStr(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        this.boardGtrFactory = new BoardGtrFactory(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }
}
