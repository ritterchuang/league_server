package org.lsj.gs.math.core.common.state;

import org.lsj.gs.math.core.common.logic.ILogic;

// 狀態介面
public interface IState {
    // 狀態初始化
    void onInit();

    // 進入狀態
    void onEnter();

    // 離開狀態
    void onLeave();

    // 狀態超時處理
    void onTimeout();

    // 主動離開狀態
    void iLeave();

    // 取得狀態索引
    int getStateId();

    // 取得剩餘秒數
    double getLeftTimeSec();

    // 設定剩餘秒數
    void setLeftTimeSec(double leftTimeSec);

    // 新增遊戲開始日誌
    void addLogGameBegin(ILogic logic);
}
