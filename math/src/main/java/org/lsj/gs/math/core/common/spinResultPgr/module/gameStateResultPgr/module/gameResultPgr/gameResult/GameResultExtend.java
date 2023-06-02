package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製算分額外結果
public class GameResultExtend {
    protected final ConstMathSlot.GameHitDirectionType gameHitDirectionType; // 算分方向類型

    public GameResultExtend(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        this.gameHitDirectionType = gameHitDirectionType;
    }

    public ConstMathSlot.GameHitDirectionType getGameHitDirectionType() {
        return gameHitDirectionType;
    }
}
