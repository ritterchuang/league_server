package com.lx.gs.mathStr.config.entity;

import com.lx.gs.math.core.common.poolCtr.entity.AbstractPoolConfig;
import com.lx.gs.pool.AgencyPool;
import com.lx.gs.pool.PersonControlConfig;

// 水池計算器設定
public class PoolCtrConfig extends AbstractPoolConfig {
    public PoolCtrConfig(AgencyPool agencyPool, PersonControlConfig personControlConfig) {
        super(agencyPool, personControlConfig);
    }
}
