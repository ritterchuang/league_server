package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;
import org.lsj.test.utils.ReflectionUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 抽象百人龍虎邏輯測試
@ExtendWith(MockitoExtension.class)
class AbstractLogicBaiRenLhTest {

    @Mock
    ITableBase mockTable;    // 牌桌
    @Mock
    TableFieldConfig mockTableFieldConfig;  // 房間設定檔
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池計算者
    @Mock
    IGameBetLogResultCtrBaiRen mockGameBetLogResultCtrBaiRen; // 下注記錄計算器
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Mock
    CardWallCtr mockCardWallCtr; // 牌牆計算器


    // 給定 無區域牌號2D陣列，當取區域牌號陣列，回傳 [0, 0]
    @Test
    void test_given_noAreaToCardNumber2dArray_when_getAllAreaCardNumberArray_then_return_0ArrayWithLength2() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        AbstractLogicBaiRenLh abstractLogicBaiRenLh = this.initLogic();
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{});

        // 2. when
        int[] actualResult = abstractLogicBaiRenLh.getAllAreaCardNumberArray();
        int[] expectResult = new int[2];

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定 區域牌號2D陣列，當取區域牌號陣列，回傳 [3011, 4121]
    @Test
    void test_given_areaToCardNumber2dArray_when_getAllAreaCardNumberArray_then_return_correctArrayWithLength2() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        AbstractLogicBaiRenLh abstractLogicBaiRenLh = this.initLogic();
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{{3011}, {4121}});

        // 2. when
        int[] actualResult = abstractLogicBaiRenLh.getAllAreaCardNumberArray();
        int[] expectResult = new int[]{3011, 4121};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定 無區域牌號2D陣列，當取區域牌點陣列，回傳 [0, 0]
    @Test
    void test_given_noAreaToCardPoint2dArray_when_getAllAreaCardPointArray_then_return_0ArrayWithLength2() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        AbstractLogicBaiRenLh abstractLogicBaiRenLh = this.initLogic();
        Mockito.when(mockCardWallCtr.getAllAreaCardPoint2dArray()).thenReturn(new int[][]{});

        // 2. when
        int[] actualResult = abstractLogicBaiRenLh.getAllAreaCardPointArray();
        int[] expectResult = new int[2];

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定 區域牌號2D陣列，當取區域牌點陣列，回傳 [1, 12]
    @Test
    void test_given_areaToCardPoint2dArray_when_getAllAreaCardPointArray_then_return_correctArrayWithLength2() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        AbstractLogicBaiRenLh abstractLogicBaiRenLh = this.initLogic();
        Mockito.when(mockCardWallCtr.getAllAreaCardPoint2dArray()).thenReturn(new int[][]{{1}, {12}});

        // 2. when
        int[] actualResult = abstractLogicBaiRenLh.getAllAreaCardPointArray();
        int[] expectResult = new int[]{1, 12};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 初始化 抽象龍虎邏輯
    private AbstractLogicBaiRenLh initLogic() throws NoSuchFieldException, IllegalAccessException {
        AbstractLogicBaiRenLh abstractLogicBaiRenLh = Mockito.mock(AbstractLogicBaiRenLh.class,
                Mockito.withSettings().useConstructor(
                                mockTable
                                , mockTableFieldConfig
                                , mockGamePlayerHlr
                                , mockPoolCtr
                                , mockGameBetLogResultCtrBaiRen
                                , mockTableUtil
                                , this.generateBetAreaCtrConfig()
                                , this.generateCardWallCtrConfig())
                        .defaultAnswer(Mockito.CALLS_REAL_METHODS));

        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractLogicBaiRenLh.class,  abstractLogicBaiRenLh, "cardWallCtr", mockCardWallCtr);

        return abstractLogicBaiRenLh;
    }

    private BetAreaCtrConfig generateBetAreaCtrConfig() {
        BetAreaCtrGameConfig betAreaCtrGameConfig = this.generateBetAreaCtrGameConfig();

        return new BetAreaCtrConfig(3.0,
                betAreaCtrGameConfig.getChipsOddsList(),
                betAreaCtrGameConfig.getAreasTopLimitOddsMap(),
                betAreaCtrGameConfig.getCantBetTogetherArray(),
                ConstHhdzJava.BetAreaIdHhdzJava.getBetAreaCount());
    }

    private CardWallCtrConfig generateCardWallCtrConfig() {
        CardWallCtrGameConfig cardWallCtrGameConfig = this.generateCardWallCtrGameConfig();

        return new CardWallCtrConfig(12,
                cardWallCtrGameConfig.getCardType(),
                cardWallCtrGameConfig.getInitWallList(),
                cardWallCtrGameConfig.getCardValueWeightMap());
    }

    private BetAreaCtrGameConfig generateBetAreaCtrGameConfig() {
        return new BetAreaCtrGameConfig(
                new ArrayList<>(){{
                    add(1);
                    add(2);
                    add(10);
                    add(20);
                    add(100);
                    add(200);
                }},
                new HashMap<>(){{
                    put(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode(), 1000);
                    put(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode(), 1000);
                    put(ConstHhdzJava.BetAreaIdHhdzJava.CARD_TYPE.getCode(), 1000);
                }},
                new int[][]{{
                        ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode(),
                        ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()
                }}
        );
    }

    private CardWallCtrGameConfig generateCardWallCtrGameConfig() {
        return new CardWallCtrGameConfig(
                ConstMathCard.CardType.POKER,
                new int[][]{
                        {101,1}, {102,1}, {103,1}, {104,1}, {105,1}, {106,1}, {107,1}, {108,1}, {109,1}, {110,1}, {111,1}, {112,1}, {113,1}, // 方塊
                        {201,1}, {202,1}, {203,1}, {204,1}, {205,1}, {206,1}, {207,1}, {208,1}, {209,1}, {210,1}, {211,1}, {212,1}, {213,1}, // 梅花
                        {301,1}, {302,1}, {303,1}, {304,1}, {305,1}, {306,1}, {307,1}, {308,1}, {309,1}, {310,1}, {311,1}, {312,1}, {313,1}, // 紅心
                        {401,1}, {402,1}, {403,1}, {404,1}, {405,1}, {406,1}, {407,1}, {408,1}, {409,1}, {410,1}, {411,1}, {412,1}, {413,1}},// 黑桃
                new HashMap<>(){{
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
                }}
        );
    }
}