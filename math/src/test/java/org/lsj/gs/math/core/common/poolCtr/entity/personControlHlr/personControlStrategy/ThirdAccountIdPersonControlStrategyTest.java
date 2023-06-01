package org.lsj.gs.math.core.common.poolCtr.entity.personControlHlr.personControlStrategy;

import org.lsj.gs.user.IUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

// 帳號索引個人控策略測試
@ExtendWith(MockitoExtension.class)
class ThirdAccountIdPersonControlStrategyTest {
    private ThirdAccountIdPersonControlStrategy thirdAccountIdPersonControlStrategy; // 帳號索引個人控策略

    @BeforeEach
    void setUp() {
        this.thirdAccountIdPersonControlStrategy = new ThirdAccountIdPersonControlStrategy();
    }

    //* 測試 isSatisfyStrategy *//
    // 給定檢驗清單為空指標，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_null_valueStart_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        this.thirdAccountIdPersonControlStrategy = this.initThirdAccountIdPersonControlStrategy(null, 0);

        // 2. when
        boolean result = this.thirdAccountIdPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定檢驗清單為空陣列，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_empty_valueStart_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        this.thirdAccountIdPersonControlStrategy = this.initThirdAccountIdPersonControlStrategy(new int[]{}, 0);

        // 2. when
        boolean result = this.thirdAccountIdPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定判斷類型非0，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_rangeId_not_zero_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        int rangeId = 1;

        this.thirdAccountIdPersonControlStrategy = this.initThirdAccountIdPersonControlStrategy(
                (new ArrayList<Integer>(){{add(1);}}).stream().mapToInt(i -> i).toArray(),
                rangeId);

        // 2. when
        boolean result = this.thirdAccountIdPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定玩家編碼與設定編碼不一致，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_user_id_not_satisfy_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        int userThirdAccountId = 1;
        int configThirdAccountId = 2;

        Mockito.when(user.getThirdAccountId()).thenReturn(userThirdAccountId);
        this.thirdAccountIdPersonControlStrategy = this.initThirdAccountIdPersonControlStrategy(
                (new ArrayList<Integer>(){{add(configThirdAccountId);}}).stream().mapToInt(i -> i).toArray(),
                0);

        // 2. when
        boolean result = this.thirdAccountIdPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定玩家編碼與設定編碼一致，執行檢驗策略符合性，預期為真
    @Test
    void test_given_user_id_satisfy_when_isSatisfyStrategy_then_true(@Mock IUser user) {
        // 1. given
        int userThirdAccountId = 1;
        int configThirdAccountId = 1;

        Mockito.when(user.getThirdAccountId()).thenReturn(userThirdAccountId);
        this.thirdAccountIdPersonControlStrategy = this.initThirdAccountIdPersonControlStrategy(
                (new ArrayList<Integer>(){{add(configThirdAccountId);}}).stream().mapToInt(i -> i).toArray(),
                0);

        // 2. when
        boolean result = this.thirdAccountIdPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertTrue(result);
    }

    private ThirdAccountIdPersonControlStrategy initThirdAccountIdPersonControlStrategy(int[] valueStart, int rangeId){
        return new ThirdAccountIdPersonControlStrategy(
                "userIds",
                0,
                "用户ID",
                true,
                0,
                0,
                rangeId,
                0,
                valueStart
        );
    }
}