package org.lsj.gs.math.core.slot.state;

import org.lsj.gs.math.core.common.gameAdjust.GameAdjustSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.state.stateSlot.AbstractStateGameBeginSlot;
import org.lsj.gs.math.core.common.table.TableCommandSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 遊戲開始狀態
public class StateGameBeginSlot extends AbstractStateGameBeginSlot {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameBeginSlot.class);

    public StateGameBeginSlot(TableCommandSlot table, ILogicSlot logic, GameAdjustSlot gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
    }

    //* 狀態機相關 *//
    // 進入狀態
    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 遊戲開始日誌
        super.addLogGameBegin(super.logic);
    }

    // 離開狀態
    @Override
    public void onLeave() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
    }

    //* 計時器相關 *//
    // 狀態超時處理
    @Override
    public void onTimeout() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        super.iLeave();
    }
}
