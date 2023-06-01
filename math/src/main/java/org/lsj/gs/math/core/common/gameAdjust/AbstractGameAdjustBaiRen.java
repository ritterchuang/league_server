package org.lsj.gs.math.core.common.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 抽象百人遊戲調控
public abstract class AbstractGameAdjustBaiRen extends AbstractGameAdjust {

    public AbstractGameAdjustBaiRen(AlgorithmTypeCtr algorithmTypeCtr, IShuffle shuffle, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr, shuffle, poolCtr, tableUtil);
    }
}