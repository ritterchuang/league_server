package org.lsj.gs.math.core.card.resultCtr.jhResultCtr;

import org.lsj.gs.math.core.card.resultCtr.AbstractBaiRenResultCtr;
import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.ConstJhStack;
import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;
import org.lsj.utils.MathUtil;

import java.util.*;
import java.util.stream.Collectors;

// 金花結果計算器
public class JhBaiRenResultCtr extends  AbstractBaiRenResultCtr{
    private final JhBaiRenResultCtrConfig config; // 金花結果計算器設定

    public JhBaiRenResultCtr(JhBaiRenResultCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config.getFeeType(), config.getGameRate(), gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }

    //* 計算輸贏相關 *//

    // 計算玩家得分對應表
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, JhStack> areaStackMap, Map<Integer, Map<Integer, Integer>> allPlayerAreaBetMap) {
        return allPlayerAreaBetMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> this.calculateUidScore(entry.getKey(), areaStackMap, entry.getValue())
        ));
    }

    // 計算玩家得分
    private UidScore calculateUidScore(int chairIndex, Map<Integer, JhStack> areaStackMap, Map<Integer, Integer> areaBetMap){
        // 1. 計算各區域 不扣手續費淨利
        double[] areaNoFeeScoreArray = this.calculateAreaNoFeeScoreArray(areaStackMap, areaBetMap);

        // 2. 回傳
        return super.calculateUidScore(chairIndex, areaBetMap, areaNoFeeScoreArray, this.config.getAreaCount());
    }

    // 計算贏分區域陣列
    public int[] calculateWinAreasArray(Map<Integer, JhStack> areaStackMap){
        List<Integer> resultList = new ArrayList<>();

        // 1. 防呆
        if(Objects.isNull(areaStackMap) || areaStackMap.size() == 0){
            return new int[0];
        }

        // 2. 建立全押注對應表
        Map<Integer, Integer> areaBetMap = new HashMap<>();
        Arrays.stream(ConstHhdzJava.BetAreaIdHhdzJava.getBetAreaCodeArray()).forEach(areaId -> areaBetMap.put(areaId, 1));

        // 3. 計算區域淨利陣列
        double[] areaNoFeeScoreArray = this.calculateAreaNoFeeScoreArray(areaStackMap, areaBetMap);

        // 4. 計算贏分區域
        for (int areaIndex = 0; areaIndex < areaNoFeeScoreArray.length; areaIndex++) {
            if(areaNoFeeScoreArray[areaIndex] > 0.0){
                resultList.add(areaIndex);
            }
        }

        return resultList.stream().mapToInt(areaIndex -> areaIndex).toArray();
    }

    // 計算玩家區域淨利陣列
    public double[] calculateAreaNoFeeScoreArray(Map<Integer, JhStack> areaStackMap, Map<Integer, Integer> areaBetMap){
        double[] result = new double[this.config.getAreaCount()];

        if(Objects.isNull(areaBetMap)){
            return result;
        }

        // 1. 計算紅黑區域淨利
        double[] compareAreaNoFeeScoreArray = this.calculateCompareAreaNoFeeScoreArray(areaStackMap, areaBetMap);

        // 2. 計算牌型區域淨利
        double[] cardTypeAreaNoFeeScoreArray = this.calculateCardTypeAreaNoFeeScoreArray(areaStackMap, areaBetMap);

        // 3. 計算總和
        for (int areaIndex = 0; areaIndex < result.length; areaIndex++) {
            result[areaIndex] = MathUtil.add(compareAreaNoFeeScoreArray[areaIndex], cardTypeAreaNoFeeScoreArray[areaIndex]);
        }

        return result;
    }

    // 計算牌型區域淨利
    private double[] calculateCardTypeAreaNoFeeScoreArray(Map<Integer, JhStack> areaStackMap, Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getAreaCount()];

        // 1. 玩家無押注牌型區
        if(Objects.isNull(areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()))
                || areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()) <= 0){
            return result;
        }

        // 2. 計算獲勝區域
        int winAreaId = (areaStackMap.get(0).compareTo(areaStackMap.get(1)) > 0) ? ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode() : ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode();

        // 3. 計算獲勝牌型
        JhStack winStack = areaStackMap.get(winAreaId);

        // 4. 過濾無效牌型
        if(winStack.getTypeEnumCommon().equals(ConstJhStack.JhTypeEnumCommon.INVALID)
        || winStack.getTypeEnumCommon().equals(ConstJhStack.JhTypeEnumCommon.NONE)){
            result[ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()),
                            -1
                    );
            return result;
        }

        // 5. 過濾單張
        if(winStack.getTypeEnumCommon().equals(ConstJhStack.JhTypeEnumCommon.DAN_ZHANG)){
            result[ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()),
                            -1
                    );
            return result;
        }

        // 6. 過濾對八以下
        if(winStack.getTypeEnumCommon().equals(ConstJhStack.JhTypeEnumCommon.DUI_ZI)
        && winStack.calculateDuiZiPoint() <= 8){
            result[ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()),
                            -1
                    );
            return result;
        }

        // 7. 牌型倍數區
        result[ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()] =
                MathUtil.multiply(
                    areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode()),
                    this.config.getCardTypeRateMap().get(winStack.getTypeEnumCommon())
                );
        return result;
    }

    // 計算紅黑區域淨利
    private double[] calculateCompareAreaNoFeeScoreArray(Map<Integer, JhStack> areaStackMap, Map<Integer, Integer> areaBetMap) {
        switch(ConstHhdzJava.BetAreaIdHhdzJava.calculateWinBetAreaIdHhdzJava(areaStackMap)){
            case BLACK: return this.calculateBlackAreaNoFeeScoreArray(areaBetMap);
            case RED: return this.calculateRedAreaNoFeeScoreArray(areaBetMap);
            default: return new double[this.config.getAreaCount()];
        }
    }

    // 計算黑淨利
    private double[] calculateBlackAreaNoFeeScoreArray(Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getAreaCount()];

        // 黑區
        if(Objects.nonNull(areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()))){
            result[ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()),
                            ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getRate()
                    );
        }

        // 紅區
        if(Objects.nonNull(areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()))){
            result[ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()] =
                areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()) * -1;
        }

        return result;
    }

    // 計算紅淨利
    private double[] calculateRedAreaNoFeeScoreArray(Map<Integer, Integer> areaBetMap) {
        double[] result = new double[this.config.getAreaCount()];

        // 黑區
        if(Objects.nonNull(areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()))){
            result[ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()] =
                areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()) * -1;
        }

        // 紅區
        if(Objects.nonNull(areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()))){
            result[ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()] =
                    MathUtil.multiply(
                            areaBetMap.get(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()),
                            ConstHhdzJava.BetAreaIdHhdzJava.RED.getRate()
                    );
        }

        return result;
    }
}
