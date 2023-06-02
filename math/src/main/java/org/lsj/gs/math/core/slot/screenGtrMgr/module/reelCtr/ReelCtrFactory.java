package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ReelCtrConfig;

// 輪帶結果計算者工廠
public class ReelCtrFactory {

    // 創建輪帶表結果計算者 TODO Default
    public IReelCtr create(ReelCtrConfig reelCtrConfig, ITableUtil tableUtil) {
        // 1. 取得輪帶表類型
        ConstMathSlot.ReelType reelType = reelCtrConfig.getReelConfig().getReelType();

        // 2. 回傳
        switch (reelType) {
            case NON_REEL_WEIGHT: return new ReelCtrNonReelWeight(reelCtrConfig, tableUtil);
            default: return new ReelCtrReelDependent(reelCtrConfig, tableUtil);
        }
    }
}
