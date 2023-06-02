package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBaiRenResultCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr.NiuBaiRenStackCtrConfig;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 抽象百人牛邏輯測試
@ExtendWith(MockitoExtension.class)
class AbstractLogicBaiRenNiuTest {

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

    // 給定 無區域牌號2D陣列，當取區域牌號陣列，回傳2D空陣列
    @Test
    void test_given_noAreaToCardNumber2dArray_when_getAllAreaCardNumberArray_then_return_2dEmptyArray() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        AbstractLogicBaiRenNiu abstractLogicBaiRenNiu = this.initLogic();
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{});

        // 2. when
        int[][] actualResult = abstractLogicBaiRenNiu.getAllAreaCardNumber2dArray();
        int[][] expectResult = new int[][]{};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定 區域牌號2D陣列，當取區域牌號陣列，回傳正確2D陣列
    @Test
    void test_given_areaToCardNumber2dArray_when_getAllAreaCardNumberArray_then_return_correct2dArray() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        AbstractLogicBaiRenNiu abstractLogicBaiRenNiu = this.initLogic();
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{{1111, 2111, 3111, 4111, 4121}, {1101, 2101, 3101, 4101, 4131}});

        // 2. when
        int[][] actualResult = abstractLogicBaiRenNiu.getAllAreaCardNumber2dArray();
        int[][] expectResult = new int[][]{{1111, 2111, 3111, 4111, 4121}, {1101, 2101, 3101, 4101, 4131}};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 初始化 抽象百人牛邏輯
    private AbstractLogicBaiRenNiu initLogic() throws NoSuchFieldException, IllegalAccessException {
        AbstractLogicBaiRenNiu abstractLogicBaiRenNiu = Mockito.mock(AbstractLogicBaiRenNiu.class,
                Mockito.withSettings().useConstructor(
                                mockTable
                                , mockTableFieldConfig
                                , mockGamePlayerHlr
                                , mockPoolCtr
                                , mockGameBetLogResultCtrBaiRen
                                , mockTableUtil
                                , this.generateBetAreaCtrConfig()
                                , this.generateCardWallCtrConfig()
                                , this.generateNiuBaiRenStackCtrConfig()
                                , this.generateNiuBaiRenResultCtrConfig())
                        .defaultAnswer(Mockito.CALLS_REAL_METHODS));

        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractLogicBaiRenNiu.class,  abstractLogicBaiRenNiu, "cardWallCtr", mockCardWallCtr);

        return abstractLogicBaiRenNiu;
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

    private NiuBaiRenStackCtrConfig generateNiuBaiRenStackCtrConfig() {
        return new NiuBaiRenStackCtrConfig(this.generateNiuTypeToRateMap());
    }

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

    private NiuBaiRenResultCtrConfig generateNiuBaiRenResultCtrConfig() {
        return new NiuBaiRenResultCtrConfig(
                4, 2, 5.0, this.generateNiuTypeToRateMap()
        );
    }
}