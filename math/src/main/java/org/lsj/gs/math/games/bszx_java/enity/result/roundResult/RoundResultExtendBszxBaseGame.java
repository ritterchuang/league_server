package org.lsj.gs.math.games.bszx_java.enity.result.roundResult;


import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;

// 客端客製遊戲局額外結果 寶石之星虎機基本遊戲
public class RoundResultExtendBszxBaseGame extends RoundResultExtend {

    public RoundResultExtendBszxBaseGame(double totalWin) {
        super(totalWin);
    }

    public double getTotalWin() {
        return totalWin;
    }

}
