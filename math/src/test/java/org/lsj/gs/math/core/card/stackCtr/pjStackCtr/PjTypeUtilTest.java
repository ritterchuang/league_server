package org.lsj.gs.math.core.card.stackCtr.pjStackCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PjTypeUtilTest extends AbstractCardTestUtil {
    private PjTypeUtil pjTypeUtil; // 牌九牌型工具包

    @BeforeEach
    public void setUp() {
        this.pjTypeUtil = new PjTypeUtil();
    }

    // 給地高九牌，計算對牌正確
    @Test
    void test_given_DGJ_when_calculatePjComboType_then_correct() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.PAIGOW, new int[]{106, 101});

        // 2. 運算結果
        ConstPjStack.PjTypeEnumCommon actualResult = this.pjTypeUtil.calculatePjComboType(cardsList);

        // 3. 驗證
        assertEquals(ConstPjStack.PjTypeEnumCommon.DGJ, actualResult);
    }

    // 給單牌5點，計算單牌正確
    @Test
    void test_given_singleCardTypePoint5_when_calculatePjSingleType_then_correct() {
        // 1. 準備資料
        List<ICard> cardsList = super.generateCardList(ConstMathCard.CardType.PAIGOW, new int[]{606, 102});

        // 2. 運算結果
        ConstPjStack.PjTypeEnumCommon actualResult = this.pjTypeUtil.calculatePjSingleType(cardsList);

        // 3. 驗證
        assertEquals(ConstPjStack.PjTypeEnumCommon.PT_5, actualResult);
    }
}