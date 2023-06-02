package org.lsj.gs.math.core.common.table.module.tableProtocol;

// 牌桌傳輸介面
public interface ITableProtocol {
    void sendUpdateState(int enterStateIndex); // 傳送狀態更新訊息
}
