package org.lsj.gs.math.core.slot.readyHandHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;

// 聽牌處理器工廠
public class ReadyHandHlrFactory {

    // 創建聽牌處理者
    public IReadyHandHlr create(ReadyHandHlrConfig readyHandHlrConfig, ITableUtil tableUtil) {
        switch (readyHandHlrConfig.getReadyHandConfig().getReadyHandType()) {
            case READY_HAND_01: return new ReadyHandHlr_01(readyHandHlrConfig, tableUtil);
            case READY_HAND_02: return new ReadyHandHlr_02(readyHandHlrConfig, tableUtil);
            case READY_HAND_03: return new ReadyHandHlr_03(readyHandHlrConfig, tableUtil);
            case READY_HAND_04: return new ReadyHandHlr_04(readyHandHlrConfig, tableUtil);
            case READY_HAND_05: return new ReadyHandHlr_05(readyHandHlrConfig, tableUtil);
            case READY_HAND_06: return new ReadyHandHlr_06(readyHandHlrConfig, tableUtil);
            default: return new ReadyHandHlrInvalid();
        }
    }
}
