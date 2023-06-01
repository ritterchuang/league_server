package org.lsj.gs.math.core.common.table.module.tableUtil;

import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.utils.StringUtil;
import org.lsj.websocket.IWebSocketUtil;

// 老虎機牌桌工具包
public class TableUtilSlot extends AbstractTableUtil implements ITableUtilSlot{
    public TableUtilSlot(IWebSocketUtil webSocketUtil, IRandomUtilMain randomUtil, IControlAlgorithmUtil controlAlgorithmUtil) {
        super(webSocketUtil, randomUtil, controlAlgorithmUtil, StringUtil.getInstance().getEmptyString());
    }
}
