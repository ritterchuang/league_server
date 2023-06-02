package org.lsj.gs.math.games.qznn_ksz_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.qznn_ksz_java.module.logic.LogicQznnKszJava;

// 看三張搶莊牛牛遊戲調控
public class GameAdjustQznnKszJava extends AbstractGameAdjust {
    public GameAdjustQznnKszJava(AlgorithmTypeCtr algorithmTypeCtr, LogicQznnKszJava logicQZNN, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleQznnKszJava(algorithmTypeCtr, logicQZNN, tableUtil),
                poolCtr,
                tableUtil);
    }
}
