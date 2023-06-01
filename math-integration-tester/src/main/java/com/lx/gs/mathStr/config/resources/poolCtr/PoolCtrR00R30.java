package com.lx.gs.mathStr.config.resources.poolCtr;

import com.lx.gs.mathStr.config.entity.PoolCtrConfig;
import com.lx.gs.pool.AgencyPool;

// 水池計算器設定 手續費率 3%
public class PoolCtrR00R30 {
    public PoolCtrConfig create(){
        return new PoolCtrConfig(new AgencyPool(
                0.0,
                0.0,
                0.03,
                1002,
                1002,
                1002,
                true
        ),
                null);
    }
}
