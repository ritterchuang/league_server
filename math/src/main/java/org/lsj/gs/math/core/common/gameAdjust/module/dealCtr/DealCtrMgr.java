package org.lsj.gs.math.core.common.gameAdjust.module.dealCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 開牌器管理者
public class DealCtrMgr {
    private final IShuffle shuffle; // 發牌器工具包
    private final ITableUtil tableUtil; // 牌桌工具包

    public DealCtrMgr(IShuffle shuffle, ITableUtil tableUtil) {
        this.shuffle = shuffle;
        this.tableUtil = tableUtil;
    }

    // 建立開牌器
    public IDealCtr createDealCtr(ConstMathCommon.DealType dealType) {
        switch (dealType){
            case NONE: return new DealCtrNone(this.shuffle, this.tableUtil);
            case GRADIENT: return new DealCtrGradient(this.shuffle, this.tableUtil);
            default: return new DealCtrInvalid(this.shuffle, this.tableUtil);
        }
    }

    // 重設
    public void reset() {}
}
