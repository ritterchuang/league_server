package org.lsj.gs.mathStr.core.module.playStr;

import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathBoardGtr.core.BoardGtrFactory;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

// 抽象遊戲模擬器
public abstract class AbstractPlayStr implements IPlayStr {
    protected final BoardGtrFactory boardGtrFactory; // 局產生器工廠

    public AbstractPlayStr(GamePlayerSimulation gamePlayerSimulation, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        this.boardGtrFactory = new BoardGtrFactory(gamePlayerSimulation, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }
}
