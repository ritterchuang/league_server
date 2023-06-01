package org.lsj.gs.math.core.card.resultCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.MathUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 搶莊類的抽象算分結果計算器 測試
@ExtendWith(MockitoExtension.class)
class AbstractBankerResultCtrTest extends AbstractCardTestUtil {
    @Mock GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理者
    @Mock PoolCtr mockPoolCtr; // 水池計算者
    @Mock ITableUtil mockTableUtil; // 牌桌工具包

    // 給定莊家不夠賠，當計算閒家校正得分時，會返還校正金額
    @Test
    void test_given_bankerHasNoEnoughMoney_when_calculatePlayerCorrectUnFeeScoreMap_then_return_correctValue() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        int bankerChairIndex = 0;
        Mockito.when(mockGamePlayerHlr.getPlayerBeginMoney(Mockito.anyInt())).thenReturn(100.0);
        AbstractBankerResultCtr abstractBankerResultCtr = Mockito.mock(AbstractBankerResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        double correctCoefficient = Math.abs(MathUtil.divide(100, -150));
        Map<Integer, Double> unCorrectScoreMap = new HashMap<>(){
            {
                put(1, -50.0);
                put(2, -100.0);
            }
        };

        // 2. when
        Map<Integer, Double> actualResult = abstractBankerResultCtr.calculatePlayerCorrectUnFeeScoreMap(unCorrectScoreMap, bankerChairIndex);
        Map<Integer, Double> expectResult = new HashMap<>(){
            {
                put(1, MathUtil.multiply(-50, correctCoefficient));
                put(2, MathUtil.multiply(-100, correctCoefficient));
            }
        };

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定閒家校正金額，當計算閒家輸贏結果時，返還全部閒家輸贏結果
    @Test
    void test_given_correctScoreMap_when_calculatePlayerUidScoreMap_then_return_correctValue() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0};
        Mockito.when(mockGamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanAiSetting, beginMoney));
        AbstractBankerResultCtr abstractBankerResultCtr = Mockito.mock(AbstractBankerResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        Map<Integer, Double> unCorrectScoreMap = new HashMap<>(){
            {
                put(1, 20.0);
                put(2, 80.0);
            }
        };

        // 2. when
        Map<Integer, UidScore> actualResult = abstractBankerResultCtr.calculatePlayerUidScoreMap(unCorrectScoreMap);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(1, 2),
                List.of(100001, 100002),
                List.of(20.0, 80.0),
                List.of(20.0, 80.0),
                List.of(40.0, 160.0),
                List.of(19.0, 76.0),
                List.of(1.0, 4.0));

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定閒家計算結果，當計算莊家輸贏結果時，返還正確莊家結果
    @Test
    void test_given_playerUidScoreAndPlayerUnCorrectUnFeeScoreMap_when_calculateBankerUidScore_then_return_correctBankerUidScore() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        int[] humanAiSetting = new int[]{0};
        double[] beginMoney = new double[]{100.0};
        Mockito.when(mockGamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanAiSetting, beginMoney));
        Mockito.when(mockGamePlayerHlr.getPlayerBeginMoney(0)).thenReturn(100.0);
        AbstractBankerResultCtr abstractBankerResultCtr = Mockito.mock(AbstractBankerResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        Map<Integer, Double> playerUnCorrectUnFeeScoreMap = new HashMap<>(){
            {
                put(1, 20.0);
                put(2, -80.0);
            }
        };
        Map<Integer, UidScore> playerUidScore = super.generateUidScoreMap(
                List.of(1, 2),
                List.of(100001, 100002),
                List.of(20.0, 80.0),
                List.of(20.0, 80.0),
                List.of(40.0, 0.0),
                List.of(19.0, -80.0),
                List.of(1.0, 0.0));

        // 2. when
        UidScore actualResult = abstractBankerResultCtr.calculateBankerUidScore(playerUidScore, playerUnCorrectUnFeeScoreMap, 0);
        UidScore expectResult = super.generateUidScoreMap(
                List.of(0),
                List.of(100000),
                List.of(100.0),
                List.of(100.0),
                List.of(160.0),
                List.of(57.0),
                List.of(3.0)).get(0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }
}