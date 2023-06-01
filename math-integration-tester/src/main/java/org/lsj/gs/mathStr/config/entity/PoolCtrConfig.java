package org.lsj.gs.mathStr.config.entity;

import org.lsj.gs.math.core.common.poolCtr.entity.AbstractPoolConfig;
import org.lsj.gs.pool.AgencyPool;
import org.lsj.gs.pool.PersonControlConfig;

// 水池計算器設定
public class PoolCtrConfig extends AbstractPoolConfig {
    public PoolCtrConfig(AgencyPool agencyPool, PersonControlConfig personControlConfig) {
        super(agencyPool, personControlConfig);
    }
}
