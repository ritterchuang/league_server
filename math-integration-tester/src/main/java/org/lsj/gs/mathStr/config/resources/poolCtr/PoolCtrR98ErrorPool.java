package org.lsj.gs.mathStr.config.resources.poolCtr;

import org.lsj.gs.mathStr.config.entity.PoolCtrConfig;
import org.lsj.gs.pool.AgencyPool;

// 水池計算器設定 錯誤值
public class PoolCtrR98ErrorPool {
    public PoolCtrConfig create(){
        return new PoolCtrConfig(new AgencyPool(
                -1000000,
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
