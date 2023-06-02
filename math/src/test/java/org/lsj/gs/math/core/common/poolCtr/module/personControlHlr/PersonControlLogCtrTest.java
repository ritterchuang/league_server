package org.lsj.gs.math.core.common.poolCtr.module.personControlHlr;

import org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.AdjustInfo;
import org.lsj.gs.math.core.common.poolCtr.module.personControlHlr.dataGtr.PersonControlConfigDataGtr;
import org.lsj.gs.pool.PersonControlConfig;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

// 個人控次數紀錄計算器測試
@ExtendWith(MockitoExtension.class)
class PersonControlLogCtrTest {
    private PersonControlLogCtr personControlLogCtr; // 個人控次數紀錄計算器
    private PersonControlConfigDataGtr personControlConfigDataGtr; // 個人控設定資料產生器

    @BeforeEach
    void setUp() {
        this.personControlConfigDataGtr = new PersonControlConfigDataGtr();
    }

    /* 測試 constructor */
    // 給定玩家個人控次數字串為空指標，執行建構子，預期空對應表字串
    @Test
    void test_given_null_user_personControlLog_when_constructor_then_personControlLogMapString_empty_map_string(@Mock PersonControlConfig config, @Mock IUser user) {
        // 1. given

        // 2. when
        this.personControlLogCtr = new PersonControlLogCtr(config, user);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>()),
                this.personControlLogCtr.getPersonControlLogMapString());
    }

    // 給定玩家個人控次數字串為空指標，執行建構子，預期對應表字串<1,1>
    @Test
    void test_given_user_personControlLog_map_string_1_1_when_constructor_then_personControlLogMapString_map_string_1_1(@Mock PersonControlConfig config, @Mock IUser user) {
        // 1. given
        String personControlLogMapString = JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>(){{put(1, 1);}});

        Mockito.when(user.getPersonControlLog()).thenReturn(personControlLogMapString);

        // 2. when
        this.personControlLogCtr = new PersonControlLogCtr(config, user);

        // 3. then
        Assertions.assertEquals(personControlLogMapString, this.personControlLogCtr.getPersonControlLogMapString());
    }



    /* 測試 calculatePersonControlLog */
    // 給定公司個人控設定為空指標，執行計算個人控次數紀錄，預期個人控次數紀錄字串為空對應表字串
    @Test
    void test_given_null_companyNewPersonControlObjList_when_calculatePersonControlLog_then_personControlLogMapString_empty_map_string(
            @Mock PersonControlConfig config,
            @Mock IUser user,
            @Mock AdjustInfo adjustInfo) {
        // 1. given
        Mockito.when(config.getCompanyNewPersonControlObjList()).thenReturn(null);
        this.personControlLogCtr = new PersonControlLogCtr(config, user);

        // 2. when
        this.personControlLogCtr.calculatePersonControlLog(adjustInfo);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>()),
                this.personControlLogCtr.getPersonControlLogMapString());
    }

    // 給定公司個人控設定為空陣列，執行計算個人控次數紀錄，預期個人控次數紀錄字串為空對應表字串
    @Test
    void test_given_empty_companyNewPersonControlObjList_when_calculatePersonControlLog_then_personControlLogMapString_empty_map_string(
            @Mock PersonControlConfig config,
            @Mock IUser user,
            @Mock AdjustInfo adjustInfo) {
        // 1. given
        Mockito.when(config.getCompanyNewPersonControlObjList()).thenReturn(new ArrayList<>());
        this.personControlLogCtr = new PersonControlLogCtr(config, user);

        // 2. when
        this.personControlLogCtr.calculatePersonControlLog(adjustInfo);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>()),
                this.personControlLogCtr.getPersonControlLogMapString());
    }

    // 給定個人控結果為空指標，執行計算個人控次數紀錄，預期個人控次數紀錄字串為空對應表字串
    @Test
    void test_given_null_adjustInfo_when_calculatePersonControlLog_then_personControlLogMapString_empty_map_string(@Mock PersonControlConfig config, @Mock IUser user) {
        // 1. given
        AdjustInfo adjustInfo= null;

        Mockito.when(config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListDefault());
        this.personControlLogCtr = new PersonControlLogCtr(config, user);

        // 2. when
        this.personControlLogCtr.calculatePersonControlLog(adjustInfo);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>()),
                this.personControlLogCtr.getPersonControlLogMapString());
    }

    // 給定個人控結果為索引1且個人控次數無紀錄，執行計算個人控次數紀錄，預期個人控次數紀錄字串為對應表字串<1,1>
    @Test
    void test_given_adjustInfo_index_1_and_personControlLogMap_none_when_calculatePersonControlLog_then_personControlLogMapString_map_string_1_1(
            @Mock PersonControlConfig config,
            @Mock IUser user,
            @Mock AdjustInfo adjustInfo) {
        // 1. given
        Mockito.when(adjustInfo.getId()).thenReturn(1);

        Mockito.when(config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListDefault());
        this.personControlLogCtr = new PersonControlLogCtr(config, user);

        // 2. when
        this.personControlLogCtr.calculatePersonControlLog(adjustInfo);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>(){{put(1, 1);}}),
                this.personControlLogCtr.getPersonControlLogMapString());
    }

    // 給定個人控結果為索引1且個人控次數有紀錄<1,1>，執行計算個人控次數紀錄，預期個人控次數紀錄字串為對應表字串<1,2>
    @Test
    void test_given_adjustInfo_index_1_and_personControlLogMap_1_1_when_calculatePersonControlLog_then_personControlLogMapString_map_string_1_2(
            @Mock PersonControlConfig config,
            @Mock IUser user,
            @Mock AdjustInfo adjustInfo) {
        // 1. given
        Mockito.when(adjustInfo.getId()).thenReturn(1);
        String personControlLogMapString = JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>(){{put(1, 1);}});
        Mockito.when(user.getPersonControlLog()).thenReturn(personControlLogMapString);

        Mockito.when(config.getCompanyNewPersonControlObjList()).thenReturn(this.personControlConfigDataGtr.getCompanyNewPersonControlObjListDataGtr().createOneCompanyNewPersonControlObjListDefault());
        this.personControlLogCtr = new PersonControlLogCtr(config, user);

        // 2. when
        this.personControlLogCtr.calculatePersonControlLog(adjustInfo);

        // 3. then
        Assertions.assertEquals(
                JsonUtil.getInstance().writeValueAsStringWithoutException(new HashMap<>(){{put(1, 2);}}),
                this.personControlLogCtr.getPersonControlLogMapString());
    }
}