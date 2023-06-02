package org.lsj.gs.math.core.slot.readyHandHlrMgr.module;

import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultCluster;

import java.util.List;
import java.util.Optional;

// 聽牌處理者 非法
public class ReadyHandHlrInvalid implements IReadyHandHlr{

    @Override
    public Optional<ReadyHandHlrResultCluster> calculateReadyHandHlrResultCluster(List<List<Integer>> screenSymbol, ProgressHlrResult progressHlrResult) {
        return Optional.empty();
    }
}
