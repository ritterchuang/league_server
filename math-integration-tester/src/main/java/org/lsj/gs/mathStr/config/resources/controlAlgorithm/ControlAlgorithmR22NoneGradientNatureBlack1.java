package org.lsj.gs.mathStr.config.resources.controlAlgorithm;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;

// 強控演算法設定 不控水池 梯度開牌 自然發牌 個人控黑一
public class ControlAlgorithmR22NoneGradientNatureBlack1 {
    public ControlAlgorithmConfig create(){
        return new ControlAlgorithmConfig(
            new ControlAlgorithmUtil(
                    true,
                    ConstMathCommon.PoolControlType.NONE,
                    ConstMathCommon.DealType.GRADIENT,
                    ConstMathCommon.ShuffleType.NATURE,
                    ConstMathCommon.PersonAdjustType.BLACK1
            )
        );
    }
}
