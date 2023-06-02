package org.lsj.gs.math.core.card.stackCtr.sgStackCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SgTypeUtilTest extends AbstractCardTestUtil {
    private SgTypeUtil sgTypeUtil; // 三公牌型工具包

    @BeforeEach
    public void setUp() {
        this.sgTypeUtil = new SgTypeUtil();
    }

    // 給至尊(3,3,3)，判斷至尊，回傳 true
    @Test
    public void test_given_zhiZun_when_isZhiZun_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{103, 203, 303});

        // 2. 運算結果
        boolean isZhiZun = this.sgTypeUtil.isZhiZun(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isZhiZun);
    }

    // 給大三公(1,1,1)，判斷大三公，回傳 true
    @Test
    public void test_given_daSanGong_when_isDaSanGong_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 201, 301});

        // 2. 運算結果
        boolean isDaSanGong = this.sgTypeUtil.isDaSanGong(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isDaSanGong);
    }

    // 給三公(1,1,1)，判斷三公，回傳 true
    @Test
    public void test_given_daSanGong_when_isSanGong_then_returnTrue() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{111, 211, 312});

        // 2. 運算結果
        boolean isSanGong = this.sgTypeUtil.isSanGong(cardsList);

        // 3. 驗證
        Assertions.assertTrue(isSanGong);
    }

    // 給非大三公(1,1,3)，判斷大三公，回傳 false
    @Test
    public void test_given_daSanGong_when_isDaSanGong_then_returnFalse() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{101, 201, 103});

        // 2. 運算結果
        boolean isDaSanGong = this.sgTypeUtil.isDaSanGong(cardsList);

        // 3. 驗證
        Assertions.assertFalse(isDaSanGong);
    }
}