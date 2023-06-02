package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardPoker;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 通用牛型測試
class NiuStackCommonTest {

    //* 牌型不同的比較 *//
    // 給定兩組不一樣牌型，比牌正確
    @Test
    public void test_given_twoDifferentCardType_when_compareTo_then_bigType() {
        // 1. 準備資料
        NiuStackCommon bigType = new NiuStackCommon(this.generateCardList(new int[]{105,110,111,112,113}), this.generateCardList(new int[]{101,113}), ConstNiu.NiuTypeEnumCommon.NIU_5, true);
        NiuStackCommon smallType = new NiuStackCommon(this.generateCardList(new int[]{206,202,203,305,205}), this.generateCardList(new int[]{206,205}), ConstNiu.NiuTypeEnumCommon.NIU_1, true);

        // 2. 計算結果
        int compareResult = bigType.compareTo(smallType);

        // 3. 驗證
        Assertions.assertEquals(1, compareResult);
    }


    //* 牌型相同的比較 *//
    // 給定兩個順金牛，一個AK順且另一個非AK順，比牌正確
    @Test
    public void test_given_shunJinNiuWithAkAndNotAk_when_compareTo_then_shunJinNiuWithAk() {
        // 1. 準備資料
        NiuStackCommon shunJinNiuAk = new NiuStackCommon(this.generateCardList(new int[]{111,110,101,113,112}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU, true);
        NiuStackCommon shunJinNiuNotAk = new NiuStackCommon(this.generateCardList(new int[]{409,410,411,412,413}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU, true);

        // 2. 計算結果
        int compareResult = shunJinNiuAk.compareTo(shunJinNiuNotAk);

        // 3. 驗證
        Assertions.assertEquals(1, compareResult);
    }

    // 給定兩個順金牛，一個KQ順且另一個54順，比牌正確
    @Test
    public void test_given_shunJinNiuWithKqAndWith54_when_compareTo_then_shunJinNiuWithKq() {
        // 1. 準備資料
        NiuStackCommon shunJinNiuKq = new NiuStackCommon(this.generateCardList(new int[]{111,110,109,113,112}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU, true);
        NiuStackCommon shunJinNiu54 = new NiuStackCommon(this.generateCardList(new int[]{105,104,103,102,101}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.SHUNJIN_NIU, true);

        // 2. 計算結果
        int compareResult = shunJinNiuKq.compareTo(shunJinNiu54);

        // 3. 驗證
        Assertions.assertEquals(1, compareResult);
    }

    // 給定兩個四炸，比牌正確
    @Test
    public void test_given_bombJWith3AndBomb2WithK_when_compareTo_then_bombJWith3() {
        // 1. 準備資料
        NiuStackCommon bombJ = new NiuStackCommon(this.generateCardList(new int[]{103,111,211,311,411}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.BOMB, true);
        NiuStackCommon bomb2 = new NiuStackCommon(this.generateCardList(new int[]{113,102,202,302,402}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.BOMB, false);

        // 2. 計算結果
        int compareResult = bombJ.compareTo(bomb2);

        // 3. 驗證
        Assertions.assertEquals(1, compareResult);
    }

    // 給定兩個葫蘆牛，比牌正確
    @Test
    public void test_given_huluNiuWithThree12Two3AndThree2Two13_when_compareTo_then_bombJWith3() {
        // 1. 準備資料
        NiuStackCommon huluNiuWithThree12Two3 = new NiuStackCommon(this.generateCardList(new int[]{112,212,312,303,403}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.HULU_NIU, true);
        NiuStackCommon huluNiuWithThree2Two13 = new NiuStackCommon(this.generateCardList(new int[]{102,202,302,313,413}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.HULU_NIU, false);

        // 2. 計算結果
        int compareResult = huluNiuWithThree12Two3.compareTo(huluNiuWithThree2Two13);

        // 3. 驗證
        Assertions.assertEquals(1, compareResult);
    }

    // 給定兩個順子牛，比牌正確
    @Test
    public void test_given_shunZiNiuNotAkAndWithAk_when_compareTo_then_shunZiNiuWithAk() {
        // 1. 準備資料
        NiuStackCommon shunZiNiuNotAk = new NiuStackCommon(this.generateCardList(new int[]{113,112,211,309,410}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.SHUNZI_NIU, true);
        NiuStackCommon shunZiNiuAk = new NiuStackCommon(this.generateCardList(new int[]{113,201,210,311,412}), this.generateCardList(new int[]{}), ConstNiu.NiuTypeEnumCommon.SHUNZI_NIU, true);

        // 2. 計算結果
        int compareResult = shunZiNiuNotAk.compareTo(shunZiNiuAk);

        // 3. 驗證
        Assertions.assertEquals(-1, compareResult);
    }

    // 給定兩組一樣牌型、一樣點數、不同花色，比牌正確
    @Test
    public void test_given_twoSameCardTypeCardPointWithDifferentColor_when_compareTo_then_bigColor() {
        // 1. 準備資料
        NiuStackCommon bigColor = new NiuStackCommon(this.generateCardList(new int[]{201,202,203,204,205}), this.generateCardList(new int[]{201,204}), ConstNiu.NiuTypeEnumCommon.NIU_5, true);
        NiuStackCommon smallColor = new NiuStackCommon(this.generateCardList(new int[]{101,102,103,104,105}), this.generateCardList(new int[]{101,104}), ConstNiu.NiuTypeEnumCommon.NIU_5, true);

        // 2. 計算結果
        int compareResult = bigColor.compareTo(smallColor);

        // 3. 驗證
        Assertions.assertEquals(1, compareResult);
    }

    // 給定兩組一樣牌型、不同點數，比牌正確
    @Test
    public void test_given_twoSameCardTypeWithDifferentCardPoint_when_compareTo_then_bigCardPoint() {
        // 1. 準備資料
        NiuStackCommon bigPoint = new NiuStackCommon(this.generateCardList(new int[]{101,110,111,112,113}), this.generateCardList(new int[]{101,113}), ConstNiu.NiuTypeEnumCommon.NIU_1, true);
        NiuStackCommon smallPoint = new NiuStackCommon(this.generateCardList(new int[]{206,202,203,305,205}), this.generateCardList(new int[]{206,205}), ConstNiu.NiuTypeEnumCommon.NIU_1, true);

        // 2. 計算結果
        int compareResult = bigPoint.compareTo(smallPoint);

        // 3. 驗證
        Assertions.assertEquals(1, compareResult);
    }


    //* 共用工具檢驗 *//
    // 給定牛5，計算最大牌正確
    @Test
    public void test_given_Niu5Cards_when_calculateMaxCard_then_returnCorrect() {
        // 1. 準備資料
        NiuStackCommon niuStack = new NiuStackCommon(this.generateCardList(new int[]{101,102,107,310,105}), this.generateCardList(new int[]{310,105}), ConstNiu.NiuTypeEnumCommon.NIU_5, true);

        // 2. 計算結果
        ICard maxCard = niuStack.calculateMaxCard();

        // 3. 驗證
        Assertions.assertEquals(3, maxCard.getType());
        Assertions.assertEquals(10, maxCard.getPoint());
        Assertions.assertEquals(310, maxCard.getValue());
        Assertions.assertEquals(3101, maxCard.getNumber());
    }

    // 給定五花牛，計算最大牌正確
    @Test
    public void test_given_fiveFlowerCards_when_calculateMaxCard_then_returnCorrect() {
        // 1. 準備資料
        NiuStackCommon niuStack = new NiuStackCommon(this.generateCardList(new int[]{111,413,113,213,313}), this.generateCardList(new int[]{213,313}), ConstNiu.NiuTypeEnumCommon.FLOWER_5, true);

        // 2. 計算結果
        ICard maxCard = niuStack.calculateMaxCard();

        // 3. 驗證
        Assertions.assertEquals(4, maxCard.getType());
        Assertions.assertEquals(13, maxCard.getPoint());
        Assertions.assertEquals(413, maxCard.getValue());
        Assertions.assertEquals(4131, maxCard.getNumber());
    }

    // 依照指定牌Array，輸出牌List
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