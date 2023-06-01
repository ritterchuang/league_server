package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr;

import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;

// 累積得分結果包裝者
public class AccumulateWinResultPgr {

    // 包裝客端累積得分結果
    public AccumulateWinResult packageAccumulateWinResult(AccumulateWinCtrResult accumulateWinCtrResult) {
        return new AccumulateWinResult(
                accumulateWinCtrResult.getBeforeAccWin(),
                accumulateWinCtrResult.getAfterAccWin());
    }
}
