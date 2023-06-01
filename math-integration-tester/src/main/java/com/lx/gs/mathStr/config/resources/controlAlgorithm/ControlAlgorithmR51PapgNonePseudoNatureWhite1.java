package com.lx.gs.mathStr.config.resources.controlAlgorithm;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;

// 強控演算法設定 個人控優先水池 不控開牌 偽自然發牌 個人控白一
public class ControlAlgorithmR51PapgNonePseudoNatureWhite1 {
    public ControlAlgorithmConfig create(){
        return new ControlAlgorithmConfig(
            new ControlAlgorithmUtil(
                    true,
                    ConstMathCommon.PoolControlType.PERSON_ADJUST_PREFER_GUARANTEE,
                    ConstMathCommon.DealType.NONE,
                    ConstMathCommon.ShuffleType.PSEUDO_NATURE,
                    ConstMathCommon.PersonAdjustType.WHITE1
            )
        );
    }
}
