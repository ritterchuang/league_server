package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 畫面計算結果
public class GameCtrResult {
    private final double totalWin; // 總得分
    private final ConstMathSlot.GameHitType gameHitType; // 遊戲打擊類型
    private final GameCtrResultExtend gameCtrResultExtend; // 遊戲額外算分資訊

    public GameCtrResult(double totalWin, ConstMathSlot.GameHitType gameHitType, GameCtrResultExtend gameCtrResultExtend) {
        this.totalWin = totalWin;
        this.gameHitType = gameHitType;
        this.gameCtrResultExtend = gameCtrResultExtend;
    }

    public GameCtrResult() {
        this.totalWin = 0.0;
        this.gameHitType = ConstMathSlot.GameHitType.INVALID;
        this.gameCtrResultExtend = new GameCtrResultExtendInvalid();
    }

    // 取得得分位置聯集
    public List<List<Boolean>> getIntegralHitPosition() {
        return this.gameCtrResultExtend.calculateIntegralHitPosition();
    }

    // 取得有破框資訊，得分位置聯集
    public List<List<Boolean>> getIntegralDampHitPosition() {
        return this.gameCtrResultExtend.calculateIntegralDampHitPosition();
    }

    public double getTotalWin() {
        return totalWin;
    }

    public ConstMathSlot.GameHitType getGameHitType() {
        return gameHitType;
    }

    public GameCtrResultExtend getGameCtrResultExtend() {
        return gameCtrResultExtend;
    }
}
