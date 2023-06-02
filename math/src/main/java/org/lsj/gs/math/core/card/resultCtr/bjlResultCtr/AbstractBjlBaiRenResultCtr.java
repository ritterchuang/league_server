package org.lsj.gs.math.core.card.resultCtr.bjlResultCtr;

import org.lsj.gs.math.core.card.resultCtr.AbstractBaiRenResultCtr;
import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 抽象百家結果計算器
public abstract class AbstractBjlBaiRenResultCtr extends  AbstractBaiRenResultCtr{
    protected final BjlBaiRenResultCtrConfig config; // 百家結果計算器設定
    protected final BjlResultUtil bjlResultUtil; // 百家樂結果工具包

    public AbstractBjlBaiRenResultCtr(BjlBaiRenResultCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config.getFeeType(), config.getGameRate(), gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.bjlResultUtil = new BjlResultUtil();
    }

    //* 計算輸贏相關 *//

    // 計算贏分區域陣列
    public int[] calculateWinAreasArray(Map<Integer, BjlStack> areaStackMap, int[] areaBetArray){
        return this.bjlResultUtil.calculateWinBetAreaIdList(
                        areaStackMap.keySet().stream().collect(Collectors.toMap(betAreaId -> betAreaId, betAreaId -> areaStackMap.get(betAreaId).getHandCardList())),
                        areaBetArray)
                .stream()
                .mapToInt(winAreaId -> winAreaId)
                .toArray();
    }

    // 計算返還區域陣列
    public int[] calculateReturnBetAreasArray(Map<Integer, BjlStack> areaStackMap, int[] areaBetArray){
        List<Integer> winBetAreaIdList = this.bjlResultUtil.calculateWinBetAreaIdList(
                areaStackMap.keySet().stream().collect(Collectors.toMap(betAreaId -> betAreaId, betAreaId -> areaStackMap.get(betAreaId).getHandCardList())),
                areaBetArray);

        return this.bjlResultUtil.calculateReturnBetAreaIdList(winBetAreaIdList)
                .stream()
                .mapToInt(returnAreaId -> returnAreaId)
                .toArray();
    }

    // 抽象方法 計算未含稅淨利
    public abstract double[] calculateAreaNoFeeScoreArray(
            int chairIndex,
            Map<Integer, BjlStack> areaStackMap,
            Map<Integer, Map<Integer, Integer>> chairToAreaBetMap);

    // 抽象方法 計算所有玩家得分
    public abstract Map<Integer, UidScore> calculateUidScoreMap(
            Map<Integer, BjlStack> areaStackMap,
            Map<Integer, Map<Integer, Integer>> allPlayerAreaBetMap);

    //  計算各區下注陣列
    protected int[] calculateTotalAreaBetArray(Map<Integer, Map<Integer, Integer>> chairToAreaBetMap) {
        // 1. 計算 位置，下注陣列 對應表 <chair, betArray>
        Map<Integer, int[]> chairToAreaBetArrayMap = chairToAreaBetMap.entrySet().stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> IntStream.range(0, this.config.getBetAreaCount())
                                .map(betAreaIndex -> entry.getValue().getOrDefault(betAreaIndex, 0)).toArray())
        );

        // 2. 回傳各區域下注總和陣列
        return IntStream.range(0, this.config.getBetAreaCount())
                .map(areaIndex -> chairToAreaBetArrayMap.values()
                        .stream()
                        .mapToInt(areaBetArray -> areaBetArray[areaIndex]).sum())
                .toArray();
    }

    // 計算玩家得分
    protected UidScore calculateUidScore(int chairIndex, Map<Integer, BjlStack> areaStackMap, Map<Integer, Map<Integer, Integer>> allPlayerAreaBetMap){
        // 1. 計算各區域 不扣手續費淨利
        double[] areaNoFeeScoreArray = this.calculateAreaNoFeeScoreArray(chairIndex, areaStackMap, allPlayerAreaBetMap);

        // 2. 回傳
        return super.calculateUidScore(chairIndex, allPlayerAreaBetMap.get(chairIndex), areaNoFeeScoreArray, this.config.getBetAreaCount());
    }
}
