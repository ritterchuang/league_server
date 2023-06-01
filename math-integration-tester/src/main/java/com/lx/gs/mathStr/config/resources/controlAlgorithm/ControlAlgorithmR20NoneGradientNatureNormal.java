package com.lx.gs.mathStr.config.resources.controlAlgorithm;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;

// 強控演算法設定 不控水池 梯度開牌 自然發牌 個人控自然
public class ControlAlgorithmR20NoneGradientNatureNormal {
    public ControlAlgorithmConfig create(){
        return new ControlAlgorithmConfig(
            new ControlAlgorithmUtil(
                    true,
                    ConstMathCommon.PoolControlType.NONE,
                    ConstMathCommon.DealType.GRADIENT,
                    ConstMathCommon.ShuffleType.NATURE,
                    ConstMathCommon.PersonAdjustType.NORMAL
            )
        );
    }
}
