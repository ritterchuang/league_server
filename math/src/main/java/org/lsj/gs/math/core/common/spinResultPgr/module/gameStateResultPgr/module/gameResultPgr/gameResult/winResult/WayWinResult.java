package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.winResult;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult.SymbolHitResultExtend;

// 客端路得分結果
public class WayWinResult {
    private final double totalWin; // 總得分

    private final int payComboId; // 賠率組合識別碼
    private final int hitNumber; // 連線數
    @JsonIgnore
    private final int hitCount; // 堆疊數
    @JsonIgnore
    private final int hitOdds; // 獎項倍數

    private final ConstPgrSlot.ClientSymbolHitType symbolHitType; // 中獎位置表示類型
    private final SymbolHitResultExtend symbolHitResultExtend; // 客製化面中獎位置結果

    public WayWinResult(double totalWin, int payComboId, int hitNumber, int hitCount, int hitOdds, ConstPgrSlot.ClientSymbolHitType symbolHitType, SymbolHitResultExtend symbolHitResultExtend) {
        this.totalWin = totalWin;
        this.payComboId = payComboId;
        this.hitNumber = hitNumber;
        this.hitCount = hitCount;
        this.hitOdds = hitOdds;
        this.symbolHitType = symbolHitType;
        this.symbolHitResultExtend = symbolHitResultExtend;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public int getPayComboId() {
        return payComboId;
    }

    public int getHitNumber() {
        return hitNumber;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getHitOdds() {
        return hitOdds;
    }

    public ConstPgrSlot.ClientSymbolHitType getSymbolHitType() {
        return symbolHitType;
    }

    public SymbolHitResultExtend getSymbolHitResultExtend() {
        return symbolHitResultExtend;
    }
}
