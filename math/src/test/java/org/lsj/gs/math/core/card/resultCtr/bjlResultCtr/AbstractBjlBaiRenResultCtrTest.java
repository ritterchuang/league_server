package org.lsj.gs.math.core.card.resultCtr.bjlResultCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 抽象百家樂百人結果計算者測試
@ExtendWith(MockitoExtension.class)
class AbstractBjlBaiRenResultCtrTest extends AbstractCardTestUtil {
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池控制者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定空對應表、空陣列，當計算贏家區域，返還空陣列
    @Test
    void test_given_emptyMapAndEmptyArray_when_calculateWinAreasArray_then_return_emptyArray() {
        // 1. given
        BjlBaiRenResultCtrConfig config = this.generateConfig();
        AbstractBjlBaiRenResultCtr abstractBjlBaiRenResultCtr = Mockito.mock(AbstractBjlBaiRenResultCtr.class,
                Mockito.withSettings().useConstructor(config, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int[] actualResult = abstractBjlBaiRenResultCtr.calculateWinAreasArray(new HashMap<>(), new int[]{});
        int[] expectResult = new int[]{};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定玩家各區下注對應表，當計算各區下注陣列，返還正確陣列
    @Test
    void test_given_chairToAreaBetMap_when_calculateTotalAreaBetArray_then_return_correctArray() {
        // 1. given
        BjlBaiRenResultCtrConfig config = this.generateConfig();
        Map<Integer, Map<Integer, Integer>> chairToAreaMap = this.generateChairToAreaBetMap();
        AbstractBjlBaiRenResultCtr abstractBjlBaiRenResultCtr = Mockito.mock(AbstractBjlBaiRenResultCtr.class,
                Mockito.withSettings().useConstructor(config, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int[] actualResult = abstractBjlBaiRenResultCtr.calculateTotalAreaBetArray(chairToAreaMap);
        int[] expectResult = new int[]{45, 30, 0, 0, 0, 30, 0, 0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定空下注對應表，當計算各區下注陣列，返還 0 陣列
    @Test
    void test_given_chairToAreaBetMap_when_calculateTotalAreaBetArray_then_return_zeroArray() {
        // 1. given
        BjlBaiRenResultCtrConfig config = this.generateConfig();
        AbstractBjlBaiRenResultCtr abstractBjlBaiRenResultCtr = Mockito.mock(AbstractBjlBaiRenResultCtr.class,
                Mockito.withSettings().useConstructor(config, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int[] actualResult = abstractBjlBaiRenResultCtr.calculateTotalAreaBetArray(new HashMap<>());
        int[] expectResult = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 產生百家樂百人結果計算者設定
    private BjlBaiRenResultCtrConfig generateConfig() {
        return new BjlBaiRenResultCtrConfig(8, 2, 5,
                new HashMap<>(){{
                    // 闲家
                    put(ConstMathBjl.BetAreaEnum.UPBANK_LOSE.getCode() , 0.95);   // 上庄输
                    put(ConstMathBjl.BetAreaEnum.PLAY.getCode()        , 1.0);    // 闲
                    put(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode()   , 11.0);   // 闲对
                    put(ConstMathBjl.BetAreaEnum.SMALL.getCode()       , 1.5);    // 小
                    // 庄家
                    put(ConstMathBjl.BetAreaEnum.UPBANK_WIN.getCode()  , 0.95);   // 上庄赢
                    put(ConstMathBjl.BetAreaEnum.BANK.getCode()        , 0.95);   // 庄
                    put(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode()   , 11.0);   // 庄对
                    put(ConstMathBjl.BetAreaEnum.BIG.getCode()         , 0.54);   // 大
                    // 和
                    put(ConstMathBjl.BetAreaEnum.TIE.getCode()         , 8.0);    // 和
                }});
    }

    // 產生玩家各區押住
    private Map<Integer, Map<Integer, Integer>> generateChairToAreaBetMap() {
        return new HashMap<>(){
            {
                put(1, new HashMap<>(){
                    {
                        put(0, 30);
                        put(5, 30);
                    }
                });
                put(3, new HashMap<>(){
                    {
                        put(0, 15);
                        put(1, 30);
                    }
                });
            }
        };
    }
}