package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.enity.ReadyHandResultExtend01;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.enity.ReadyHandResultExtend03;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultReadyHand01;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultReadyHand03;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 聽牌包裝者測試
@ExtendWith(MockitoExtension.class)
class ReadyHandResultPgrTest {
    ReadyHandResultPgr readyHandResultPgr; // 聽牌結果包裝者

    // 給定空聽牌連集結果，當包裝資訊，包裝結果也為空
    @Test
    void test_given_emptyReadyHandHlrResultUnionCluster_when_packageReadyHandResultCluster_then_emptyResult() {
        // 1. given
        this.readyHandResultPgr = new ReadyHandResultPgr();
        ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster = new ReadyHandHlrResultUnionCluster();

        // 2. when
        ReadyHandResultCluster actualResult = this.readyHandResultPgr.packageReadyHandResultCluster(readyHandHlrResultUnionCluster);
        ReadyHandResultCluster expectResult = new ReadyHandResultCluster();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定空聽牌連集結果，當包裝資訊，包裝結果也為空
    @Test
    void test_given_readyHandHlrResultUnionClusterWithTwoType_when_packageReadyHandResultCluster_then_packageCorrect() {
        // 1. given
        this.readyHandResultPgr = new ReadyHandResultPgr();
        ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster = new ReadyHandHlrResultUnionCluster(
                new ArrayList<>(){
                    {
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.READY_HAND_01,
                                new ReadyHandHlrExtendResultReadyHand01(List.of(1), 3)));
                        add(new ReadyHandHlrResult(ConstMathSlot.ReadyHandType.READY_HAND_03,
                                new ReadyHandHlrExtendResultReadyHand03(List.of(2), 4)));
                    }
                }
        );

        // 2. when
        ReadyHandResultCluster actualResult = this.readyHandResultPgr.packageReadyHandResultCluster(readyHandHlrResultUnionCluster);
        ReadyHandResultCluster expectResult = new ReadyHandResultCluster(new ArrayList<>(){
            {
                add(new ReadyHandResult(ConstMathSlot.ReadyHandType.READY_HAND_01, new ReadyHandResultExtend01(List.of(1), 3)));
                add(new ReadyHandResult(ConstMathSlot.ReadyHandType.READY_HAND_03, new ReadyHandResultExtend03(List.of(2), 4)));
            }
        });

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}