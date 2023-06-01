package com.lx.gs.math.games.cjnn_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.cjnn_java.module.logic.LogicCjnnJava;

// 新超級牛牛遊戲調控
public class GameAdjustCjnnJava extends AbstractGameAdjustBaiRen {
    public GameAdjustCjnnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicCjnnJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleCjnnJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
