package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr;

import org.lsj.gs.math.core.card.cardWallCtr.CardPoker;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.*;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 百人牛牌型計算者測試
@ExtendWith(MockitoExtension.class)
class NiuBaiRenStackCtrTest {
    private NiuBaiRenStackCtr niuBaiRenStackCtr; // 百人牛牌型計算者
    @Mock NiuBaiRenStackCtrConfig mockConfig; // 設定檔
    @Mock
    CardTypeUtilNiu mockCardTypeUtilNiu; // 牛牌型計算工具包
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池計算者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定玩家手牌，當計算牌型，計算正確
    @Test
    void test_given_chairToCardListMap_when_calculateStackMap_then_correctChairToStack() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        this.setMockTool_1();
        Map<Integer, List<ICard>> chairToCardListMap = this.generateChairToCardListMap();

        // 2. when
        Map<Integer, AbstractStack> actualResult = this.niuBaiRenStackCtr.calculateStackMap(chairToCardListMap);
        Map<Integer, AbstractStack> expectResult = this.generateAreaIdToStackMap();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定選定結果，當要取得區域提牌，回傳正確區域提牌
    @Test
    void test_given_selectResult_when_getAreaLiftCardList_then_correctChairToLiftCards() {
        // 1. given
        this.niuBaiRenStackCtr = new NiuBaiRenStackCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        this.niuBaiRenStackCtr.setStackMap(this.generateAreaIdToStackMap());

        // 2. when
        Map<Integer, List<ICard>> actualResult = this.niuBaiRenStackCtr.getAreaLiftCardList();
        Map<Integer, List<ICard>> expectResult = this.generateAreaIdToLiftCardListMap();

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定選定結果，當要取得區域牌型，回傳正確區域提牌
    @Test
    void test_given_selectResult_when_getAreaNiuTypeCommonArray_then_correctAreaNiuTypeCommonArray(){
        // 1. given
        this.niuBaiRenStackCtr = new NiuBaiRenStackCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        this.niuBaiRenStackCtr.setStackMap(this.generateAreaIdToStackMap());

        // 2. when
        int[] actualResult = this.niuBaiRenStackCtr.getAreaNiuTypeCommonArray();
        int[] expectResult = new int[]{0, 0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 取得 區域、提牌 對應表
    private Map<Integer, List<ICard>> generateAreaIdToLiftCardListMap() {
        return new HashMap<>(){
            {
                put(0, new ArrayList<>());
                put(1, new ArrayList<>());
            }
        };
    }

    // mockTool 1
    private void setMockTool_1() throws NoSuchFieldException, IllegalAccessException {
        this.niuBaiRenStackCtr = new NiuBaiRenStackCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractNiuStackCtr.class, this.niuBaiRenStackCtr, "cardTypeUtilNiu", mockCardTypeUtilNiu);
        Mockito.when(mockCardTypeUtilNiu.calculatePreNiuResult(Mockito.anyList())).thenReturn(this.generatePreNiuResultList().get(0)).thenReturn(this.generatePreNiuResultList().get(1));
        Mockito.when(mockConfig.getNiuTypeToRateConfig()).thenReturn(this.generateNiuTypeToRateMap());
        Mockito.when(mockCardTypeUtilNiu.checkSmallNiu(Mockito.anyList())).thenReturn(false);
        Mockito.when(mockCardTypeUtilNiu.checkBomb(Mockito.anyList())).thenReturn(false);
        Mockito.when(mockCardTypeUtilNiu.checkFiveFlower(Mockito.anyList())).thenReturn(false);
        Mockito.when(mockCardTypeUtilNiu.checkFourFlower(Mockito.anyList())).thenReturn(false);
    }

    // 產出預期結果 1
    private Map<Integer, AbstractStack> generateAreaIdToStackMap() {
        Map<Integer, List<ICard>> chairToCardListMap = this.generateChairToCardListMap();

        return chairToCardListMap.keySet().stream().collect(Collectors.toMap(
                chairIndex -> chairIndex, chairIndex -> new NiuStackCommon(chairToCardListMap.get(chairIndex), new ArrayList<>(), ConstNiu.NiuTypeEnumCommon.NIU_0, false)
        ));
    }

    // 產出 預發牌牛結果
    private List<PreNiuResult> generatePreNiuResultList() {
        Map<Integer, List<ICard>> chairToCardListMap = this.generateChairToCardListMap();
        List<PreNiuResult> result = new ArrayList<>();
        for (Map.Entry<Integer, List<ICard>> entry: chairToCardListMap.entrySet()) {
            PreNiuResult preNiuResult = new PreNiuResult();
            preNiuResult.setNiu(false);
            preNiuResult.setNiuCardList(entry.getValue());
            preNiuResult.setLiftCardList(new ArrayList<>());

            result.add(preNiuResult);
        }

        return result;
    }

    // 產出 押注區域, 牌列表對應表
    private Map<Integer, List<ICard>> generateChairToCardListMap() {
        Map<Integer, Integer> cardValueWeightMap = this.generateCardValueWeightMap();

        Map<Integer, List<ICard>> result = new HashMap<>();

        result.put(0, List.of(
                new CardPoker(103, 1, cardValueWeightMap)
                , new CardPoker(206, 1, cardValueWeightMap)
                , new CardPoker(302, 1, cardValueWeightMap)
                , new CardPoker(303, 1, cardValueWeightMap)
                , new CardPoker(310, 1, cardValueWeightMap)));
        result.put(1, List.of(
                new CardPoker(104, 1, cardValueWeightMap)
                , new CardPoker(204, 1, cardValueWeightMap)
                , new CardPoker(304, 1, cardValueWeightMap)
                , new CardPoker(413, 1, cardValueWeightMap)
                , new CardPoker(313, 1, cardValueWeightMap)));

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

    // 產出牛型、倍數對應表
    private Map<ConstNiu.NiuTypeEnumCommon, Integer> generateNiuTypeToRateMap() {
        return new HashMap<>(){{
            put(ConstNiu.NiuTypeEnumCommon.INVALID, 0);
            put(ConstNiu.NiuTypeEnumCommon.NIU_0, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_1, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_2, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_3, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_4, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_5, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_6, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_7, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_8, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_9, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_NIU, 3);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_4, 4);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_5, 4);
            put(ConstNiu.NiuTypeEnumCommon.BOMB, 4);
            put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, 4);
        }};
    }
}