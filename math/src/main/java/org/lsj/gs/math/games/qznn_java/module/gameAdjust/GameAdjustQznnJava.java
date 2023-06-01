package org.lsj.gs.math.games.qznn_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.qznn_java.module.logic.LogicQznnJava;

// 搶莊牛牛遊戲調控
public class GameAdjustQznnJava extends AbstractGameAdjust {
    public GameAdjustQznnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicQznnJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleQznnJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
