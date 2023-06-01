package org.lsj.gs.math.core.card.vieBankerCtr;

import org.lsj.ConstCommon;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.user.User;
import org.lsj.gs.user.UserBdr;
import org.lsj.utils.JsonUtil;
import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class VieBankerCtrTest {
    VieBankerCtr vieBankerCtr; // 搶莊計算器
    @Mock
    VieBankerCtrConfigRateList config; // 搶莊計算器設定
    @Mock
    GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr poolCtr; // 水池控制器
    @Mock ITableUtil tableUtil; // 牌桌工具包
    @Mock IRandomUtilMain randomUtil; // 隨機工具包

    @BeforeEach
    void setUp() {
        this.setMockToolReturnValue();
        this.vieBankerCtr = new VieBankerCtr(config, gamePlayerHlr, poolCtr, tableUtil);
    }

    // 不存在玩家，判斷合法押注，回傳false
    @Test
    public void test_given_nonExistPlayerChairIndex_when_isValidRate_then_returnFalse() {
        // 1. 計算結果
        boolean result = this.vieBankerCtr.isValidVieRate(5,0);

        // 2. 驗證
        Assert.assertFalse(result);
    }

    // 不存在倍數，判斷合法押注，回傳false
    @Test
    public void test_given_nonExistVieRate_when_isValidRate_then_returnFalse() {
        // 1. 計算結果
        boolean result = this.vieBankerCtr.isValidVieRate(0,5);

        // 2. 驗證
        Assert.assertFalse(result);
    }

    // 正確玩家押注倍數，判斷合法押注，回傳true
    @Test
    public void test_given_correctChairIndexAndRate_when_isValidRate_then_returnTrue() {
        // 1. 計算結果
        boolean result = this.vieBankerCtr.isValidVieRate(1,1);

        // 2. 驗證
        Assert.assertTrue(result);
    }

    // 未押注玩家，判斷是否押注過，回傳false
    @Test
    public void test_given_nonViedChairIndex_when_isPlayerVied_then_returnFalse() {
        // 1. 計算結果
        boolean result = this.vieBankerCtr.isPlayerVied(0);

        // 2. 驗證
        Assert.assertFalse(result);
    }

    // 押注玩家，判斷是否押注過，回傳true
    @Test
    public void test_given_viedChairIndex_when_isPlayerVied_then_returnTrue() {
        // 1. 準備資料
        this.vieBankerCtr.setPlayerVieRate(0, 1);

        // 2. 計算結果
        boolean result = this.vieBankerCtr.isPlayerVied(0);

        // 3. 驗證
        Assert.assertTrue(result);
    }

    // 檢查初始化時，產出的倍數資料恆正
    @Test
    public void test_given_beginMoneyFrom1To100_when_initialCtr_then_nonNegativeVieList() {
        for (double index = 1.0; index <= 100; index++) {
            // 1. 重新準備 mock 資料
            Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(2)).thenReturn(index);

            // 2. 初始化
            this.vieBankerCtr = new VieBankerCtr(config, gamePlayerHlr, poolCtr, tableUtil);

            // 3. 驗證資料
            List<Integer> vieRateList = this.vieBankerCtr.canVieRateListMap.get(2);
            for (Integer integer : vieRateList) {
                boolean isNegative = integer < 0;
                Assert.assertFalse(isNegative);
            }
        }
    }

    // 非莊家，檢查是否為莊，回傳 false
    @Test
    public void test_given_nonBankerChairIndex_when_isBankerChairIndex_then_returnFalse() {
        // 1. 計算莊家並設定
        this.vieBankerCtr.setPlayerVieRate(0, 3);
        this.vieBankerCtr.finishVie();

        // 2. 計算結果
        boolean result = this.vieBankerCtr.isBankerChairIndex(1);

        // 3. 驗證
        Assert.assertFalse(result);
    }

    // 莊家，檢查是否為莊，回傳 true
    @Test
    public void test_given_bankerChairIndex_when_isBankerChairIndex_then_returnTrue() {
        // 1. 計算莊家並設定
        this.vieBankerCtr.setPlayerVieRate(0, 3);
        this.vieBankerCtr.finishVie();

        // 2. 計算結果
        boolean result = this.vieBankerCtr.isBankerChairIndex(0);

        // 3. 驗證
        Assert.assertTrue(result);
    }

    // 當沒人搶莊，搶莊表演列表為全部玩家
    @Test
    public void test_given_noOneVieBank_when_finishVie_then_allPlayersInVieCandidateList() {
        // 1. 額外設定 mock
        Mockito.when(this.gamePlayerHlr.getHumanChairIndex()).thenReturn(0);
        Mockito.when(this.tableUtil.getMainRandomUtil()).thenReturn(randomUtil);
        Mockito.when(this.randomUtil.getRandomIntWithAccuracy(Mockito.anyInt(), Mockito.any())).thenReturn(0);

        // 2. 計算結果
        this.vieBankerCtr.finishVie();

        // 3. 取得資料
        List<Integer> result = this.vieBankerCtr.vieCandidateList;
        int[] vieCandidatesMessage = this.vieBankerCtr.getVieCandidateArrayMessage();

        // 4. 驗證
        Assertions.assertEquals(0, result.get(0));
        Assertions.assertEquals(1, result.get(1));
        Assertions.assertEquals(2, result.get(2));
        Assertions.assertEquals(3, vieCandidatesMessage.length);
    }

    // 當只要搶莊，會被算入搶莊表演列表
    @Test
    public void test_given_vieBankPlayers_when_finishVie_then_allVieBankPlayersInVieCandidateList() {
        // 1. 準備資料
        this.vieBankerCtr.setPlayerVieRate(0,3);
        this.vieBankerCtr.setPlayerVieRate(2,1);

        // 2. 計算結果
        this.vieBankerCtr.finishVie();

        // 3. 取得資料
        List<Integer> result = this.vieBankerCtr.vieCandidateList;
        int[] vieCandidatesMessage = this.vieBankerCtr.getVieCandidateArrayMessage();

        // 4. 驗證
        Assertions.assertEquals(0, result.get(0));
        Assertions.assertEquals(2, result.get(1));
        Assertions.assertEquals(2, vieCandidatesMessage.length);
    }

    // 當搶莊表演列表超過一人，回傳true
    @Test
    public void test_given_moreThanOneInVieCandidateList_when_isShowVieBankAnimation_then_returnTrue() {
        // 1. 準備資料
        this.vieBankerCtr.setPlayerVieRate(0,3);
        this.vieBankerCtr.setPlayerVieRate(2,1);

        // 2. 計算結果
        this.vieBankerCtr.finishVie();
        boolean result = this.vieBankerCtr.isShowVieBankAnimation();

        // 4. 驗證
        Assertions.assertTrue(result);
    }

    // 當搶莊表演列表僅一人，回傳false
    @Test
    public void test_given_onlyOneInVieCandidateList_when_isShowVieBankAnimation_then_returnFalse() {
        // 1. 準備資料
        this.vieBankerCtr.setPlayerVieRate(0,3);

        // 2. 計算結果
        this.vieBankerCtr.finishVie();
        boolean result = this.vieBankerCtr.isShowVieBankAnimation();

        // 4. 驗證
        Assertions.assertFalse(result);
    }

    // 給定搶莊對應表大小非最大玩家數，當計算搶莊陣列，無玩家的位置搶莊為-1
    @Test
    public void test_given_vieRateSizeNotMaxUser_when_getVieRateArray_then_nonPlayerChairResultIsNegative1() {
        // 1. given
        Mockito.when(this.config.getMaxUser()).thenReturn(5);
        this.vieBankerCtr = new VieBankerCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.vieBankerCtr.vieRateListMap = this.generateVieRateMap();

        // 2. when
        int[] actualResult = this.vieBankerCtr.getVieRateArray();
        int[] expectResult = new int[]{3, -1, -1, 1, 2};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        Assertions.assertEquals(expectString, actualString);
    }

    // 給定真人11元，當取真人可搶莊列表，僅回傳不搶
    @Test
    public void test_given_humanBeginMoney11_when_getHumanCanVieRateArray_then_returnNoVie() {
        // 1. given
        this.vieBankerCtr = new VieBankerCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        Mockito.when(gamePlayerHlr.getHumanChairIndex()).thenReturn(2);

        // 2. when
        int[] actualResult = this.vieBankerCtr.getHumanCanVieRateArray();
        int[] expectResult = new int[]{0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        Assertions.assertEquals(expectString, actualString);
    }

    private Map<Integer, Integer> generateVieRateMap() {
        return new HashMap<>(){
            {
                put(0, 3);
                put(3, 1);
                put(4, 2);
            }
        };
    }

    // 設定 mock 資料
    private void setMockToolReturnValue() {
        Mockito.when(this.gamePlayerHlr.getAllGamePlayerMap()).thenReturn(this.generateGamePlayerMapWithBeginMoney(new int[]{0,1,1}, new double[]{1111.1,111.1,11.1}));
        Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(0)).thenReturn(1111.1);
        Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(1)).thenReturn(111.1);
        Mockito.when(this.gamePlayerHlr.getPlayerBeginMoney(2)).thenReturn(11.1);

        Mockito.when(this.config.getBaseScore()).thenReturn(1.0);
        Mockito.when(this.config.getBankerType()).thenReturn(ConstMathCard.BankerType.RATE_LIST);
        Mockito.when(this.config.getVieRateList()).thenReturn(new ArrayList<>(){{add(0);add(1);add(2);add(3);}});
        Mockito.when(this.config.getVieThresholdList()).thenReturn(new ArrayList<>(){{add(0);add(18);add(54);add(90);}});
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