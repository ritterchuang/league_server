package org.lsj.gs.math.core.slot.state;

import org.lsj.gs.math.core.common.gameAdjust.GameAdjustSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.state.stateSlot.AbstractStateGameEndSlot;
import org.lsj.gs.math.core.common.table.TableCommandSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 遊戲結束狀態
public class StateGameEndSlot extends AbstractStateGameEndSlot {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndSlot.class);

    public StateGameEndSlot(TableCommandSlot table, ILogicSlot logic, GameAdjustSlot gameAdjust, int stateId) {
        super(table, logic, gameAdjust, stateId);
    }

    //* 狀態機相關 *//
    // 進入狀態
    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
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
