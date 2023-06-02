package org.lsj.gs.math.core.card.resultCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 通比類的抽象算分結果計算器 測試
@ExtendWith(MockitoExtension.class)
class AbstractTonbiResultCtrTest extends AbstractCardTestUtil {
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池計算者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定 贏家位置、所有輸家得分，當計算輸家輸贏結果，回傳正確
    @Test
    void test_given_loserScoreMapAndWinnerChairIndex_when_calculateLoserUidScoreMap_then_return_correctValue() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        int winnerChairIndex = 0;
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0};
        Mockito.when(mockGamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanAiSetting, beginMoney));
        AbstractTonbiResultCtr abstractTonbiResultCtr = Mockito.mock(AbstractTonbiResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        Map<Integer, Double> loserScoreMap = new HashMap<>(){
            {
                put(1, -50.0);
                put(2, -100.0);
            }
        };

        // 2. when
        Map<Integer, UidScore> actualResult = abstractTonbiResultCtr.calculateLoserUidScoreMap(loserScoreMap, winnerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(1, 2),
                List.of(100001, 100002),
                List.of(50.0, 100.0),
                List.of(50.0, 100.0),
                List.of(0.0, 0.0),
                List.of(-50.0, -100.0),
                List.of(0.0, 0.0));

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定 贏家位置、所有輸家得分，當計算贏家輸贏結果，回傳正確
    @Test
    void test_given_loserScoreMapAndWinnerChairIndex_when_calculateWinnerUidScore_then_return_correctValue() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        int winnerChairIndex = 0;
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0};
        Mockito.when(mockGamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanAiSetting, beginMoney));
        Mockito.when(mockGamePlayerHlr.getPlayerCount()).thenReturn(3);
        AbstractTonbiResultCtr abstractTonbiResultCtr = Mockito.mock(AbstractTonbiResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        Map<Integer, Double> loserScoreMap = new HashMap<>(){
            {
                put(1, -50.0);
                put(2, -100.0);
            }
        };

        // 2. when
        UidScore actualResult = abstractTonbiResultCtr.calculateWinnerUidScore(loserScoreMap, winnerChairIndex);
        UidScore expectResult = super.generateUidScoreMap(
                List.of(0),
                List.of(100000),
                List.of(75.0),
                List.of(75.0),
                List.of(225.0),
                List.of(142.5),
                List.of(7.5)).get(0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定輸家輸贏結果、贏家輸贏結果、贏家座位，當計算封裝結果，回傳正確
    @Test
    public void test_given_loserUidScoreMapAndWinnerUidScore_when_packageUidScoreMap_then_correct() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        AbstractTonbiResultCtr abstractTonbiResultCtr = Mockito.mock(AbstractTonbiResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        Map<Integer, UidScore> loserUidScoreMap = super.generateUidScoreMap(
                List.of(1, 2),
                List.of(100001, 100002),
                List.of(50.0, 100.0),
                List.of(50.0, 100.0),
                List.of(0.0, 0.0),
                List.of(-50.0, -100.0),
                List.of(0.0, 0.0));
        UidScore winnerUidScore = super.generateUidScoreMap(
                List.of(0),
                List.of(100000),
                List.of(75.0),
                List.of(75.0),
                List.of(225.0),
                List.of(142.5),
                List.of(7.5)).get(0);
        int winnerChairIndex = 0;

        // 2. when
        Map<Integer, UidScore> actualResult = abstractTonbiResultCtr.packageUidScoreMap(loserUidScoreMap, winnerUidScore, winnerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002),
                List.of(75.0, 50.0, 100.0),
                List.of(75.0, 50.0, 100.0),
                List.of(225.0, 0.0, 0.0),
                List.of(142.5, -50.0, -100.0),
                List.of(7.5, 0.0, 0.0));

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }
}