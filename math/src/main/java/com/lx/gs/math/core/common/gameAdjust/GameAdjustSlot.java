package com.lx.gs.math.core.common.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.ShuffleSlot;
import com.lx.gs.math.core.common.gameFlowHlr.GameFlowHlr;
import com.lx.gs.math.core.common.logic.logicSlot.ILogicSlot;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 老虎機調控
public class GameAdjustSlot extends AbstractGameAdjust {
    public GameAdjustSlot(AlgorithmTypeCtr algorithmTypeCtr, ILogicSlot logic, GameFlowHlr gameFlowHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleSlot(algorithmTypeCtr, logic, gameFlowHlr, tableUtil),
                poolCtr,
                tableUtil);
    }
}
