package org.lsj.gs.math.games.cjnn_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.cjnn_java.module.logic.LogicCjnnJava;

// 新超級牛牛遊戲調控
public class GameAdjustCjnnJava extends AbstractGameAdjustBaiRen {
    public GameAdjustCjnnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicCjnnJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleCjnnJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
