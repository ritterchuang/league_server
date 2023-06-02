package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardPoker;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.junit.jupiter.api.Assertions;
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

@ExtendWith(MockitoExtension.class)
class NiuStackCtrTest {
    NiuStackCtr niuStackCtr; // 搶莊計算器
    @Mock
    NiuStackCtrConfig config; // 搶莊計算器設定
    @Mock
    GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr poolCtr; // 水池控制器
    @Mock ITableUtil tableUtil; // 牌桌工具包

    @BeforeEach
    void setUp() {
        this.niuStackCtr = new NiuStackCtr(config, gamePlayerHlr, poolCtr, tableUtil);
    }

    // 當玩家已選牌，預期回傳true
    @Test
    public void test_given_playerHadSelectResult_when_isPlayerSelect_then_returnTrue() {
        // 1. 準備資料
        this.niuStackCtr.selectResult.put(0, this.generateNiuStackBySpecifyCard(new int[]{101,102,107,103,106}, ConstNiu.NiuTypeEnumCommon.NIU_9, true));

        // 2. 運算結果
        boolean result = this.niuStackCtr.isPlayerSelect(0);

        // 3. 驗證
        Assertions.assertTrue(result);
    }

    // 當無效玩家，預期回傳false
    @Test
    public void test_given_nonExistPlayer_when_isPlayerSelect_then_returnFalse() {
        // 1. 準備資料
        this.niuStackCtr.selectResult.put(0, this.generateNiuStackBySpecifyCard(new int[]{101,102,107,103,106}, ConstNiu.NiuTypeEnumCommon.NIU_9, true));

        // 2. 運算結果
        boolean result = this.niuStackCtr.isPlayerSelect(-1);

        // 3. 驗證
        Assertions.assertFalse(result);
    }

    // 當玩家傳入最大牌型，預期回傳true
    @Test
    public void test_given_maxNiuStack_when_isMaxStack_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{101,102,107,103,106});
        this.setMockToolReturnValue();

        // 2. 計算結果
        boolean result = this.niuStackCtr.isMaxStack(cardList);

        // 3. 驗證
        Assertions.assertTrue(result);
    }

    // 當玩家傳入非最大牌型，預期回傳false
    @Test
    public void test_given_nonMaxNiuStack_when_isMaxStack_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{101,102,103,107,106});
        this.setMockToolReturnValue();

        // 2. 計算結果
        boolean result = this.niuStackCtr.isMaxStack(cardList);

        // 3. 驗證
        Assertions.assertFalse(result);
    }

    // 當無牛，不會有提牌
    @Test
    public void test_given_noNiuCard_when_calculateNiuStack_then_correctTypeAndNoLiftCards() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{101,201,103,107,105});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);

        // 3. 驗證
        Assertions.assertEquals(0, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.NIU_0, result.getNiuTypeEnumCommon());
    }

    // 當有牛，會提牌，且提牌為牛數
    @Test
    public void test_given_niu8Card_when_calculateNiuStack_then_correctTypeAndLiftCards() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{101,201,103,108,105});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);
        int cardPoint = (result.getLiftCardList().get(0).getPoint() + result.getLiftCardList().get(1).getPoint()) % 10;

        // 3. 驗證
        Assertions.assertEquals(2, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.NIU_8, result.getNiuTypeEnumCommon());
        Assertions.assertEquals(8, cardPoint);
    }

    // 當牌滿足五小牛、炸彈，選牌結果為五小牛
    @Test
    public void test_given_fiveSmallNiuAndBomb_when_calculateNiuStack_then_resultFiveSmallNiu() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{101,201,101,401,102});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);

        // 3. 驗證
        Assertions.assertEquals(0, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, result.getNiuTypeEnumCommon());
    }

    // 當牌滿足炸彈、五花牛，選牌結果為炸彈
    @Test
    public void test_given_bombAndFiveFlowerNiu_when_calculateNiuStack_then_resultBomb() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{111,211,311,411,112});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);

        // 3. 驗證
        Assertions.assertEquals(2, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.BOMB, result.getNiuTypeEnumCommon());
    }

    // 當牌滿足炸彈、四花牛，選牌結果為炸彈
    @Test
    public void test_given_bombAndFourFlowerNiu_when_calculateNiuStack_then_resultBomb() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{111,211,311,411,110});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);

        // 3. 驗證
        Assertions.assertEquals(2, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.BOMB, result.getNiuTypeEnumCommon());
    }

    // 當牌滿足炸彈、牛牛，選牌結果為炸彈
    @Test
    public void test_given_bombAndNiuNiu_when_calculateNiuStack_then_resultBomb() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{105,205,305,405,110});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);

        // 3. 驗證
        Assertions.assertEquals(2, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.BOMB, result.getNiuTypeEnumCommon());
    }

    // 當牌滿足炸彈、沒牛，選牌結果為炸彈
    @Test
    public void test_given_bombAndNiu0_when_calculateNiuStack_then_resultBomb() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{104,204,304,404,110});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);

        // 3. 驗證
        Assertions.assertEquals(0, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.BOMB, result.getNiuTypeEnumCommon());
    }

    // 當牌點數和12，且每張牌小於5，選牌結果為牛2
    @Test
    public void test_given_niu2AndEachCardSmallerThan5_when_calculateNiuStack_then_resultNiu2() {
        // 1. 準備資料
        List<ICard> cardList = this.generateCardList(new int[]{102, 104, 204, 101, 201});
        this.setMockToolReturnValue();

        // 2. 計算結果
        AbstractNiuStack result = this.niuStackCtr.calculateNiuStack(cardList);

        // 3. 驗證
        Assertions.assertEquals(2, result.getLiftCardList().size());
        Assertions.assertEquals(ConstNiu.NiuTypeEnumCommon.NIU_2, result.getNiuTypeEnumCommon());
    }

    // 設定 mock 物件
    private void setMockToolReturnValue() {
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

    // 創建NiuStack
    private AbstractNiuStack generateNiuStackBySpecifyCard(int[] cardArray, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon, boolean isNiu) {
        // 1. 產出牌型List
        List<ICard> cardList = this.generateCardList(cardArray);

        // 2. 回傳
        return new NiuStackCommon(
                cardList,
                new ArrayList<>() {},
                niuTypeEnumCommon,
                isNiu
        );
    }

    // 依照指定牌，輸出牌List
    private List<ICard> generateCardList(int[] cardsArray) {
        // 1. 產牌權重
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();

        // 2. 產牌List
        List<ICard> result = new ArrayList<>();
        for (int cardIndex = 0; cardIndex < cardsArray.length; cardIndex++) {
            result.add(new CardPoker(cardsArray[cardIndex], 1, cardValueWeightMap));
        }

        return result;
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