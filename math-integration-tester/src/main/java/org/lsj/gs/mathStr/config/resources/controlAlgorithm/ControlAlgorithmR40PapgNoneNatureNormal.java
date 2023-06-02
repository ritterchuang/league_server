package org.lsj.gs.mathStr.config.resources.controlAlgorithm;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;

// 強控演算法設定 個人控優先水池 不控開牌 自然發牌 個人控自然
public class ControlAlgorithmR40PapgNoneNatureNormal {
    public ControlAlgorithmConfig create(){
        return new ControlAlgorithmConfig(
            new ControlAlgorithmUtil(
                    true,
                    ConstMathCommon.PoolControlType.PERSON_ADJUST_PREFER_GUARANTEE,
                    ConstMathCommon.DealType.NONE,
                    ConstMathCommon.ShuffleType.NATURE,
                    ConstMathCommon.PersonAdjustType.NORMAL
            )
        );
    }
}
