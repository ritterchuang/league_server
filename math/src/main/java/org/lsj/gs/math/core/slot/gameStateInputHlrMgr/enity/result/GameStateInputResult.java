package org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 遊戲輸入結果
public class GameStateInputResult {
    private final ConstMathSlot.GameStateInputType gameStateInputType;
    private final GameStateInputResultExtend gameStateInputResultExtend;

    public GameStateInputResult() {
        this.gameStateInputType = ConstMathSlot.GameStateInputType.DEFAULT;
        this.gameStateInputResultExtend = new GameStateInputResultExtend();
    }

    public GameStateInputResult(ConstMathSlot.GameStateInputType gameStateInputType, GameStateInputResultExtend gameStateInputResultExtend) {
        this.gameStateInputType = gameStateInputType;
        this.gameStateInputResultExtend = gameStateInputResultExtend;
    }

    public ConstMathSlot.GameStateInputType getGameStateInputType() {
        return gameStateInputType;
    }

    public GameStateInputResultExtend getGameStateInputResultExtend() {
        return gameStateInputResultExtend;
    }
}
