package org.lsj.gs.math.core.card.stackCtr.bjlStackCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 百家樂發牌工具包測試
class BjlDealUtilTest extends AbstractCardTestUtil {
    private BjlDealUtil bjlDealUtil; // 百家樂發牌工具包

    @BeforeEach
    void setUp() {
        this.bjlDealUtil = new BjlDealUtil();
    }

    //* 檢查是否天牌 *//

    // 給定莊家天牌、閒家無天牌，當計算是否有天牌，回傳 true
    @Test
    void test_given_bankTianPai_when_checkTianCardType_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{301, 302, 303, 305, 401, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkTianCardType(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家無天牌、閒家天牌，當計算是否有天牌，回傳 true
    @Test
    void test_given_playTianPai_when_checkTianCardType_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{304, 405, 301, 302, 401, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkTianCardType(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家無天牌、閒家無天牌，當計算是否有天牌，回傳 false
    @Test
    void test_given_noTianPai_when_checkTianCardType_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{301, 302, 302, 401, 401, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkTianCardType(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }


    //* 檢查閒家是否補牌 *//

    // 給定閒家點數 <= 5，當計算是否補閒家牌，回傳 true
    @Test
    void test_given_playPointSmallerEqualThan5_when_checkAddPlayCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 301, 302, 401, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddPlayCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定閒家點數 > 5，當計算是否補閒家牌，回傳 false
    @Test
    void test_given_playPointBiggerThan5_when_checkAddPlayCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 406, 301, 302, 401, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddPlayCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }


    //* 檢查莊家是否補牌 *//

    // 給定莊家點數 7，閒家補牌，當計算是否補莊家牌，回傳 false
    @Test
    void test_given_bankPoint7_playAddCard_when_checkAddBankCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 301, 306, 403, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定莊家點數 7，閒家沒補牌，當計算是否補莊家牌，回傳 false
    @Test
    void test_given_bankPoint7_playNoAddCard_when_checkAddBankCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 407, 301, 306, 403, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定莊家點數 6，閒家有補牌屬於 [0, 1, 2, 3, 4, 5, 8, 9]，當計算是否補莊家牌，回傳 false
    @Test
    void test_given_bankPoint6_playAddCardBelongToList_when_checkAddBankCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 306, 403, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定莊家點數 6，閒家有補牌不屬於 [0, 1, 2, 3, 4, 5, 8, 9]，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint6_playAddCardNotBelongToList_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 306, 406, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 6，閒家沒補牌，當計算是否補莊家牌，回傳 false
    @Test
    void test_given_bankPoint6_playNoAddCard_when_checkAddBankCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 407, 410, 306, 403, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定莊家點數 5，閒家有補牌屬於 [0, 1, 2, 3, 8, 9]，當計算是否補莊家牌，回傳 false
    @Test
    void test_given_bankPoint5_playAddCardBelongToList_when_checkAddBankCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 305, 403, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定莊家點數 5，閒家有補牌不屬於 [0, 1, 2, 3, 8, 9]，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint5_playAddCardNotBelongToList_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 305, 404, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 5，閒家沒補牌，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint5_playNoAddCard_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 407, 410, 305, 404, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 4，閒家有補牌屬於 [0, 1, 8, 9]，當計算是否補莊家牌，回傳 false
    @Test
    void test_given_bankPoint4_playAddCardBelongToList_when_checkAddBankCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 304, 401, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定莊家點數 4，閒家有補牌不屬於 [0, 1, 8, 9]，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint4_playAddCardNotBelongToList_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 304, 403, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 4，閒家沒補牌，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint4_playNoAddCard_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 407, 410, 304, 403, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 3，閒家有補牌屬於 [8]，當計算是否補莊家牌，回傳 false
    @Test
    void test_given_bankPoint3_playAddCardBelongToList_when_checkAddBankCard_then_return_false() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 303, 408, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定莊家點數 3，閒家有補牌不屬於 [8]，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint3_playAddCardNotBelongToList_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 405, 410, 303, 409, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 3，閒家沒補牌，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint3_playNoAddCard_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 407, 410, 303, 409, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 2，閒家沒補牌，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint2_playNoAddCard_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 407, 410, 302, 409, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }

    // 給定莊家點數 2，閒家補牌，當計算是否補莊家牌，回傳 true
    @Test
    void test_given_bankPoint2_playAddCard_when_checkAddBankCard_then_return_true() {
        // 1. given
        Map<Integer, List<Integer>> dealAreaIdToCardIndexListMap = this.generatePlayBankToCardIndexListMap(List.of(0, 1, 4), List.of(2, 3));
        List<ICard> unTakenCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{310, 403, 410, 302, 409, 402});

        // 2. when
        boolean actualResult = this.bjlDealUtil.checkAddBankCard(dealAreaIdToCardIndexListMap, unTakenCardList);

        // 3. then
        assertTrue(actualResult);
    }


    //* 初始化相關 *//

    // 產出 發牌區域、排序列表 對應
    private Map<Integer, List<Integer>> generatePlayBankToCardIndexListMap(List<Integer> playCardIndexList, List<Integer> bankCardIndexList) {
        Map<Integer, List<Integer>> result = new HashMap<>();

        result.put(ConstMathBjl.DealAreaEnum.PLAY.getCode(), playCardIndexList);
        result.put(ConstMathBjl.DealAreaEnum.BANK.getCode(), bankCardIndexList);

        return result;
    }
}