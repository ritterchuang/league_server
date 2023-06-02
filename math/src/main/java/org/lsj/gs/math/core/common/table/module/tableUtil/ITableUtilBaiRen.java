package org.lsj.gs.math.core.common.table.module.tableUtil;

import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;

// 百人 遊戲桌工具包介面
public interface ITableUtilBaiRen extends ITableUtil{
    ITableTimer getTableTimer(); // 牌桌定時器
    void reset(); // 重設
}
