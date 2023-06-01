package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity;

import java.util.List;

// 基礎畫面與重轉畫面資訊
public class BaseScreenReSpinScreenBox {
    private final List<int[]> screenList; // 畫面
    private final int weight; // 權重

    public BaseScreenReSpinScreenBox(List<int[]> screenList, int weight) {
        this.screenList = screenList;
        this.weight = weight;
    }

    public List<int[]> getScreenList() {
        return screenList;
    }

    public int getWeight() {
        return weight;
    }
}
