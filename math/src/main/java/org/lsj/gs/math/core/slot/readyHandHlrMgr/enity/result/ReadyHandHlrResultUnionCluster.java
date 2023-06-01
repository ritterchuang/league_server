package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result;

import java.util.Collections;
import java.util.List;

// 聽牌處理者結果聯集後群集
public class ReadyHandHlrResultUnionCluster {
    private final List<ReadyHandHlrResult> readyHandHlrResultList; // 聽牌處理者結果列表

    public ReadyHandHlrResultUnionCluster(List<ReadyHandHlrResult> readyHandHlrResultList) {
        this.readyHandHlrResultList = readyHandHlrResultList;
    }

    public ReadyHandHlrResultUnionCluster() {
        this.readyHandHlrResultList = Collections.emptyList();
    }

    public List<ReadyHandHlrResult> getReadyHandHlrResultList() {
        return readyHandHlrResultList;
    }
}
