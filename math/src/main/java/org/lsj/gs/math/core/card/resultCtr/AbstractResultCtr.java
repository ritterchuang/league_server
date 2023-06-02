package org.lsj.gs.math.core.card.resultCtr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.HashMap;
import java.util.Map;

// 抽象算分結果計算器
public abstract class AbstractResultCtr extends AbstractModule {
    protected Map<Integer, UidScore> uidScoreMap;  // 所有玩家輸贏結果 <座位、輸贏結果>
    protected final int feeType; // 手續費類型
    protected final double gameRate; // 手續費率 百分位
    protected final double MIN_FEE = 0.01; // 最低手續費

    public AbstractResultCtr(int feeType, double gameRate, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.uidScoreMap = new HashMap<>();
        this.feeType = feeType;
        this.gameRate = gameRate;
    }

    //* 存取輸贏相關 *//

    // 取得所有贏家總贏分
    public double getAllWinScore(){
        double result = 0.0;

        for (UidScore uidScore: this.uidScoreMap.values()) {
            if(uidScore.getScore() > 0.0) {
                result = MathUtil.add(result, uidScore.getScore());
            }
        }

        return result;
    }

    // 取得指定玩家淨利
    public double getPlayerScore(int chairIndex) { return this.uidScoreMap.get(chairIndex).getScore(); }

    // 取得真人玩家淨利
    public double getHumanPlayerScore() { return this.getPlayerScore(super.gamePlayerHlr.getHumanChairIndex()); }

    // 取得真人輸贏結果
    public UidScore getHumanPlayerUidScore() {
        return this.uidScoreMap.get(this.gamePlayerHlr.getHumanChairIndex());
    }

    // 取得所有玩家輸贏對應表
    public Map<Integer, UidScore> getUidScoreMap(){
        return this.uidScoreMap;
    }

    // 設定玩家輸贏
    public void setUidScoreMap(Map<Integer, UidScore> playerScoreMap) {
        this.uidScoreMap = playerScoreMap;
    }


    //* 手續費相關 *//

    // 計算手續費
    protected double calculateFee(double noFeeScore) {
        if (ConstMathCommon.FeeType.fromType(this.feeType) == ConstMathCommon.FeeType.RATE) {
            return this.calculateFeeByRate(noFeeScore);
        }

        return 0.0;
    }

    // 計算手續費 依照手續費率
    private double calculateFeeByRate(double noFeeScore){
        // 1. 判斷是否收費
        if (noFeeScore <= 0) {
            return 0.0;
        }

        // 2. 計算手續費
        double result = MathUtil.multiply(noFeeScore, MathUtil.divide(this.gameRate, 100.0));

        // 3. 校正手續費
        if (result < this.MIN_FEE) {
            result = this.MIN_FEE;
        }

        return MathUtil.moneyScale(result);
    }


    //* 重設相關 *//

    // 重設
    @Override
    public void reset() {
        this.uidScoreMap.clear();
    }
}
