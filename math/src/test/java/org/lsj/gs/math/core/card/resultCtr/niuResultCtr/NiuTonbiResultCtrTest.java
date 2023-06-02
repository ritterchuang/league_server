package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.AbstractCardTestUtil;
import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

// 通比牛牛的算分結果計算器 測試
@ExtendWith(MockitoExtension.class)
class NiuTonbiResultCtrTest extends AbstractCardTestUtil {
    NiuTonbiResultCtr niuTonbiResultCtr; // 通比牛牛的算分結果計算器

    @Mock
    NiuTonbiResultCtrConfig mockConfig; // 算分結果計算器設定
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr mockPoolCtr; // 水池控制器
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    @BeforeEach
    void setUp() {
        Mockito.when(this.mockConfig.getFeeType()).thenReturn(ConstMathCommon.FeeType.RATE.getType());
        Mockito.when(this.mockConfig.getGameRate()).thenReturn(5.0);
        this.niuTonbiResultCtr = new NiuTonbiResultCtr(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
    }

    // 給定1、3號有真人玩家，當取得所有玩家淨利時，無真人玩家淨利為0，有真人玩家回傳正確淨利
    @Test
    public void test_given_chair1AndChair3ArePlayer_when_getAllPlayerScoreArray_then_noPlayerChairIndexScoreIs0() {
        // 1. given
        Mockito.when(this.mockConfig.getMaxUser()).thenReturn(5);
        this.niuTonbiResultCtr.setUidScoreMap(
                super.generateUidScoreMap(
                        List.of(1, 3),
                        List.of(1, 3),
                        List.of(10.0, 100.0),
                        List.of(10.0, 100.0),
                        List.of(0.0, 100.0),
                        List.of(-10.0, 95.0),
                        List.of(0.0, 5.0)));

        // 2. when
        double[] actualResult = this.niuTonbiResultCtr.getAllPlayerScoreArray();
        double[] expectResult = new double[]{0.0, -10.0, 0.0, 95.0, 0.0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 贏家正常贏錢，閒1正常輸錢，閒2正常輸錢
    @Test
    public void test_given_player1Loss450_player2Loss300_when_calculateUidScoreMap_then_winnerAndPlayersAreEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> niuStackPerPlayer = this.generateThreeNiuStackMapBySpecifyOrder(new int[]{0, 1, 2});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{0, 1, 2}, new int[]{5, 6, 4});

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{1000.0, 10000.0, 20000.0};
        double baseScore = 5.0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.niuTonbiResultCtr.calculateUidScoreMap(niuStackPerPlayer, betRateMap);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002),
                List.of(375.0, 450.0, 300.0),
                List.of(375.0, 450.0, 300.0),
                List.of(1125.0, 0.0, 0.0),
                List.of(712.5, -450.0, -300.0),
                List.of(37.5, 0.0, 0.0)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 玩家贏錢不夠賠，閒1正常輸錢不夠賠，閒2正常輸錢
    @Test
    public void test_given_player1Loss1950_player2Win2250_when_calculateUidScoreMap_then_winnerAndPlayer1NotEnough() {
        // 1. 初始遊戲計算參數
        Map<Integer, AbstractStack> niuStackPerPlayer = this.generateThreeNiuStackMapBySpecifyOrder(new int[]{0, 1, 2});
        Map<Integer, Integer> betRateMap = super.generateBetRateMap(new int[]{0, 1, 2}, new int[]{5, 13, 15});

        // 2. 設定 Mock物件
        int[] humanAiSetting = new int[]{0, 1, 1};
        double[] beginMoney = new double[]{500.0, 200.0, 20000.0};
        double baseScore = 10.0;
        this.setMockToolReturnValue(humanAiSetting, beginMoney, baseScore);

        // 3. 計算結果
        Map<Integer, UidScore> actualResult = this.niuTonbiResultCtr.calculateUidScoreMap(niuStackPerPlayer, betRateMap);
        Map<Integer, UidScore> expectResult = super.generateUidScoreMap(
                List.of(0, 1, 2),
                List.of(100000, 100001, 100002),
                List.of(350.0, 200.0, 500.0),
                List.of(350.0, 200.0, 500.0),
                List.of(1050.0, 0.0, 0.0),
                List.of(665.0, -200.0, -500.0),
                List.of(35.0, 0.0, 0.0)
        );

        // 4. 驗證執行結果
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 設定 mock 物件
    private void setMockToolReturnValue(int[] humanRobotType, double[] beginMoneys, double baseScore) {
        Mockito.when(this.mockGamePlayerHlr.getAllGamePlayerMap()).thenReturn(super.generateGamePlayerMapWithBeginMoney(humanRobotType, beginMoneys));
        Mockito.when(this.mockGamePlayerHlr.getPlayerCount()).thenReturn(3);
        Mockito.when(this.mockConfig.getBaseScore()).thenReturn(baseScore);
        Mockito.when(this.mockConfig.getNiuTypeRateConfig()).thenReturn(this.createNiuTypeConfig());
    }

    // 創建牛型設定
    private Map<ConstNiu.NiuTypeEnumCommon, Integer> createNiuTypeConfig(){
        return new HashMap<>(){{
            put(ConstNiu.NiuTypeEnumCommon.INVALID, 0);
            put(ConstNiu.NiuTypeEnumCommon.NIU_0, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_1, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_2, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_3, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_4, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_5, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_6, 1);
            put(ConstNiu.NiuTypeEnumCommon.NIU_7, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_8, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_9, 2);
            put(ConstNiu.NiuTypeEnumCommon.NIU_NIU, 3);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_4, 4);
            put(ConstNiu.NiuTypeEnumCommon.FLOWER_5, 4);
            put(ConstNiu.NiuTypeEnumCommon.BOMB, 4);
            put(ConstNiu.NiuTypeEnumCommon.SMALL_NIU, 4);
        }};
    }

    // 依照指定順序產牌
    private Map<Integer, AbstractStack> generateThreeNiuStackMapBySpecifyOrder(int[] playerOrder) {
        // 1. 準備資料 牛9 / 牛9 / 牛8
        List<ICard> maxCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{102, 208, 210, 110, 113});
        List<ICard> secondCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{407, 203, 310, 212, 211});
        List<ICard> thirdCardList = super.generateCardList(ConstMathCard.CardType.POKER, new int[]{307, 103, 410, 104, 205});

        NiuStackCommon maxStack = new NiuStackCommon(
                maxCardList,
                new ArrayList<>() {},
                ConstNiu.NiuTypeEnumCommon.NIU_NIU,
                true
        );

        NiuStackCommon secondStack = new NiuStackCommon(
                secondCardList,
                new ArrayList<>() {},
                ConstNiu.NiuTypeEnumCommon.NIU_NIU,
                true
        );

        NiuStackCommon thirdStack = new NiuStackCommon(
                thirdCardList,
                new ArrayList<>() {},
                ConstNiu.NiuTypeEnumCommon.NIU_9,
                true
        );

        // 2. 封裝
        Map<Integer, AbstractStack> result = new HashMap<>();

        result.put(playerOrder[0], maxStack);
        result.put(playerOrder[1], secondStack);
        result.put(playerOrder[2], thirdStack);

        return result;
    }
}