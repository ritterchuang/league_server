package com.lx.gs.math.core.fish.state;

import com.lx.gs.math.core.common.gameAdjust.GameAdjustFish;
import com.lx.gs.math.core.common.logic.ILogicFish;
import com.lx.gs.math.core.common.state.stateFish.AbstractStateGameEndFish;
import com.lx.gs.math.core.common.table.TableCommandFish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 遊戲結束狀態
public class StateGameEndFish extends AbstractStateGameEndFish {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndFish.class);

    public StateGameEndFish(TableCommandFish table, ILogicFish logic, GameAdjustFish gameAdjust, int stateId) {
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
