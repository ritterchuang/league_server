package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 路 計算類者測試
@ExtendWith(MockitoExtension.class)
class WayCtrTest {
    WayCtr wayCtr; // 路計算者

    @BeforeEach
    void initWayCtr() {
        this.wayCtr = new WayCtr(this.generateSymbolConfig(), this.generatePayTableConfig());
    }

    // 給定畫面，計算盤面正確[呼叫效率法]
    @Test
    void test_given_screenAndBet_when_calculateWayWinCtrResultList_then_correct() {
        // 1. given
        double playerCreditBase = 3.0;
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(0, 2, 8));
                add(List.of(1, 0, 2));
                add(List.of(2, 0, 0));
                add(List.of(2, 4, 6));
                add(List.of(5, 5, 7));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<WayCtrWinResult> actualResult = this.wayCtr.calculateWayCtrWinResultList(playerCreditBase, screenGtrResult, gameHitDirectionType)
                .stream()
                .sorted(Comparator.comparing(WayCtrWinResult::getPayComboId))
                .collect(Collectors.toList());
        List<WayCtrWinResult> expectResult = this.generateExpectResult();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給訂畫面，計算盤面正確[呼叫窮舉法]
    @Test
    void test_given_screenAndBet_when_calculateWayWinCtrResultListWithMultiplier_then_correct() {
        // 1. given
        double playerCreditBase = 3.0;
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(0, 2, 8));
                add(List.of(1, 0, 2));
                add(List.of(2, 0, 0));
                add(List.of(2, 4, 6));
                add(List.of(5, 5, 7));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));

        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<WayCtrWinResult> actualResult = this.wayCtr.calculateWayCtrWinResultListWithMultiplier(playerCreditBase, screenGtrResult, 0, new HashMap<>(), new ArrayList<>(), gameHitDirectionType)
                .stream()
                .sorted(Comparator.comparing(WayCtrWinResult::getPayComboId))
                .collect(Collectors.toList());
        List<WayCtrWinResult> expectResult = this.generateExpectResult();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定不會乘到的倍數陣列，確認窮舉與效率算法一致
    @Test
    void test_given_screenAndBetAndNoUseMultiplierMatrix_when_useEfficientAndExhaustedToCalculateResult_then_sameResult() {
        // 1. given
        double playerCreditBase = 3.0;
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(4, 5, 6));
                add(List.of(5, 6, 7));
                add(List.of(4, 5, 6));
                add(List.of(3, 8, 9));
                add(List.of(0, 1, 2));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;
        List<List<Integer>> multiplierMatrix = new ArrayList<>(){
            {
                List.of(0, 0, 0);
                List.of(0, 0, 0);
                List.of(0, 0, 0);
                List.of(0, 0, 0);
                List.of(3, 4, 5);
            }
        };

        // 2. when
        List<WayCtrWinResult> exhaustedResult = this.wayCtr.calculateWayCtrWinResultListWithMultiplier(playerCreditBase, screenGtrResult, 0, new HashMap<>(), multiplierMatrix, gameHitDirectionType)
                .stream()
                .sorted(Comparator.comparing(WayCtrWinResult::getPayComboId))
                .collect(Collectors.toList());
        List<WayCtrWinResult> efficientResult = this.wayCtr.calculateWayCtrWinResultList(playerCreditBase, screenGtrResult, gameHitDirectionType)
                .stream()
                .sorted(Comparator.comparing(WayCtrWinResult::getPayComboId))
                .collect(Collectors.toList());

        // 3. then
        String exhaustedString = JsonUtil.getInstance().writeValueAsStringWithoutException(exhaustedResult);
        String efficientString = JsonUtil.getInstance().writeValueAsStringWithoutException(efficientResult);
        assertEquals(exhaustedString, efficientString);
    }

    // 給定僅用全屏倍數，確認窮舉與效率算法一致
    @Test
    void test_given_screenAndBetAndOnlyScreenMultiplier_when_useEfficientAndExhaustedToCalculateResult_then_sameResult() {
        // 1. given
        double playerCreditBase = 3.0;
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(4, 5, 6));
                add(List.of(5, 6, 7));
                add(List.of(4, 5, 6));
                add(List.of(3, 8, 9));
                add(List.of(0, 1, 2));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;
        int screenMultiplier = 3;

        // 2. when
        List<WayCtrWinResult> exhaustedResult = this.wayCtr.calculateWayCtrWinResultListWithMultiplier(playerCreditBase, screenGtrResult, screenMultiplier, new HashMap<>(), new ArrayList<>(), gameHitDirectionType)
                .stream()
                .sorted(Comparator.comparing(WayCtrWinResult::getPayComboId))
                .collect(Collectors.toList());
        List<WayCtrWinResult> efficientResult = this.wayCtr.calculateWayCtrWinResultListWithScreenMultiplier(playerCreditBase, screenGtrResult, screenMultiplier, gameHitDirectionType)
                .stream()
                .sorted(Comparator.comparing(WayCtrWinResult::getPayComboId))
                .collect(Collectors.toList());

        // 3. then
        String exhaustedString = JsonUtil.getInstance().writeValueAsStringWithoutException(exhaustedResult);
        String efficientString = JsonUtil.getInstance().writeValueAsStringWithoutException(efficientResult);
        assertEquals(exhaustedString, efficientString);
    }

    // TODO 重複多次
    private SymbolConfig generateSymbolConfig() {
        return new SymbolConfig(
                List.of(
                        ConstMathSlot.SymbolAttribute.WILD_01,
                        ConstMathSlot.SymbolAttribute.WILD_02,
                        ConstMathSlot.SymbolAttribute.WILD_03,
                        ConstMathSlot.SymbolAttribute.FREE_GAME_03,
                        ConstMathSlot.SymbolAttribute.M1,
                        ConstMathSlot.SymbolAttribute.M2,
                        ConstMathSlot.SymbolAttribute.M3,
                        ConstMathSlot.SymbolAttribute.KING,
                        ConstMathSlot.SymbolAttribute.QUEEN,
                        ConstMathSlot.SymbolAttribute.JOKER
                ),
                List.of(
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL
                ));
    }

    private PayTableConfig generatePayTableConfig() {
        return new PayTableConfig(
                List.of(
                        // single wild
                        new PayCombo(List.of(0), List.of(0, 0, 100, 500, 1000)),    // 0
                        new PayCombo(List.of(1), List.of(0, 0, 100, 400, 1000)),    // 1
                        new PayCombo(List.of(2), List.of(0, 0, 100, 300, 1000)),    // 2
                        // mix Wild
                        new PayCombo(List.of(0, 1), List.of(0, 0, 100, 200, 500)),  // 3
                        new PayCombo(List.of(0, 2, 1), List.of(0, 0, 30, 50, 400)), // 4
                        new PayCombo(List.of(0, 2), List.of(0, 0, 100, 200, 500)),  // 5
                        new PayCombo(List.of(1, 2), List.of(0, 0, 100, 200, 500)),  // 6

                        // single Symbol
                        new PayCombo(List.of(4), List.of(0, 0, 100, 200, 500)),     // 7
                        new PayCombo(List.of(5), List.of(0, 0, 100, 200, 500)),     // 8
                        new PayCombo(List.of(6), List.of(0, 0, 50, 100, 400)),      // 9
                        new PayCombo(List.of(7), List.of(0, 0, 50, 100, 400)),      // 10
                        new PayCombo(List.of(8), List.of(0, 0, 50, 100, 400)),      // 11
                        new PayCombo(List.of(9), List.of(0, 0, 50, 100, 400)),      // 12
                        // mix Symbol
                        new PayCombo(List.of(4, 5, 7), List.of(0, 0, 10, 20, 50)),  // 13
                        new PayCombo(List.of(4, 5, 6, 7), List.of(0, 0, 0, 20, 50)),// 14
                        new PayCombo(List.of(5, 6), List.of(0, 0, 50, 100, 400)),   // 15
                        new PayCombo(List.of(4, 5), List.of(0, 0, 50, 100, 400)),   // 16
                        new PayCombo(List.of(4, 6), List.of(0, 0, 100, 200, 500)),  // 17
                        new PayCombo(List.of(5, 7), List.of(0, 0, 100, 200, 500)),  // 18
                        new PayCombo(List.of(4, 7), List.of(0, 0, 100, 200, 500)),  // 19
                        new PayCombo(List.of(6, 7), List.of(0, 0, 100, 200, 500)),  // 20
                        new PayCombo(List.of(4, 5, 6), List.of(0, 0, 10, 200, 500)),// 21
                        new PayCombo(List.of(4, 6, 7), List.of(0, 0, 10, 200, 500)),// 22
                        new PayCombo(List.of(5, 6, 7), List.of(0, 0, 10, 200, 500)) // 23
                )
        );
    }

    // 產出預期結果
    private List<WayCtrWinResult> generateExpectResult() {
        return new ArrayList<>(){
            {
                add(new WayCtrWinResult(0, 3, 2, 100, 600.0,
                        List.of(
                                List.of(true, false, false),
                                List.of(false, true, false),
                                List.of(false, true, true),
                                List.of(false, false, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 0
                add(new WayCtrWinResult(2, 4, 1, 300, 900.0,
                        List.of(
                                List.of(false, true, false),
                                List.of(false, false, true),
                                List.of(true, false, false),
                                List.of(true, false, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 2
                add(new WayCtrWinResult(3, 3, 2, 100, 600.0,
                        List.of(
                                List.of(true, false, false),
                                List.of(true, true, false),
                                List.of(false, true, true),
                                List.of(false, false, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 0,1
                add(new WayCtrWinResult(4, 4, 5, 50, 750.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, false, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 0,2,1
                add(new WayCtrWinResult(5, 4, 11, 200, 6600.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(false, true, true),
                                List.of(true, true, true),
                                List.of(true, false, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 0,2
                add(new WayCtrWinResult(6, 4, 1, 200, 600.0,
                        List.of(
                                List.of(false, true, false),
                                List.of(true, false, true),
                                List.of(true, false, false),
                                List.of(true, false, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 1,2
                add(new WayCtrWinResult(7, 4,  18, 200, 10800.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, true, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 4
                add(new WayCtrWinResult(8, 5,  36, 500, 54000.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, false, false),
                                List.of(true, true, false)
                        ), new ArrayList<>())); // symbolId: 5
                add(new WayCtrWinResult(9, 4,  18, 100, 5400.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, false, true),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 6
                add(new WayCtrWinResult(10, 5,  18, 400, 21600.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, false, false),
                                List.of(false, false, true)
                        ), new ArrayList<>())); // symbolId: 7
                add(new WayCtrWinResult(11, 4,  9, 100, 2700.0,
                        List.of(
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, false, false),
                                List.of(false, false, false)
                        ), new ArrayList<>())); // symbolId: 8
                add(new WayCtrWinResult(15, 5,  36, 400, 43200.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, false, true),
                                List.of(true, true, false)
                        ), new ArrayList<>())); // symbolId: 5,6
                add(new WayCtrWinResult(16, 5, 36, 400, 43200.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, true, false),
                                List.of(true, true, false)
                        ), new ArrayList<>())); // symbolId: 4,5
                add(new WayCtrWinResult(19, 5, 18, 500, 27000.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, true, false),
                                List.of(false, false, true)
                        ), new ArrayList<>())); // symbolId: 4,7
                add(new WayCtrWinResult(20, 5, 18, 500, 27000.0,
                        List.of(
                                List.of(true, true, false),
                                List.of(true, true, true),
                                List.of(true, true, true),
                                List.of(true, false, true),
                                List.of(false, false, true)
                        ), new ArrayList<>())); // symbolId: 6,7

            }
        };
    }
}