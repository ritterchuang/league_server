package org.lsj.gs.math.core.card.resultCtr.lhdResultCtr;

import org.lsj.gs.math.core.card.resultCtr.AbstractBaiRenResultCtr;
import org.lsj.gs.math.core.card.stackCtr.lhStackCtr.LhStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;
import org.lsj.utils.MathUtil;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// 龍虎結果計算器
public class LhdResultCtr extends AbstractBaiRenResultCtr {
    private final LhdResultCtrConfig config; // 龍虎結果計算器設定
    private final double RETURN_RATE = 0.64; // 返還比例

    public LhdResultCtr(LhdResultCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config.getFeeType(), config.getGameRate(), gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }

    //* 計算輸贏相關 *//

    // 計算玩家得分對應表
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, LhStack> areaStackMap, Map<Integer, Map<Integer, Integer>> allPlayerAreaBetMap) {
        return allPlayerAreaBetMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> this.calculateUidScore(entry.getKey(), areaStackMap, entry.getValue())
                ));
    }

    // 計算玩家得分
    private UidScore calculateUidScore(int chairIndex, Map<Integer, LhStack> stack, Map<Integer, Integer> areaBetMap){
        // 1. 計算各區域 不扣手續費淨利
        double[] areaNoFeeScoreArray = this.calculateAreaNoFeeScoreArray(stack, areaBetMap);

        // 2. 回傳
        return super.calculateUidScore(chairIndex, areaBetMap, areaNoFeeScoreArray, this.config.getAreaCount());
    }

    // 計算各區域 不扣手續費淨利
    public double[] calculateAreaNoFeeScoreArray(Map<Integer, LhStack> stackMap, Map<Integer, Integer> areaBetMap){
        if(Objects.isNull(areaBetMap)){
            return new double[this.config.getAreaCount()];
        }

        switch(ConstLhdJava.BetAreaIdLhdJava.calculateWinBetAreaIdLhdJava(stackMap)) {
            case DRAGON: return this.calculateDragonAreaScoreArray(areaBetMap);
            case TIGER: return this.calculateTigerAreaScoreArray(areaBetMap);
            case TIE: return this.calculateTieAreaScoreArray(areaBetMap);
            default: return new double[this.config.getAreaCount()];
        }
    }

    // 計算龍淨利
    private double[] calculateDragonAreaScoreArray(Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getAreaCount()];

        // 龍區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()),
                            ConstLhdJava.BetAreaIdLhdJava.DRAGON.getRate()
                    );
        }

        // 虎區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()] =
                areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()) * -1;
        }

        // 和區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()] =
                    areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()) * -1;
        }

        return result;
    }

    // 計算虎淨利
    private double[] calculateTigerAreaScoreArray(Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getAreaCount()];

        // 龍區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()] =
                areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()) * -1;
        }

        // 虎區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()),
                            ConstLhdJava.BetAreaIdLhdJava.TIGER.getRate()
                    );
        }

        // 和區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()] =
                areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()) * -1;
        }

        return result;
    }

    // 計算和淨利
    private double[] calculateTieAreaScoreArray(Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getAreaCount()];

        // 龍區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()] =
                    MathUtil.multiply(MathUtil.multiply(
                            areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode()),
                            MathUtil.subtract(1.0, this.RETURN_RATE)),
                            -1);
        }

        // 虎區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()] =
                    MathUtil.multiply(MathUtil.multiply(
                            areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode()),
                            MathUtil.subtract(1.0, this.RETURN_RATE)),
                            -1);
        }

        // 和區
        if(Objects.nonNull(areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()))){
            result[ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstLhdJava.BetAreaIdLhdJava.TIE.getCode()),
                            ConstLhdJava.BetAreaIdLhdJava.TIE.getRate());
        }

        return result;
    }
}
