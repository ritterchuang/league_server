package org.lsj.gs.math.games._develop_hj_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;

// 客端客製遊戲局額外結果 模板虎機基本遊戲
public class RoundResultExtendDevelopHjBaseGame extends RoundResultExtend {

    public RoundResultExtendDevelopHjBaseGame(double totalWin) {
        super(totalWin);
    }

    public double getTotalWin() {
        return totalWin;
    }

}
