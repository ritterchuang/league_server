package org.lsj.gs.math.core.card.stackCtr.ebgStackCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EbgTypeUtilTest extends AbstractCardTestUtil {
    private EbgTypeUtil ebgTypeUtil; // 二八槓牌型工具包

    @BeforeEach
    public void setUp() {
        this.ebgTypeUtil = new EbgTypeUtil();
    }

    // 給定寶牌，檢查是否有寶牌，回傳 true
    @Test
    public void test_given_baoType_when_checkBaoType_then_return_true() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{31, 31});

        // 2. 運算結果
        boolean isBaoType = this.ebgTypeUtil.checkBaoType(cardsList);

        // 3. 驗證
        assertTrue(isBaoType);
    }

    // 給定非寶牌，檢查是否有寶牌，回傳 false
    @Test
    public void test_given_nonBaoType_when_checkBaoType_then_return_false() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{31, 35});

        // 2. 運算結果
        boolean isBaoType = this.ebgTypeUtil.checkBaoType(cardsList);

        // 3. 驗證
        assertFalse(isBaoType);
    }

    // 給定白板，檢查是否有白板，回傳 true
    @Test
    public void test_given_baiBen_when_checkBaiBen_then_return_true() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{47, 31});

        // 2. 運算結果
        boolean isBaiBen = this.ebgTypeUtil.checkBaiBan(cardsList);

        // 3. 驗證
        assertTrue(isBaiBen);
    }

    // 給定非白板，檢查是否有白板，回傳 false
    @Test
    public void test_given_nonBaiBen_when_checkBaiBen_then_return_false() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{31, 35});

        // 2. 運算結果
        boolean isBaiBen = this.ebgTypeUtil.checkBaiBan(cardsList);

        // 3. 驗證
        assertFalse(isBaiBen);
    }

    // 給定王牌，計算寶牌結果時，回傳王牌
    @Test
    public void test_given_king_when_calculateBaoType_then_return_king() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{47, 47});

        // 2. 運算結果
        ConstEbgStack.EbgTypeEnumCommon baoType = this.ebgTypeUtil.calculateBaoType(cardsList);

        // 3. 驗證
        assertEquals(ConstEbgStack.EbgTypeEnumCommon.KING, baoType);
    }

    // 給定寶牌5，計算寶牌結果時，回傳寶牌5
    @Test
    public void test_given_bao5_when_calculateBaoType_then_return_bao5() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{35, 35});

        // 2. 運算結果
        ConstEbgStack.EbgTypeEnumCommon baoType = this.ebgTypeUtil.calculateBaoType(cardsList);

        // 3. 驗證
        assertEquals(ConstEbgStack.EbgTypeEnumCommon.BAO_5, baoType);
    }

    // 給定5點半，計算半點牌型結果時，回傳5點半
    @Test
    public void test_given_fiveAndHalf_when_calculateHalfType_then_return_fiveAndHalf() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.MAHJONG, new int[]{47, 35});

        // 2. 運算結果
        ConstEbgStack.EbgTypeEnumCommon halfType = this.ebgTypeUtil.calculateHalfType(cardsList);

        // 3. 驗證
        assertEquals(ConstEbgStack.EbgTypeEnumCommon.FIVE_AND_HALF, halfType);
    }
}