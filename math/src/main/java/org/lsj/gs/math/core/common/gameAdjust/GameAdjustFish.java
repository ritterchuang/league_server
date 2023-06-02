package org.lsj.gs.math.core.common.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.ShuffleFish;
import org.lsj.gs.math.core.common.logic.ILogicFish;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 捕魚遊戲調控
public class GameAdjustFish extends AbstractGameAdjust {
    public GameAdjustFish(AlgorithmTypeCtr algorithmTypeCtr, ILogicFish logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(  algorithmTypeCtr,
                new ShuffleFish(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
