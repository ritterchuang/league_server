package org.lsj.gs.math.core.card.resultCtr;

import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// 通比類的算分結果計算器
public abstract class AbstractTonbiResultCtr extends AbstractResultCtr {
    public AbstractTonbiResultCtr(int feeType, double gameRate, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(feeType, gameRate, gamePlayerHlr, poolCtr, tableUtil);
    }

    public abstract Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, AbstractStack> stackPerPlayer, Map<Integer, Integer> betRates);


    //* 輸贏結果相關 *//

    // 計算所有輸家輸贏結果
    protected Map<Integer, UidScore> calculateLoserUidScoreMap(Map<Integer, Double> loserScoreMap, int winnerChairIndex) {
        return loserScoreMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry ->
                                this.calculateLoserUidScore(entry.getKey(), winnerChairIndex, entry.getValue())));
    }

    // 計算贏家輸贏結果
    protected UidScore calculateWinnerUidScore(Map<Integer, Double> loserScoreMap, int winnerChairIndex) {
        // 1. 取得玩家識別碼
        int uid = super.gamePlayerHlr.getAllGamePlayerMap().get(winnerChairIndex).getUid();

        // 2. 計算贏家得分
        double winnerUnFeeScore = this.calculateWinnerUnFeeScore(loserScoreMap);

        // 3. 計算手續費率
        double fee = super.calculateFee(winnerUnFeeScore);

        // 4. 計算淨利
        double score = this.calculateScore(winnerUnFeeScore, fee);

        // 5. 計算有效投注
        double validBet = this.calculateValidBet(winnerChairIndex, winnerChairIndex, score, fee);

        // 6. 計算總得分
        double returnAward = this.calculateReturnAward(validBet, score, fee);

        // 7. 封裝
        return new UidScore(winnerChairIndex, uid, validBet, validBet, returnAward, score, fee);
    }

    // 封裝結果
    protected Map<Integer, UidScore> packageUidScoreMap(Map<Integer, UidScore> loserUidScore, UidScore winnerUidScore, int winnerChairIndex) {
        Map<Integer, UidScore> result = new HashMap<>(loserUidScore);
        result.put(winnerChairIndex, winnerUidScore);
        return result;
    }


    // 計算閒家輸贏結果
    private UidScore calculateLoserUidScore(int playerChairIndex, int winnerChairIndex, double loseScore) {
        // 1. 取得玩家識別碼
        int uid = super.gamePlayerHlr.getAllGamePlayerMap().get(playerChairIndex).getUid();

        // 2. 計算手續費率
        double fee = super.calculateFee(loseScore);

        // 3. 計算淨利
        double score = this.calculateScore(loseScore, fee);

        // 4. 計算有效投注
        double validBet = this.calculateValidBet(playerChairIndex, winnerChairIndex, score, fee);

        // 5. 計算總得分
        double returnAward = this.calculateReturnAward(validBet, score, fee);

        // 6. 封裝
        return new UidScore(playerChairIndex, uid, validBet, validBet, returnAward, score, fee);
    }

    // 計算贏家總得分
    private double calculateWinnerUnFeeScore(Map<Integer, Double> loserScoreMap) {
        double result = 0.0;

        for (Integer key: loserScoreMap.keySet()) {
            result = MathUtil.add(result, Math.abs(loserScoreMap.get(key)));
        }

        return result;
    }

    // 計算有效投注
    private double calculateValidBet(int playerChairIndex, int winnerChairIndex, double score, double fee) {
        if (playerChairIndex != winnerChairIndex) {
            return MathUtil.moneyScale(MathUtil.add( Math.abs(score), Math.abs(fee)));
        }

        double winnerScore = MathUtil.add( Math.abs(score), Math.abs(fee));
        return MathUtil.moneyScale(MathUtil.divide(winnerScore, super.gamePlayerHlr.getPlayerCount() - 1));
    }

    // 計算總得分
    private double calculateReturnAward(double validBet, double score, double fee) {
        return MathUtil.moneyScale(MathUtil.add(validBet, MathUtil.add(score, fee)));
    }

    // 計算淨利
    private double calculateScore(double score, double fee) {
        return MathUtil.moneyScale(MathUtil.subtract(score, fee));
    }
}
