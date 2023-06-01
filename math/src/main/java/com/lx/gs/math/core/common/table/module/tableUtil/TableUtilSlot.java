package com.lx.gs.math.core.common.table.module.tableUtil;

import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import com.lx.utils.StringUtil;
import com.lx.websocket.IWebSocketUtil;

// 老虎機牌桌工具包
public class TableUtilSlot extends AbstractTableUtil implements ITableUtilSlot{
    public TableUtilSlot(IWebSocketUtil webSocketUtil, IRandomUtilMain randomUtil, IControlAlgorithmUtil controlAlgorithmUtil) {
        super(webSocketUtil, randomUtil, controlAlgorithmUtil, StringUtil.getInstance().getEmptyString());
    }
}
