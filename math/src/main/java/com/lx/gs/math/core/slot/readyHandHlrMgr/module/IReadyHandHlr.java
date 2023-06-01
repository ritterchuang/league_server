package com.lx.gs.math.core.slot.readyHandHlrMgr.module;

import com.lx.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import com.lx.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultCluster;

import java.util.List;
import java.util.Optional;

// 聽牌處理器介面
public interface IReadyHandHlr {

    // 計算聽牌結果群集
    Optional<ReadyHandHlrResultCluster> calculateReadyHandHlrResultCluster(List<List<Integer>> screenSymbol, ProgressHlrResult progressHlrResult);
}
