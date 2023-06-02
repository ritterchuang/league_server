package org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.ControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.reset;

// 演算法類型計算器測試
@ExtendWith(MockitoExtension.class)
class AlgorithmTypeCtrTest {
    AlgorithmTypeCtr algorithmTypeCtr;

    @Mock ITableUtil tableUtil;
    @Mock IRandomUtilMain randomUtilMain;

    @BeforeEach
    void setUp() {
        // 1. 建構
        this.algorithmTypeCtr = new AlgorithmTypeCtr(
                new GameAdjustConfig(
                        ConstMathCommon.PoolControlType.NONE,
                        ConstMathCommon.DealType.NONE,
                        ConstMathCommon.ShuffleType.NATURE
                ),
                new RngAlgorithmConfig(
                        ConstMathCommon.PoolControlType.NONE,
                        ConstMathCommon.DealType.NONE,
                        ConstMathCommon.ShuffleType.NATURE,
                        ConstMathCommon.PersonAdjustType.NORMAL
                ),
                this.tableUtil);

        // 2. 重置替換物件
        reset(tableUtil);
    }

    //* 計算演算法類型 *//

    // 給定需要控制為否，執行計算演算法類型，則演算法類型為不調控類型
    @Test
    void test_given_needControl_false_when_calculateAlgorithmType_then_algorithm_type_none() {
        // 1. given
        boolean needControl = false;
        ConstMathCommon.PersonAdjustType companyPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;

        // 2. when
        AlgorithmType algorithmType = this.algorithmTypeCtr.calculateAlgorithmType(needControl, companyPersonAdjustType);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new AlgorithmType(
                        ConstMathCommon.PersonAdjustType.NORMAL,
                        ConstMathCommon.PoolControlType.NONE,
                        ConstMathCommon.DealType.NONE,
                        ConstMathCommon.ShuffleType.NATURE
                )),
                JsonUtil.getInstance().writeValueAsStringWithoutException(algorithmType));
    }

    // 給定需要控制為是且設定強控，執行計算演算法類型，則演算法類型為強控類型
    @Test
    void test_given_needControl_true_isControlFlag_true_when_calculateAlgorithmType_then_algorithm_type_control() {
        // 1. given
        boolean needControl = true;
        ConstMathCommon.PersonAdjustType companyPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;
        Mockito.when(this.tableUtil.getControlAlgorithmUtil()).thenReturn(
                new ControlAlgorithmUtil(
                        true,
                        ConstMathCommon.PoolControlType.NONE,
                        ConstMathCommon.DealType.NONE,
                        ConstMathCommon.ShuffleType.NATURE,
                        ConstMathCommon.PersonAdjustType.NORMAL
                ));

        // 2. when
        AlgorithmType algorithmType = this.algorithmTypeCtr.calculateAlgorithmType(needControl, companyPersonAdjustType);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new AlgorithmType(
                        ConstMathCommon.PersonAdjustType.NORMAL,
                        ConstMathCommon.PoolControlType.NONE,
                        ConstMathCommon.DealType.NONE,
                        ConstMathCommon.ShuffleType.NATURE
                )),
                JsonUtil.getInstance().writeValueAsStringWithoutException(algorithmType));
    }

    // 給定需要控制為是且不設定強控且設定亂數狀態，執行計算演算法類型，則演算法類型為使用亂數類型
    @Test
    void test_given_needControl_true_isControlFlag_false_isSetRngDataState_true_when_calculateAlgorithmType_then_algorithm_type_rng() {
        // 1. given
        boolean needControl = true;
        ConstMathCommon.PersonAdjustType companyPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;
        Mockito.when(this.tableUtil.getControlAlgorithmUtil()).thenReturn(new ControlAlgorithmUtil());
        Mockito.when(this.tableUtil.getMainRandomUtil()).thenReturn(this.randomUtilMain);
        Mockito.when(this.tableUtil.getMainRandomUtil().isSetRngDataState()).thenReturn(true);

        // 2. when
        AlgorithmType algorithmType = this.algorithmTypeCtr.calculateAlgorithmType(needControl, companyPersonAdjustType);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new AlgorithmType(
                        ConstMathCommon.PersonAdjustType.NORMAL,
                        ConstMathCommon.PoolControlType.NONE,
                        ConstMathCommon.DealType.NONE,
                        ConstMathCommon.ShuffleType.NATURE
                )),
                JsonUtil.getInstance().writeValueAsStringWithoutException(algorithmType));
    }

    // 給定需要控制為是且不設定強控且不設定亂數狀態，執行計算演算法類型，則演算法類型為設定類型
    @Test
    void test_given_needControl_true_isControlFlag_false_isSetRngDataState_false_when_calculateAlgorithmType_then_algorithm_type_setting() {
        // 1. given
        boolean needControl = true;
        ConstMathCommon.PersonAdjustType companyPersonAdjustType = ConstMathCommon.PersonAdjustType.NORMAL;
        Mockito.when(this.tableUtil.getControlAlgorithmUtil()).thenReturn(new ControlAlgorithmUtil());
        Mockito.when(this.tableUtil.getMainRandomUtil()).thenReturn(this.randomUtilMain);
        Mockito.when(this.tableUtil.getMainRandomUtil().isSetRngDataState()).thenReturn(false);

        // 2. when
        AlgorithmType algorithmType = this.algorithmTypeCtr.calculateAlgorithmType(needControl, companyPersonAdjustType);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new AlgorithmType(
                        companyPersonAdjustType,
                        ConstMathCommon.PoolControlType.NONE,
                        ConstMathCommon.DealType.NONE,
                        ConstMathCommon.ShuffleType.NATURE
                )),
                JsonUtil.getInstance().writeValueAsStringWithoutException(algorithmType));
    }
}