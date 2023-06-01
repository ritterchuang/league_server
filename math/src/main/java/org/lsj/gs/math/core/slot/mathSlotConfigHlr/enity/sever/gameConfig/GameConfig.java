package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 遊戲算分設定
public class GameConfig {
    private final ConstMathSlot.GameHitType gameHitType; // 算分類型
    private final GameHitConfigExtend gameHitConfigExtend; // 客製算分額外設定

    public GameConfig(ConstMathSlot.GameHitType gameHitType, GameHitConfigExtend gameHitConfigExtend) {
        this.gameHitType = gameHitType;
        this.gameHitConfigExtend = gameHitConfigExtend;
    }

    public ConstMathSlot.GameHitType getGameHitType() {
        return gameHitType;
    }

    public GameHitConfigExtend getGameHitConfigExtend() {
        return gameHitConfigExtend;
    }
}
