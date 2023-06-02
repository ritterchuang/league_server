package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;

import java.util.ArrayList;
import java.util.List;

public class QzBetUtilBetType02 implements IQzBetUtil {

    @Override
    public List<Integer> calculateCanBetRateListByPlayer(GamePlayerHlr gamePlayerHlr, QzBetCtrConfig qzBetCtrConfig, int bankChair, int playerChair, int bankerRate) {
        // 1. 取得參數
        QzBetCtrConfigBetType02 configBetType02 = (QzBetCtrConfigBetType02) qzBetCtrConfig;
        double playerMoney = gamePlayerHlr.getPlayerBeginMoney(playerChair);
        double baseScore = configBetType02.getBaseScore();
        int playerCount = gamePlayerHlr.getPlayerCount();

        // 2. 計算最大倍數
        int maxRate = Math.min(
                                bankerRate / (playerCount - 1)
                               ,(int)(playerMoney / baseScore));


        // 3. 計算倍數列表
        return this.calculateBetRateList(maxRate, configBetType02.getMinRate(), configBetType02.getThresholdValue());
    }

    // 計算下注倍數 TODO 可抽
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
