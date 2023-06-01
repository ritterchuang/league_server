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

// IP個人控策略測試
@ExtendWith(MockitoExtension.class)
class IpPersonControlStrategyTest {
    private IpPersonControlStrategy ipPersonControlStrategy; // IP個人控策略

    @BeforeEach
    void setUp() {
        this.ipPersonControlStrategy = new IpPersonControlStrategy();
    }

    //* 測試 isSatisfyStrategy *//
    // 給定檢驗清單為空指標，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_null_valueStart_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        this.ipPersonControlStrategy = this.initIpPersonControlStrategyTest(null, 0);

        // 2. when
        boolean result = this.ipPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定檢驗清單為空陣列，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_empty_valueStart_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        this.ipPersonControlStrategy = this.initIpPersonControlStrategyTest(new String[]{}, 0);

        // 2. when
        boolean result = this.ipPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定判斷類型非0，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_rangeId_not_zero_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        int rangeId = 1;

        this.ipPersonControlStrategy = this.initIpPersonControlStrategyTest(
                (new ArrayList<String>() {{add("127.0.0.1");}}).toArray(String[]::new),
                rangeId);

        // 2. when
        boolean result = this.ipPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定玩家IP與設定IP不一致，執行檢驗策略符合性，預期為偽
    @Test
    void test_given_user_id_not_satisfy_when_isSatisfyStrategy_then_false(@Mock IUser user) {
        // 1. given
        String userIp = "127.0.0.1";
        String configIp = "127.0.0.2";

        Mockito.when(user.getIp()).thenReturn(userIp);
        this.ipPersonControlStrategy = this.initIpPersonControlStrategyTest(
                (new ArrayList<String>(){{add(configIp);}}).toArray(String[]::new),
                0);

        // 2. when
        boolean result = this.ipPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertFalse(result);
    }

    // 給定玩家IP與設定IP一致，執行檢驗策略符合性，預期為真
    @Test
    void test_given_user_id_satisfy_when_isSatisfyStrategy_then_true(@Mock IUser user) {
        // 1. given
        String userIp = "127.0.0.1";
        String configIp = "127.0.0.1";

        Mockito.when(user.getIp()).thenReturn(userIp);
        this.ipPersonControlStrategy = this.initIpPersonControlStrategyTest(
                (new ArrayList<String>(){{add(configIp);}}).toArray(String[]::new),
                0);

        // 2. when
        boolean result = this.ipPersonControlStrategy.isSatisfyStrategy(user);

        // 3. then
        Assertions.assertTrue(result);
    }

    private IpPersonControlStrategy initIpPersonControlStrategyTest(String[] valueStart, int rangeId){
        return new IpPersonControlStrategy(
                "limitIps",
                0,
                "IP限制",
                true,
                0,
                0,
                rangeId,
                0,
                valueStart
        );
    }
}