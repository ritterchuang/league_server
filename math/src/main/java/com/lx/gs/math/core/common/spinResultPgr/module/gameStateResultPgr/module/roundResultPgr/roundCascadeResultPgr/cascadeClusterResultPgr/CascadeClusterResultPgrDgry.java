package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr;

import com.lx.gs.math.core.common.spinResultPgr.enity.pgrConfig.CascadeClusterResultPgrConfig;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.ProgressResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity.CascadeClusterResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity.CascadeClusterResultExtend;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.CascadeResultPgrFactory;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.ICascadeResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResult;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;

import java.util.List;

// 消除結果集合包裝者 帝国榮耀
public class CascadeClusterResultPgrDgry implements ICascadeClusterResultPgr{
    private final CascadeClusterResultPgrConfig config; // 消除集合結果包裝者設定
    private final ITableUtil tableUtil; // 牌桌工具包
    private final ICascadeResultPgr cascadeResultPgr; // 消結果包裝者

    public CascadeClusterResultPgrDgry(CascadeClusterResultPgrConfig config, ProgressResultPgr progressResultPgr, AccumulateWinResultPgr accumulateWinResultPgr, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.cascadeResultPgr = new CascadeResultPgrFactory().create(config.getCascadeResultPgrConfig(), progressResultPgr, accumulateWinResultPgr, tableUtil);
    }

    @Override
    public CascadeClusterResult packageCascadeClusterResult(CascadeClusterHlrResult cascadeClusterHlrResult) {

        // 2. 計算消除結果列表
        List<CascadeResult> cascadeResultList = this.cascadeResultPgr.packageCascadeResultList(cascadeClusterHlrResult.getCascadeHlrResultList());

        // 3. 回傳
        return new CascadeClusterResult(
                cascadeClusterHlrResult.getTotalWin(), ConstMathSlot.CascadeClusterType.DGRY,
                new CascadeClusterResultExtend(),
                cascadeResultList);
    }
}
