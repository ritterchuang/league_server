package org.lsj.gs.math.games.bszx_java.enity.result.roundResult;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend.RoundHlrResultExtend;

// 客製遊戲局額外結果 寶石之星虎機免費遊戲
public class RoundHlrResultExtendBszxFreeGame extends RoundHlrResultExtend {

    public RoundHlrResultExtendBszxFreeGame(double totalWin) {
        super(totalWin);
    }

    public RoundHlrResultExtendBszxFreeGame() {
        super(0.0);
    }
}
