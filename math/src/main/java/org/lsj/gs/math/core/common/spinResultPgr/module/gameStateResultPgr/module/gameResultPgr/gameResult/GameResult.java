package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 遊戲算分結果
public class GameResult {
    private final double totalWin; // 總得分
    private final ConstMathSlot.GameHitType gameHitType; // 遊戲算分類型
    private final GameResultExtend gameResultExtend; // 客製遊戲算分額外結果

    public GameResult(double totalWin, ConstMathSlot.GameHitType gameHitType, GameResultExtend gameResultExtend) {
        this.totalWin = totalWin;
        this.gameHitType = gameHitType;
        this.gameResultExtend = gameResultExtend;
    }

    public GameResult() {
        this.totalWin = 0.0;
        this.gameHitType = ConstMathSlot.GameHitType.INVALID;
        this.gameResultExtend = new GameResultExtendInvalid();
    }

    public ConstMathSlot.GameHitType getGameHitType() {
        return gameHitType;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public GameResultExtend getGameResultExtend() {
        return gameResultExtend;
    }
}
