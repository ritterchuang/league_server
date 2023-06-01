package com.lx.gs.math.games.qznn_ksz_java.entity;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;

import java.util.List;
import java.util.Map;

// 看三張搶莊牛牛預遊戲結果
public class PreGameAdjustResultQznnKszJava extends PreGameAdjustResult {
    private final Map<Integer, List<ICard>> unTakenCardListMap; // 預發牌結果 <玩家座位， 玩家手牌>

    public PreGameAdjustResultQznnKszJava(Map<Integer, List<ICard>> unTakenCardListMap) {
        super();
        this.unTakenCardListMap = unTakenCardListMap;
    }

    public Map<Integer, List<ICard>> getAllPlayerUnTakenCardListMap() {
        return unTakenCardListMap;
    }
}
