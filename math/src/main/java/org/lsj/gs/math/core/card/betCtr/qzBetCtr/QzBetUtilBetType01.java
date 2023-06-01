package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;

import java.util.ArrayList;
import java.util.List;

public class QzBetUtilBetType01 implements IQzBetUtil {

    @Override
    public List<Integer> calculateCanBetRateListByPlayer(GamePlayerHlr gamePlayerHlr, QzBetCtrConfig qzBetCtrConfig, int bankChair, int playerChair, int bankerRate) {
        // 1. 取得參數
        QzBetCtrConfigBetType01 configBetType01 = (QzBetCtrConfigBetType01) qzBetCtrConfig;
        int maxRateConfig = configBetType01.getMaxRate();
        int specifyRate = configBetType01.getSpecifyRate();
        double playerMoney = gamePlayerHlr.getPlayerBeginMoney(playerChair);
        double bankerMoney = gamePlayerHlr.getPlayerBeginMoney(bankChair);
        double baseScore = configBetType01.getBaseScore();
        int playerCount = gamePlayerHlr.getPlayerCount();

        // 2. 計算最大倍數
        int maxRate = Math.min(maxRateConfig,
                               Math.min(
                                       (int)(bankerMoney / (baseScore * (playerCount - 1) * bankerRate * specifyRate))
                                     , (int)(playerMoney / (baseScore * bankerRate))));


        // 3. 計算倍數列表
        return this.calculateBetRateList(maxRate, configBetType01.getMinRate(), configBetType01.getThresholdValue());
    }

    // 計算下注倍數
    private List<Integer> calculateBetRateList(int maxRate, int minRate, int thresholdValue) {
        // 1. 計算倍數區間
        int bet1 = (maxRate - minRate) / thresholdValue + minRate;
        int bet2 = (maxRate - minRate) * 2 / thresholdValue + minRate;
        int bet3 = (maxRate - minRate) * 3 / thresholdValue + minRate;

        // 2. 依照倍數區間計算結果
        List<Integer> result = new ArrayList<>();
        result.add(minRate);
        if (bet1 > minRate)
            result.add(bet1);
        if (bet2 > minRate && bet2 > bet1)
            result.add(bet2);
        if (bet3 > minRate && bet3 > bet2)
            result.add(bet3);
        if (maxRate > minRate && maxRate > bet3)
            result.add(maxRate);

        return result;
    }
}
