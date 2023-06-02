package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 抽象牛牛結果計算器設定
public abstract class AbstractNiuResultCtrConfig {
    private final int maxUser; // 最大玩家數
    private final double baseScore; // 底注
    private final int feeType; // 手續費類型
    private final double gameRate; // 手續費率
    private final Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig; // 牛型設定

    public AbstractNiuResultCtrConfig(int maxUser, double baseScore, int feeType, double gameRate, Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        this.maxUser = maxUser;
        this.baseScore = baseScore;
        this.feeType = feeType;
        this.gameRate = gameRate;
        this.niuTypeRateConfig = niuTypeRateConfig;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public int getFeeType() {
        return feeType;
    }

    public double getGameRate() {
        return gameRate;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeRateConfig() {
        return niuTypeRateConfig;
    }
}
