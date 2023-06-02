package org.lsj.gs.math.core.slot.readyHandHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultDefault;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultReadyHand01;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 聽牌處理者03 測試
@ExtendWith(MockitoExtension.class)
class ReadyHandHlr_03Test {
    IReadyHandHlr readyHandHlr; // 聽牌處理者
    @Mock
    ReadyHandHlrConfig mockConfig; // 設定檔
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定最大觸發，當計算聽牌結果，預期回傳空
    @Test
    void test_given_maxTrigger_when_calculateReadyHandHlrResultCluster_then_return_emptyResult() {
        // 1. given
        Mockito.when(mockConfig.getReadyHandConfig()).
                thenReturn(new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_03, ConstMathSlot.ReadyHandLimitType.MAX_TRIGGER_LIMIT));
        this.readyHandHlr = new ReadyHandHlr_03(mockConfig, mockTableUtil);
        ProgressHlrResult progressHlrResult = new ProgressHlrResult(
                true,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1, 5, 5)));
        List<List<Integer>> screenSymbol = new ArrayList<>();

        // 2. when
        Optional<ReadyHandHlrResultCluster> actualResult = this.readyHandHlr.calculateReadyHandHlrResultCluster(screenSymbol, progressHlrResult);

        // 3. then
        assertTrue(actualResult.isEmpty());
    }

    // 給定無免費標誌，當計算聽牌結果，回傳值全為 default
    @Test
    void test_given_screenSymbolWithoutBonusGameSymbol_when_calculateReadyHandHlrResultCluster_then_return_allSpecialFeatureHlrResultDefault() {
        // 1. given
        Mockito.when(mockConfig.getReadyHandConfig()).
                thenReturn(new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_03, ConstMathSlot.ReadyHandLimitType.NO_LIMIT));
        Mockito.when(mockConfig.getSymbolConfig()).thenReturn(this.generateSymbolConfig());
        this.readyHandHlr = new ReadyHandHlr_03(mockConfig, mockTableUtil);
        ProgressHlrResult progressHlrResult = new ProgressHlrResult(
                true,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1, 5, 5)));
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
            }
        };

        // 2. when
        Optional<ReadyHandHlrResultCluster> actualResult = this.readyHandHlr.calculateReadyHandHlrResultCluster(screenSymbol, progressHlrResult);
        ReadyHandHlrResultCluster expectResult = new ReadyHandHlrResultCluster(
                new ArrayList<>(){
                    {
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                    }
                }
        );

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult.get());
        assertEquals(expectString, actualString);
    }

    // 給定1軸免費標誌，當計算聽牌結果，回傳值5軸有值
    @Test
    void test_given_screenSymbolWithR1BonusGameSymbol_when_calculateReadyHandHlrResultCluster_then_return_R5NotDefault() {
        // 1. given
        Mockito.when(mockConfig.getReadyHandConfig()).
                thenReturn(new ReadyHandConfig(ConstMathSlot.ReadyHandType.READY_HAND_03, ConstMathSlot.ReadyHandLimitType.NO_LIMIT));
        Mockito.when(mockConfig.getSymbolConfig()).thenReturn(this.generateSymbolConfig());
        this.readyHandHlr = new ReadyHandHlr_03(mockConfig, mockTableUtil);
        ProgressHlrResult progressHlrResult = new ProgressHlrResult(
                true,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1, 5, 5)));
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
                add(List.of(0,0,0));
            }
        };

        // 2. when
        Optional<ReadyHandHlrResultCluster> actualResult = this.readyHandHlr.calculateReadyHandHlrResultCluster(screenSymbol, progressHlrResult);
        ReadyHandHlrResultCluster expectResult = new ReadyHandHlrResultCluster(
                new ArrayList<>(){
                    {
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.DEFAULT, new ReadyHandHlrExtendResultDefault()));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.READY_HAND_03, new ReadyHandHlrExtendResultReadyHand01(List.of(1), 4)));
                    }
                }
        );

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult.get());
        assertEquals(expectString, actualString);
    }

    // TODO
    private SymbolConfig generateSymbolConfig() {
        return new SymbolConfig(
                List.of(
                        ConstMathSlot.SymbolAttribute.WILD_01,
                        ConstMathSlot.SymbolAttribute.BONUS_GAME_01,
                        ConstMathSlot.SymbolAttribute.ACE,
                        ConstMathSlot.SymbolAttribute.M1,
                        ConstMathSlot.SymbolAttribute.FIVE
                ),
                List.of(
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BONUS_GAME_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL
                )
        );
    }
}