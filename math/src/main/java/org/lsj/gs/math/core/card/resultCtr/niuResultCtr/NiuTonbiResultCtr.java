package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.resultCtr.AbstractTonbiResultCtr;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 通比牛牛結果計算器
public class NiuTonbiResultCtr extends AbstractTonbiResultCtr {
    private final NiuTonbiResultCtrConfig config; // 算分結果計算器設定

    public NiuTonbiResultCtr(NiuTonbiResultCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config.getFeeType(), config.getGameRate(), gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }

    //* 輸贏結果相關 *//

    // 取得所有玩家淨利
    public double[] getAllPlayerScoreArray() {
        // 1. 建立空間
        List<Double> playerScoreList = new ArrayList<>();
        Stream.iterate(0.0, i -> 0.0).limit(this.config.getMaxUser()).forEach((playerScoreList::add));

        // 2. 設定值
        super.uidScoreMap.forEach((key, value) -> playerScoreList.set(key, value.getScore()));

        return playerScoreList.stream().mapToDouble(i -> i).toArray();
    }


    //* 計算結果相關 *//

    // 計算玩家輸贏結果
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, AbstractStack> abstractStackPerPlayer, Map<Integer, Integer> betRateMap) {
        // 1. 轉型
        Map<Integer, AbstractNiuStack> abstractNiuStackPerPlayer = this.calculateAbstractNiuStackPerPlayer(abstractStackPerPlayer);

        // 2. 計算贏家座位
        int winnerChairIndex = this.calculateWinnerChairIndex(abstractNiuStackPerPlayer);

        // 3. 計算輸家贏分
        Map<Integer, Double> loserScoreMap = this.calculateLoserScoreMap(winnerChairIndex, abstractNiuStackPerPlayer, betRateMap);

        // 4. 計算輸家輸贏結果
        Map<Integer, UidScore> loserUidScoreMap = super.calculateLoserUidScoreMap(loserScoreMap, winnerChairIndex);

        // 5. 計算贏家輸贏結果
        UidScore winnerUidScore = super.calculateWinnerUidScore(loserScoreMap, winnerChairIndex);

        // 6. 封裝
        return super.packageUidScoreMap(loserUidScoreMap, winnerUidScore, winnerChairIndex);
    }

    // 轉型 **
    private Map<Integer, AbstractNiuStack> calculateAbstractNiuStackPerPlayer(Map<Integer, AbstractStack> abstractStackPerPlayer) {
        return abstractStackPerPlayer.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (AbstractNiuStack) entry.getValue()
                ));
    }

    // 計算最大牌型座位
    private int calculateWinnerChairIndex(Map<Integer, AbstractNiuStack> abstractNiuStackPerPlayer) {
        return abstractNiuStackPerPlayer.entrySet()
                .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
    }

    // 計算輸家校正得分
    private Map<Integer, Double> calculateLoserScoreMap(int winnerChairIndex, Map<Integer, AbstractNiuStack> abstractNiuStackPerPlayer, Map<Integer, Integer> betRateMap) {
        return abstractNiuStackPerPlayer.entrySet()
                .stream().filter(entry -> entry.getKey() != winnerChairIndex)
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                        entry -> this.calculateAbsoluteScore(
                                entry.getKey(),
                                winnerChairIndex,
                                abstractNiuStackPerPlayer,
                                betRateMap) * -1
        ));
    }

    // 計算淨利絕對值
    private double calculateAbsoluteScore(int playerChairIndex, int maxStackChairIndex, Map<Integer, AbstractNiuStack> abstractNiuStackPerPlayer, Map<Integer, Integer> betRateMap) {
        // 1. 準備參數
        double winnerMoney = super.gamePlayerHlr.getAllGamePlayerMap().get(maxStackChairIndex).getBeginMoney();
        double playerMoney = super.gamePlayerHlr.getAllGamePlayerMap().get(playerChairIndex).getBeginMoney();
        int winnerTypeRate = this.config.getNiuTypeRateConfig().get(((abstractNiuStackPerPlayer.get(maxStackChairIndex))).getNiuTypeEnumCommon());
        int winnerBetRate = betRateMap.get(maxStackChairIndex);
        int playerBetRate = betRateMap.get(playerChairIndex);
        double baseScore = this.config.getBaseScore();

        // 2. 計算理論最大贏分
        double expectScore = MathUtil.multiply(baseScore, playerBetRate * winnerBetRate * winnerTypeRate);

        // 3. 輸不起機制
        return Math.min(expectScore, Math.min(winnerMoney, playerMoney));
    }


    // 重設
    @Override
    public void reset() {
        super.reset();
    }
}
