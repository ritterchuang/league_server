package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.symbolHitResult.SymbolHitResultExtend;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;

import java.util.List;

// 客端中獎標誌處理者
public interface IClientSymbolHitPgr {

    // 計算標誌額外打擊結果
    SymbolHitResultExtend calculateSymbolHitResultExtend(ScreenResult screenResult, List<List<Boolean>> targetPositionList);
}
