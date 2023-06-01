package com.lx.gs.math.core.common.table.module.tableUtil;

import com.lx.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import com.lx.utils.StringUtil;
import com.lx.websocket.IWebSocketUtil;

// 魚機牌桌工具包
public class TableUtilFish extends AbstractTableUtil implements ITableUtilFish{
    public TableUtilFish(IWebSocketUtil webSocketUtil, IRandomUtilMain randomUtil, IControlAlgorithmUtil controlAlgorithmUtil) {
        super(webSocketUtil, randomUtil, controlAlgorithmUtil, StringUtil.getInstance().getEmptyString());
    }
}
