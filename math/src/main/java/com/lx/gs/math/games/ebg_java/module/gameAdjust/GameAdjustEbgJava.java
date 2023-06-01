package com.lx.gs.math.games.ebg_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.ebg_java.module.logic.LogicEbgJava;

// 搶莊二八槓遊戲調控
public class GameAdjustEbgJava extends AbstractGameAdjust {
    public GameAdjustEbgJava(AlgorithmTypeCtr algorithmTypeCtr, LogicEbgJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleEbgJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
