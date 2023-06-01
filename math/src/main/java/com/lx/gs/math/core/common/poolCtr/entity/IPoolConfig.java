package com.lx.gs.math.core.common.poolCtr.entity;

import com.lx.gs.pool.AgencyPool;
import com.lx.gs.pool.PersonControlConfig;

// 水池設定介面
public interface IPoolConfig {
    AgencyPool getAgencyPool(); // 取得代理水池
    PersonControlConfig getPersonControlConfig(); // 取得個人調控資訊
}
