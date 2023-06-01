package com.lx.gs.mathStr.core.module.playStr;

import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.playStrResult.PlayStrResult;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 魚機遊戲模擬器
public class DefaultPlayStr extends AbstractPlayStr {
    public DefaultPlayStr(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public PlayStrResult run() {
        return null;
    }

}
