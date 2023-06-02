package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.result;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.DampScreenBox;

import java.util.List;

// 倍數表演額外結果 基礎遊戲 + 重轉
public class FiniteAwardPoolResultExtendBaseReSpin extends FiniteAwardPoolResultExtend {
    private final int totalOdds; // 總倍數
    private final List<DampScreenBox> dampScreenBoxList; // 畫面結果列表 [第幾個畫面] = 畫面結果

    public FiniteAwardPoolResultExtendBaseReSpin(int totalOdds, List<DampScreenBox> dampScreenBoxList) {
        this.totalOdds = totalOdds;
        this.dampScreenBoxList = dampScreenBoxList;
    }

    public int getTotalOdds() {
        return totalOdds;
    }

    public List<DampScreenBox> getDampScreenBoxList() {
        return dampScreenBoxList;
    }
}
