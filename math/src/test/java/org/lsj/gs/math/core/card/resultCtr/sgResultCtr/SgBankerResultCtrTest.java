package org.lsj.gs.math.core.card.resultCtr.sgResultCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.ConstSgStack;
import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.SgStack;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SgBankerResultCtrTest extends AbstractCardTestUtil {
    SgBankerResultCtr sgBankerResultCtr; // 三公的算分結果計算器

    @Mock
    SgBankerResultCtrConfig mockConfig; // 算分結果計算器設定
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr mockPoolCtr; // 水池控制器
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    @BeforeEach
    void setUp() {
        Mockito.when(this.mockConfig.getFeeType()).thenReturn(ConstMathCommon.FeeType.RATE.getType());
        Mockito.when(this.mockConfig.getGameRate()).thenReturn(5.0);
        this.sgBankerResultCtr = new SgBankerResultCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
    }

    // 給定1、3號有真人玩家，當取得所有玩家淨利時，無真人玩家淨利為0，有真人玩家回傳正確淨利
    @Test
    public void test_given_chair1AndChair3ArePlayer_when_getAllPlayerScoreArray_then_noPlayerChairIndexScoreIs0() {
        // 1. given
        Mockito.when(this.mockConfig.getMaxUser()).thenReturn(5);
        this.sgBankerResultCtr.setUidScoreMap(
                super.generateUidScoreMap(
                        List.of(1, 3),
                        List.of(1, 3),
                        List.of(10.0, 100.0),
                        List.of(10.0, 100.0),
                        List.of(0.0, 100.0),
                        List.of(-10.0, 95.0),
                        List.of(0.0, 5.0)));

        // 2. when
        double[] actualResult = this.sgBankerResultCtr.getAllPlayerScoreArray();
        double[] expectResult = new double[]{0.0, -10.0, 0.0, 95.0, 0.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊家正常輸錢，閒1正常輸錢，閒2正常贏錢
    @Test
    void test_given_bankerHas100AndLoss30_player1Loss45_player2Win75_when_calculateUidScoreMap_then_bankerHasEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> sgStackPerPlayer = this.generateThreeSgStackMapBySpecifyOrder(new int[]{2, 0, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{3, 5});
        int bankerRate = 1;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.sgBankerResultCtr.calculateUidScoreMap(sgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(120.0, 45.0, 75.0),
                List.of(120.0, 45.0, 75.0),
                List.of(90.0, 0.0, 150.0),
                List.of(-30.0, -45.0, 71.25),
                List.of(0.0, 0.0, 3.75)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊家正常贏錢，閒1正常輸錢，閒2正常贏錢
    @Test
    void test_given_bankerHas100AndWin30_player1Loss75_player2Win45_when_calculateUidScoreMap_then_bankerHasEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> sgStackPerPlayer = this.generateThreeSgStackMapBySpecifyOrder(new int[]{2, 0, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{5, 3});
        int bankerRate = 1;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.sgBankerResultCtr.calculateUidScoreMap(sgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(120.0, 75.0, 45.0),
                List.of(120.0, 75.0, 45.0),
                List.of(150.0, 0.0, 90.0),
                List.of(28.5, -75.0, 42.75),
                List.of(1.5, 0.0, 2.25)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊閒不夠賠，莊贏閒1贏不夠賠，閒2輸
    @Test
    void test_given_bankerHas100AndWin105_player1Has100AndWin120_player2Loss225_when_calculateUidScoreMap_then_bankerAndPlayer1NotEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> sgStackPerPlayer = this.generateThreeSgStackMapBySpecifyOrder(new int[]{1, 0, 2});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{8, 15});
        int bankerRate = 1;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 100.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.sgBankerResultCtr.calculateUidScoreMap(sgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(260.0, 80.0, 180.0),
                List.of(260.0, 80.0, 180.0),
                List.of(360.0, 160.0, 0.0),
                List.of(95.0, 76.0, -180.0),
                List.of(5.0, 4.0, 0.0)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊閒不夠賠，莊贏閒1贏不夠賠，閒2輸
    @Test
    void test_given_bankerHas100AndLoss180_player1Has100AndLoss120_player2Win300_when_calculateUidScoreMap_then_bankerAndPlayer1NotEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> sgStackPerPlayer = this.generateThreeSgStackMapBySpecifyOrder(new int[]{2, 0, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{8, 15});
        int bankerRate = 1;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 100.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.sgBankerResultCtr.calculateUidScoreMap(sgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(260.0, 80.0, 180.0),
                List.of(260.0, 80.0, 180.0),
                List.of(160.0, 0.0, 360.0),
                List.of(-100.0, -80.0, 171.0),
                List.of(0.0, 0.0, 9.0)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 依照指定順序產牌
    private Map<Integer, AbstractStack> generateThreeSgStackMapBySpecifyOrder(int[] playerOrder) {
        // 1. 準備資料 三公 / 三公 / 八點
        List<ICard> maxCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{113,213,312});
        List<ICard> secondCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{112,211,111});
        List<ICard> thirdCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{110,303,305});

        SgStack maxStack = new SgStack(
                maxCardList,
                ConstSgStack.SgTypeEnumCommon.SANGONG
        );

        SgStack secondStack = new SgStack(
                secondCardList,
                ConstSgStack.SgTypeEnumCommon.SANGONG
        );

        SgStack thirdStack = new SgStack(
                thirdCardList,
                ConstSgStack.SgTypeEnumCommon.PT_8
        );

        // 2. 封裝
        Map<Integer, AbstractStack> result = new HashMap<>();

        result.put(playerOrder[0], maxStack);
        result.put(playerOrder[1], secondStack);
        result.put(playerOrder[2], thirdStack);

        return result;
    }

    // 設定 mock 物件
    private void setMockToolReturnValue(int[] humanRobotType, double[] beginMoneys, double baseScore, int bankerChairIndex) {
        Mockito.when(this.mockGamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanRobotType, beginMoneys));
        Mockito.when(this.mockGamePlayerHlr.getPlayerBeginMoney(bankerChairIndex)).thenReturn(beginMoneys[bankerChairIndex]);
        Mockito.when(this.mockConfig.getBaseScore()).thenReturn(baseScore);
        Mockito.when(this.mockConfig.getSgTypeConfig()).thenReturn(this.createSgTypeConfig());
    }

    // 創建三公型設定
    private Map<ConstSgStack.SgTypeEnumCommon, Integer> createSgTypeConfig(){
        return new HashMap<>(){{
            put(ConstSgStack.SgTypeEnumCommon.PT_0, 1);
            put(ConstSgStack.SgTypeEnumCommon.PT_1, 1);
            put(ConstSgStack.SgTypeEnumCommon.PT_2, 1);
            put(ConstSgStack.SgTypeEnumCommon.PT_3, 1);
            put(ConstSgStack.SgTypeEnumCommon.PT_4, 1);
            put(ConstSgStack.SgTypeEnumCommon.PT_5, 1);
            put(ConstSgStack.SgTypeEnumCommon.PT_6, 1);
            put(ConstSgStack.SgTypeEnumCommon.PT_7, 2);
            put(ConstSgStack.SgTypeEnumCommon.PT_8, 2);
            put(ConstSgStack.SgTypeEnumCommon.PT_9, 2);
            put(ConstSgStack.SgTypeEnumCommon.SANGONG, 3);
            put(ConstSgStack.SgTypeEnumCommon.DASANGONG, 4);
            put(ConstSgStack.SgTypeEnumCommon.ZHIZUN, 5);
        }};
    }
}