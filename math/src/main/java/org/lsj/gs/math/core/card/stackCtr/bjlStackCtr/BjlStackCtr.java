package org.lsj.gs.math.core.card.stackCtr.bjlStackCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 百家牌型計算器
public class BjlStackCtr {
    private Map<Integer, BjlStack> stackMap; // 百家牌型對應表
    private final CardTypeUtilBjl cardTypeUtil; // 牌型工具包

    public BjlStackCtr() {
        this.stackMap = new HashMap<>();
        this.cardTypeUtil = new CardTypeUtilBjl();
    }

    // 計算區域牌型對應表
    public Map<Integer, BjlStack> calculateStackMap(Map<Integer, List<ICard>> unTakenCardListMap) {
        return unTakenCardListMap.keySet().stream().collect(Collectors.toMap(
                areaId -> areaId, areaId -> new BjlStack(unTakenCardListMap.get(areaId))
        ));
    }

    // 取得區域百家牌型
    public Map<Integer, BjlStack> getStackMap() {
        return stackMap;
    }

    // 設定區域百家牌型
    public void setStackMap(Map<Integer, BjlStack> stackMap) {
        this.stackMap = new HashMap<>(stackMap);
    }


    //* 取得百家牌型點數 *//

    // 計算對應發牌區點數
    public int calculateAreaPint(ConstMathBjl.DealAreaEnum dealAreaEnum) {
        if (stackMap.isEmpty() || dealAreaEnum.equals(ConstMathBjl.DealAreaEnum.INVALID)) {
            return 0;
        }

        return this.cardTypeUtil.calculateCardPointSumModuleBy10(
                stackMap.get(dealAreaEnum.getCode())
                        .getHandCardList());
    }

    // 計算對應發牌區累積點數
    public int[] calculateAccumulateAreaPointArray(ConstMathBjl.DealAreaEnum dealAreaEnum) {
        if (stackMap.isEmpty() || dealAreaEnum.equals(ConstMathBjl.DealAreaEnum.INVALID)) {
            return new int[]{};
        }

        List<ICard> dealCardList = stackMap.get(dealAreaEnum.getCode()).getHandCardList();

        return IntStream.range(0, dealCardList.size())
                .map(endListIndex ->
                        this.cardTypeUtil.calculateCardPointSumModuleBy10(dealCardList.subList(0, endListIndex + 1)))
                .toArray();
    }


    // 重設
    public void reset() {
        this.stackMap.clear();
    }
}
