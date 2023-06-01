package org.lsj.gs.math.core.slot.commonInputHlr;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;

import java.util.List;

// 公用輸入處理者
public interface ICommonInputHlr {

    // 計算共用輸入 TODO 參數待確認
    CommonInputResult calculateCommonInput(List<RoundHlrResult> roundHlrResultList);

    // 記錄結果 TODO 參數待確認
    void apply(GameFlowHlrResult gameFlowHlrResult);
}
