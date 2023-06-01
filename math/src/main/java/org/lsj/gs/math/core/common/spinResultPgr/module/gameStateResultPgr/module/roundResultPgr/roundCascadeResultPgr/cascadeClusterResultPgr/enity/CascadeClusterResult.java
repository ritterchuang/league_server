package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 消除集合結果
public class CascadeClusterResult {
    private final double totalWin; // 總得分
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 消除集合類型
    private final CascadeClusterResultExtend cascadeClusterResultExtend; // 消除集合額外結果
    private final List<CascadeResult> cascadeResultList; // 消除結果列表

    public CascadeClusterResult(double totalWin, ConstMathSlot.CascadeClusterType cascadeClusterType, CascadeClusterResultExtend cascadeClusterResultExtend, List<CascadeResult> cascadeResultList) {
        this.cascadeClusterType = cascadeClusterType;
        this.totalWin = totalWin;
        this.cascadeClusterResultExtend = cascadeClusterResultExtend;
        this.cascadeResultList = cascadeResultList;
    }

    public ConstMathSlot.CascadeClusterType getCascadeClusterType() {
        return cascadeClusterType;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public CascadeClusterResultExtend getCascadeClusterResultExtend() {
        return cascadeClusterResultExtend;
    }

    public List<CascadeResult> getCascadeResultList() {
        return cascadeResultList;
    }
}
