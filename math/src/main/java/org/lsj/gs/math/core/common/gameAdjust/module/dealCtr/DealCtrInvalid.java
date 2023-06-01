package org.lsj.gs.math.core.common.gameAdjust.module.dealCtr;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 無效發牌器
public class DealCtrInvalid extends AbstractDealCtr {
    public DealCtrInvalid(IShuffle shuffle, ITableUtil tableUtil) {
        super(shuffle, tableUtil);
    }

    // 開牌
    @Override
    public GameAdjustResult deal(GameAdjustParameter gameAdjustParameter) {
        // 防呆使用預設
        return super.dealGameResult(gameAdjustParameter);
    }
}
