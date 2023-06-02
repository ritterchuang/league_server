package org.lsj.gs.math.games.mybjl_java.entity;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;

import java.util.List;
import java.util.Map;

// 新免傭百家樂預遊戲結果
public class PreGameAdjustResultMybjlJava extends PreGameAdjustResult {
    private final Map<Integer, List<ICard>> unTakenCardListMap; // 預發牌結果

    public PreGameAdjustResultMybjlJava(Map<Integer, List<ICard>> unTakenCardListMap) {
        this.unTakenCardListMap = unTakenCardListMap;
    }

    public Map<Integer, List<ICard>> getUnTakenCardListMap() {
        return unTakenCardListMap;
    }
}
