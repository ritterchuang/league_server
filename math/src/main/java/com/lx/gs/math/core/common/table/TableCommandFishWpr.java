package com.lx.gs.math.core.common.table;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import com.lx.gs.math.core.common.poolCtr.entity.IPoolConfig;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;
import com.lx.gs.user.IUser;

import java.util.Optional;

// 捕魚遊戲桌封裝器
public class TableCommandFishWpr implements ISeverTableCommandFish {
    private final TableCommandFish table; // 遊戲桌

    public TableCommandFishWpr(TableCommandFish table) {
        this.table = table;
    }

    @Override
    public void startBeforeGameEnd() {
        this.table.startBeforeGameEnd();
    }

    @Override
    public MathFishConfig getMathFishConfig() {
        return this.table.getMathFishConfig();
    }

    @Override
    public IPoolConfig getPoolConfig() {
        return this.table.getPoolConfig();
    }

    @Override
    public void updatePoolConfig(IPoolConfig poolConfig) {
        this.table.updatePoolConfig(poolConfig);
    }

    @Override
    public void updateUser(IUser user) {
        this.table.updateUser(user);
    }

    @Override
    public IGameBetLogResultFish getHitResult(String ctsGetHitResultDataString) throws TableException {
        return this.table.getHitResult(ctsGetHitResultDataString);
    }

    @Override
    public Optional<IGameBetLogResultFish> calculateFishReturnResult() {
        return this.table.calculateFishReturnResult();
    }

    @Override
    public void setRngData(String rngDataString) {
        this.table.setRngData(rngDataString);
    }
}
