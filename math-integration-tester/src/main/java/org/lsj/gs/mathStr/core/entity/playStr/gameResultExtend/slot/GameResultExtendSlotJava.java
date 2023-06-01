package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

// 虎機客製遊戲結果
public class GameResultExtendSlotJava extends GameResultExtend {
    private final double validBet; // 有效押注
    private final double score; // 玩家淨利
    private final double credit; // 玩家有效投注
    private final GameFlowHlrResult gameFlowHlrResult; // 玩家打擊結果

    public GameResultExtendSlotJava(double validBet, double score, double credit, GameFlowHlrResult gameFlowHlrResult) {
        this.validBet = validBet;
        this.score = score;
        this.credit = credit;
        this.gameFlowHlrResult = gameFlowHlrResult;
    }

    public double getValidBet() {
        return validBet;
    }

    public double getScore() {
        return score;
    }

    public double getCredit() {
        return credit;
    }

    public GameFlowHlrResult getGameFlowHlrResult() {
        return gameFlowHlrResult;
    }
}
