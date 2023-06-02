package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult;

import java.util.List;

// 標誌額外打擊結果位置
public class SymbolHitResultExtendPositionBoolean extends SymbolHitResultExtend{
    private final List<List<Boolean>> screenHitPosition; // 畫面中獎位置

    public SymbolHitResultExtendPositionBoolean(List<List<Boolean>> screenHitPosition) {
        this.screenHitPosition = screenHitPosition;
    }

    public List<List<Boolean>> getScreenHitPosition() {
        return screenHitPosition;
    }
}
