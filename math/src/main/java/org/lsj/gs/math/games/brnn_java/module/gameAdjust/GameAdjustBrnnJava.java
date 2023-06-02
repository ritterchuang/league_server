package org.lsj.gs.math.games.brnn_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.brnn_java.module.logic.LogicBrnnJava;

// 新百人牛牛遊戲調控
public class GameAdjustBrnnJava extends AbstractGameAdjustBaiRen {
    public GameAdjustBrnnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicBrnnJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleBrnnJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
