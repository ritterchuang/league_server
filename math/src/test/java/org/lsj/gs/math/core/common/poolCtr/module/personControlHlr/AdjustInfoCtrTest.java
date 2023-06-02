package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr;

import org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr.ControlStrategyJsonDataGtr;
import org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr.PersonControlConfigDataGtr;
import org.lsj.gs.pool.PersonControlConfig;
import org.lsj.gs.user.IUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Map;

import static org.mockito.Mockito.reset;

// 個人控結果計算器測試
@ExtendWith(MockitoExtension.class)
class AdjustInfoCtrTest {
    private AdjustInfoCtr adjustInfoCtr; // 個人控結果計算器
    private PersonControlConfigDataGtr personControlConfigDataGtr; // 個人控設定資料產生器
    private ControlStrategyJsonDataGtr controlStrategyJsonDataGtr; // 個人控策略JSON格式資料產生器

    @Mock PersonControlConfig config; // 個人控設定

    @BeforeEach
    void setUp() {
        // 1. 建構模組
        this.adjustInfoCtr = new AdjustInfoCtr(this.config);
        this.personControlConfigDataGtr = new PersonControlConfigDataGtr();
        this.controlStrategyJsonDataGtr = new ControlStrategyJsonDataGtr();

        // 2. 重置替換物件
        reset(this.config);
    }

    //* 測試 calculateAdjustInfo *//
    // 給定公司個人控設定為空指標，執行計算個人控結果，預期個人控結果為空指標
    @Test
    void test_given_null_companyNewPersonControlObjList_when_calculateAdjustInfo_then_adjustInfo_null(@Mock Map<Integer, Integer> personControlLogObj, @Mock IUser user){
        // 1. given
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(null);

        // 2. when
        this.adjustInfoCtr.calculateAdjustInfo(personControlLogObj, user);

        // 3. then
        Assertions.assertNull(this.adjustInfoCtr.getAdjustInfo());
    }

    // 給定公司個人控設定為空陣列，執行計算個人控結果，預期個人控結果為空指標
    @Test
    void test_given_empty_companyNewPersonControlObjList_when_calculateAdjustInfo_then_adjustInfo_null(@Mock Map<Integer, Integer> personControlLogObj, @Mock IUser user){
        // 1. given
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(new ArrayList<>());

        // 2. when
        this.adjustInfoCtr.calculateAdjustInfo(personControlLogObj, user);

        // 3. then
        Assertions.assertNull(this.adjustInfoCtr.getAdjustInfo());
    }

    // 給定非啟用的公司個人控設定，執行計算個人控結果，預期個人控結果為空指標
    @Test
    void test_given_companyNewPersonControlObjList_status_0_when_calculateAdjustInfo_then_adjustInfo_null(@Mock Map<Integer, Integer> personControlLogObj, @Mock IUser user){
        // 1. given
        int status = 0;
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListWithControlStrategyJson(
                1, status, 1, controlStrategyJsonDataGtr.getDefaultControlStrategyJson()));

        // 2. when
        this.adjustInfoCtr.calculateAdjustInfo(personControlLogObj, user);

        // 3. then
        Assertions.assertNull(this.adjustInfoCtr.getAdjustInfo());
    }

    // 給定玩家不合理的執行次數，執行計算個人控結果，預期個人控結果為空指標
    @Test
    void test_given_user_invalid_execute_time_when_calculateAdjustInfo_then_adjustInfo_null(@Mock Map<Integer, Integer> personControlLogObj, @Mock IUser user){
        // 1. given
        int usedExecTimes = 100;
        int maxExecTimes = 100;

        int companyNewPersonControlObjIndex = 1;
        Mockito.when(personControlLogObj.get(companyNewPersonControlObjIndex)).thenReturn(usedExecTimes);
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListWithControlStrategyJson(
                companyNewPersonControlObjIndex, 1, maxExecTimes, controlStrategyJsonDataGtr.getDefaultControlStrategyJson()));

        // 2. when
        this.adjustInfoCtr.calculateAdjustInfo(personControlLogObj, user);

        // 3. then
        Assertions.assertNull(this.adjustInfoCtr.getAdjustInfo());
    }

    // 給定玩家不滿足公司個人控設定，執行計算個人控結果，預期個人控結果為空指標
    @Test
    void test_given_user_not_satisfy_companyNewPersonControlObjList_when_calculateAdjustInfo_then_adjustInfo_null(@Mock Map<Integer, Integer> personControlLogObj, @Mock IUser user){
        // 1. given
        int userThirdAccountId = 1;
        int configThirdAccountId = 2;

        Mockito.when(user.getThirdAccountId()).thenReturn(userThirdAccountId);
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListWithControlStrategyJson(
            1, 1, 100, this.controlStrategyJsonDataGtr.getThirdAccountIdControlStrategyJson(configThirdAccountId)
        ));

        // 2. when
        this.adjustInfoCtr.calculateAdjustInfo(personControlLogObj, user);

        // 3. then
        Assertions.assertNull(this.adjustInfoCtr.getAdjustInfo());
    }

    // 給定玩家滿足公司個人控設定，執行計算個人控結果，預期回傳個人控結果物件
    @Test
    void test_given_user_satisfy_companyNewPersonControlObjList_when_calculateAdjustInfo_then_adjustInfo_obj(@Mock Map<Integer, Integer> personControlLogObj, @Mock IUser user){
        // 1. given
        int userThirdAccountId = 1;
        int configThirdAccountId = 1;
        int companyNewPersonControlObjIndex = 1;

        Mockito.when(user.getThirdAccountId()).thenReturn(userThirdAccountId);
        Mockito.when(this.config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListWithControlStrategyJson(
                companyNewPersonControlObjIndex, 1, 100, this.controlStrategyJsonDataGtr.getThirdAccountIdControlStrategyJson(configThirdAccountId)
        ));

        // 2. when
        this.adjustInfoCtr.calculateAdjustInfo(personControlLogObj, user);

        // 3. then
        Assertions.assertEquals(companyNewPersonControlObjIndex, this.adjustInfoCtr.getAdjustInfo().getId());
    }
}