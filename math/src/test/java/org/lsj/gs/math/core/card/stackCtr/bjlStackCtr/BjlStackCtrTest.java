package org.lsj.gs.math.core.card.stackCtr.bjlStackCtr;

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

// 百家牌型計算器測試
class BjlStackCtrTest extends AbstractCardTestUtil {
    private BjlStackCtr bjlStackCtr; // 百家牌型計算器

    @BeforeEach
    void setUp() {
        this.bjlStackCtr = new BjlStackCtr();
    }

    // 給定無牌型對應表，當計算閒家點數，回傳0點
    @Test
    void test_given_noAreaStackMap_playArea_when_calculateAreaPint_then_return0() {
        // 1. given
        ConstMathBjl.DealAreaEnum dealAreaEnum = ConstMathBjl.DealAreaEnum.PLAY;

        // 2. when
        int actualResult = this.bjlStackCtr.calculateAreaPint(dealAreaEnum);

        // 3. then
        assertEquals(0, actualResult);
    }

    // 給定牌型對應表，，當計算非法區域點數，回傳0點
    @Test
    void test_given_noAreaStackMap_invalidArea_when_calculateAreaPint_then_return0() {
        // 1. given
        ConstMathBjl.DealAreaEnum dealAreaEnum = ConstMathBjl.DealAreaEnum.INVALID;
        this.bjlStackCtr.setStackMap(this.generateDealAreaToStackMap());

        // 2. when
        int actualResult = this.bjlStackCtr.calculateAreaPint(dealAreaEnum);

        // 3. then
        assertEquals(0, actualResult);
    }

    // 給定空牌型對應表，，當計算莊家累積點數陣列，回傳空陣列
    @Test
    void test_given_noAreaStackMap_bankArea_when_calculateAccumulateAreaPointArray_then_return_emptyArray() {
        // 1. given
        ConstMathBjl.DealAreaEnum dealAreaEnum = ConstMathBjl.DealAreaEnum.BANK;

        // 2. when
        int[] actualResult = this.bjlStackCtr.calculateAccumulateAreaPointArray(dealAreaEnum);
        int[] expectResult = new int[]{};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定牌型對應表，，當計算非法區域累積點數陣列，回傳空陣列
    @Test
    void test_given_areaStackMap_invalidArea_when_calculateAccumulateAreaPointArray_then_return_emptyArray() {
        // 1. given
        ConstMathBjl.DealAreaEnum dealAreaEnum = ConstMathBjl.DealAreaEnum.INVALID;
        this.bjlStackCtr.setStackMap(this.generateDealAreaToStackMap());

        // 2. when
        int[] actualResult = this.bjlStackCtr.calculateAccumulateAreaPointArray(dealAreaEnum);
        int[] expectResult = new int[]{};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定莊家三張牌，，當計算莊家區域累積點數陣列，回傳正確陣列
    @Test
    void test_given_bankAreaWith3Cards_when_calculateAccumulateAreaPointArray_then_return_correctArray() {
        // 1. given
        ConstMathBjl.DealAreaEnum dealAreaEnum = ConstMathBjl.DealAreaEnum.BANK;
        this.bjlStackCtr.setStackMap(this.generateDealAreaToStackMap());

        // 2. when
        int[] actualResult = this.bjlStackCtr.calculateAccumulateAreaPointArray(dealAreaEnum);
        int[] expectResult = new int[]{1, 3, 8};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定莊家二張牌，，當計算閒家區域累積點數陣列，回傳正確陣列
    @Test
    void test_given_playAreaWith2Cards_when_calculateAccumulateAreaPointArray_then_return_correctArray() {
        // 1. given
        ConstMathBjl.DealAreaEnum dealAreaEnum = ConstMathBjl.DealAreaEnum.PLAY;
        this.bjlStackCtr.setStackMap(this.generateDealAreaToStackMap());

        // 2. when
        int[] actualResult = this.bjlStackCtr.calculateAccumulateAreaPointArray(dealAreaEnum);
        int[] expectResult = new int[]{3, 7};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    private Map<Integer, BjlStack> generateDealAreaToStackMap() {
        List<ICard> bankCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{301, 302, 305});
        List<ICard> playCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{303, 304});

        return new HashMap<>(){
            {
                put(ConstMathBjl.DealAreaEnum.PLAY.getCode(), new BjlStack(playCardList));
                put(ConstMathBjl.DealAreaEnum.BANK.getCode(), new BjlStack(bankCardList));
            }
        };
    }
}