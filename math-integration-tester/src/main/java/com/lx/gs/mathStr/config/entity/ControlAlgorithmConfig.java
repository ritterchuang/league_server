package com.lx.gs.mathStr.config.entity;

import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;

// 強控演算法設定
public class ControlAlgorithmConfig {
    private final ControlAlgorithmUtil controlAlgorithmUtil; // 強控演算法工具包

    public ControlAlgorithmConfig(ControlAlgorithmUtil controlAlgorithmUtil) {
        this.controlAlgorithmUtil = controlAlgorithmUtil;
    }

    public ControlAlgorithmUtil getControlAlgorithmUtil() {
        return controlAlgorithmUtil;
    }
}
