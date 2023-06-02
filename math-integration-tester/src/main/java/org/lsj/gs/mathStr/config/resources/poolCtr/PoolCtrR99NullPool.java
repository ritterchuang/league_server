package org.lsj.gs.mathStr.config.resources.poolCtr;

import org.lsj.gs.mathStr.config.entity.PoolCtrConfig;

// 水池計算器設定 空值
public class PoolCtrR99NullPool {
    public PoolCtrConfig create(){
        return new PoolCtrConfig(null,null);
    }
}
