package org.lsj.gs.math.core.card.resultCtr.jhResultCtr;

import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.ConstJhStack;

import java.util.Map;

// 金花結果計算器設定
public class JhBaiRenResultCtrConfig {
    private final int areaCount; // 區域個數
    private final int feeType; // 手續費類型
    private final double gameRate; // 手續費率 百分位
    private final Map<ConstJhStack.JhTypeEnumCommon, Integer> cardTypeRateMap; // 牌型賠率表

    public JhBaiRenResultCtrConfig(int areaCount, int feeType, double gameRate, Map<ConstJhStack.JhTypeEnumCommon, Integer> cardTypeRateMap) {
        this.areaCount = areaCount;
        this.feeType = feeType;
        this.gameRate = gameRate;
        this.cardTypeRateMap = cardTypeRateMap;
    }

    public int getAreaCount() {
        return areaCount;
    }

    public int getFeeType() {
        return feeType;
    }

    public double getGameRate() {
        return gameRate;
    }

    public Map<ConstJhStack.JhTypeEnumCommon, Integer> getCardTypeRateMap() {
        return cardTypeRateMap;
    }
}
