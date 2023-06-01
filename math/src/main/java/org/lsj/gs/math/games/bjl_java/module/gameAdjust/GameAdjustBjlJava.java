package org.lsj.gs.math.games.bjl_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.bjl_java.module.logic.LogicBjlJava;

// 新百家樂 遊戲調控
public class GameAdjustBjlJava extends AbstractGameAdjustBaiRen {
    public GameAdjustBjlJava(AlgorithmTypeCtr algorithmTypeCtr, LogicBjlJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleBjlJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
