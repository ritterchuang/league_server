package com.lx.gs.math.games.zjh_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.zjh_java.module.logic.LogicZjhJava;

// 炸金花遊戲調控
public class GameAdjustZjhJava extends AbstractGameAdjust {
    public GameAdjustZjhJava(AlgorithmTypeCtr algorithmTypeCtr, LogicZjhJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleZjhJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
