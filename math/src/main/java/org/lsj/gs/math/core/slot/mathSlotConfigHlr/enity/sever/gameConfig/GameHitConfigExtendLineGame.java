package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 遊戲算分額外設定線
public class GameHitConfigExtendLineGame extends GameHitConfigExtend {
    private final ConstMathSlot.GameHitDirectionType gameHitDirectionType; // 算分方向類型
    private final List<List<Integer>> linePositionIndexList; // 線中獎位置 [lineId][columnIndex] = rowIndex

    public GameHitConfigExtendLineGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType, List<List<Integer>> linePositionIndexList) {
        this.gameHitDirectionType = gameHitDirectionType;
        this.linePositionIndexList = linePositionIndexList;
    }

    public ConstMathSlot.GameHitDirectionType getGameHitDirectionType() {
        return gameHitDirectionType;
    }

    public List<List<Integer>> getLinePositionIndexList() {
        return linePositionIndexList;
    }
}
