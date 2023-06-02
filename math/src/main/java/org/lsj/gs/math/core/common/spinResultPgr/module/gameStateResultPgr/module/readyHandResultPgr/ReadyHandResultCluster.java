package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr;

import java.util.Collections;
import java.util.List;

// 聽牌結果群集
public class ReadyHandResultCluster {
    private List<ReadyHandResult> readyHandResultList; // 聽牌結果列表

    public ReadyHandResultCluster(List<ReadyHandResult> readyHandResultList) {
        this.readyHandResultList = readyHandResultList;
    }

    public ReadyHandResultCluster() {
        this.readyHandResultList = Collections.emptyList();
    }

    public List<ReadyHandResult> getReadyHandResultList() {
        return readyHandResultList;
    }
}
