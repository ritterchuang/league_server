package org.lsj.gs.math.core.card.resultCtr;

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

// 抽象百人結果計算者測試
@ExtendWith(MockitoExtension.class)
class AbstractBaiRenResultCtrTest {
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 玩家處理者
    @Mock
    PoolCtr mockPoolCtr; // 水池控制者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定各區域下注金額，當計算各區域下注金額，計算正確
    @Test
    void test_given_area1Bet100AndArea2Bet15_when_calculateAreaBetArray_then_correctArray() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        AbstractBaiRenResultCtr abstractBaiRenResultCtr = Mockito.mock(AbstractBaiRenResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        int areaCount = 4;
        Map<Integer, Integer> areaBetMap = new HashMap<>(){
            {
                put(1, 100);
                put(2, 15);
            }
        };

        // 2. when
        double[] actualResult = abstractBaiRenResultCtr.calculateAreaBetArray(areaBetMap, areaCount);
        double[] expectResult = new double[]{0.0, 100.0, 15.0, 0.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定各押注區域未扣手續費淨利 和各押注區域手續費，當計算各區域淨利，計算正確
    @Test
    void test_given_areaNoFeeScoreArrayAndAreaFeeArray_when_calculateAreaScoreArray_then_correctArray() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        AbstractBaiRenResultCtr abstractBaiRenResultCtr = Mockito.mock(AbstractBaiRenResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        double[] areaNoFeeScoreArray = new double[]{-10.0, 100.0, 5.0, 0.0};
        double[] areaFeeArray = new double[]{0.0, 5.5, 0.1, 0.0};

        // 2. when
        double[] actualResult = abstractBaiRenResultCtr.calculateAreaScoreArray(areaNoFeeScoreArray, areaFeeArray);
        double[] expectResult = new double[]{-10.0, 94.5, 4.9, 0.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定各押注區域未扣手續費淨利，當計算各區域有效投注，計算正確
    @Test
    void test_given_areaNoFeeScoreArray_when_calculateAreaValidBetArray_then_correctArray() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        AbstractBaiRenResultCtr abstractBaiRenResultCtr = Mockito.mock(AbstractBaiRenResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        double[] areaNoFeeScoreArray = new double[]{-10.0, 100.0, 5.0, 0.0};

        // 2. when
        double[] actualResult = abstractBaiRenResultCtr.calculateAreaValidBetArray(areaNoFeeScoreArray);
        double[] expectResult = new double[]{10.0, 100.0, 5.0, 0.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定各押注區域 押注、淨利、手續費，當計算各區域返獎，計算正確
    @Test
    void test_given_betArrayAndAreaScoreArrayAndAreaFeeArray_when_calculateAreaReturnAwardArray_then_correctArray() {
        // 1. given
        int feeType = 2;
        double gameRate = 5.0;
        AbstractBaiRenResultCtr abstractBaiRenResultCtr = Mockito.mock(AbstractBaiRenResultCtr.class,
                Mockito.withSettings().useConstructor(feeType, gameRate, mockGamePlayerHlr, mockPoolCtr, mockTableUtil).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        double[] betArray = new double[]{10.0, 100.0, 5.0, 0.0};
        double[] areaScoreArray = new double[]{-10.0, 94.5, 4.9, 0.0};
        double[] areaFeeArray = new double[]{0.0, 5.5, 0.1, 0.0};

        // 2. when
        double[] actualResult = abstractBaiRenResultCtr.calculateAreaReturnAwardArray(betArray, areaScoreArray, areaFeeArray);
        double[] expectResult = new double[]{0.0, 200.0, 10.0, 0.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }
}