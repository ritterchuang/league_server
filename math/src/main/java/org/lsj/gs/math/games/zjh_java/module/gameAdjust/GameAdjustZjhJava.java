package org.lsj.gs.math.games.zjh_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.zjh_java.module.logic.LogicZjhJava;

// 炸金花遊戲調控
public class GameAdjustZjhJava extends AbstractGameAdjust {
    public GameAdjustZjhJava(AlgorithmTypeCtr algorithmTypeCtr, LogicZjhJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleZjhJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
