package com.lx.gs.math.core;

import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象模組
public abstract class AbstractModule implements IModule{
    protected GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    protected PoolCtr poolCtr; // 水池控制器
    protected ITableUtil tableUtil; // 牌桌工具包

    public AbstractModule(GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil){
        this.gamePlayerHlr = gamePlayerHlr;
        this.poolCtr = poolCtr;
        this.tableUtil = tableUtil;
    }
}
