package org.lsj.gs.math.core.card.cardWallCtr;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
import io.smallrye.common.constraint.Assert;
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
class CardWallCtrTest {
    private CardWallCtr cardWallCtr;
    @Mock
    CardWallCtrConfig config; // 發牌計算器設定
    @Mock
    GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr poolCtr; // 水池控制器
    @Mock ITableUtil tableUtil; // 牌桌工具包


    // 給定錯誤手牌張數，判斷玩家手牌是否存在，回傳錯誤
    @Test
    public void test_give_wrongCardsCount_when_isInPlayerHandCard_then_returnFalse() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.setPlayerHandCardMapInTest();

        // 2. 計算結果
        boolean result = this.cardWallCtr.isInPlayerHandCard(-1, new int[]{1101,1102,1103});

        // 3. 驗證
        Assert.assertFalse(result);
    }

    // 給定錯誤座位，判斷玩家手牌是否存在，回傳錯誤
    @Test
    public void test_give_nonExistChairIndex_when_isInPlayerHandCard_then_returnFalse() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.setPlayerHandCardMapInTest();

        // 2. 計算結果
        boolean result = this.cardWallCtr.isInPlayerHandCard(-1, new int[]{1101,1102,1103,1104,1105});

        // 3. 驗證
        Assert.assertFalse(result);
    }

    // 給定錯誤手牌，判斷玩家手牌是否存在，回傳錯誤
    @Test
    public void test_give_nonExistCards_when_isInPlayerHandCard_then_returnFalse() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.setPlayerHandCardMapInTest();

        // 2. 計算結果
        boolean result = this.cardWallCtr.isInPlayerHandCard(-1, new int[]{1101,-56,1103,1104,1105});

        // 3. 驗證
        Assert.assertFalse(result);
    }

    // 給定正確位置、手牌，判斷玩家手牌是否存在，回傳正確
    @Test
    public void test_give_correctChairIndexAndCards_when_isInPlayerHandCard_expect_returnTrue() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.setPlayerHandCardMapInTest();

        // 2. 計算結果
        boolean result = this.cardWallCtr.isInPlayerHandCard(0, new int[]{2021,2031,2051,2091,2111});

        // 3. 驗證
        Assert.assertTrue(result);
    }

    // 給定玩家手牌，取得所有玩家牌號陣列，正確回傳轉譯結果
    @Test
    public void test_give_maxUser4_when_getAllPlayerHandCardNumberArray_expect_returnCorrectArray() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        Mockito.when(this.config.getMaxUser()).thenReturn(4);
        this.setPlayerHandCardMapInTest();

        // 2. 計算結果
        int[][] actualResult = this.cardWallCtr.getAllPlayerHandCardNumberArray();
        int[][] expectResult = new int[][]{
                {2021, 2031, 2051, 2091, 2111},
                {2021, 2031, 2051, 2091, 2111},
                {3021, 3031, 3051, 4091, 4091},
                {}
        };

        // 3. 驗證
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定手牌，正確轉換牌號陣列
    @Test
    public void test_given_cardList_when_changeCardList2CardNumberArray_then_returnCorrectArray() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        List<ICard> cardList = this.generateCardList();

        // 2. 計算結果
        int[] actualResult = this.cardWallCtr.changeCardList2CardNumberArray(cardList);
        int[] expectResult = new int[]{3021, 3031, 3051, 4091, 4091};

        // 3. 驗證
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定手牌，正確轉換牌點陣列
    @Test
    public void test_given_cardList_when_changeCardList2CardPointArray_then_returnCorrectArray() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        List<ICard> cardList = this.generateCardList();

        // 2. 計算結果
        int[] actualResult = this.cardWallCtr.changeCardList2CardPointArray(cardList);
        int[] expectResult = new int[]{2, 3, 5, 9, 9};

        // 3. 驗證
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定區域手牌，當取區域牌號陣列，正確回傳轉譯結果
    @Test
    public void test_give_3AreaWithOneNoCard_when_getAllAreaCardNumber2dArray_expect_returnCorrectArray() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.setAreaHandCardMapInTest();

        // 2. 計算結果
        int[][] actualResult = this.cardWallCtr.getAllAreaCardNumber2dArray();
        int[][] expectResult = new int[][]{
                {2021, 2031, 2051, 2091, 2111},
                {2021, 2031, 2051, 2091, 2111},
                {}
        };

        // 3. 驗證
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定區域手牌，當取區域點數陣列，正確回傳轉譯結果
    @Test
    public void test_give_3AreaWithOneNoCard_when_getAllAreaCardPoint2dArray_expect_returnCorrectArray() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.setAreaHandCardMapInTest();

        // 2. 計算結果
        int[][] actualResult = this.cardWallCtr.getAllAreaCardPoint2dArray();
        int[][] expectResult = new int[][]{
                {2, 3, 5, 9, 11},
                {2, 3, 5, 9, 11},
                {}
        };

        // 3. 驗證
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 傳入玩家座位手牌對應表，當轉換玩家手牌號2D陣列，正確回傳轉譯結果
    @Test
    public void test_give_maxUser4With3PeopleHaveCards_when_changeAllPlayerCardListMap2allPlayerCardNumberArray_expect_returnCorrectArray() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        Mockito.when(config.getMaxUser()).thenReturn(4);
        this.setPlayerHandCardMapInTest();

        // 2. 計算結果
        int[][] actualResult = this.cardWallCtr.changeAllPlayerCardListMap2allPlayerCardNumberArray(this.generateIdToCardListMap());
        int[][] expectResult = new int[][]{
                {2021, 2031, 2051, 2091, 2111},
                {},
                {2021, 2031, 2051, 2091, 2111},
                {}
        };

        // 3. 驗證
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 傳入押注區域手牌對應表，當轉換區域手牌號2D陣列，正確回傳轉譯結果
    @Test
    public void test_give_3AreaWith2AreaHaveCards_when_changeAllAreaCardListMap2allPlayerCardNumberArray_expect_returnCorrectArray() {
        // 1. 設置手牌
        this.setMockToolReturnValue();
        this.cardWallCtr = new CardWallCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.setPlayerHandCardMapInTest();

        // 2. 計算結果
        int[][] actualResult = this.cardWallCtr.changeAllAreaCardListMap2allPlayerCardNumberArray(this.generateIdToCardListMap());
        int[][] expectResult = new int[][]{
                {2021, 2031, 2051, 2091, 2111},
                {},
                {2021, 2031, 2051, 2091, 2111}
        };

        // 3. 驗證
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 設定 mock 資料
    private void setMockToolReturnValue() {
        Mockito.when(this.config.getInitWallList()).thenReturn(new int[][]{
                {101,1}, {102,1}, {103,1}, {104,1}, {105,1}, {106,1}, {107,1}, {108,1}, {109,1}, {110,1}, {111,1}, {112,1}, {113,1}, // 方塊
                {201,1}, {202,1}, {203,1}, {204,1}, {205,1}, {206,1}, {207,1}, {208,1}, {209,1}, {210,1}, {211,1}, {212,1}, {213,1}, // 梅花
                {301,1}, {302,1}, {303,1}, {304,1}, {305,1}, {306,1}, {307,1}, {308,1}, {309,1}, {310,1}, {311,1}, {312,1}, {313,1}, // 紅心
                {401,1}, {402,1}, {403,1}, {404,1}, {405,1}, {406,1}, {407,1}, {408,1}, {409,1}, {410,1}, {411,1}, {412,1}, {413,1}});
        Mockito.when(this.config.getCardValueWeightMap()).thenReturn(this.generateCardValueWeightMap());
        Mockito.when(this.config.getCardType()).thenReturn(ConstMathCard.CardType.POKER);
    }

    // 設定手牌
    private void setPlayerHandCardMapInTest() {
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();
        this.cardWallCtr.handCardListMap.put(0, new ArrayList<>() {
            {   add(new CardPoker(202,1,cardValueWeightMap));
                add(new CardPoker(203,1,cardValueWeightMap));
                add(new CardPoker(205,1,cardValueWeightMap));
                add(new CardPoker(209,1,cardValueWeightMap));
                add(new CardPoker(211,1,cardValueWeightMap));
            }
        });

        this.cardWallCtr.handCardListMap.put(1, new ArrayList<>() {
            {   add(new CardPoker(202,1,cardValueWeightMap));
                add(new CardPoker(203,1,cardValueWeightMap));
                add(new CardPoker(205,1,cardValueWeightMap));
                add(new CardPoker(209,1,cardValueWeightMap));
                add(new CardPoker(211,1,cardValueWeightMap));
            }
        });

        this.cardWallCtr.handCardListMap.put(2, new ArrayList<>() {
            {   add(new CardPoker(302,1,cardValueWeightMap));
                add(new CardPoker(303,1,cardValueWeightMap));
                add(new CardPoker(305,1,cardValueWeightMap));
                add(new CardPoker(409,1,cardValueWeightMap));
                add(new CardPoker(409,1,cardValueWeightMap));
            }
        });
    }

    // 設定手牌
    private void setAreaHandCardMapInTest() {
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();
        this.cardWallCtr.areaCardListMap.put(0, new ArrayList<>() {
            {   add(new CardPoker(202,1,cardValueWeightMap));
                add(new CardPoker(203,1,cardValueWeightMap));
                add(new CardPoker(205,1,cardValueWeightMap));
                add(new CardPoker(209,1,cardValueWeightMap));
                add(new CardPoker(211,1,cardValueWeightMap));
            }
        });

        this.cardWallCtr.areaCardListMap.put(1, new ArrayList<>() {
            {   add(new CardPoker(202,1,cardValueWeightMap));
                add(new CardPoker(203,1,cardValueWeightMap));
                add(new CardPoker(205,1,cardValueWeightMap));
                add(new CardPoker(209,1,cardValueWeightMap));
                add(new CardPoker(211,1,cardValueWeightMap));
            }
        });

        this.cardWallCtr.areaCardListMap.put(2, new ArrayList<>());
    }

    // 產出測試用牌列表
    private List<ICard> generateCardList() {
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();
        return new ArrayList<>() {
            {   add(new CardPoker(302,1,cardValueWeightMap));
                add(new CardPoker(303,1,cardValueWeightMap));
                add(new CardPoker(305,1,cardValueWeightMap));
                add(new CardPoker(409,1,cardValueWeightMap));
                add(new CardPoker(409,1,cardValueWeightMap));
            }
        };
    }

    // 產出 <Integer, List<ICard>> 對應表
    private Map<Integer, List<ICard>> generateIdToCardListMap() {
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();

        return new HashMap<>(){
            {
                put(0, new ArrayList<>() {
                    {   add(new CardPoker(202,1,cardValueWeightMap));
                        add(new CardPoker(203,1,cardValueWeightMap));
                        add(new CardPoker(205,1,cardValueWeightMap));
                        add(new CardPoker(209,1,cardValueWeightMap));
                        add(new CardPoker(211,1,cardValueWeightMap));
                    }
                });
                put(1, new ArrayList<>());
                put(2, new ArrayList<>() {
                    {   add(new CardPoker(202,1,cardValueWeightMap));
                        add(new CardPoker(203,1,cardValueWeightMap));
                        add(new CardPoker(205,1,cardValueWeightMap));
                        add(new CardPoker(209,1,cardValueWeightMap));
                        add(new CardPoker(211,1,cardValueWeightMap));
                    }
                });
            }
        };
    }

    // 創建牌權重
    private Map<Integer, Integer> generateCardValueWeightMap() {
        return new HashMap<>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
            put(6,6);
            put(7,7);
            put(8,8);
            put(9,9);
            put(10,10);
            put(11,11);
            put(12,12);
            put(13,13);
        }};
    }
}