package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.initialScreenConfig;

import java.util.List;

// 初始化面額外設定 麻將胡了
public class InitialScreenConfigExtendMjhl extends InitialScreenConfigExtend{
    private final List<List<Integer>> goldenPositionIdList; // 黃金位置座標列表

    public InitialScreenConfigExtendMjhl(List<List<Integer>> goldenPositionIdList) {
        this.goldenPositionIdList = goldenPositionIdList;
    }

    public List<List<Integer>> getGoldenPositionIdList() {
        return goldenPositionIdList;
    }
}
