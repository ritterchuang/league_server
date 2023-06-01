package com.lx.gs.math.games.ebg_java.entity;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;

import java.util.List;
import java.util.Map;

// 搶莊二八槓預遊戲結果
public class PreGameAdjustResultEbgJava extends PreGameAdjustResult {
    private final Map<Integer, List<ICard>> unTakenCardListMap; // 預發牌結果 <玩家座位， 玩家手牌>

    public PreGameAdjustResultEbgJava(Map<Integer, List<ICard>> unTakenCardListMap) {
        super();
        this.unTakenCardListMap = unTakenCardListMap;
    }

    public Map<Integer, List<ICard>> getAllPlayerUnTakenCardListMap() {
        return unTakenCardListMap;
    }
}
