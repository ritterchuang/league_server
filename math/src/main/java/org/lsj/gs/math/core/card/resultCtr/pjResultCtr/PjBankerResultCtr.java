package org.lsj.gs.math.core.card.resultCtr.pjResultCtr;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.resultCtr.AbstractBankerResultCtr;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.pjStackCtr.PjStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 牌九結果計算器
public class PjBankerResultCtr extends AbstractBankerResultCtr {
    private final PjBankerResultCtrConfig config; // 算分結果設定

    public PjBankerResultCtr(PjBankerResultCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config.getFeeType(), config.getGameRate(), gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }

    /* 輸贏結果相關 */

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
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, AbstractStack> abstractStackPerPlayer, Map<Integer, Integer> betRateMap, int bankerRate, int bankerChairIndex) {
        // 1. 計算與莊家輸贏的玩家列表
        Map<ConstMathCard.WinType, List<Integer>> winTypeToBankerList = this.calculateWinTypeToBankerList(abstractStackPerPlayer, bankerChairIndex);

        // 2. 計算閒家未校正贏分
        Map<Integer, Double> playerUnCorrectUnFeeScoreMap = this.calculatePlayerUnCorrectUnFeeScoreMap(winTypeToBankerList, abstractStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);

        // 3. 計算閒家校正贏分
        Map<Integer, Double> playerCorrectUnFeeScoreMap = super.calculatePlayerCorrectUnFeeScoreMap(playerUnCorrectUnFeeScoreMap, bankerChairIndex);

        // 4. 計算閒家輸贏結果
        Map<Integer, UidScore> playerUidScoreMap = super.calculatePlayerUidScoreMap(playerCorrectUnFeeScoreMap);

        // 5. 計算莊家輸贏結果
        UidScore bankerUidScore = super.calculateBankerUidScore(playerUidScoreMap, playerUnCorrectUnFeeScoreMap, bankerChairIndex);

        // 6. 封裝
        return super.packageUidScoreMap(playerUidScoreMap, bankerUidScore, bankerChairIndex);
    }

    // 轉型
    private Map<Integer, PjStack> calculatePjStackPerPlayer(Map<Integer, AbstractStack> abstractStackPerPlayer) {
        return abstractStackPerPlayer.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (PjStack) entry.getValue()
        ));
    }

    // 計算與莊家輸贏的玩家列表
    private Map<ConstMathCard.WinType, List<Integer>> calculateWinTypeToBankerList(Map<Integer, AbstractStack> abstractStackPerPlayer, int bankerChairIndex) {
        // 1. 轉型
        Map<Integer, PjStack> pjStackPerPlayer = this.calculatePjStackPerPlayer(abstractStackPerPlayer);

        // 2. 計算輸贏莊家列表
        List<Integer> winBankerList = this.calculateWinBankerList(pjStackPerPlayer, bankerChairIndex);
        List<Integer> lossBankerList = this.calculateLossBankerList(pjStackPerPlayer, bankerChairIndex);
        List<Integer> tieBankerList = this.calculateTieBankerList(pjStackPerPlayer, bankerChairIndex);

        // 3. 封裝結果
        Map<ConstMathCard.WinType, List<Integer>> result = new HashMap<>();
        result.put(ConstMathCard.WinType.WIN, winBankerList);
        result.put(ConstMathCard.WinType.LOSS, lossBankerList);
        result.put(ConstMathCard.WinType.TIE, tieBankerList);

        // 4. 回傳
        return result;

    }

    // 計算贏莊家的玩家列表
    private List<Integer> calculateWinBankerList(Map<Integer, PjStack> pjStackPerPlayer, int bankerChairIndex) {
        return pjStackPerPlayer.entrySet().stream()
                .filter(entry -> entry.getKey() != bankerChairIndex
                        && pjStackPerPlayer.get(entry.getKey()).compareTo(pjStackPerPlayer.get(bankerChairIndex)) > 0)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    // 計算輸莊家的玩家列表
    private List<Integer> calculateLossBankerList(Map<Integer, PjStack> pjStackPerPlayer, int bankerChairIndex) {
        return pjStackPerPlayer.entrySet().stream()
                .filter(entry -> entry.getKey() != bankerChairIndex
                        && pjStackPerPlayer.get(entry.getKey()).compareTo(pjStackPerPlayer.get(bankerChairIndex)) < 0)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    // 計算平莊家的玩家列表
    private List<Integer> calculateTieBankerList(Map<Integer, PjStack> pjStackPerPlayer, int bankerChairIndex) {
        return pjStackPerPlayer.entrySet().stream()
                .filter(entry -> entry.getKey() != bankerChairIndex
                        && pjStackPerPlayer.get(entry.getKey()).compareTo(pjStackPerPlayer.get(bankerChairIndex)) == 0)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    // 計算所有閒家未校正得分
    private Map<Integer, Double> calculatePlayerUnCorrectUnFeeScoreMap(Map<ConstMathCard.WinType, List<Integer>> winTypeToBankerList, Map<Integer, AbstractStack> abstractStackPerPlayer, Map<Integer, Integer> betRateMap, int bankerRate, int bankerChairIndex) {
        // 1. 轉型
        Map<Integer, PjStack> pjStackPerPlayer = this.calculatePjStackPerPlayer(abstractStackPerPlayer);

        // 2. 創建空間
        Map<Integer, Double> result = new HashMap<>();

        // 3. 計算贏莊家結果
        winTypeToBankerList.get(ConstMathCard.WinType.WIN).forEach(chairIndex -> result.put(chairIndex, this.calculateAbsoluteScore(chairIndex, bankerChairIndex, bankerRate, pjStackPerPlayer, betRateMap)));

        // 4. 計算輸莊家結果
        winTypeToBankerList.get(ConstMathCard.WinType.LOSS).forEach(chairIndex -> result.put(chairIndex, this.calculateAbsoluteScore(chairIndex, bankerChairIndex, bankerRate, pjStackPerPlayer, betRateMap) * -1));

        // 5. 計算平局結果
        winTypeToBankerList.get(ConstMathCard.WinType.TIE).forEach(chairIndex -> result.put(chairIndex, 0.0));

        // 6. 回傳
        return result;
    }

    // 計算淨利絕對值
    private double calculateAbsoluteScore(int playerChairIndex, int bankerChairIndex, int bankerRate, Map<Integer, PjStack> pjStackPerPlayer, Map<Integer, Integer> betRateMap) {
        // 1. 準備參數
        double playerMoney = super.gamePlayerHlr.getAllGamePlayerMap().get(playerChairIndex).getBeginMoney();
        int betRate = betRateMap.get(playerChairIndex);
        double baseScore = this.config.getBaseScore();

        // 2. 計算理論最大輸贏
        double expectScore = MathUtil.multiply(baseScore, betRate * bankerRate) ;

        // 3. 輸不起機制
        return Math.min(expectScore, playerMoney);
    }
}
