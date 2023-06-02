package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity;

import java.util.List;

public class CascadeClusterResultExtendDevHj extends CascadeClusterResultExtend{
    private final List<List<Integer>> goldenPositionIdList; // 黃金位置列表 [col][index] = golden position Id

    public CascadeClusterResultExtendDevHj(List<List<Integer>> goldenPositionIdList) {
        this.goldenPositionIdList = goldenPositionIdList;
    }

    public List<List<Integer>> getGoldenPositionIdList() {
        return goldenPositionIdList;
    }
}
