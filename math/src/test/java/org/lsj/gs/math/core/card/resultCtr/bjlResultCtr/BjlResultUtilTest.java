package org.lsj.gs.math.core.card.resultCtr.bjlResultCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BjlResultUtilTest extends AbstractCardTestUtil {
    private BjlResultUtil bjlResultUtil; // 百家樂結果工具包

    //* 初始化*//

    @BeforeEach
    void setUp() {
        this.bjlResultUtil = new BjlResultUtil();
    }

    //* 計算勝利押注區域 *//

    // 給定庄、閒點數相同，當計算和局結果，結果包含和局
    @Test
    void test_given_bankPlaySamePoint_when_calculateWinTieResult_then_containTieBetArea() {
        // 1. given
        int bankPoint = 8;
        int playPoint = 8;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinTieResult(bankPoint, playPoint);
        List<Integer> expectResult = List.of(8);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄、閒點數不同，當計算和局結果，結果不包含和局
    @Test
    void test_given_bankPlayDifferentPoint_when_calculateWinTieResult_then_emptyList() {
        // 1. given
        int bankPoint = 8;
        int playPoint = 7;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinTieResult(bankPoint, playPoint);
        List<Integer> expectResult = List.of();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 7、閒 6 不同，庄閒押注分數相同，當計算莊家結果，結果僅 庄
    @Test
    void test_given_bank7Play6_sameAreaBet_when_calculateWinBankResult_then_onlyBank() {
        // 1. given
        int bankPoint = 7;
        int playPoint = 6;
        int bankAreaChip = 100;
        int playAreaChip = 100;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinBankResult(bankPoint, playPoint, bankAreaChip, playAreaChip);
        List<Integer> expectResult = List.of(5);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 7、閒 6 不同，庄押注 100，閒押注 50 相同，當計算莊家結果，結果含 庄、上庄輸
    @Test
    void test_given_bank7Play6_bankBet100PlayBet50_when_calculateWinBankResult_then_contain_bankAndUpBankLoss() {
        // 1. given
        int bankPoint = 7;
        int playPoint = 6;
        int bankAreaChip = 100;
        int playAreaChip = 50;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinBankResult(bankPoint, playPoint, bankAreaChip, playAreaChip);
        List<Integer> expectResult = List.of(5, 0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 7、閒 6 不同，庄押注 50，閒押注 100 相同，當計算莊家結果，結果含 庄、上庄贏
    @Test
    void test_given_bank7Play6_bankBet100PlayBet50_when_calculateWinBankResult_then_contain_bankAndUpBankWin() {
        // 1. given
        int bankPoint = 7;
        int playPoint = 6;
        int bankAreaChip = 50;
        int playAreaChip = 100;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinBankResult(bankPoint, playPoint, bankAreaChip, playAreaChip);
        List<Integer> expectResult = List.of(5, 4);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 6、閒 7 不同，庄閒押注分數相同，當計算閒家結果，結果僅 閒
    @Test
    void test_given_bank6Play7_sameAreaBet_when_calculateWinPlayResult_then_onlyBank() {
        // 1. given
        int bankPoint = 6;
        int playPoint = 7;
        int bankAreaChip = 100;
        int playAreaChip = 100;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinPlayResult(bankPoint, playPoint, bankAreaChip, playAreaChip);
        List<Integer> expectResult = List.of(1);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 6、閒 7 不同，庄押注 100，閒押注 50 相同，當計算閒家結果，結果含 閒、上庄贏
    @Test
    void test_given_bank6Play7_bankBet100PlayBet50_when_calculateWinPlayResult_then_contain_playAndUpBankWin() {
        // 1. given
        int bankPoint = 6;
        int playPoint = 7;
        int bankAreaChip = 100;
        int playAreaChip = 50;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinPlayResult(bankPoint, playPoint, bankAreaChip, playAreaChip);
        List<Integer> expectResult = List.of(1, 4);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 6、閒 7 不同，庄押注 50，閒押注 100 相同，當計算閒家結果，結果含 閒、上庄輸
    @Test
    void test_given_bank6Play7_bankBet50PlayBet100_when_calculateWinPlayResult_then_contain_playAndUpBankLoss() {
        // 1. given
        int bankPoint = 6;
        int playPoint = 7;
        int bankAreaChip = 50;
        int playAreaChip = 100;

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinPlayResult(bankPoint, playPoint, bankAreaChip, playAreaChip);
        List<Integer> expectResult = List.of(1, 0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 3 張牌、閒 2 張牌，當計算大小結果，結果含 大
    @Test
    void test_given_bank3Cards_play2Cards_when_calculateWinBigSmallResult_then_contain_big() {
        // 1. given
        int[] bankCardArray = new int[]{101, 201, 301};
        int[] playCardArray = new int[]{101, 206};

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinBigSmallResult(this.generateDealAreaIdToCardListMap(bankCardArray, playCardArray));
        List<Integer> expectResult = List.of(7);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄 2 張牌、閒 2 張牌，當計算大小結果，結果含 小
    @Test
    void test_given_bank2Cards_play2Cards_when_calculateWinBigSmallResult_then_contain_small() {
        // 1. given
        int[] bankCardArray = new int[]{101, 205};
        int[] playCardArray = new int[]{101, 206};

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinBigSmallResult(this.generateDealAreaIdToCardListMap(bankCardArray, playCardArray));
        List<Integer> expectResult = List.of(3);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定庄對 3 張牌、閒對 2 張牌，當計算大小結果，結果含 庄對、閒對
    @Test
    void test_given_bank3CardsPair_play2CardsPai_when_calculateWinPairResult_then_contain_bankPairAndPlayPair() {
        // 1. given
        int[] bankCardArray = new int[]{101, 201, 304};
        int[] playCardArray = new int[]{103, 203};

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateWinPairResult(this.generateDealAreaIdToCardListMap(bankCardArray, playCardArray));
        List<Integer> expectResult = List.of(6, 2);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }


    //* 計算返還押注區域 *//

    // 給定和局，當計算返還下注區域，含 庄、閒、上庄贏、上庄輸
    @Test
    void test_given_tie_when_calculateReturnBetAreaIdList_then_contain_bank_play_upBankWin_upBankLoss() {
        // 1. given
        List<Integer> winBetAreaIdList = List.of(8, 6, 2, 3);

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateReturnBetAreaIdList(winBetAreaIdList);
        List<Integer> expectResult = List.of(5, 1, 4, 0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定非和局 且 庄閒 下注相等，當計算返還下注區域，含 上庄贏、上庄輸
    @Test
    void test_given_nonTieAndSameAreaBetChips_when_calculateReturnBetAreaIdList_then_contain_upBankWin_upBankLoss() {
        // 1. given
        List<Integer> winBetAreaIdList = List.of(5, 6, 2, 3);

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateReturnBetAreaIdList(winBetAreaIdList);
        List<Integer> expectResult = List.of(4, 0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定非和局 且 庄閒 下注不相等，當計算返還下注區域，回傳空陣列
    @Test
    void test_given_nonTieAndDifferentAreaBetChips_when_calculateReturnBetAreaIdList_then_return_emptyList() {
        // 1. given
        List<Integer> winBetAreaIdList = List.of(5, 6, 2, 3, 0);

        // 2. when
        List<Integer> actualResult = this.bjlResultUtil.calculateReturnBetAreaIdList(winBetAreaIdList);
        List<Integer> expectResult = List.of();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    private Map<Integer, List<ICard>> generateDealAreaIdToCardListMap(int[] bankCardArray, int[] playCardArray) {
        List<ICard> bankCardList = super.generateCardList(ConstMathCard.CardType.POKER, bankCardArray);
        List<ICard> playCardList = super.generateCardList(ConstMathCard.CardType.POKER, playCardArray);

        return new HashMap<>(){
            {
                put(ConstMathBjl.DealAreaEnum.PLAY.getCode(), playCardList);
                put(ConstMathBjl.DealAreaEnum.BANK.getCode(), bankCardList);
            }
        };
    }
}