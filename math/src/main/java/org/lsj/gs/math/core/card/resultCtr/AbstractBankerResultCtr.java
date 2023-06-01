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

// 搶莊類的抽象算分結果計算器
public abstract class AbstractBankerResultCtr extends AbstractResultCtr {
    public AbstractBankerResultCtr(int feeType, double gameRate, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(feeType, gameRate, gamePlayerHlr, poolCtr, tableUtil);
    }

    public abstract Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, AbstractStack> stackPerPlayer, Map<Integer, Integer> betRates, int bankerRate, int bankerChairIndex);


    //* 計算輸贏結果相關 *//

    // 計算所有閒家輸贏結果
    protected Map<Integer, UidScore> calculatePlayerUidScoreMap(Map<Integer, Double> correctScoreMap) {
        return correctScoreMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.calculatePlayerUidScore(entry.getKey(), entry.getValue())));
    }

    // 計算莊家輸贏結果
    protected UidScore calculateBankerUidScore(Map<Integer, UidScore> playerUidScore, Map<Integer, Double> playerUnCorrectUnFeeScoreMap, int bankerChairIndex) {
        // 1. 取得莊家識別碼
        int uid = super.gamePlayerHlr.getAllGamePlayerMap().get(bankerChairIndex).getUid();

        // 2. 計算莊家校正分數
        double bankerCorrectUnFeeScore = this.calculateBankerCorrectUnFeeScore(playerUnCorrectUnFeeScoreMap, bankerChairIndex);

        // 3. 計算莊家手續費
        double fee = super.calculateFee(bankerCorrectUnFeeScore);

        // 4. 計算莊家淨利
        double score = this.calculateScore(bankerCorrectUnFeeScore, fee);

        // 5. 計算莊家有效投注
        double validBet = this.calculateBankerValidBet(playerUidScore);

        // 6. 取得莊家總得分
        double returnAward = this.calculateReturnAward(validBet, score, fee);

        // 7. 封裝
        return new UidScore(bankerChairIndex, uid, validBet, validBet, returnAward, score, fee);
    }

    // 封裝輸贏結果
    protected Map<Integer, UidScore> packageUidScoreMap(Map<Integer, UidScore> playerUidScore, UidScore bankerUidScore, int bankerChairIndex) {
        Map<Integer, UidScore> result = new HashMap<>(playerUidScore);
        result.put(bankerChairIndex, bankerUidScore);
        return result;
    }


    //* 計算得分相關 *//

    // 計算所有閒家校正得分
    protected Map<Integer, Double> calculatePlayerCorrectUnFeeScoreMap(Map<Integer, Double> unCorrectScoreMap, int bankerChairIndex) {
        // 1. 計算所有閒家得分和
        double playerScoreSum = 0.0;
        for (Integer key: unCorrectScoreMap.keySet()) {
            playerScoreSum = MathUtil.add(playerScoreSum, unCorrectScoreMap.get(key));
        }

        // 2. 計算校正值
        double correctCoefficient = this.calculateCorrectCoefficient(playerScoreSum, super.gamePlayerHlr.getPlayerBeginMoney(bankerChairIndex));

        // 3. 回傳
        Map<Integer, Double> result = new HashMap<>();
        unCorrectScoreMap.forEach((chair, unCorrectScore) -> result.put(chair, MathUtil.multiply(unCorrectScore, correctCoefficient)));
        return result;
    }

    // 計算校正係數
    private double calculateCorrectCoefficient(double playerScoreSum, double bankerMoney) {
        // 分母為零防呆
        if(playerScoreSum == 0.0){
            return 1;
        }
        return (Math.abs(MathUtil.divide(bankerMoney, playerScoreSum)) >= 1) ? 1 : (Math.abs(MathUtil.divide(bankerMoney, playerScoreSum)));
    }

    // 計算閒家輸贏結果
    private UidScore calculatePlayerUidScore(int chairIndex, double correctScore) {
        // 1. 取得玩家識別碼
        int uid = super.gamePlayerHlr.getAllGamePlayerMap().get(chairIndex).getUid();

        // 2. 計算手續費率
        double fee = super.calculateFee(correctScore);

        // 3. 計算淨利
        double score = this.calculateScore(correctScore, fee);

        // 4. 計算有效投注
        double validBet = this.calculateValidBet(score, fee);

        // 5. 計算總得分
        double returnAward = this.calculateReturnAward(validBet, score, fee);

        // 6. 封裝
        return new UidScore(chairIndex, uid, validBet, validBet, returnAward, score, fee);
    }

    // 計算有效投注
    private double calculateValidBet(double score, double fee) {
        return MathUtil.moneyScale(MathUtil.add( Math.abs(score), Math.abs(fee)));
    }

    // 計算總得分
    private double calculateReturnAward(double validBet, double score, double fee) {
        return MathUtil.moneyScale(MathUtil.add(validBet, MathUtil.add(score, fee)));
    }

    // 計算淨利
    private double calculateScore(double score, double fee) {
        return MathUtil.moneyScale(MathUtil.subtract(score, fee));
    }

    // 依照閒家未校正未扣手續費分數計算莊家未校正未扣手續費分數
    private double calculateBankerCorrectUnFeeScore(Map<Integer, Double> playerUnCorrectUnFeeScoreMap, int bankerChairIndex) {
        // 1. 計算未校正莊家分數
        double bankerUnCorrectUnFeeScore = 0.0;
        for (Integer key: playerUnCorrectUnFeeScoreMap.keySet()) {
            bankerUnCorrectUnFeeScore = MathUtil.add(bankerUnCorrectUnFeeScore, playerUnCorrectUnFeeScoreMap.get(key));
        }

        // 2. 取得莊家金額
        double bankerMoney = super.gamePlayerHlr.getPlayerBeginMoney(bankerChairIndex);

        // 3. 計算校正後未扣稅莊家輸贏金額
        // 3.1 莊家最多輸攜入金額
        if (bankerUnCorrectUnFeeScore > bankerMoney) {
            return bankerMoney * -1;
        }
        // 3.2 莊家最多贏攜入金額
        if (bankerUnCorrectUnFeeScore * -1 > bankerMoney) {
            return bankerMoney;
        }

        return bankerUnCorrectUnFeeScore * -1;
    }

    // 計算莊家有效投注
    private double calculateBankerValidBet(Map<Integer, UidScore> playerUidScore) {
        double result = 0.0;

        // 計算所有閒家validBet加總
        for (Integer key: playerUidScore.keySet()) {
            result = MathUtil.add(result, playerUidScore.get(key).getValidBet());
        }

        // 無條件捨去到小數第二位
        return MathUtil.moneyScale(result);
    }
}
