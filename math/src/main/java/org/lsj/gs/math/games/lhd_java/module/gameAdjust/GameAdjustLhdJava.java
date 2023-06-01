package org.lsj.gs.math.games.lhd_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.lhd_java.module.logic.LogicLhdJava;

// 新龍虎鬥遊戲調控
public class GameAdjustLhdJava extends AbstractGameAdjustBaiRen {
    public GameAdjustLhdJava(AlgorithmTypeCtr algorithmTypeCtr, LogicLhdJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleLhdJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
