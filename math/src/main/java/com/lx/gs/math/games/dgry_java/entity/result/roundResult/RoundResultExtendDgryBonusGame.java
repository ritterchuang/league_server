package com.lx.gs.math.games.dgry_java.entity.result.roundResult;

import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;
import com.lx.gs.math.games.dgry_java.entity.config.DgryBonusGameDisplayType;

// 客端客製遊戲局額外結果帝国榮耀額外遊戲
public class RoundResultExtendDgryBonusGame extends RoundResultExtend {
    private final DgryBonusGameDisplayType bonusGameResults;

    public RoundResultExtendDgryBonusGame(double totalWin, DgryBonusGameDisplayType bonusGameResults) {
        super(totalWin);
        this.bonusGameResults = bonusGameResults;
    }
    public DgryBonusGameDisplayType getBonusGameResults() {
        return bonusGameResults;
    }
}
