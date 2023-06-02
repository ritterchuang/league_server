package org.lsj.gs.math.core.card.qzBetCtr;

import org.lsj.ConstCommon;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtr;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrConfigBetType01;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.user.User;
import org.lsj.gs.user.UserBdr;
import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class QzBetCtrTest {
    QzBetCtr qzBetCtr; // 搶閒計算器
    @Mock
    QzBetCtrConfigBetType01 config; // 搶閒計算器設定
    @Mock
    GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr poolCtr; // 水池控制器
    @Mock ITableUtil tableUtil; // 牌桌工具包

    @BeforeEach
    public void setUp() {

    }

    // 檢查產出的倍數資料恆大於0
    @Test
    public void test_given_bankerRateFrom0To3_playerMoneyFrom1To1000_when_generateCanBetRateListMap_then_nonZeroNegative() {
        // 1. 設定初始 mock
        this.setMockToolReturnValues();

        // 2. 初始化搶莊計算器
        this.qzBetCtr = new QzBetCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        for (int bankerRate = 0; bankerRate <= 3; bankerRate++) {
            for (double playerMoney = 1.0; playerMoney <= 6.0; playerMoney++) {
                // 2. 設定閒家金額
                Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(2)).thenReturn(playerMoney);

                // 3. 執行
                this.qzBetCtr.generateCanBetRateListMap(0, bankerRate);

                // 4. 驗證不會有負數
                List<Integer> betRateList = this.qzBetCtr.getPlayerCanBetRateList(2);
                for (int rateIndex = 0; rateIndex < betRateList.size(); rateIndex++) {
                    boolean isBiggerThan0 =  betRateList.get(rateIndex) > 0;
                    Assert.assertTrue(isBiggerThan0);
                }
            }
        }
    }

    // 給定錯誤座位，判斷合法押注，回傳false
    @Test
    public void test_given_nonExistPlayerChairIndex_when_isValidBetRate_then_returnFalse() {
        // 1. 設定初始 mock
        this.setMockToolReturnValues();

        // 2. 初始化搶莊計算器
        this.qzBetCtr = new QzBetCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 3. 計算結果
        this.qzBetCtr.generateCanBetRateListMap(0, 1);
        boolean result = this.qzBetCtr.isValidBetRate(5,1);

        // 4. 驗證
        Assert.assertFalse(result);
    }

    // 給定錯誤倍數，判斷合法押注，回傳false
    @Test
    public void test_given_nonExistBetRate_when_isValidBetRate_then_returnFalse() {
        // 1. 設定初始 mock
        this.setMockToolReturnValues();

        // 2. 初始化搶莊計算器
        this.qzBetCtr = new QzBetCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 3. 計算結果
        this.qzBetCtr.generateCanBetRateListMap(0, 1);
        boolean result = this.qzBetCtr.isValidBetRate(1,-1);

        // 4. 驗證
        Assert.assertFalse(result);
    }

    // 給定正確座位、押注，判斷合法押注，回傳回傳true
    @Test
    public void test_given_correctPlayerChairIndexAndBetRate_when_isValidBetRate_then_returnTrue() {
        // 1. 設定初始 mock
        this.setMockToolReturnValues();

        // 2. 初始化搶莊計算器
        this.qzBetCtr = new QzBetCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 3. 計算結果
        this.qzBetCtr.generateCanBetRateListMap(0, 1);
        boolean result = this.qzBetCtr.isValidBetRate(1,1);

        // 4. 驗證
        Assert.assertTrue(result);
    }

    // 給定尚未押注座位，判斷是否押注，回傳false
    @Test
    public void test_given_nonBetPlayerChairIndex_when_isPlayerBet_then_returnFalse() {
        // 1. 設定初始 mock
        this.setMockToolSimple();

        // 2. 初始化搶莊計算器
        this.qzBetCtr = new QzBetCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 1. 計算結果
        boolean result = this.qzBetCtr.isPlayerBet(-1);

        // 3. 驗證
        Assert.assertFalse(result);
    }

    // 給定押注座位，判斷是否押注，回傳true
    @Test
    public void test_given_betPlayerChairIndex_when_isPlayerBet_then_returnTrue() {
        // 1. 設定初始 mock
        this.setMockToolSimple();

        // 2. 初始化搶莊計算器
        this.qzBetCtr = new QzBetCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 1. 設置倍數
        this.qzBetCtr.setPlayerBetRate(2, 1);

        // 1. 計算結果
        boolean result = this.qzBetCtr.isPlayerBet(2);

        // 3. 驗證
        Assert.assertTrue(result);
    }

    // 設定 mock 資料
    private void setMockToolReturnValues() {
        Mockito.when(this.gamePlayerHlr.getAllGamePlayerMap()).thenReturn(this.generateGamePlayerMapWithBeginMoney(new int[]{0,1,1}, new double[]{1111.1,111.1,11.1}));
        Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(0)).thenReturn(1111.1);
        Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(1)).thenReturn(111.1);
        Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(2)).thenReturn(55.5);
        Mockito.when(this.gamePlayerHlr.getPlayerCount()).thenReturn(3);
        Mockito.when(this.config.getBetType()).thenReturn(ConstMathCard.QzBetType.BET_01);
        Mockito.when(this.config.getThresholdValue()).thenReturn(4);
        Mockito.when(this.config.getSpecifyRate()).thenReturn(3);
        Mockito.when(this.config.getBaseScore()).thenReturn(1.0);
        Mockito.when(this.config.getMaxRate()).thenReturn(15);
        Mockito.when(this.config.getMinRate()).thenReturn(1);
    }

    // 設定 mock 資料
    private void setMockToolSimple() {
        Mockito.when(this.config.getBetType()).thenReturn(ConstMathCard.QzBetType.BET_01);
    }

    // TODO 重複出現，是否要抽?
    // 給定真人/機器人，初始金額，創建 Map<Integer, GamePlayer>
    private Map<Integer, GamePlayer> generateGamePlayerMapWithBeginMoney(int[] humanAiCode, double[] beginMoneys) {
        Map<Integer, GamePlayer> result = new HashMap<>();

        for (int chairIndex = 0; chairIndex < humanAiCode.length; chairIndex++) {
            User tempUser = (User) new UserBdr()
                    .setUid(100000 + chairIndex)
                    .setIp("127.0.0.1")
                    .setRobot(humanAiCode[chairIndex])
                    .setNickName("test_" + chairIndex)
                    .setAccount("testUser_" + chairIndex)
                    .setHeadImgUrl("2")
                    .setSex(0)
                    .setBoxid(0)
                    .setRole(ConstCommon.RoleType.PLAYER.getCode())
                    .setChair(0)
                    .setState(ConstCommon.UserStateType.WAIT.getCode())
                    .setVipLevel(1)
                    .createUser();

            result.put(chairIndex, new GamePlayer(tempUser, chairIndex , beginMoneys[chairIndex]));
        }

        return result;
    }
}