package org.lsj.gs.math.games._model_hj_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResultExtend;

// 客端客製遊戲局額外結果 模板虎機免費遊戲
public class RoundResultExtendModelHjFreeGame extends RoundResultExtend {

    public RoundResultExtendModelHjFreeGame(double totalWin) {
        super(totalWin);
    }

    public RoundResultExtendModelHjFreeGame() {
        super(0.0);
    }
}
