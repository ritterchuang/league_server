package org.lsj.gs.math.games.qznn_k4z_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.qznn_k4z_java.module.logic.LogicQznnK4zJava;

// 新看四張搶莊牛牛遊戲調控
public class GameAdjustQznnK4zJava extends AbstractGameAdjust {
    public GameAdjustQznnK4zJava(AlgorithmTypeCtr algorithmTypeCtr, LogicQznnK4zJava logicQZNN, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleQznnK4zJava(algorithmTypeCtr, logicQZNN, tableUtil),
                poolCtr,
                tableUtil);
    }
}
