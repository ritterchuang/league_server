package com.lx.gs.math.core.common.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象百人遊戲調控
public abstract class AbstractGameAdjustBaiRen extends AbstractGameAdjust {

    public AbstractGameAdjustBaiRen(AlgorithmTypeCtr algorithmTypeCtr, IShuffle shuffle, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr, shuffle, poolCtr, tableUtil);
    }
}