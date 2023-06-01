package org.lsj.gs.math.core.slot.readyHandHlrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfigCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrMgrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultReadyHand01;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 聽牌處理器管理者測試
@ExtendWith(MockitoExtension.class)
class ReadyHandHlrMgrTest {
    ReadyHandHlrMgr readyHandHlrMgr; // 聽牌處理器管理者
    @Mock
    ReadyHandHlrMgrConfig mockConfig; // 設定檔
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定畫面不滿足聽牌，當計算聽牌結果時，預期給空
    @Test
    void test_given_screenWithoutReadyHand_when_getReadyHandHlrResultUnionCluster_then_return_emptyResult() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        Mockito.when(mockConfig.getConditionIdToReadyHandHlrConfigClusterMap()).thenReturn(
                new HashMap<>(){
                    {
                        put(0, new ReadyHandHlrConfigCluster(new ArrayList<>(){
                            {
                                add(new ReadyHandHlrConfig(null, symbolConfig, new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)));
                                add(new ReadyHandHlrConfig(null, symbolConfig, new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_03, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)));
                            }
                        }));
                    }
                }
        );
        this.readyHandHlrMgr = new ReadyHandHlrMgr(mockConfig, mockTableUtil);
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
            }
        };
        ProgressHlrResult progressHlrResult = new ProgressHlrResult(
                true,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1, 5, 5)));

        // 2. when
        ReadyHandHlrResultUnionCluster actualResult = this.readyHandHlrMgr.getReadyHandHlrResultUnionCluster(0, screenSymbol, progressHlrResult);
        ReadyHandHlrResultUnionCluster expectResult = new ReadyHandHlrResultUnionCluster();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定畫面滿足兩種聽牌，當計算聽牌結果時，預期給優先序高 01聽牌
    @Test
    void test_given_screenSatisfyTwoReadyHand_when_getReadyHandHlrResultUnionCluster_then_return_allReadyHand01() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        Mockito.when(mockConfig.getConditionIdToReadyHandHlrConfigClusterMap()).thenReturn(
                new HashMap<>(){
                    {
                        put(0, new ReadyHandHlrConfigCluster(new ArrayList<>(){
                            {
                                add(new ReadyHandHlrConfig(null, symbolConfig, new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)));
                                add(new ReadyHandHlrConfig(null, symbolConfig, new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_03, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)));
                            }
                        }));
                    }
                }
        );
        this.readyHandHlrMgr = new ReadyHandHlrMgr(mockConfig, mockTableUtil);
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(0,2,0));
                add(List.of(0,1,0));
                add(List.of(0,1,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
            }
        };
        ProgressHlrResult progressHlrResult = new ProgressHlrResult(
                true,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1, 5, 5)));

        // 2. when
        ReadyHandHlrResultUnionCluster actualResult = this.readyHandHlrMgr.getReadyHandHlrResultUnionCluster(0, screenSymbol, progressHlrResult);
        ReadyHandHlrResultUnionCluster expectResult = new ReadyHandHlrResultUnionCluster(
                new ArrayList<>(){
                    {
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.READY_HAND_01, new ReadyHandHlrExtendResultReadyHand01(List.of(1), 3)));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.READY_HAND_01, new ReadyHandHlrExtendResultReadyHand01(List.of(1), 4)));
                    }
                }
        );

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定畫面滿足兩種聽牌，當計算聽牌結果時，預期給優先序高 01聽牌
    @Test
    void test_given_screenSatisfyTwoReadyHand_when_getReadyHandHlrResultUnionCluster_then_return_readyHand01AndReadyHand03() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        Mockito.when(mockConfig.getConditionIdToReadyHandHlrConfigClusterMap()).thenReturn(
                new HashMap<>(){
                    {
                        put(0, new ReadyHandHlrConfigCluster(new ArrayList<>(){
                            {
                                add(new ReadyHandHlrConfig(null, symbolConfig, new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_03, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)));
                                add(new ReadyHandHlrConfig(null, symbolConfig, new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_01, ConstMathSlot.ReadyHandLimitType.NO_LIMIT)));
                            }
                        }));
                    }
                }
        );
        this.readyHandHlrMgr = new ReadyHandHlrMgr(mockConfig, mockTableUtil);
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(0,2,0));
                add(List.of(0,1,0));
                add(List.of(0,1,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
            }
        };
        ProgressHlrResult progressHlrResult = new ProgressHlrResult(
                true,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1, 5, 5)));

        // 2. when
        ReadyHandHlrResultUnionCluster actualResult = this.readyHandHlrMgr.getReadyHandHlrResultUnionCluster(0, screenSymbol, progressHlrResult);
        ReadyHandHlrResultUnionCluster expectResult = new ReadyHandHlrResultUnionCluster(
                new ArrayList<>(){
                    {
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.READY_HAND_01, new ReadyHandHlrExtendResultReadyHand01(List.of(1), 3)));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.READY_HAND_03, new ReadyHandHlrExtendResultReadyHand01(List.of(2), 4)));
                    }
                }
        );

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // TODO
    private SymbolConfig generateSymbolConfig() {
        return new SymbolConfig(
                List.of(
                        ConstMathSlot.SymbolAttribute.WILD_01,
                        ConstMathSlot.SymbolAttribute.FREE_GAME_01,
                        ConstMathSlot.SymbolAttribute.BONUS_GAME_01,
                        ConstMathSlot.SymbolAttribute.ACE,
                        ConstMathSlot.SymbolAttribute.M1,
                        ConstMathSlot.SymbolAttribute.FIVE
                ),
                List.of(
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BONUS_GAME_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL
                )
        );
    }
}