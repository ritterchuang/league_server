package com.lx.gs.math.games.brnn_java.entity;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;

import java.util.List;
import java.util.Map;

// 新百人牛牛預遊戲結果
public class PreGameAdjustResultBrnnJava extends PreGameAdjustResult {
    private final Map<Integer, List<ICard>> unTakenCardListMap; // 預發牌結果

    public PreGameAdjustResultBrnnJava(Map<Integer, List<ICard>> unTakenCardListMap) {
        this.unTakenCardListMap = unTakenCardListMap;
    }

    public Map<Integer, List<ICard>> getUnTakenCardListMap() {
        return unTakenCardListMap;
    }
}
