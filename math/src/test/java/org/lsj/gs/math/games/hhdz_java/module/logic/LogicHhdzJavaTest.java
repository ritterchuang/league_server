package org.lsj.gs.math.games.hhdz_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigHhdzJava;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.moduleConfigMgr.ModuleConfigMgrBaiRen;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.moduleConfigMgr.ModuleConfigMgrCard;
import org.lsj.gs.math.core.card.resultCtr.jhResultCtr.JhBaiRenResultCtrConfig;
import org.lsj.gs.math.core.card.resultCtr.jhResultCtr.JhBaiRenResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.ConstJhStack;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRenJh;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.AbstractTable;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.tableConfigMgr.TableConfigMgr;
import org.lsj.gs.math.games.hhdz_java.TableHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;
import org.lsj.test.utils.ReflectionUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 紅黑邏輯測試
@ExtendWith(MockitoExtension.class)
class LogicHhdzJavaTest {
    private LogicHhdzJava logic;    // 紅黑邏輯

    @Mock
    TableHhdzJava mockTable;    // 牌桌
    @Mock
    TableFieldConfig mockTableFieldConfig;  // 房間設定檔
    @Mock
    TableGameConfigHhdzJava mockTableGameConfig; // 遊戲設定檔
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池計算者
    @Mock
    IGameBetLogResultCtrBaiRen mockGameBetLogResultCtrBaiRen; // 下注記錄計算器
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Mock
    TableConfigMgr mockTableConfigMgr; // 遊戲桌設定管理器
    @Mock
    ModuleConfigMgrBaiRen mockModuleConfigMgrBaiRen; // 百人模組設定管理器
    @Mock
    ModuleConfigMgrCard mockModuleConfigMgrCard; // 棋牌模組設定管理器
    @Mock
    CardWallCtr mockCardWallCtr; // 牌牆計算器

    // 邏輯設定
    @BeforeEach
    void setLogic() throws NoSuchFieldException, IllegalAccessException {
        Mockito.when(mockTable.getTableConfigMgr()).thenReturn(mockTableConfigMgr);

        Mockito.when(mockTableConfigMgr.getModuleConfigMgrBaiRen()).thenReturn(mockModuleConfigMgrBaiRen);
        Mockito.when(mockTableConfigMgr.getModuleConfigMgrCard()).thenReturn(mockModuleConfigMgrCard);

        Mockito.when(mockTableGameConfig.getBetAreaCtrGameConfig()).thenReturn(this.generateBetAreaCtrGameConfig());
        Mockito.when(mockTableGameConfig.getCardWallCtrGameConfig()).thenReturn(this.generateCardWallCtrGameConfig());
        Mockito.when(mockTableGameConfig.getResultCtrGameConfig()).thenReturn(this.generateJhBaiRenResultCtrGameConfig());

        Mockito.when(mockTableFieldConfig.getFeeType()).thenReturn((short) 2);
        Mockito.when(mockTableFieldConfig.getGameRate()).thenReturn(5.0);

        Mockito.when(mockModuleConfigMgrBaiRen.createBetAreaCtrConfig(Mockito.anyList(), Mockito.anyMap(), Mockito.any(), Mockito.anyInt())).thenReturn(this.generateBetAreaCtrConfig());
        Mockito.when(mockModuleConfigMgrBaiRen.createHhdzBaiRenResultCtrConfig(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyDouble(), Mockito.anyMap())).thenReturn(this.generateJhBaiRenResultCtrConfig());
        Mockito.when(mockModuleConfigMgrCard.createCardWallCtrConfig(Mockito.any(), Mockito.any(), Mockito.anyMap())).thenReturn(this.generateCardWallCtrConfig());

        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractTable.class, mockTable, "tableConfigMgr", mockTableConfigMgr);
        ReflectionUtil.setMockFinalFieldToTarget(mockTableConfigMgr, "moduleConfigMgrBaiRen", mockModuleConfigMgrBaiRen);
        ReflectionUtil.setMockFinalFieldToTarget(mockTableConfigMgr, "moduleConfigMgrCard", mockModuleConfigMgrCard);

        this.logic = new LogicHhdzJava(mockTable, mockTableFieldConfig, mockTableGameConfig,mockGamePlayerHlr, mockPoolCtr, mockGameBetLogResultCtrBaiRen, mockTableUtil);
    }

    // 給定無區域、牌對應表，當要取得紅區牌陣列，回傳空陣列
    @Test
    void test_given_areaToCardListMapSize0_when_getRedCardArray_then_return_arrayWithSize0() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractLogicBaiRenJh.class, this.logic, "cardWallCtr", mockCardWallCtr);
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{});

        // 2. when
        int[] actualResult = this.logic.getRedCardArray();
        int[] expectResult = new int[]{};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定 區域、牌對應表，當要取得紅區牌陣列，回傳紅牌
    @Test
    void test_given_areaToCardListMapSizeWithValue_when_getRedCardArray_then_return_arrayWithValue() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractLogicBaiRenJh.class, this.logic, "cardWallCtr", mockCardWallCtr);
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{{1101}, {2101}});

        // 2. when
        int[] actualResult = this.logic.getRedCardArray();
        int[] expectResult = new int[]{2101};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定無區域、牌對應表，當要取得黑區牌陣列，回傳空陣列
    @Test
    void test_given_areaToCardListMapSize0_when_getBlackCardArray_then_return_arrayWithSize0() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractLogicBaiRenJh.class, this.logic, "cardWallCtr", mockCardWallCtr);
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{});

        // 2. when
        int[] actualResult = this.logic.getBlackCardArray();
        int[] expectResult = new int[]{};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定 區域、牌對應表，當要取得黑區牌陣列，回傳黑牌
    @Test
    void test_given_areaToCardListMapSizeWithValue_when_getBlackCardArray_then_return_arrayWithValue() throws NoSuchFieldException, IllegalAccessException {
        // 1. given
        ReflectionUtil.setMockSuperClassFinalFieldToTarget(AbstractLogicBaiRenJh.class, this.logic, "cardWallCtr", mockCardWallCtr);
        Mockito.when(mockCardWallCtr.getAllAreaCardNumber2dArray()).thenReturn(new int[][]{{1101}, {2101}});

        // 2. when
        int[] actualResult = this.logic.getBlackCardArray();
        int[] expectResult = new int[]{1101};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
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

    private JhBaiRenResultCtrConfig generateJhBaiRenResultCtrConfig() {
        JhBaiRenResultCtrGameConfig jhBaiRenResultCtrGameConfig = this.generateJhBaiRenResultCtrGameConfig();

        return new JhBaiRenResultCtrConfig(2,
                2,
                5.0,
                jhBaiRenResultCtrGameConfig.getCardTypeRateConfig());
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

    private JhBaiRenResultCtrGameConfig generateJhBaiRenResultCtrGameConfig() {
        return new JhBaiRenResultCtrGameConfig(
                new HashMap<>(){{
                    put(ConstJhStack.JhTypeEnumCommon.DAN_ZHANG, 0);
                    put(ConstJhStack.JhTypeEnumCommon.DUI_ZI, 1);
                    put(ConstJhStack.JhTypeEnumCommon.SHUN_ZI, 2);
                    put(ConstJhStack.JhTypeEnumCommon.TONG_HUA, 3);
                    put(ConstJhStack.JhTypeEnumCommon.TONG_HUA_SHUN, 5);
                    put(ConstJhStack.JhTypeEnumCommon.BAO_ZI, 10);
                }}
        );
    }
}