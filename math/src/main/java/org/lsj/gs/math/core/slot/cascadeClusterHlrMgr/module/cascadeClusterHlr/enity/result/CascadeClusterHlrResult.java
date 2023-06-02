package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;

import java.util.List;

// 消除集合處理者結果
public class CascadeClusterHlrResult {
    private final double totalWin; // 總得分
    private final ConstMathSlot.CascadeClusterType cascadeClusterType; // 消除集合類型
    private final CascadeClusterHlrResultExtend cascadeClusterHlrResultExtend; // 消除集合處理者額外結果
    private final List<CascadeHlrResult> cascadeHlrResultList; // 消除處理者結果列表

    public CascadeClusterHlrResult( double totalWin,
                                    ConstMathSlot.CascadeClusterType cascadeClusterType,
                                    CascadeClusterHlrResultExtend cascadeClusterHlrResultExtend,
                                    List<CascadeHlrResult> cascadeHlrResultList) {
        this.totalWin = totalWin;
        this.cascadeClusterType = cascadeClusterType;
        this.cascadeClusterHlrResultExtend = cascadeClusterHlrResultExtend;
        this.cascadeHlrResultList = cascadeHlrResultList;
    }

    public ConstMathSlot.CascadeClusterType getCascadeClusterType() {
        return cascadeClusterType;
    }

    public CascadeClusterHlrResultExtend getCascadeClusterHlrResultExtend() {
        return cascadeClusterHlrResultExtend;
    }

    public List<CascadeHlrResult> getCascadeHlrResultList() {
        return cascadeHlrResultList;
    }

    public double getTotalWin() {
        return totalWin;
    }
}
