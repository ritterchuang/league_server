package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundNormalResultPgr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import com.lx.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientRoundResultPgrConfig;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultNormal;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 客製局結果包裝者一般 預設
public class RoundResultNormalPgrDefault extends AbstractRoundResultNormalPgr {

    public RoundResultNormalPgrDefault(ClientRoundResultPgrConfig config, ITableUtil tableUtil) {
        super(config, tableUtil);
    }

    // 包裝局結果
    public RoundResultNormal packageRoundResult(RoundHlrResult roundHlrResult) {
        // 回傳
        return new RoundResultNormal();
    }

}
