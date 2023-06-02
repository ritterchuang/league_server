package org.lsj.gs.math.core.card.stackCtr.jhStackCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardTypeUtilJhTest extends AbstractCardTestUtil {
    private CardTypeUtilJh cardTypeUtil; // 牌型工具包

    @BeforeEach
    void setUp() {
        this.cardTypeUtil = new CardTypeUtilJh();
    }

    // 給定豹子(1,1,1)，執行判斷豹子，則符合
    @Test
    void given_baoZi_when_checkBaoZi_then_true() {
        // 1. given
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 201, 301});

        // 2. when
        boolean isMatch = this.cardTypeUtil.checkBaoZi(cardsList);

        // 3. then
        Assertions.assertTrue(isMatch);
    }

    // 給定同花順(1,2,3)，執行判斷同花順，則符合
    @Test
    void given_tongHuaShun_when_checkTongHuaShun_then_true() {
        // 1. given
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 102, 103});

        // 2. when
        boolean isMatch = this.cardTypeUtil.checkTongHuaShun(cardsList);

        // 3. then
        Assertions.assertTrue(isMatch);
    }

    // 給定金花(1,3,5)，執行判斷金花，則符合
    @Test
    void given_tongHua_when_checkTongHua_then_true() {
        // 1. given
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 103, 105});

        // 2. when
        boolean isMatch = this.cardTypeUtil.checkTongHua(cardsList);

        // 3. then
        Assertions.assertTrue(isMatch);
    }

    // 給定順子(1,2,3)，執行判斷順子，則符合
    @Test
    void given_shunZi_when_checkShunZi_then_true() {
        // 1. given
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 202, 403});

        // 2. when
        boolean isMatch = this.cardTypeUtil.checkShunZi(cardsList);

        // 3. then
        Assertions.assertTrue(isMatch);
    }

    // 給定對子(1,2,2)，執行判斷對子，則符合
    @Test
    void given_duiZi_when_checkDuiZi_then_true() {
        // 1. given
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 202, 402});

        // 2. when
        boolean isMatch = this.cardTypeUtil.checkDuiZi(cardsList);

        // 3. then
        Assertions.assertTrue(isMatch);
    }
}
