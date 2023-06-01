package com.lx.gs.mathStr.config.resources.controlAlgorithm;

import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;

// 強控演算法設定 不強控
public class ControlAlgorithmR00NoDebug {
    public ControlAlgorithmConfig create(){
        return new ControlAlgorithmConfig(
            new ControlAlgorithmUtil()
        );
    }
}
