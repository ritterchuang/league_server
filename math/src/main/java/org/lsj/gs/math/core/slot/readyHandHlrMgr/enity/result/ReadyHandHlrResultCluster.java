package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result;

import java.util.Collections;
import java.util.List;

// 聽牌處理者結果群集
public class ReadyHandHlrResultCluster {
    private final List<ReadyHandHlrResult> readyHandHlrResultList; // 聽牌處理者結果列表

    public ReadyHandHlrResultCluster(List<ReadyHandHlrResult> readyHandHlrResultList) {
        this.readyHandHlrResultList = readyHandHlrResultList;
    }

    public ReadyHandHlrResultCluster() {
        this.readyHandHlrResultList = Collections.emptyList();
    }

    public List<ReadyHandHlrResult> getReadyHandHlrResultList() {
        return readyHandHlrResultList;
    }
}
