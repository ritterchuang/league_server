package org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 遊戲輸入計算者設定
public class GameStateInputConfig {
    private final ConstMathSlot.GameStateInputType gameStateInputType; // 遊戲額外輸入類型
    private final GameStateInputConfigExtend gameStateInputConfigExtend; // 遊戲額外輸入額外設定

    public GameStateInputConfig(ConstMathSlot.GameStateInputType gameStateInputType, GameStateInputConfigExtend gameStateInputConfigExtend) {
        this.gameStateInputType = gameStateInputType;
        this.gameStateInputConfigExtend = gameStateInputConfigExtend;
    }

    public ConstMathSlot.GameStateInputType getGameStateInputType() {
        return gameStateInputType;
    }

    public GameStateInputConfigExtend getGameStateInputConfigExtend() {
        return gameStateInputConfigExtend;
    }
}
