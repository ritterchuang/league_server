package org.lsj.gs.math.core.card.stackCtr.lhStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 龍虎牌型計算器
public class LhStackCtr {
    private Map<Integer, LhStack> stackMap; // 龍虎牌型對應表

    public LhStackCtr() {
        this.stackMap = new HashMap<>();
    }

    public Map<Integer, LhStack> calculateStackMap(Map<Integer, List<ICard>> unTakenCardListMap) {
        return unTakenCardListMap.keySet().stream().collect(Collectors.toMap(
                areaId -> areaId, areaId -> new LhStack(unTakenCardListMap.get(areaId))
        ));
    }

    public Map<Integer, LhStack> getStackMap() {
        return stackMap;
    }

    public void setStackMap(Map<Integer, LhStack> stackMap) {
        this.stackMap = stackMap;
    }

    // 重設
    public void reset() {
        this.stackMap.clear();
    }
}
