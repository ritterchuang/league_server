package org.lsj.gs.math.core.common.poolCtr.entity;

import org.lsj.gs.pool.AgencyPool;
import org.lsj.gs.pool.PersonControlConfig;

// 水池設定介面
public interface IPoolConfig {
    AgencyPool getAgencyPool(); // 取得代理水池
    PersonControlConfig getPersonControlConfig(); // 取得個人調控資訊
}
