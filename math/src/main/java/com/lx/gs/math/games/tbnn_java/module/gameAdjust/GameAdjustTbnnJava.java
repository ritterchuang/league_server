package com.lx.gs.math.games.tbnn_java.module.gameAdjust;

import com.lx.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;

// 通比牛牛遊戲調控
public class GameAdjustTbnnJava extends AbstractGameAdjust {
    public GameAdjustTbnnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicTbnnJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleTbnnJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
