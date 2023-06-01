package com.lx.gs.math.core.common.logic.logicSlot;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrSlot;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.ITableBase;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import com.lx.gs.math.core.slot.ConstMathSlot;

// 虎機邏輯工廠
public class LogicSlotFactory {
    public ILogicSlot create(
            ConstMathSlot.LogicType logicType,
            ITableBase table,
            TableFieldConfig tableFieldConfig,
            TableGameConfigSlot tableGameConfig,
            GamePlayerHlr gamePlayerHlr,
            PoolCtr poolCtr,
            GameBetLogResultCtrSlot gameBetLogResultCtrSlot,
            ITableUtilSlot tableUtilSlot) {
        switch(logicType){
            case CASCADE: return new LogicSlotCascade(
                    table,
                    tableFieldConfig,
                    tableGameConfig,
                    gamePlayerHlr,
                    poolCtr,
                    gameBetLogResultCtrSlot,
                    tableUtilSlot
            );
            default: return new LogicSlotNormal(
                    table,
                    tableFieldConfig,
                    tableGameConfig,
                    gamePlayerHlr,
                    poolCtr,
                    gameBetLogResultCtrSlot,
                    tableUtilSlot
            );
        }
    }
}
