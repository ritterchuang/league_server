package org.lsj.gs.math.games.sg_java.entity;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;

import java.util.List;
import java.util.Map;

// 新三公預遊戲結果
public class PreGameAdjustResultSgJava extends PreGameAdjustResult {
    private final Map<Integer, List<ICard>> unTakenCardListMap; // 預發牌結果 <玩家座位， 玩家手牌>

    public PreGameAdjustResultSgJava(Map<Integer, List<ICard>> unTakenCardListMap) {
        super();
        this.unTakenCardListMap = unTakenCardListMap;
    }

    public Map<Integer, List<ICard>> getAllPlayerUnTakenCardListMap() {
        return unTakenCardListMap;
    }
}
