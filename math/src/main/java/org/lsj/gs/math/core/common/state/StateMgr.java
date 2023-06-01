package org.lsj.gs.math.core.common.state;

import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 狀態管理器
public class StateMgr {
    private static final Logger LOG = LoggerFactory.getLogger(StateMgr.class);
    private final ITableBase table; // 牌桌
    private final Map<Integer, AbstractState> stateMap; // 狀態對應表
    private int currentStateId; // 當前狀態識別碼

    public StateMgr(ITableBase table){
        this.table = table;
        this.stateMap = new HashMap<>();
        this.currentStateId = -1;
    }

    // 註冊狀態
    public void registerState(AbstractState state) {
        this.stateMap.put(state.getStateId(), state);
    }

    // 切換狀態
    public void setState(int enterStateId){
        this.setState(enterStateId, 0.0);
    }

    // 切換狀態
    public void setState(int enterStateId, double leftTimeSec){
        // 1. 相同狀態不切換
        if(this.currentStateId == enterStateId){
            LOG.error(this.table.getTableLogTitle() + " the same state error. currentStateId: {}, enterStateId:{}",
                    this.currentStateId,
                    enterStateId);
            return;
        }

        // 2. 離開當前狀態
        AbstractState currentState = this.stateMap.get(this.currentStateId);
        if(!Objects.isNull(currentState)){
            currentState.onLeave();
        }

        // 3. 切換至指定狀態
        AbstractState enterState = this.stateMap.get(enterStateId);
        if(!Objects.isNull(enterState)){
            LOG.info(this.table.getTableLogTitle() + " currentStateId: {}, enterStateId:{}", this.currentStateId, enterStateId);
            this.table.sendUpdateState(enterStateId);
            this.currentStateId = enterStateId;
            enterState.onInit();
            enterState.setLeftTimeSec(leftTimeSec);
            enterState.onEnter();
        }
    }

    // 取得狀態對應表
    public Map<Integer, AbstractState> getStateMap() {
        return stateMap;
    }

    // 取得當前狀態索引
    public int getCurrentStateId() {
        return currentStateId;
    }

    // 重設
    public void reset() {}
}
