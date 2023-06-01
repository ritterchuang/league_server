package org.lsj.gs.mathStr.core.module.poolCltr;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.poolCtr.entity.PoolConfig;
import org.lsj.gs.mathStr.config.entity.PoolCtrConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.pool.AgencyPool;
import org.lsj.gs.pool.PersonControlConfig;

import java.util.Optional;

// 水池計算器
public class PoolCtr {
    private final AgencyPoolCtr agencyPoolCtr; // 代理水池計算器

    public PoolCtr(PoolCtrConfig poolCtrConfig) {
        this.agencyPoolCtr = new AgencyPoolCtr(new AgencyPool().checkConstructor(poolCtrConfig.getAgencyPool())); // 水池計算器設定
    }

    // 創建水池設定 TODO 個人控尚未完成(先寫 null)
    public IPoolConfig createPoolConfig() {
        return new PoolConfig(this.agencyPoolCtr.getAgentPool(), new PersonControlConfig(null, null));
    }

    // 更新水池
    public void update(BoardGtrResult boardGtrResult) {
        this.agencyPoolCtr.update(boardGtrResult);
    }

    // 更新水池返還
    public void updateReturnResult(Optional<IGameBetLogResultFish> returnResult) {
        this.agencyPoolCtr.updateReturnResult(returnResult);
    }
}
