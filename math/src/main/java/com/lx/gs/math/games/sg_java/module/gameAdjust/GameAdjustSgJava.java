package com.lx.gs.math.games.sg_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.sg_java.module.logic.LogicSgJava;

// 新三公遊戲調控
public class GameAdjustSgJava extends AbstractGameAdjust {
    public GameAdjustSgJava(AlgorithmTypeCtr algorithmTypeCtr, LogicSgJava logicSgJava, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleSgJava(algorithmTypeCtr, logicSgJava, tableUtil),
                poolCtr,
                tableUtil);
    }
}
