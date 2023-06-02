package org.lsj.gs.math.games.qzpj_java.module.gameAdjust;

import org.lsj.gs.math.core.common.gameAdjust.AbstractGameAdjust;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.qzpj_java.module.logic.LogicQzpjJava;

// 搶莊牌九遊戲調控
public class GameAdjustQzpjJava extends AbstractGameAdjust {
    public GameAdjustQzpjJava(AlgorithmTypeCtr algorithmTypeCtr, LogicQzpjJava logic, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(algorithmTypeCtr,
                new ShuffleQzpjJava(algorithmTypeCtr, logic, tableUtil),
                poolCtr,
                tableUtil);
    }
}
