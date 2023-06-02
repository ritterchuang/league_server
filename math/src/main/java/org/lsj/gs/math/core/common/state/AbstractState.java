package org.lsj.gs.math.core.common.state;

import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 抽象狀態
public abstract class AbstractState implements IState {
    protected ITableBase table; // 牌桌
    protected StateInfoHlr stateInfoHlr; // 狀態資訊處理器
    private static final Logger LOG = LoggerFactory.getLogger(AbstractState.class);

    public AbstractState(ITableBase table, int stateId){
        this.table = table;
        this.stateInfoHlr = new StateInfoHlr(stateId);
    }

    // 狀態初始化
    @Override
    public void onInit(){}

    // 進入狀態
    @Override
    public abstract void onEnter();

    // 離開狀態
    @Override
    public abstract void onLeave();

    // 狀態超時處理
    @Override
    public abstract void onTimeout();

    // 主動離開狀態
    @Override
    public void iLeave(){
        this.table.switchState(this.stateInfoHlr.getStateId());
    }

    // 取得狀態索引
    @Override
    public int getStateId() {
        return this.stateInfoHlr.getStateId();
    }

    // 取得剩餘秒數
    @Override
    public double getLeftTimeSec(){
        return this.stateInfoHlr.getLeftTimeSec();
    }

    // 設定剩餘秒數
    @Override
    public void setLeftTimeSec(double leftTimeSec){
        this.stateInfoHlr.setLeftTimeSec(leftTimeSec);
    }

    // 新增遊戲日誌
    @Override
    public void addLogGameBegin(ILogic logic){
        // 印出玩家資訊
        logic.getAllGamePlayerMap().forEach((chairIndex, gamePlayer) -> LOG.info(
                this.table.getTableLogTitle() + " chairIndex: {}, isRobot: {}, uid: {}, money: {}",
                chairIndex,
                gamePlayer.isRobot(),
                gamePlayer.getUid(),
                gamePlayer.getBeginMoney()));

        // 印出水池資訊
        LOG.info(this.table.getTableLogTitle() + " personAdjustType: {}, adjustInfo: {}, adjustPersonControlLog : {}, originalPersonControlLog: {}, thirdAccountID: {}, agentId: {}, ip : {}",
                this.table.getPoolCtr().getPersonAdjustType(),
                this.table.getPoolCtr().getAdjustInfoString(),
                this.table.getPoolCtr().getPersonControlLogString(),
                this.table.getUser().getPersonControlLog(),
                this.table.getUser().getThirdAccountId(),
                this.table.getUser().getBaseAgencyId(),
                this.table.getUser().getIp());
    }
}
