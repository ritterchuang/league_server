package com.lx.gs.math.core.common.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.ShuffleFish;
import com.lx.gs.math.core.common.logic.ILogicFish;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 捕魚遊戲調控
public class GameAdjustFish extends AbstractGameAdjust {
    public GameAdjustFish(AlgorithmTypeCtr algorithmTypeCtr, ILogicFish logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(  algorithmTypeCtr,
                new ShuffleFish(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
