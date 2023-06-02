package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製遊戲算分額外設定路
public class GameHitConfigExtendWayGame extends GameHitConfigExtend {
    private final ConstMathSlot.GameHitDirectionType gameHitDirectionType; // 算分方向類型

    public GameHitConfigExtendWayGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        this.gameHitDirectionType = gameHitDirectionType;
    }

    public ConstMathSlot.GameHitDirectionType getGameHitDirectionType() {
        return gameHitDirectionType;
    }
}
