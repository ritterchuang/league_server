package org.lsj.gs.mathStr.core.module.poolCltr;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.pool.AgencyPool;

import java.util.Optional;

// 代理水池計算器
public class AgencyPoolCtr {
    private final AgencyPool agencyPool; // 代理水池設定

    public AgencyPoolCtr(AgencyPool agencyPool) {
        this.agencyPool = agencyPool;
    }

    // 更新水池
    public void update(BoardGtrResult boardGtrResult) {
        this.agencyPool.setTotalBet(this.agencyPool.getTotalBet() + boardGtrResult.getGameBetLogResult().getGameBetLogObj().getValidBet());
        this.agencyPool.setTotalScore(this.agencyPool.getTotalScore() + boardGtrResult.getGameBetLogResult().getGameBetLogObj().getScore());
    }

    // 更新水池
    public void updateReturnResult(Optional<IGameBetLogResultFish> returnResult) {
        // 1. 空值，回傳
        if (returnResult.isEmpty()) {
            return;
        }

        // 2. 更新返還結果
        this.agencyPool.setTotalBet(this.agencyPool.getTotalBet() + returnResult.get().getGameBetLogObj().getValidBet());
        this.agencyPool.setTotalScore(this.agencyPool.getTotalScore() + returnResult.get().getGameBetLogObj().getScore());
    }

    public AgencyPool getAgentPool() {
        return agencyPool;
    }
}
