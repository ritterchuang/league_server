package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result;

import java.util.List;

// 消除集合處理者額外結果 麻將胡了
public class CascadeClusterHlrResultExtendMjhl extends CascadeClusterHlrResultExtend{
    private List<List<Integer>> goldenPositionList; // 黃金圖標位置 [col][index] = positionId

    public CascadeClusterHlrResultExtendMjhl(List<List<Integer>> goldenPositionList) {
        this.goldenPositionList = goldenPositionList;
    }

    public List<List<Integer>> getGoldenPositionList() {
        return goldenPositionList;
    }
}
