package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.AdjustInfo;
import org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr.NumericalObjDataGtr;
import org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr.PersonControlConfigDataGtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.pool.PersonControlConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

// 個人控類型計算器測試
@ExtendWith(MockitoExtension.class)
class PersonAdjustTypeCtrTest {
    private PersonAdjustTypeCtr personAdjustTypeCtr; // 個人控類型計算器
    private PersonControlConfigDataGtr personControlConfigDataGtr; // 個人控設定資料產生器
    private NumericalObjDataGtr numericalObjDataGtr; // 數控物件資料產生器

    @Mock PersonControlConfig config; // 個人控設定
    AlgorithmTypeCtr algorithmTypeCtr; // 演算法類型計算器
    @Mock ITableUtil tableUtil; // 牌桌工具包

    @BeforeEach
    void setUp() {
        // 1. 建構計算器
        this.personAdjustTypeCtr = new PersonAdjustTypeCtr(this.config, this.algorithmTypeCtr, this.tableUtil);
        this.personControlConfigDataGtr = new PersonControlConfigDataGtr();
        this.numericalObjDataGtr = new NumericalObjDataGtr();

        // 2. 重置替換物件
        reset(config);
        reset(tableUtil);
    }

    //* 測試 calculatePersonAdjustType *//
    // 給定公司個人控設定為空指標，執行計算個人控類型，預期個人控類型為自然
    @Test
    void test_given_null_companyNewPersonControlObjList_when_calculatePersonAdjustType_then_companyPersonAdjustType_normal(@Mock AdjustInfo adjustInfo) {
        // 1. given
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(null);

        // 2. when
        this.personAdjustTypeCtr.calculatePersonAdjustType(adjustInfo);

        // 3. then
        Assertions.assertEquals(ConstMathCommon.PersonAdjustType.NORMAL, this.personAdjustTypeCtr.getCompanyPersonAdjustType());
    }

    // 給定公司個人控設定為空陣列，執行計算個人控類型，預期個人控類型為自然
    @Test
    void test_given_empty_companyNewPersonControlObjList_when_calculatePersonAdjustType_then_companyPersonAdjustType_normal(@Mock AdjustInfo adjustInfo) {
        // 1. given
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(new ArrayList<>());

        // 2. when
        this.personAdjustTypeCtr.calculatePersonAdjustType(adjustInfo);

        // 3. then
        Assertions.assertEquals(ConstMathCommon.PersonAdjustType.NORMAL, this.personAdjustTypeCtr.getCompanyPersonAdjustType());
    }

    // 給定個人控結果為空指標，執行計算個人控類型，預期個人控類型為自然
    @Test
    void test_given_null_adjustInfo_when_calculatePersonAdjustType_then_companyPersonAdjustType_normal() {
        // 1. given
        AdjustInfo adjustInfo = null;

        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListDefault());

        // 2. when
        this.personAdjustTypeCtr.calculatePersonAdjustType(adjustInfo);

        // 3. then
        Assertions.assertEquals(ConstMathCommon.PersonAdjustType.NORMAL, this.personAdjustTypeCtr.getCompanyPersonAdjustType());
    }

    // 給定數控為白一，執行計算個人控類型，預期個人控類型為白一
    @Test
    void test_given_numericalObj_name_white_1_when_calculatePersonAdjustType_then_companyPersonAdjustType_white_1(@Mock AdjustInfo adjustInfo) {
        // 1. given
        String numericalObjString = this.numericalObjDataGtr.getOnlyWhite1NumericalJsonString();

        int adjustInfoIndex = 1;
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListWithNumericalJsonString(
                adjustInfoIndex,
                1,
                100,
                numericalObjString
        ));
        Mockito.when(adjustInfo.getId()).thenReturn(adjustInfoIndex);
        IRandomUtilMain randomUtil = mock(IRandomUtilMain.class);
        Mockito.when(randomUtil.getArrayIndexByWeightWithAccuracy(Mockito.anyList(), Mockito.any())).thenReturn(0);
        Mockito.when(this.tableUtil.getMainRandomUtil()).thenReturn(randomUtil);

        // 2. when
        this.personAdjustTypeCtr.calculatePersonAdjustType(adjustInfo);

        // 3. then
        Assertions.assertEquals(ConstMathCommon.PersonAdjustType.WHITE1, this.personAdjustTypeCtr.getCompanyPersonAdjustType());
    }
}