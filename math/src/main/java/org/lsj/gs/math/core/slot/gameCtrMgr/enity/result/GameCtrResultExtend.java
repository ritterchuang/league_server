package org.lsj.gs.math.core.slot.gameCtrMgr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 畫面計算結果父類別
public abstract class GameCtrResultExtend {
    protected final ConstMathSlot.GameHitDirectionType gameHitDirectionType; // 算分方向類型

    // 計算所有得分位置聯集
    public abstract List<List<Boolean>> calculateIntegralHitPosition();

    // 計算所有得分破框位置聯集
    public abstract List<List<Boolean>> calculateIntegralDampHitPosition();

    public GameCtrResultExtend(ConstMathSlot.GameHitDirectionType gameHitDirectionType) {
        this.gameHitDirectionType = gameHitDirectionType;
    }

    public ConstMathSlot.GameHitDirectionType getGameHitDirectionType() {
        return gameHitDirectionType;
    }
}
