package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity;

import java.util.List;

// 消除結果 TODO 以後增加 type 處理 mega
public class EliminateResult {
    private final List<List<Integer>> eliminatePositionIdList; // 消除位置列表 [col][index] = positionId
    private List<List<Integer>> savePositionIdList; // 要求客端留存位置列表[col][index] = positionId，讓客端知道消除後新增畫面放置位置

    public EliminateResult(List<List<Integer>> eliminatePositionIdList, List<List<Integer>> savePositionIdList) {
        this.eliminatePositionIdList = eliminatePositionIdList;
        this.savePositionIdList = savePositionIdList;
    }

    public List<List<Integer>> getEliminatePositionIdList() {
        return eliminatePositionIdList;
    }

    public List<List<Integer>> getSavePositionIdList() {
        return savePositionIdList;
    }
}
