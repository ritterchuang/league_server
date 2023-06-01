package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.enity.*;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.*;

import java.util.ArrayList;
import java.util.List;

// 聽牌結果包裝者
public class ReadyHandResultPgr {

    // 包裝聽牌結果
    public ReadyHandResultCluster packageReadyHandResultCluster(ReadyHandHlrResultUnionCluster readyHandHlrResultUnionCluster) {
        // 1. 計算客端聽牌資訊
        List<ReadyHandResult> readyHandResultList = this.calculateReadyHandResultList(readyHandHlrResultUnionCluster.getReadyHandHlrResultList());

        // 2. 封裝回傳
        return new ReadyHandResultCluster(readyHandResultList);
    }

    // 計算客端聽牌列表
    private List<ReadyHandResult> calculateReadyHandResultList(List<ReadyHandHlrResult> readyHandHlrResultList) {
        List<ReadyHandResult> result = new ArrayList<>();

        readyHandHlrResultList.forEach(readyHandHlrResult -> result.add(this.calculateReadyHandResult(readyHandHlrResult)));

        return result;
    }

    // 計算客端聽牌結果
    private ReadyHandResult calculateReadyHandResult(ReadyHandHlrResult readyHandHlrResult) {
        // 1. 取得 type
        ConstMathSlot.ReadyHandType readyHandType = readyHandHlrResult.getReadyHandType();

        // 2. 取得對應額外結果
        ReadyHandResultExtend extendResult = this.calculateReadyHandExtendResult(readyHandType, readyHandHlrResult.getReadyHandHlrExtendResult());

        // 3. 回傳
        return new ReadyHandResult(readyHandType, extendResult);
    }

    // 取得客端聽牌額外資訊
    private ReadyHandResultExtend calculateReadyHandExtendResult(ConstMathSlot.ReadyHandType readyHandType, ReadyHandHlrExtendResult readyHandHlrExtendResult) {
        switch (readyHandType) {
            case READY_HAND_01: return new ReadyHandResultExtend01(((ReadyHandHlrExtendResultReadyHand01)readyHandHlrExtendResult).getTargetSymbolIdList(), ((ReadyHandHlrExtendResultReadyHand01)readyHandHlrExtendResult).getColumnIndex());
            case READY_HAND_02: return new ReadyHandResultExtend02(((ReadyHandHlrExtendResultReadyHand02)readyHandHlrExtendResult).getTargetSymbolIdList(), ((ReadyHandHlrExtendResultReadyHand02)readyHandHlrExtendResult).getColumnIndex());
            case READY_HAND_03: return new ReadyHandResultExtend03(((ReadyHandHlrExtendResultReadyHand03)readyHandHlrExtendResult).getTargetSymbolIdList(), ((ReadyHandHlrExtendResultReadyHand03)readyHandHlrExtendResult).getColumnIndex());
            case READY_HAND_04: return new ReadyHandResultExtend04(((ReadyHandHlrExtendResultReadyHand04)readyHandHlrExtendResult).getTargetSymbolIdList(), ((ReadyHandHlrExtendResultReadyHand04)readyHandHlrExtendResult).getColumnIndex());
            case READY_HAND_05: return new ReadyHandResultExtend05(((ReadyHandHlrExtendResultReadyHand05)readyHandHlrExtendResult).getTargetSymbolIdList(), ((ReadyHandHlrExtendResultReadyHand05)readyHandHlrExtendResult).getColumnIndex());
            case READY_HAND_06: return new ReadyHandResultExtend06(((ReadyHandHlrExtendResultReadyHand06)readyHandHlrExtendResult).getTargetSymbolIdList(), ((ReadyHandHlrExtendResultReadyHand06)readyHandHlrExtendResult).getColumnIndex());
            default: return new ReadyHandResultExtendDefault();
        }
    }
}
