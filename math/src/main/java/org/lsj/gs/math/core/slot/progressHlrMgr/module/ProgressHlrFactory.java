package org.lsj.gs.math.core.slot.progressHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;

// 遊戲進度處理者工廠
public class ProgressHlrFactory {

    // 創建遊戲進度處理者
    public IProgressHlr create(ProgressConfig progressConfig, ITableUtil tableUtil) {
        switch (progressConfig.getProgressType()) {
            case ROUND: return new ProgressHlrRound(progressConfig, tableUtil);
            case TRIGGER_ROUND: return new ProgressHlrTriggerRound(progressConfig, tableUtil);
            default: return new ProgressHlrInvalid();
        }
    }
}
