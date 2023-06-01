package com.lx.gs.math.games.hhdz_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjustBaiRen;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.hhdz_java.module.logic.LogicHhdzJava;

// 新紅黑大戰遊戲調控
public class GameAdjustHhdzJava extends AbstractGameAdjustBaiRen {
    public GameAdjustHhdzJava(AlgorithmTypeCtr algorithmTypeCtr, LogicHhdzJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleHhdzJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
