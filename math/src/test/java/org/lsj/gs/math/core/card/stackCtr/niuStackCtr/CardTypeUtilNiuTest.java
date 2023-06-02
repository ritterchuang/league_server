package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardTypeUtilNiuTest extends AbstractCardTestUtil {
    private CardTypeUtilNiu cardTypeUtil; // 牌型工具包

    @BeforeEach
    public void setUp() {
        this.cardTypeUtil = new CardTypeUtilNiu();
    }

    // 給五小牛(1,1,1,2,2)，判斷五小牛，回傳 true
    @Test
    public void test_given_fiveSmallNiuCard_when_isSmallNiu_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 201, 301, 402, 302});

        // 2. 運算結果
        boolean isSmallNiu = this.cardTypeUtil.checkSmallNiu(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isSmallNiu);
    }

    // 給非五小牛(1,1,1,5,2)，判斷五小牛，回傳 false
    @Test
    public void test_given_nonFiveSmallNiuCard_when_isSmallNiu_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 201, 301, 405, 302});

        // 2. 運算結果
        boolean isSmallNiu = this.cardTypeUtil.checkSmallNiu(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isSmallNiu);
    }

    // 給非五小牛(有牛 2,4,4,1,1)，判斷五小牛，回傳 false
    @Test
    public void test_given_nonFiveSmallNiuCardWithNiuPoint_when_isSmallNiu_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{102, 204, 304, 401, 301});

        // 2. 運算結果
        boolean isSmallNiu = this.cardTypeUtil.checkSmallNiu(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isSmallNiu);
    }

    // 給順金牛(413, 401, 410, 411, 412)，判斷順金牛，回傳 true
    @Test
    public void test_given_bigShunJinNiuCard_when_isShunJinNiu_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{413, 401, 410, 411, 412});

        // 2. 運算結果
        boolean isSmallNiu = this.cardTypeUtil.checkShunJinNiu(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isSmallNiu);
    }

    // 給順金牛(403, 404, 405, 406, 407)，判斷順金牛，回傳 true
    @Test
    public void test_given_shunJinNiuCard_when_isShunJinNiu_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{403, 404, 405, 406, 407});

        // 2. 運算結果
        boolean isSmallNiu = this.cardTypeUtil.checkShunJinNiu(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isSmallNiu);
    }

    // 給非順金牛(403, 404, 105, 406, 407)，判斷順金牛，回傳 false
    @Test
    public void test_given_nonShunJinNiuCard_when_isShunJinNiu_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{403, 404, 105, 406, 407});

        // 2. 運算結果
        boolean isSmallNiu = this.cardTypeUtil.checkShunJinNiu(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isSmallNiu);
    }

    // 給四炸(1,1,1,1,2)，判斷四炸，回傳 true
    @Test
    public void test_given_fourBombCard_when_isBomb_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 201, 301, 401, 302});

        // 2. 運算結果
        boolean isFourBomb = this.cardTypeUtil.checkBomb(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isFourBomb);
    }

    // 給非四炸(1,1,2,1,2)，判斷四炸，回傳 false
    @Test
    public void test_given_nonFourBombCard_when_isBomb_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 201, 302, 401, 302});

        // 2. 運算結果
        boolean isFourBomb = this.cardTypeUtil.checkBomb(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isFourBomb);
    }

    // 給五花(11,11,11,11,12)，判斷五花，回傳 true
    @Test
    public void test_given_fiveFlowerNiuCard_when_isFiveFlower_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{111, 211, 311, 411, 312});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkFiveFlower(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isFiveFlower);
    }

    // 給非五花(11,09,11,11,12)，判斷五花，回傳 false
    @Test
    public void test_given_nonFiveFlowerNiuCard_when_isFiveFlower_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{111, 209, 311, 411, 312});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkFiveFlower(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isFiveFlower);
    }

    // 給葫蘆牛(101,201,102,202,302)，判斷葫蘆牛，回傳 true
    @Test
    public void test_given_huluNiuCard_when_isHuluNiu_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101,201,102,202,302});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkHuluNiu(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isFiveFlower);
    }

    // 給非葫蘆牛(101,201,102,202,303)，判斷葫蘆牛，回傳 false
    @Test
    public void test_given_nonHuluNiuCard3PointCategory_when_isHuluNiu_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101,201,102,202,303});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkHuluNiu(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isFiveFlower);
    }

    // 給非葫蘆牛(101,201,301,401,303)，判斷葫蘆牛，回傳 false
    @Test
    public void test_given_nonHuluNiuCardBomb_when_isHuluNiu_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101,201,301,401,303});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkHuluNiu(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isFiveFlower);
    }

    // 給同花牛(101,103,105,107,109)，判斷同花牛，回傳 true
    @Test
    public void test_given_tongHuaNiuCard_when_isTongHuaNiu_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101,103,105,107,109});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkTongHuaNiu(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isFiveFlower);
    }

    // 給非同花牛(101,103,105,107,209)，判斷同花牛，回傳 false
    @Test
    public void test_given_nonTongHuaNiuCard3PointCategory_when_isTongHuaNiu_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101,103,105,107,209});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkTongHuaNiu(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isFiveFlower);
    }

    // 給順子牛(101,202,303,404,405)，判斷順子牛，回傳 true
    @Test
    public void test_given_shunZiNiuCard_when_isShunZiNiu_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101,202,303,404,405});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkShunZiNiu(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isFiveFlower);
    }

    // 給非順子牛(101,103,105,107,209)，判斷順子牛，回傳 false
    @Test
    public void test_given_nonShunZiNiuCard_when_isShunZiNiu_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101,103,105,107,209});

        // 2. 運算結果
        boolean isFiveFlower = this.cardTypeUtil.checkShunZiNiu(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isFiveFlower);
    }

    // 給四花(10,11,11,11,12)，判斷四花，回傳 true
    @Test
    public void test_given_fourFlowerNiuCard_when_isFourFlower_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{110, 211, 311, 411, 312});

        // 2. 運算結果
        boolean isFourFlower = this.cardTypeUtil.checkFourFlower(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isFourFlower);
    }

    // 給非四花(10,9,11,11,12)，判斷四花，回傳 false
    @Test
    public void test_given_nonFiveFlowerNiuCard_when_isFourFlower_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{110, 209, 311, 411, 312});

        // 2. 運算結果
        boolean isFourFlower = this.cardTypeUtil.checkFourFlower(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isFourFlower);
    }

    // 給任意有牛牌(10,9,11,11,12)，加總計算正確
    @Test
    public void test_given_niu9Card_when_calculateCardPointSum_then_correct() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{110, 209, 311, 411, 312});

        // 2. 運算結果
        int cardPointSum = this.cardTypeUtil.calculateCardPointSum(cardsList);
        int niuPoint = this.cardTypeUtil.calculateCardPointSumModuleBy10(cardsList);

        // 3. 驗證
        Assertions.assertEquals(49, cardPointSum);
        Assertions.assertEquals(9, niuPoint);
    }
}