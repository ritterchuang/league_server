package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult;

import java.util.Collections;
import java.util.List;

// 標誌額外打擊結果位置
public class SymbolHitResultExtendPositionId extends SymbolHitResultExtend{
    private final List<List<Integer>> hitPositionIdList; // 畫面中獎位置 [column][index] = positionId

    public SymbolHitResultExtendPositionId(List<List<Integer>> hitPositionIdList) {
        this.hitPositionIdList = hitPositionIdList;
    }

    public SymbolHitResultExtendPositionId() {
        this.hitPositionIdList = Collections.emptyList();
    }

    public List<List<Integer>> getHitPositionIdList() {
        return hitPositionIdList;
    }
}
