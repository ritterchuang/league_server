package com.lx.gs.math.core.common.table.module.tableUtil;

import com.lx.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;

// 棋牌 遊戲桌工具包介面
public interface ITableUtilCard extends ITableUtil{
    ITableTimer getTableTimer(); // 牌桌定時器
}
