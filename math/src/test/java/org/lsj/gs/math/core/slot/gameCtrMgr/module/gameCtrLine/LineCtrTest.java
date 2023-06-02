package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.LineCtrWinResult;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 線計算者測試
@ExtendWith(MockitoExtension.class)
class LineCtrTest {
    LineCtr lineCtr; // 線計算者

    // 初始化計算者
    @BeforeEach
    void initCtr() {
        this.lineCtr = new LineCtr(this.generateSymbolConfig(), this.generatePayTableConfig(), this.generateLinePositionIndexList());
    }

    // 給定混搭畫面，沒有倍數，當計算線結果，回傳正確陣列
    @Test
    void test_given_screenWithoutMultiplier_when_calculateLineWinCtrResultList_then_return_correctList() {
        // 1. when
        double playerCreditBase = 3.0;
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(1,0,3));
                add(List.of(1,0,3));
                add(List.of(1,0,3));
                add(List.of(1,1,0));
                add(List.of(5,1,4));
            }
        },
                null,
                new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));

        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. given
        List<LineCtrWinResult> actualResult = this.lineCtr.calculateLineCtrWinResultList(playerCreditBase, screenGtrResult, gameHitDirectionType);
        List<LineCtrWinResult> expectResult = new ArrayList<>(){
            {
                add(new LineCtrWinResult(1, 0, 4, 400, 1, 1200.0, new ArrayList<>(){
                    {
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(false, false, false));
                    }
                }, new ArrayList<>()));
                add(new LineCtrWinResult(2, 1, 5, 500, 1, 1500.0, new ArrayList<>(){
                    {
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                    }
                }, new ArrayList<>()));
                add(new LineCtrWinResult(3, 2, 4, 250, 1, 750.0, new ArrayList<>(){
                    {
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, false));
                    }
                }, new ArrayList<>()));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定混搭畫面，有全畫面倍數，當計算線結果，回傳正確陣列
    @Test
    void test_given_screenWithScreenMultiplier_when_calculateLineWinCtrResultList_then_return_correctList() {
        // 1. when
        double playerCreditBase = 3.0;
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(1,0,3));
                add(List.of(1,0,3));
                add(List.of(1,0,3));
                add(List.of(1,1,0));
                add(List.of(5,1,4));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));
        int screenMultiplier = 3;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. given
        List<LineCtrWinResult> actualResult = this.lineCtr.calculateLineCtrWinResultListWithScreenMultiplier(playerCreditBase, screenGtrResult, screenMultiplier, gameHitDirectionType);
        List<LineCtrWinResult> expectResult = new ArrayList<>(){
            {
                add(new LineCtrWinResult(1, 0, 4, 400, 3, 3600.0, new ArrayList<>(){
                    {
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(false, false, false));
                    }
                }, new ArrayList<>()));
                add(new LineCtrWinResult(2, 1, 5, 500, 3, 4500.0, new ArrayList<>(){
                    {
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                    }
                }, new ArrayList<>()));
                add(new LineCtrWinResult(3, 2, 4, 250, 3, 2250.0, new ArrayList<>(){
                    {
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, false));
                    }
                }, new ArrayList<>()));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定混搭畫面，有全畫面倍數，當計算線結果，回傳正確陣列
    @Test
    void test_given_screenWithSingleMultiplierAndMultiplierMatrix_when_calculateLineWinCtrResultList_then_return_correctList() {
        // 1. when
        double playerCreditBase = 3.0;
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(0,0,3));
                add(List.of(0,0,3));
                add(List.of(0,0,3));
                add(List.of(5,1,3));
                add(List.of(5,1,4));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));
        int screenMultiplier = 0;
        Map<Integer, Integer> symbolIdToMultiplierMap = new HashMap<>(){{put(4,3);}};
        List<List<Integer>> multiplierMatrix = new ArrayList<>(){
            {
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(3,0,0));
                add(List.of(4,0,0));
            }
        };
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. given
        List<LineCtrWinResult> actualResult = this.lineCtr.calculateLineCtrWinResultListWithMultiplier(playerCreditBase, screenGtrResult, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix, gameHitDirectionType);
        List<LineCtrWinResult> expectResult = new ArrayList<>(){
            {
                add(new LineCtrWinResult(6, 0, 5, 100, 12, 3600.0, new ArrayList<>(){
                    {
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                    }
                }, new ArrayList<>()));
                add(new LineCtrWinResult(2, 1, 5, 500, 1, 1500.0, new ArrayList<>(){
                    {
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                        add(List.of(false, true, false));
                    }
                }, new ArrayList<>()));
                add(new LineCtrWinResult(5, 2, 5, 200, 3, 1800.0, new ArrayList<>(){
                    {
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                        add(List.of(false, false, true));
                    }
                }, new ArrayList<>()));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // TODO
    private SymbolConfig generateSymbolConfig() {
        return new SymbolConfig(
                List.of(ConstMathSlot.SymbolAttribute.WILD_01,      // 0
                        ConstMathSlot.SymbolAttribute.WILD_02,      // 1
                        ConstMathSlot.SymbolAttribute.FREE_GAME_01, // 2
                        ConstMathSlot.SymbolAttribute.M1,           // 3
                        ConstMathSlot.SymbolAttribute.M2,           // 4
                        ConstMathSlot.SymbolAttribute.ACE),         // 5
                List.of(ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL)
        );
    }

    private PayTableConfig generatePayTableConfig() {
        return new PayTableConfig(
                List.of(
                        new PayCombo(List.of(0), List.of(0,0,100,500,1000)),
                        new PayCombo(List.of(1), List.of(0,0,80,400,800)),
                        new PayCombo(List.of(0,1), List.of(0,0,50,100,500)),

                        new PayCombo(List.of(3), List.of(0,0,30,250,500)),
                        new PayCombo(List.of(4), List.of(0,0,30,200,400)),
                        new PayCombo(List.of(3,4), List.of(0,0,20,100,200)),

                        new PayCombo(List.of(5), List.of(0,0,10,50,100))
                )
        );
    }

    private List<List<Integer>> generateLinePositionIndexList() {
        return new ArrayList<>(){
            {
                add(List.of(0,0,0,0,0));
                add(List.of(1,1,1,1,1));
                add(List.of(2,2,2,2,2));
            }
        };
    }
}