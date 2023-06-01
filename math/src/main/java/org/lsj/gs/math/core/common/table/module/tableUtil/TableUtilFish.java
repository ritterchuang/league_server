package org.lsj.gs.math.core.common.table.module.tableUtil;

import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.utils.StringUtil;
import org.lsj.websocket.IWebSocketUtil;

// 魚機牌桌工具包
public class TableUtilFish extends AbstractTableUtil implements ITableUtilFish{
    public TableUtilFish(IWebSocketUtil webSocketUtil, IRandomUtilMain randomUtil, IControlAlgorithmUtil controlAlgorithmUtil) {
        super(webSocketUtil, randomUtil, controlAlgorithmUtil, StringUtil.getInstance().getEmptyString());
    }
}
