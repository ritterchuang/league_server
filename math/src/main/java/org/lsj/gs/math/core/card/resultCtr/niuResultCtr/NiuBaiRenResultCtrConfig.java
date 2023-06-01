package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 百人牛牛結果計算器設定
public class NiuBaiRenResultCtrConfig {
    private final int betAreaCount; // 區域個數
    private final int feeType; // 手續費類型
    private final double gameRate; // 手續費率 百分位
    private final Map<ConstNiu.NiuTypeEnumCommon, Integer> cardTypeRateMap; // 牌型賠率表

    public NiuBaiRenResultCtrConfig(int betAreaCount, int feeType, double gameRate, Map<ConstNiu.NiuTypeEnumCommon, Integer> cardTypeRateMap) {
        this.betAreaCount = betAreaCount;
        this.feeType = feeType;
        this.gameRate = gameRate;
        this.cardTypeRateMap = cardTypeRateMap;
    }

    public int getBetAreaCount() {
        return betAreaCount;
    }

    public int getFeeType() {
        return feeType;
    }

    public double getGameRate() {
        return gameRate;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, Integer> getCardTypeRateMap() {
        return cardTypeRateMap;
    }
}
