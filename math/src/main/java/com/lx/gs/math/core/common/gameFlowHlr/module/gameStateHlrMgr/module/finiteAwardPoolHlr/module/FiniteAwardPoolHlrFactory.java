package com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.IFiniteAwardPoolHlr;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.config.FiniteAwardPoolConfig;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;

// 有限獎項處理者工廠
public class FiniteAwardPoolHlrFactory {

    // 創造有限獎項處理者
    public IFiniteAwardPoolHlr create(FiniteAwardPoolConfig finiteAwardPoolConfig, ITableUtilSlot tableUtilSlot) {
        switch (finiteAwardPoolConfig.getFiniteAwardPoolType()) {
            default: return new FiniteAwardPoolHlrBaseReSpin(finiteAwardPoolConfig, tableUtilSlot);
        }
    }
}
