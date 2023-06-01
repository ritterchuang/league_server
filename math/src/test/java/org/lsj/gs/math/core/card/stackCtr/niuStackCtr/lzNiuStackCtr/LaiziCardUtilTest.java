package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.lzNiuStackCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.CardPoker;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LaiziCardUtilTest extends AbstractCardTestUtil {
    LaiziCardUtil laiziCardUtil; // 賴子牌型工具包

    @Mock
    CardWallCtr mockCardWallCtr; // 牌桌計算器


    @BeforeEach
    void setUp() {
        this.laiziCardUtil = new LaiziCardUtil();
    }

    // 手牌無賴子，計算所有賴子牌可能，僅回傳原本手牌
    @Test
    void test_given_noLaiziCard_when_calculatePossibleCardList_then_return_original_cardList() {
        // 1. 準備資料
        List<ICard> cardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{102, 103, 301, 306, 402});

        // 2. 計算結果
        List<List<ICard>> possibleCardList = this.laiziCardUtil.calculatePossibleCardList(cardList, mockCardWallCtr);

        // 3. 取得結果
        String expectResult = JsonUtil.getInstance().writeValueAsStringWithoutException(List.of(cardList));
        String actualResult = JsonUtil.getInstance().writeValueAsStringWithoutException(possibleCardList);

        // 4. 驗證
        assertEquals(expectResult, actualResult);
    }

    // 手牌有同花賴子，計算所有賴子牌可能，回傳同花、最大牌色可能組合
    @Test
    void test_given_tongHuaWithLaiziCard_when_calculatePossibleCardList_then_return_maxCardListAndTongHuaCardList() {
        // 1. 準備資料
        List<ICard> cardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{102, 103, 101, 106, 502});
        Mockito.when(this.mockCardWallCtr.generateLaiziCardList(1, cardList.get(4))).thenReturn(this.generateLaiziCardList(this.generateTongHuaPossibleCardValue(), cardList.get(4)));
        Mockito.when(this.mockCardWallCtr.generateLaiziCardList(4, cardList.get(4))).thenReturn(this.generateLaiziCardList(this.generateMaxPossibleCardValue(), cardList.get(4)));

        // 2. 計算結果
        List<List<ICard>> possibleCardList = this.laiziCardUtil.calculatePossibleCardList(cardList, mockCardWallCtr);
        List<List<ICard>> expectCardList = this.generateExpectTongHuaCardList();

        // 3. 取得結果
        String expectResult = JsonUtil.getInstance().writeValueAsStringWithoutException(expectCardList);
        String actualResult = JsonUtil.getInstance().writeValueAsStringWithoutException(possibleCardList);

        // 4. 驗證
        assertEquals(expectResult, actualResult);
    }

    // 計算所有賴子牌轉變列表
    private List<ICard> generateLaiziCardList(List<Integer> cardValueList, ICard laiziCard) {
        List<ICard> result = new ArrayList<>();

        int countIndex = (laiziCard.getValue() + 2) % 10;

        for (Integer integer : cardValueList) {
            result.add(new CardPoker(integer, countIndex, super.generateCardValueWeightMapByPointToWeight()));
        }

        return result;
    }

    // 列出同花牌值列表
    private List<Integer> generateTongHuaPossibleCardValue() {
        return List.of(101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113);
    }

    // 列出最大牌值列表
    private List<Integer> generateMaxPossibleCardValue() {
        return List.of(401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413);
    }

    // 列出預期同花牌結果
    private List<List<ICard>> generateExpectTongHuaCardList() {
        List<ICard> cardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{102, 103, 101, 106});

        List<List<ICard>> result = new ArrayList<>();

        int countIndex = 4;
        List<Integer> cardValueList = List.of(401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113);

        for (Integer integer : cardValueList) {
            List<ICard> temp = new ArrayList<>(cardList);
            temp.add(new CardPoker(integer, countIndex, super.generateCardValueWeightMapByPointToWeight()));
            result.add(temp);
        }

        return result;
    }

    // 手牌有最大同花賴子，計算所有賴子牌可能，回傳最大牌色可能組合
    @Test
    void test_given_maxTongHuaWithLaiziCard_when_calculatePossibleCardList_then_return_maxCardList() {
        // 1. 準備資料
        List<ICard> cardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{402, 403, 401, 406, 502});
        Mockito.when(this.mockCardWallCtr.generateLaiziCardList(4, cardList.get(4))).thenReturn(this.generateLaiziCardList(this.generateMaxPossibleCardValue(), cardList.get(4)));

        // 2. 計算結果
        List<List<ICard>> possibleCardList = this.laiziCardUtil.calculatePossibleCardList(cardList, mockCardWallCtr);
        List<List<ICard>> expectCardList = this.generateExpectMaxCardList(new int[]{402, 403, 401, 406});

        // 3. 取得結果
        String expectResult = JsonUtil.getInstance().writeValueAsStringWithoutException(expectCardList);
        String actualResult = JsonUtil.getInstance().writeValueAsStringWithoutException(possibleCardList);

        // 4. 驗證
        assertEquals(expectResult, actualResult);
    }

    // 列出預期同花牌結果
    private List<List<ICard>> generateExpectMaxCardList(int[] handCardValueArray) {
        List<ICard> cardList = super.generateCardList(ConstMathCard.CardType.POKER, handCardValueArray);

        List<List<ICard>> result = new ArrayList<>();

        int countIndex = 4;
        List<Integer> cardValueList = List.of(401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413);

        for (int index = 0; index < cardValueList.size(); index++) {
            List<ICard> temp = new ArrayList<>(cardList);
            temp.add(new CardPoker(cardValueList.get(index), countIndex, super.generateCardValueWeightMapByPointToWeight()));
            result.add(temp);
        }

        return result;
    }

    // 手牌有賴子，計算所有賴子牌可能，回傳最大牌色可能組合
    @Test
    void test_given_laiziCard_when_calculatePossibleCardList_then_return_maxCardList() {
        // 1. 準備資料
        List<ICard> cardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{402, 403, 401, 406, 502});
        Mockito.when(this.mockCardWallCtr.generateLaiziCardList(4, cardList.get(4))).thenReturn(this.generateLaiziCardList(this.generateMaxPossibleCardValue(), cardList.get(4)));

        // 2. 計算結果
        List<List<ICard>> possibleCardList = this.laiziCardUtil.calculatePossibleCardList(cardList, mockCardWallCtr);
        List<List<ICard>> expectCardList = this.generateExpectMaxCardList(new int[]{402, 403, 401, 406});

        // 3. 取得結果
        String expectResult = JsonUtil.getInstance().writeValueAsStringWithoutException(expectCardList);
        String actualResult = JsonUtil.getInstance().writeValueAsStringWithoutException(possibleCardList);

        // 4. 驗證
        assertEquals(expectResult, actualResult);
    }

}