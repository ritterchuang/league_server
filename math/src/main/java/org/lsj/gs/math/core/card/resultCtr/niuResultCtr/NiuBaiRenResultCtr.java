package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.card.resultCtr.AbstractBaiRenResultCtr;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// 牛牛百人結果計算器
public class NiuBaiRenResultCtr extends  AbstractBaiRenResultCtr{
    private final NiuBaiRenResultCtrConfig config; // 牛牛結果計算器設定

    public NiuBaiRenResultCtr(NiuBaiRenResultCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config.getFeeType(), config.getGameRate(), gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }


    //* 計算輸贏相關 *//

    // 計算玩家得分對應表
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, AbstractStack> areaStackMap, Map<Integer, Map<Integer, Integer>> allPlayerAreaBetMap) {
        return allPlayerAreaBetMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> this.calculateUidScore(entry.getKey(), areaStackMap, entry.getValue())
        ));
    }

    // 計算玩家得分
    private UidScore calculateUidScore(int chairIndex, Map<Integer, AbstractStack> areaStackMap, Map<Integer, Integer> areaBetMap){
        // 1. 計算各區域 不扣手續費淨利
        double[] areaNoFeeScoreArray = this.calculateAreaNoFeeScoreArray(areaStackMap, areaBetMap);

        // 2. 回傳
        return super.calculateUidScore(chairIndex, areaBetMap, areaNoFeeScoreArray, this.config.getBetAreaCount());
    }

    // 計算玩家區域淨利陣列
    public double[] calculateAreaNoFeeScoreArray(Map<Integer, AbstractStack> areaStackMap, Map<Integer, Integer> areaBetMap){
        double[] result = new double[this.config.getBetAreaCount()];

        if(Objects.isNull(areaBetMap)){
            return result;
        }

        // 1. 計算獲勝區域玩家淨利
        double[] winAreaNoFeeScoreArray = this.calculateWinAreaNoFeeScoreArray(this.getAreaIdToNiuStackCommonMap(areaStackMap), areaBetMap);

        // 2. 計算失敗區域玩家淨利
        double[] lossAreaNoFeeScoreArray = this.calculateLossAreaNoFeeScoreArray(this.getAreaIdToNiuStackCommonMap(areaStackMap), areaBetMap);

        // 3. 計算總和
        for (int areaIndex = 0; areaIndex < result.length; areaIndex++) {
            result[areaIndex] = MathUtil.add(winAreaNoFeeScoreArray[areaIndex], lossAreaNoFeeScoreArray[areaIndex]);
        }

        return result;
    }

    // 轉型
    private Map<Integer, NiuStackCommon> getAreaIdToNiuStackCommonMap(Map<Integer, AbstractStack> areaStackMap) {
        return areaStackMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> (NiuStackCommon)entry.getValue()
        ));
    }

    // 計算百人玩家贏區域淨利
    private double[] calculateWinAreaNoFeeScoreArray(Map<Integer, NiuStackCommon> areaStackMap, Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getBetAreaCount()];

        List<Integer> winAreaIdList = ConstMathNiu.BetAreaEnum.calculateAreaIdListByChartType(areaStackMap, ConstMathNiu.ChartEnum.WIN);

        for (int winAreaIndex = 0; winAreaIndex < winAreaIdList.size(); winAreaIndex++) {
            int winAreaId = winAreaIdList.get(winAreaIndex);
            int rate = this.config.getCardTypeRateMap().get(areaStackMap.get(winAreaId).getNiuTypeEnumCommon());
            int areaBet = areaBetMap.getOrDefault(winAreaId, 0);

            result[winAreaId] = MathUtil.multiply(areaBet, rate);
        }

        return result;
    }

    // 計算百人玩家贏區域淨利
    private double[] calculateLossAreaNoFeeScoreArray(Map<Integer, NiuStackCommon> areaStackMap, Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getBetAreaCount()];

        List<Integer> lossAreaIdList = ConstMathNiu.BetAreaEnum.calculateAreaIdListByChartType(areaStackMap, ConstMathNiu.ChartEnum.LOSS);

        for (int lossAreaIndex = 0; lossAreaIndex < lossAreaIdList.size(); lossAreaIndex++) {
            int lossAreaId = lossAreaIdList.get(lossAreaIndex);
            int rate = this.config.getCardTypeRateMap().get(areaStackMap.get(ConstMathNiu.BetAreaEnum.getBankerAreaId()).getNiuTypeEnumCommon());
            int areaBet = areaBetMap.getOrDefault(lossAreaId, 0);

            result[lossAreaId] = MathUtil.multiply(MathUtil.multiply(areaBet, rate), -1);
        }

        return result;
    }


    //* 賠率相關 *//

    // 取得最大賠率
    public int getMaxRate() {
        return this.config.getCardTypeRateMap().values().stream().mapToInt(rate -> rate).max().orElse(1);
    }
}
