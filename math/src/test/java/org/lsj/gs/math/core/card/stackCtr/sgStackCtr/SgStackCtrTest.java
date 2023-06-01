package org.lsj.gs.math.core.card.stackCtr.sgStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardPoker;
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

// 三公槓牌型計算者測試
@ExtendWith(MockitoExtension.class)
class SgStackCtrTest {
    private SgStackCtr sgStackCtr; // 三公牌型計算者
    @Mock SgStackCtrConfig mockConfig; // 設定檔
    @Mock SgTypeUtil mockSgTypeUtil; // 三公牌型工具包
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池計算者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定玩家手牌，當計算牌型，計算正確
    @Test
    void test_given_chairToCardListMap_when_calculateSgStack_then_correctChairToStack() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        this.sgStackCtr = new SgStackCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        ReflectionUtil.setMockFinalFieldToTarget(this.sgStackCtr, "sgTypeUtil", mockSgTypeUtil);
        Mockito.when(mockSgTypeUtil.isZhiZun(Mockito.anyList())).thenReturn(true).thenReturn(false);
        Mockito.when(mockSgTypeUtil.isDaSanGong(Mockito.anyList())).thenReturn(true);
        Map<Integer, List<ICard>> chairToCardListMap = this.generateChairToCardListMap();

        // 2. when
        Map<Integer, AbstractStack> actualResult = this.sgStackCtr.calculateSgStack(chairToCardListMap);
        Map<Integer, AbstractStack> expectResult = this.generateChairToStackMap();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定玩家手牌，當取牌，取牌正確
    @Test
    void test_given_chairToCardListMap_when_getAllPlayerSelectCardList_then_correctChairToCardListMap() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        this.sgStackCtr = new SgStackCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        ReflectionUtil.setMockFinalFieldToTarget(this.sgStackCtr, "sgTypeUtil", mockSgTypeUtil);
        ReflectionUtil.setMockFinalFieldToTarget(this.sgStackCtr, "selectResult", this.generateChairToStackMap());

        // 2. when
        Map<Integer, List<ICard>> actualResult = this.sgStackCtr.getAllPlayerSelectCardList();
        Map<Integer, List<ICard>> expectResult = this.generateChairToCardListMap();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 產出 押注區域, 牌列表對應表
    private Map<Integer, List<ICard>> generateChairToCardListMap() {
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();

        Map<Integer, List<ICard>> result = new HashMap<>();

        result.put(0, List.of(new CardPoker(103, 1, cardValueWeightMap), new CardPoker(203, 1, cardValueWeightMap), new CardPoker(303, 1, cardValueWeightMap)));
        result.put(1, List.of(new CardPoker(104, 1, cardValueWeightMap), new CardPoker(204, 1, cardValueWeightMap), new CardPoker(304, 1, cardValueWeightMap)));

        return result;
    }

    // 產出 押注區域, 牌型 對應表
    private Map<Integer, AbstractStack> generateChairToStackMap() {
        Map<Integer, AbstractStack> result = new HashMap<>();

        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();

        result.put(0, new SgStack(List.of(new CardPoker(103, 1, cardValueWeightMap), new CardPoker(203, 1, cardValueWeightMap), new CardPoker(303, 1, cardValueWeightMap)), ConstSgStack.SgTypeEnumCommon.ZHIZUN));
        result.put(1, new SgStack(List.of(new CardPoker(104, 1, cardValueWeightMap), new CardPoker(204, 1, cardValueWeightMap), new CardPoker(304, 1, cardValueWeightMap)), ConstSgStack.SgTypeEnumCommon.DASANGONG));

        return result;
    }

    // 創建牌權重
    private Map<Integer, Integer> generateCardValueWeightMap() {
        return new HashMap<>(){{
            put(1,1);
            put(2,2);
            put(3,3);
            put(4,4);
            put(5,5);
            put(6,6);
            put(7,7);
            put(8,8);
            put(9,9);
            put(10,10);
            put(11,11);
            put(12,12);
            put(13,13);
        }};
    }
}