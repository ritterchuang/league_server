package org.lsj.gs.math.core.slot.progressHlrMgr.module.progressUtil;

import org.lsj.gs.math.core.slot.progressHlrMgr.enity.input.AbstractProgressHlrTotalInput;

public interface IProgressHlrUtil {
    int calculateCurrentProgress(AbstractProgressHlrTotalInput progressHlrTotalInput); // 計算總場次
    int calculateTotalProgress(AbstractProgressHlrTotalInput progressHlrTotalInput); // 計算新增場次
    int calculateAddProgress(int totalProcess, AbstractProgressHlrTotalInput progressHlrTotalInput); // 計算新增場次
    boolean calculateMaxProgress(int totalProcess); // 計算最大場次標誌
}