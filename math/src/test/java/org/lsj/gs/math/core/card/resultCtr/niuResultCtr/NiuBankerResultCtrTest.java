package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class NiuBankerResultCtrTest extends AbstractCardTestUtil {
    NiuBankerResultCtr niuResultCtr; // 搶莊牛牛的算分結果計算器

    @Mock
    NiuBankerResultCtrConfig config; // 算分結果計算器設定
    @Mock
    GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr poolCtr; // 水池控制器
    @Mock
    ITableUtil tableUtil; // 牌桌工具包

    @BeforeEach
    void setUp() {
        Mockito.when(this.config.getFeeType()).thenReturn(ConstMathCommon.FeeType.RATE.getType());
        Mockito.when(this.config.getGameRate()).thenReturn(5.0);
        this.niuResultCtr = new NiuBankerResultCtr(config, gamePlayerHlr, poolCtr, tableUtil);
    }

    // 給定1、3號有真人玩家，當取得所有玩家淨利時，無真人玩家淨利為0，有真人玩家回傳正確淨利
    @Test
    public void test_given_chair1AndChair3ArePlayer_when_getAllPlayerScoreArray_then_noPlayerChairIndexScoreIs0() {
        // 1. given
        Mockito.when(this.config.getMaxUser()).thenReturn(4);
        this.niuResultCtr.setUidScoreMap(
                super.generateUidScoreMap(
                        List.of(1, 3),
                        List.of(1, 3),
                        List.of(10.0, 100.0),
                        List.of(10.0, 100.0),
                        List.of(0.0, 100.0),
                        List.of(-10.0, 95.0),
                        List.of(0.0, 5.0)));

        // 2. when
        double[] actualResult = this.niuResultCtr.getAllPlayerScoreArray();
        double[] expectResult = new double[]{0.0, -10.0, 0.0, 95.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊家正常贏錢，閒1正常輸錢，閒2正常贏錢
    @Test
    public void test_given_bankerHas100AndWin40_player1Loss120_player2Win80_when_calculateUidScoreMap_then_bankerHasEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> niuStackPerPlayer = this.generateThreeNiuStackMapBySpecifyOrder(new int[]{2, 0, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{6, 4});
        int bankerRate = 2;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.niuResultCtr.calculateUidScoreMap(niuStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(200.0, 120.0, 80.0),
                List.of(200.0, 120.0, 80.0),
                List.of(240.0, 0.0, 160.0),
                List.of(38.0, -120.0, 76.0),
                List.of(2.0, 0.0, 4.0)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊家正常輸錢，閒1正常輸錢，閒2正常贏錢
    @Test
    public void test_given_bankerHas100AndLoss40_player1Loss260_player2Win300_when_calculateUidScoreMap_then_bankerHasEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> niuStackPerPlayer = this.generateThreeNiuStackMapBySpecifyOrder(new int[]{2, 0, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{13, 15});
        int bankerRate = 2;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 10000.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.niuResultCtr.calculateUidScoreMap(niuStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(560.0, 260.0, 300.0),
                List.of(560.0, 260.0, 300.0),
                List.of(520.0, 0.0, 600.0),
                List.of(-40.0, -260.0, 285.0),
                List.of(0.0, 0.0, 15.0)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊閒不夠賠，莊贏閒1贏不夠賠，閒2輸
    @Test
    public void test_given_bankerHas100AndWin180_player1Has100AndWin120_player2Loss300_when_calculateUidScoreMap_then_bankerAndPlayer1Enough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> niuStackPerPlayer = this.generateThreeNiuStackMapBySpecifyOrder(new int[]{1, 0, 2});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{6, 15});
        int bankerRate = 2;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 100.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.niuResultCtr.calculateUidScoreMap(niuStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(200.0, 50.0, 150.0),
                List.of(200.0, 50.0, 150.0),
                List.of(300.0, 100.0, 0.0),
                List.of(95.0, 47.5, -150.0),
                List.of(5.0, 2.5, 0.0)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 莊閒不夠賠，莊輸閒1輸不夠賠，閒2贏
    @Test
    public void test_given_bankerHas100AndLoss180_player1Has100AndLoss120_player2Win300_when_calculateUidScoreMap_then_bankerAndPlayer1Enough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> niuStackPerPlayer = this.generateThreeNiuStackMapBySpecifyOrder(new int[]{2, 0, 1});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{1, 2}, new int[]{6, 15});
        int bankerRate = 2;

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{100.0, 100.0, 20000.0};
        double baseScore = 5.0;
        int bankerChairIndex = 0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore, bankerChairIndex);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.niuResultCtr.calculateUidScoreMap(niuStackPerPlayer, betRateMap, bankerRate, bankerChairIndex);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002, 100003),
                List.of(200.0, 50.0, 150.0),
                List.of(200.0, 50.0, 150.0),
                List.of(100.0, 0.0, 300.0),
                List.of(-100.0, -50.0, 142.5),
                List.of(0.0, 0.0, 7.5)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 設定 mock 物件
    private void setMockToolReturnValue(int[] humanRobotType, double[] beginMoneys, double baseScore, int bankerChairIndex) {
        Mockito.when(this.gamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanRobotType, beginMoneys));
        Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(bankerChairIndex)).thenReturn(beginMoneys[bankerChairIndex]);
        Mockito.when(this.config.getBaseScore()).thenReturn(baseScore);
        Mockito.when(this.config.getNiuTypeRateConfig()).thenReturn(this.createNiuTypeConfig());
    }

    // 創建牛型設定
    private Map<ConstNiu.NiuTypeEnumCommon, Integer> createNiuTypeConfig(){
        return new HashMap<>(){{
            put(ConstNiu.NiuTypeEnumCommon.INVALID, 0);
            put(ConstNiu.NiuTypeEnumCommon.NIU_0, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_1, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_2, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_3, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_4, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_5, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_6, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_7, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_8, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_9, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_NIU, 3);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_4, 4);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_5, 4);
            put(ConstNiu.NiuTypeEnumCommon.BOMB, 4);
            put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, 4);
        }};
    }

    // 依照指定順序產牌
    private Map<Integer, AbstractStack> generateThreeNiuStackMapBySpecifyOrder(int[] playerOrder) {
        // 1. 準備資料 牛9 / 牛9 / 牛8
        List<ICard> maxCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{102, 202, 105, 110, 113});
        List<ICard> secondCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{402, 203, 205, 209, 211});
        List<ICard> thirdCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{302, 303, 305, 309, 409});

        NiuStackCommon maxStack = new NiuStackCommon(
                maxCardList,
                new ArrayList<>() {},
                ConstNiu.NiuTypeEnumCommon.NIU_9,
                true
        );

        NiuStackCommon secondStack = new NiuStackCommon(
                secondCardList,
                new ArrayList<>() {},
                ConstNiu.NiuTypeEnumCommon.NIU_9,
                true
        );

        NiuStackCommon thirdStack = new NiuStackCommon(
                thirdCardList,
                new ArrayList<>() {},
                ConstNiu.NiuTypeEnumCommon.NIU_8,
                true
        );

        // 2. 封裝
        Map<Integer, AbstractStack> result = new HashMap<>();

        result.put(playerOrder[0], maxStack);
        result.put(playerOrder[1], secondStack);
        result.put(playerOrder[2], thirdStack);

        return result;
    }
}