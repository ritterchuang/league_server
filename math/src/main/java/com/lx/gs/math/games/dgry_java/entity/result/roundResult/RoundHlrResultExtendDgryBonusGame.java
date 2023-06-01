package com.lx.gs.math.games.dgry_java.entity.result.roundResult;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;
import com.lx.gs.math.games.dgry_java.entity.config.DgryBonusGameDisplayType;

// 客製遊戲局額外結果帝国榮耀額外遊戲
public class RoundHlrResultExtendDgryBonusGame extends RoundHlrResultExtend {
    private final DgryBonusGameDisplayType bonusGameResults;

    public RoundHlrResultExtendDgryBonusGame(double totalWin, DgryBonusGameDisplayType bonusGameResults) {
        super(totalWin);
        this.bonusGameResults = bonusGameResults;
    }

    public DgryBonusGameDisplayType getBonusGameResults() {
        return bonusGameResults;
    }
}
