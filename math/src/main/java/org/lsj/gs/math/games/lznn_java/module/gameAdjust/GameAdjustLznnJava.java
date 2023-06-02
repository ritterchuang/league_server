package org.lsj.gs.math.games.lznn_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.lznn_java.module.logic.LogicLzNnJava;

// 賴子牛牛遊戲調控
public class GameAdjustLznnJava extends AbstractGameAdjust {
    public GameAdjustLznnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicLzNnJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleLznnJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
