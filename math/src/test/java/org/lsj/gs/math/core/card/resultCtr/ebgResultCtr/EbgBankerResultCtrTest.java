package org.lsj.gs.math.core.card.resultCtr.ebgResultCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.ebgStackCtr.ConstEbgStack;
import org.lsj.gs.math.core.card.stackCtr.ebgStackCtr.EbgStack;
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

// 二八槓的算分結果計算器 測試
@ExtendWith(MockitoExtension.class)
class EbgBankerResultCtrTest extends AbstractCardTestUtil {
    EbgBankerResultCtr ebgBankerResultCtr; // 搶莊二八槓的算分結果計算器

    @Mock
    EbgBankerResultCtrConfig mockConfig; // 算分結果計算器設定
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
        this.ebgBankerResultCtr = new EbgBankerResultCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
    }

    // 給定1、3號有真人玩家，當取得所有玩家淨利時，無真人玩家淨利為0，有真人玩家回傳正確淨利
    @Test
    public void test_given_chair1AndChair3ArePlayer_when_getAllPlayerScoreArray_then_noPlayerChairIndexScoreIs0() {
        // 1. given
        Mockito.when(this.mockConfig.getMaxUser()).thenReturn(5);
        this.ebgBankerResultCtr.setUidScoreMap(
                super.generateUidScoreMap(
                    List.of(1, 3),
                    List.of(1, 3),
                    List.of(10.0, 100.0),
                    List.of(10.0, 100.0),
                    List.of(0.0, 100.0),
                    List.of(-10.0, 95.0),
                    List.of(0.0, 5.0)));

        // 2. when
        double[] actualResult = this.ebgBankerResultCtr.getAllPlayerScoreArray();
        double[] expectResult = new double[]{0.0, -10.0, 0.0, 95.0, 0.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊家正常贏錢，閒1、3正常輸錢，閒2正常贏錢
    @Test
    public void test_given_bankerHas100AndWin50_player1Loss25_player2Win125_player3Loss150_when_calculateUidScoreMap_then_bankerHasEnough() {
        // 1. given
        Map<Integer, AbstractStack> ebgStackPerPlayer = this.generateFourEbgStackMapBySpecifyOrder(new int[]{2, 0, 3, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2, 3}, new int[]{5, 25, 30});
        int bankerChairIndex = 0;
        int bankerRate = 1;
        int[] humanAiSetting = new int[]{0, 1, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0, 1000.0};
        double baseScore = 5.0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 2. when
        Map<Integer, UidScore> actualResult = this.ebgBankerResultCtr.calculateUidScoreMap(ebgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2, 3),
                List.of(100000, 100001, 100002, 100003),
                List.of(300.0, 25.0, 125.0, 150.0),
                List.of(300.0, 25.0, 125.0, 150.0),
                List.of(350.0, 0.0, 250.0, 0.0),
                List.of(47.5, -25.0, 118.75, -150.0),
                List.of(2.5, 0.0, 6.25, 0.0));

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊家正常輸錢，閒1、3正常輸錢，閒2正常贏錢
    @Test
    public void test_given_bankerHas100AndLoss25_player1Loss260_player2Win300_when_calculateUidScoreMap_then_bankerHasEnough() {
        // 1. given
        Map<Integer, AbstractStack> ebgStackPerPlayer = this.generateFourEbgStackMapBySpecifyOrder(new int[]{2, 0, 3, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2, 3}, new int[]{3, 30, 25});
        int bankerChairIndex = 0;
        int bankerRate = 1;
        int[] humanAiSetting = new int[]{0, 1, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0, 1000.0};
        double baseScore = 5.0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 2. when
        Map<Integer, UidScore> actualResult = this.ebgBankerResultCtr.calculateUidScoreMap(ebgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2, 3),
                List.of(100000, 100001, 100002, 100003),
                List.of(290.0, 15.0, 150.0, 125.0),
                List.of(290.0, 15.0, 150.0, 125.0),
                List.of(280.0, 0.0, 300.0, 0.0),
                List.of(-10.0, -15.0, 142.5, -125.0),
                List.of(0.0, 0.0, 7.5, 0.0));

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊閒不夠賠，莊贏閒3贏不夠賠，閒1、2輸
    @Test
    public void test_given_bankerHas100AndWin180_player1Has100AndWin120_player2Loss300_when_calculateUidScoreMap_then_bankerAndPlayer1NotEnough() {
        // 1. given
        Map<Integer, AbstractStack> ebgStackPerPlayer = this.generateFourEbgStackMapBySpecifyOrder(new int[]{3, 0, 1, 2});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2, 3}, new int[]{20, 40, 30});
        int bankerChairIndex = 0;
        int bankerRate = 1;
        int[] humanAiSetting = new int[]{0, 1, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0, 100.0};
        double baseScore = 5.0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 2. when
        Map<Integer, UidScore> actualResult = this.ebgBankerResultCtr.calculateUidScoreMap(ebgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2, 3),
                List.of(100000, 100001, 100002, 100003),
                List.of(200.0, 50.0, 100.0, 50.0),
                List.of(200.0, 50.0, 100.0, 50.0),
                List.of(300.0, 0.0, 0.0, 100.0),
                List.of(95.0, -50.0, -100.0, 47.5),
                List.of(5.0, 0.0, 0.0, 2.5));

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊閒不夠賠，莊輸閒1輸、閒3輸不夠賠，閒2贏
    @Test
    public void test_given_bankerHas100AndLoss180_player1Has100AndLoss120_player2Win300_when_calculateUidScoreMap_then_bankerAndPlayer1NotEnough() {
        // 1. given
        Map<Integer, AbstractStack> ebgStackPerPlayer = this.generateFourEbgStackMapBySpecifyOrder(new int[]{2, 0, 1, 3});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2, 3}, new int[]{10, 70, 30});
        int bankerChairIndex = 0;
        int bankerRate = 1;
        int[] humanAiSetting = new int[]{0, 1, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0, 100.0};
        double baseScore = 5.0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 2. when
        Map<Integer, UidScore> actualResult = this.ebgBankerResultCtr.calculateUidScoreMap(ebgStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
           List.of(0, 1, 2, 3),
           List.of(100000, 100001, 100002, 100003),
           List.of(250.0, 25.0, 175.0, 50.0),
           List.of(250.0, 25.0, 175.0, 50.0),
           List.of(150.0, 0.0, 350.0, 0.0),
           List.of(-100.0, -25.0, 166.25, -50.0),
           List.of(0.0, 0.0, 8.75, 0.0));

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 依照指定順序產牌
    private Map<Integer, AbstractStack> generateFourEbgStackMapBySpecifyOrder(int[] playerOrder) {
        // 1. 準備資料 一寶、一點半、一點半、鱉十
        List<ICard> maxCardList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{31, 31});
        List<ICard> secondCardList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{31, 47});
        List<ICard> thirdCardList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{47, 31});
        List<ICard> fourthCardList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{33, 37});

        EbgStack maxStack = new EbgStack(
                maxCardList,
                ConstEbgStack.EbgTypeEnumCommon.BAO_1
        );

        EbgStack secondStack = new EbgStack(
                secondCardList,
                ConstEbgStack.EbgTypeEnumCommon.ONE_AND_HALF
        );

        EbgStack thirdStack = new EbgStack(
                thirdCardList,
                ConstEbgStack.EbgTypeEnumCommon.ONE_AND_HALF
        );

        EbgStack fourthStack = new EbgStack(
                fourthCardList,
                ConstEbgStack.EbgTypeEnumCommon.BS_73
        );

        // 2. 封裝
        Map<Integer, AbstractStack> result = new HashMap<>();
        result.put(playerOrder[0], maxStack);
        result.put(playerOrder[1], secondStack);
        result.put(playerOrder[2], thirdStack);
        result.put(playerOrder[3], fourthStack);

        return result;
    }

    // 設定 mock 物件
    private void setMockToolReturnValue(int[] humanRobotType, double[] beginMoneys, double baseScore, int bankerChairIndex) {
        Mockito.when(this.mockGamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanRobotType, beginMoneys));
        Mockito.when(this.mockGamePlayerHlr.getPlayerBeginMoney(bankerChairIndex)).thenReturn(beginMoneys[bankerChairIndex]);
        Mockito.when(this.mockConfig.getBaseScore()).thenReturn(baseScore);
    }
}