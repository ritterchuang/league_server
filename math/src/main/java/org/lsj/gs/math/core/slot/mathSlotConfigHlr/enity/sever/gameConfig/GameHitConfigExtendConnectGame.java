package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;
import java.util.Map;

// 遊戲算分額外設定 連接
public class GameHitConfigExtendConnectGame extends GameHitConfigExtend {
    private final ConstMathSlot.GameHitDirectionType gameHitDirectionType; // 算分方向類型
    private final Map<int[], List<int[]>> positionToConnectPositionListMap; // <指定位置, 相連位置列表> 對應表

    public GameHitConfigExtendConnectGame(ConstMathSlot.GameHitDirectionType gameHitDirectionType, Map<int[], List<int[]>> positionToConnectPositionListMap) {
        this.gameHitDirectionType = gameHitDirectionType;
        this.positionToConnectPositionListMap = positionToConnectPositionListMap;
    }

    public ConstMathSlot.GameHitDirectionType getGameHitDirectionType() {
        return gameHitDirectionType;
    }

    public Map<int[], List<int[]>> getPositionToConnectPositionListMap() {
        return positionToConnectPositionListMap;
    }
}
