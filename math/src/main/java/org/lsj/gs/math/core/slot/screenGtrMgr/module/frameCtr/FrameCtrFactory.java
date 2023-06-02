package org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.FrameCtrConfig;

// 畫面計算者工廠
public class FrameCtrFactory {

    // 創建畫面計算者 TODO Default
    public IFrameCtr create(FrameCtrConfig frameCtrConfig, ITableUtil tableUtil) {
        // 1. 取得畫面類型
        ConstMathSlot.FrameType frameType = frameCtrConfig.getFrameConfig().getFrameType();

        // 2. 回傳
        switch (frameType) {
            default: return new FrameCtrFix(frameCtrConfig, tableUtil);
        }
    }
}
