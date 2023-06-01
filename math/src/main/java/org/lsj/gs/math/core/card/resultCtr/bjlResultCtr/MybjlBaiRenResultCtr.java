package org.lsj.gs.math.core.card.resultCtr.bjlResultCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 免傭百家結果計算器
public class MybjlBaiRenResultCtr extends  AbstractBjlBaiRenResultCtr{

    public MybjlBaiRenResultCtr(BjlBaiRenResultCtrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(config, gamePlayerHlr, poolCtr, tableUtil);
    }


    //* 計算輸贏相關 *//

    // 計算玩家得分對應表
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, BjlStack> areaStackMap, Map<Integer, Map<Integer, Integer>> allPlayerAreaBetMap) {
        return allPlayerAreaBetMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> this.calculateUidScore(entry.getKey(), areaStackMap, allPlayerAreaBetMap)
        ));
    }

    // 計算玩家區域淨利陣列
    @Override
    public double[] calculateAreaNoFeeScoreArray(int chairIndex, Map<Integer, BjlStack> areaStackMap, Map<Integer, Map<Integer, Integer>> chairToAreaBetMap){
        // 1. 取得莊家牌型
        BjlStack bankBjlStack = areaStackMap.getOrDefault(ConstMathBjl.DealAreaEnum.BANK.getCode(), new BjlStack());

        // 2. 計算獲勝下注區域陣列
        int[] winBetAreaIdArray = this.calculateWinAreasArray(areaStackMap, this.calculateTotalAreaBetArray(chairToAreaBetMap));

        // 3. 計算返還下注區域陣列
        int[] returnBetAreaIdArray = this.calculateReturnBetAreasArray(areaStackMap, this.calculateTotalAreaBetArray(chairToAreaBetMap));

        // 4. 計算輸贏淨利陣列
        return this.calculateWinLossScoreArray(bankBjlStack, winBetAreaIdArray, returnBetAreaIdArray, chairToAreaBetMap.getOrDefault(chairIndex, new HashMap<>()));
    }

    // 計算輸贏淨利陣列
    private double[] calculateWinLossScoreArray(BjlStack bankBjlStack,  int[] winBetAreaIdArray, int[] returnBetAreaIdArray, Map<Integer, Integer> areaBetMap) {
        // 1. 準備資訊
        double[] result = new double[this.config.getBetAreaCount()];
        List<Integer> winBetAreaIdList = Arrays.stream(winBetAreaIdArray).boxed().collect(Collectors.toList());
        List<Integer> returnBetAreaIdList = Arrays.stream(returnBetAreaIdArray).boxed().collect(Collectors.toList());

        // 2. 計算淨利
        for (int betAreaIndex = 0; betAreaIndex < this.config.getBetAreaCount(); betAreaIndex++) {

            // 2.1 獲勝區域為莊，且點數為6，賠0.5
            if (ConstMathBjl.BetAreaEnum.BANK.getCode() == betAreaIndex
                    && winBetAreaIdList.contains(betAreaIndex)
                    && this.bjlResultUtil.calculateCardPointSumModuleBy10(bankBjlStack.getHandCardList()) == 6) {
                result[betAreaIndex] = MathUtil.multiply(
                        areaBetMap.getOrDefault(betAreaIndex, 0)
                        , 0.5);
            }

            // 2.2 獲勝區域不為莊，回傳 乘上倍數結果
            if (ConstMathBjl.BetAreaEnum.BANK.getCode() != betAreaIndex && winBetAreaIdList.contains(betAreaIndex)) {
                result[betAreaIndex] = MathUtil.multiply(
                        areaBetMap.getOrDefault(betAreaIndex, 0)
                        , this.config.getBetAreaRateMap().get(betAreaIndex));
            }

            // 2.3 非獲勝區域，非返還區域，回傳 輸的金額
            if (!winBetAreaIdList.contains(betAreaIndex) && !returnBetAreaIdList.contains(betAreaIndex)) {
                result[betAreaIndex] = MathUtil.multiply(
                        areaBetMap.getOrDefault(betAreaIndex, 0)
                        , -1);
            }
        }

        return result;
    }
}
