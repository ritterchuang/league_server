package org.lsj.gs.math.core.common.gameAdjust.entity;

import java.util.Map;

// 遊戲結果
public class GameAdjustResult {
    private final Map<Integer, UidScore> uidScoreMap; // 所有玩家輸贏結果 <玩家座位、玩家押注得分>
    private final PreGameAdjustResult preGameAdjustResult; // 預發牌結果

    public GameAdjustResult(Map<Integer, UidScore> uidScoreMap, PreGameAdjustResult preGameAdjustResult) {
        this.uidScoreMap = uidScoreMap;
        this.preGameAdjustResult = preGameAdjustResult;
    }

    public Map<Integer, UidScore> getUidScoreMap() {
        return uidScoreMap;
    }

    public PreGameAdjustResult getPreGameResult() {
        return preGameAdjustResult;
    }
}
