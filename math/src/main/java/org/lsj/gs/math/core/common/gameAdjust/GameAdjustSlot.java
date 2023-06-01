package org.lsj.gs.math.core.common.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.ShuffleSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.GameFlowHlr;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 老虎機調控
public class GameAdjustSlot extends AbstractGameAdjust {
    public GameAdjustSlot(AlgorithmTypeCtr algorithmTypeCtr, ILogicSlot logic, GameFlowHlr gameFlowHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleSlot(algorithmTypeCtr, logic, gameFlowHlr, tableUtil),
                poolCtr,
                tableUtil);
    }
}
