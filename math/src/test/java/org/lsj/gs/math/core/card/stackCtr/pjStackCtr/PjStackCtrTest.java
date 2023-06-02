package org.lsj.gs.math.core.card.stackCtr.pjStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardPaiGow;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.test.utils.ReflectionUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 牌九牌型計算者測試
@ExtendWith(MockitoExtension.class)
class PjStackCtrTest {
    private PjStackCtr pjStackCtr; // 牌九牌型計算者
    @Mock PjStackCtrConfig mockConfig; // 設定檔
    @Mock PjTypeUtil mockPjTypeUtil; // 牌久工具包
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池計算者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包


    // 給定玩家手牌，當計算牌型，計算正確
    @Test
    void test_given_chairToCardListMap_when_calculateEbgStack_then_chairToStackCorrect() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        this.pjStackCtr = new PjStackCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        ReflectionUtil.setMockFinalFieldToTarget(this.pjStackCtr, "pjTypeUtil", mockPjTypeUtil);
        Mockito.when(mockPjTypeUtil.calculatePjComboType(Mockito.anyList())).thenReturn(ConstPjStack.PjTypeEnumCommon.SFT).thenReturn(ConstPjStack.PjTypeEnumCommon.SBD);
        Map<Integer, List<ICard>> chairToCardListMap = this.generateChairToCardListMap();

        // 2. when
        Map<Integer, AbstractStack> actualResult = this.pjStackCtr.calculatePjStack(chairToCardListMap);
        Map<Integer, AbstractStack> expectResult = this.generateChairToStackMap();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }


    // 產出 <位置, 牌型> 對應表
    private Map<Integer, List<ICard>> generateChairToCardListMap() {
        Map<Integer, List<ICard>> result = new HashMap<>();

        result.put(1, List.of(
                new CardPaiGow(506, 1), new CardPaiGow(506, 2)
        ));

        result.put(3, List.of(
                new CardPaiGow(202, 1), new CardPaiGow(202, 2)
        ));

        return result;
    }

    // 產出 <位置, 牌型> 對應表
    private Map<Integer, AbstractStack> generateChairToStackMap() {
        Map<Integer, AbstractStack> result = new HashMap<>();

        result.put(1, new PjStack(List.of(new CardPaiGow(506, 1), new CardPaiGow(506, 2)), ConstPjStack.PjTypeEnumCommon.SFT));
        result.put(3, new PjStack(List.of(new CardPaiGow(202, 1), new CardPaiGow(202, 2)), ConstPjStack.PjTypeEnumCommon.SBD));

        return result;
    }
}