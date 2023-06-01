package org.lsj.gs.math.games.mybjl_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.mybjl_java.module.logic.LogicMybjlJava;

// 新免傭百家樂 遊戲調控
public class GameAdjustMybjlJava extends AbstractGameAdjustBaiRen {
    public GameAdjustMybjlJava(AlgorithmTypeCtr algorithmTypeCtr, LogicMybjlJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleMybjlJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
