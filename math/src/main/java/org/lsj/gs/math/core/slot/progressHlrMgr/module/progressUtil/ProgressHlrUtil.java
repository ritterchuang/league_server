package org.lsj.gs.math.core.slot.progressHlrMgr.module.progressUtil;

import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.AbstractProgressHlrTotalInput;

// 進度處理者工具包
public class ProgressHlrUtil {

    // 計算當前進度
    public int calculateCurrentProgress(AbstractProgressHlrTotalInput progressHlrTotalInput) {
        return progressHlrTotalInput.getProcessIndex() + 1;
    }

    // 計算最大場次標誌
    public boolean calculateMaxProgress(int totalProcess, int maxProcess){
        return (totalProcess >= maxProcess);
    }
}
