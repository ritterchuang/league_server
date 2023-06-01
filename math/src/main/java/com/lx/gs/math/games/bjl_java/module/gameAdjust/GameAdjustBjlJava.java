package com.lx.gs.math.games.bjl_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.bjl_java.module.logic.LogicBjlJava;

// 新百家樂 遊戲調控
public class GameAdjustBjlJava extends AbstractGameAdjustBaiRen {
    public GameAdjustBjlJava(AlgorithmTypeCtr algorithmTypeCtr, LogicBjlJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleBjlJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
