package org.lsj.gs.math.config.resources.tableGameConfig.core;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 抽象老虎機牌桌遊戲設定
public class AbstractTableGameResourceSlot {
    public GameAdjustConfig createGameAdjustConfig(){
        return new GameAdjustConfig(
                ConstMathCommon.PoolControlType.PERSON_ADJUST_PREFER_GUARANTEE,
                ConstMathCommon.DealType.NONE,
                ConstMathCommon.ShuffleType.PSEUDO_NATURE);
    }

    public RngAlgorithmConfig createRngAlgorithmConfig(){
        return new RngAlgorithmConfig(
                ConstMathCommon.PoolControlType.NONE,
                ConstMathCommon.DealType.NONE,
                ConstMathCommon.ShuffleType.NATURE,
                ConstMathCommon.PersonAdjustType.NORMAL);
    }
}
