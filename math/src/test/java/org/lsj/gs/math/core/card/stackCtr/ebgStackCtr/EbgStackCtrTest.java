package org.lsj.gs.math.core.card.stackCtr.ebgStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardMahjong;
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

// 二八槓牌型計算者測試
@ExtendWith(MockitoExtension.class)
class EbgStackCtrTest {
    private EbgStackCtr ebgStackCtr; // 二八槓牌型計算者
    @Mock EbgTypeUtil mockEbgTypeUtil; // 二八槓牌型計算工具包
    @Mock EbgStackCtrConfig mockConfig; // 設定
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
        this.ebgStackCtr = new EbgStackCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        ReflectionUtil.setMockFinalFieldToTarget(this.ebgStackCtr, "ebgTypeUtil", mockEbgTypeUtil);
        Mockito.when(mockEbgTypeUtil.checkBaoType(Mockito.anyList())).thenReturn(true).thenReturn(true);
        Mockito.when(mockEbgTypeUtil.calculateBaoType(Mockito.anyList())).thenReturn(ConstEbgStack.EbgTypeEnumCommon.BAO_1).thenReturn(ConstEbgStack.EbgTypeEnumCommon.BAO_2);
        Map<Integer, List<ICard>> chairToCardListMap = this.generateChairToCardListMap();

        // 2. when
        Map<Integer, AbstractStack> actualResult = this.ebgStackCtr.calculateEbgStack(chairToCardListMap);
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
                new CardMahjong(31, 1), new CardMahjong(31, 2)
        ));

        result.put(3, List.of(
                new CardMahjong(32, 1), new CardMahjong(32, 2)
        ));

        return result;
    }

    // 產出 <位置, 牌型> 對應表
    private Map<Integer, AbstractStack> generateChairToStackMap() {
        Map<Integer, AbstractStack> result = new HashMap<>();

        result.put(1, new EbgStack(List.of(new CardMahjong(31, 1), new CardMahjong(31, 2)), ConstEbgStack.EbgTypeEnumCommon.BAO_1));
        result.put(3, new EbgStack(List.of(new CardMahjong(32, 1), new CardMahjong(32, 2)), ConstEbgStack.EbgTypeEnumCommon.BAO_2));

        return result;
    }
}