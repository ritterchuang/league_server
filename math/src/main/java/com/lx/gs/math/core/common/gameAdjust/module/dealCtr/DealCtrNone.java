package com.lx.gs.math.core.common.gameAdjust.module.dealCtr;

import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 自然發牌器
public class DealCtrNone extends AbstractDealCtr implements IDealCtr {
    public DealCtrNone(IShuffle shuffle, ITableUtil tableUtil) {
        super(shuffle, tableUtil);
    }

    // 開牌
    @Override
    public GameAdjustResult deal(GameAdjustParameter gameAdjustParameter) {
        return super.dealGameResult(gameAdjustParameter);
    }
}
