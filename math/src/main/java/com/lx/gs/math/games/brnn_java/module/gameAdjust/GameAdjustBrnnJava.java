package com.lx.gs.math.games.brnn_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.brnn_java.module.logic.LogicBrnnJava;

// 新百人牛牛遊戲調控
public class GameAdjustBrnnJava extends AbstractGameAdjustBaiRen {
    public GameAdjustBrnnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicBrnnJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleBrnnJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
