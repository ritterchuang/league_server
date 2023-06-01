package org.lsj.gs.math.games._develop_hj_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;

// 客製遊戲局額外結果 模板虎機基本遊戲
public class RoundHlrResultExtendDevelopHjBaseGame extends RoundHlrResultExtend {

    public RoundHlrResultExtendDevelopHjBaseGame(double totalWin) {
        super(totalWin);
    }

    public RoundHlrResultExtendDevelopHjBaseGame() {
        super(0.0);
    }

}
