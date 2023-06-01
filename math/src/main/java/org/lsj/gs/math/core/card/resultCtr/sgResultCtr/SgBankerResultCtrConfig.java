package org.lsj.gs.math.core.card.resultCtr.sgResultCtr;

import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.ConstSgStack;

import java.util.Map;

// 三公結果計算器設定
public class SgBankerResultCtrConfig {
    private final int maxUser; // 最大玩家數
    private final double baseScore; // 底注
    private final int feeType; // 手續費類型
    private final double gameRate; // 手續費率
    private final Map<ConstSgStack.SgTypeEnumCommon, Integer> sgTypeConfig; // 三公牌型設定

    public SgBankerResultCtrConfig(int maxUser, double baseScore, int feeType, double gameRate, Map<ConstSgStack.SgTypeEnumCommon, Integer> sgTypeConfig) {
        this.maxUser = maxUser;
        this.baseScore = baseScore;
        this.feeType = feeType;
        this.gameRate = gameRate;
        this.sgTypeConfig = sgTypeConfig;
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

    public Map<ConstSgStack.SgTypeEnumCommon, Integer> getSgTypeConfig() {
        return sgTypeConfig;
    }
}
