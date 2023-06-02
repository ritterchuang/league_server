package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.CascadeClusterResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.ProgressResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity.CascadeClusterResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity.CascadeClusterResultExtendDevHj;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.CascadeResultPgrFactory;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.ICascadeResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResultExtendMjhl;

import java.util.List;

// 消除結果集合包裝者 麻將胡了
public class CascadeClusterResultPgrMjhl implements ICascadeClusterResultPgr{
    private final CascadeClusterResultPgrConfig config; // 消除集合結果包裝者設定
    private final ITableUtil tableUtil; // 牌桌工具包
    private final ICascadeResultPgr cascadeResultPgr; // 消結果包裝者

    public CascadeClusterResultPgrMjhl(CascadeClusterResultPgrConfig config, ProgressResultPgr progressResultPgr, AccumulateWinResultPgr accumulateWinResultPgr, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.cascadeResultPgr = new CascadeResultPgrFactory().create(config.getCascadeResultPgrConfig(), progressResultPgr, accumulateWinResultPgr, tableUtil);
    }

    @Override
    public CascadeClusterResult packageCascadeClusterResult(CascadeClusterHlrResult cascadeClusterHlrResult) {
        // 1. 計算消除集合額外結果
        CascadeClusterHlrResultExtendMjhl resultExtendMjhl = (CascadeClusterHlrResultExtendMjhl) cascadeClusterHlrResult.getCascadeClusterHlrResultExtend();
        CascadeClusterResultExtendDevHj cascadeClusterResultExtendDevHj = new CascadeClusterResultExtendDevHj(resultExtendMjhl.getGoldenPositionList());

        // 2. 計算消除結果列表
        List<CascadeResult> cascadeResultList = this.cascadeResultPgr.packageCascadeResultList(cascadeClusterHlrResult.getCascadeHlrResultList());

        // 3. 回傳
        return new CascadeClusterResult(
                cascadeClusterHlrResult.getTotalWin(), ConstMathSlot.CascadeClusterType.MJHL,
                cascadeClusterResultExtendDevHj,
                cascadeResultList);
    }
}
