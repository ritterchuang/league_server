package com.lx.gs.math.core.common.gameAdjust.module.poolControlHlr;

import com.lx.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;

// 水池控制處理器介面
public interface IPoolControlHlr {
    void control(IDealCtr dealCtr, GameAdjustParameter gameAdjustParameter); // 控制
}
