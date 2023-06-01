package com.lx.gs.math.games.mybjl_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.mybjl_java.module.logic.LogicMybjlJava;

// 新免傭百家樂 遊戲調控
public class GameAdjustMybjlJava extends AbstractGameAdjustBaiRen {
    public GameAdjustMybjlJava(AlgorithmTypeCtr algorithmTypeCtr, LogicMybjlJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleMybjlJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
