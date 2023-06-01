package org.lsj.gs.math.core.card.resultCtr;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

// 百人類的算分結果計算器
public abstract class AbstractBaiRenResultCtr extends AbstractResultCtr {
    public AbstractBaiRenResultCtr(int feeType, double gameRate, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(feeType, gameRate, gamePlayerHlr, poolCtr, tableUtil);
    }

    //* 計算區域資訊相關 *//

    // 計算玩家得分
    protected UidScore calculateUidScore(int chairIndex, Map<Integer, Integer> areaBetMap, double[] areaNoFeeScoreArray, int areaCount){
        // 1. 取得玩家識別碼
        int uid = this.gamePlayerHlr.getAllGamePlayerMap().get(chairIndex).getUid();

        // 2. 計算各區域 下注金額
        double[] areaBetArray = this.calculateAreaBetArray(areaBetMap, areaCount);

        // 3. 計算各區域 手續費
        double[] areaFeeArray = this.calculateAreaFeeArray(areaNoFeeScoreArray);

        // 4. 計算各區域 總淨利
        double[] areaScoreArray = this.calculateAreaScoreArray(areaNoFeeScoreArray, areaFeeArray);

        // 5. 計算各區域 有效投注
        double[] areaValidBetArray = this.calculateAreaValidBetArray(areaNoFeeScoreArray);

        // 6. 計算各區域 返獎
        double[] areaReturnAwardArray = this.calculateAreaReturnAwardArray(areaBetArray, areaScoreArray, areaFeeArray);

        // 7. 封裝
        return new UidScore(
                chairIndex,
                uid,
                MathUtil.moneyScale(MathUtil.add(areaBetArray)),
                MathUtil.moneyScale(MathUtil.add(areaValidBetArray)),
                MathUtil.moneyScale(MathUtil.add(areaReturnAwardArray)),
                MathUtil.moneyScale(MathUtil.add(areaScoreArray)),
                MathUtil.moneyScale(MathUtil.add(areaFeeArray)));
    }

    // 計算各區域 下注金額
    protected double[] calculateAreaBetArray(Map<Integer, Integer> areaBetMap, int areaCount) {
        return IntStream.range(0, areaCount)
                .mapToDouble(areaId -> areaBetMap.getOrDefault(areaId, 0))
                .toArray();
    }

    // 計算各區域 手續費
    private double[] calculateAreaFeeArray(double[] areaNoFeeScoreArray) {
       return DoubleStream.of(areaNoFeeScoreArray).map(super::calculateFee).toArray();
    }

    // 計算各區域 總淨利
    protected double[] calculateAreaScoreArray(double[] areaNoFeeScoreArray, double[] areaFeeArray) {
        return IntStream.range(0, areaNoFeeScoreArray.length)
                .mapToDouble(areaId -> MathUtil.subtract(areaNoFeeScoreArray[areaId], areaFeeArray[areaId]))
                .toArray();
    }

    // 計算各區域 有效投注
    protected double[] calculateAreaValidBetArray(double[] areaNoFeeScoreArray) {
        return DoubleStream.of(areaNoFeeScoreArray).map(areaNoFeeScore -> Math.abs(areaNoFeeScore)).toArray();
    }

    // 計算各區域 返獎
    protected double[] calculateAreaReturnAwardArray(double[] betArray, double[] areaScoreArray, double[] areaFeeArray) {
        return IntStream.range(0, betArray.length)
                .mapToDouble(
                        areaId ->
                                MathUtil.add(
                                        MathUtil.add(betArray[areaId], areaScoreArray[areaId]),
                                        areaFeeArray[areaId]))
                .toArray();
    }


    //* 重置相關 *//

    // 重設
    @Override
    public void reset() {
        super.reset();
    }
}
