package org.lsj.gs.math.games.dgry_java.entity.result.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;
import org.lsj.gs.math.games.dgry_java.entity.config.DgryBonusGameDisplayType;

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
