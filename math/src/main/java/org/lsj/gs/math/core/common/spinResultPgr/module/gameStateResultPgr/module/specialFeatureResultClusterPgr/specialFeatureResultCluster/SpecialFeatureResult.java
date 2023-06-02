package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult.SymbolHitResultExtend;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端特殊事件結果
public class SpecialFeatureResult {
    private final double totalWin; // 總得分

    @JsonIgnore
    private final ConstMathSlot.SpecialFeatureType specialFeatureType; // 特殊事件類型
    private final ConstPgrSlot.ClientSymbolHitType symbolHitType; // 中獎位置表示類型
    private final SymbolHitResultExtend symbolHitResultExtend; // 客製化面中獎位置結果

    public SpecialFeatureResult(double totalWin, ConstMathSlot.SpecialFeatureType specialFeatureType, ConstPgrSlot.ClientSymbolHitType clientSymbolHitType, SymbolHitResultExtend symbolHitResultExtend) {
        this.totalWin = totalWin;
        this.specialFeatureType = specialFeatureType;
        this.symbolHitType = clientSymbolHitType;
        this.symbolHitResultExtend = symbolHitResultExtend;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public ConstMathSlot.SpecialFeatureType getSpecialFeatureType() {
        return specialFeatureType;
    }

    public ConstPgrSlot.ClientSymbolHitType getSymbolHitType() {
        return symbolHitType;
    }

    public SymbolHitResultExtend getSymbolHitResultExtend() {
        return symbolHitResultExtend;
    }
}
