package org.lsj.gs.math.games._model_hj_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;

// 客製遊戲局額外結果 模板虎機基本遊戲
public class RoundHlrResultExtendModelHjBaseGame extends RoundHlrResultExtend {

    public RoundHlrResultExtendModelHjBaseGame(double totalWin) {
        super(totalWin);
    }

    public RoundHlrResultExtendModelHjBaseGame() {
        super(0.0);
    }

}
