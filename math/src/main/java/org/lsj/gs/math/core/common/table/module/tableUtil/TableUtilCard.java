package org.lsj.gs.math.core.common.table.module.tableUtil;

import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;
import org.lsj.websocket.IWebSocketUtil;

// 卡牌牌桌工具包
public class TableUtilCard extends AbstractTableUtil implements ITableUtilCard{
    private final ITableTimer tableTimer; // 牌桌定時器

    public TableUtilCard(IWebSocketUtil webSocketUtil, IRandomUtilMain randomUtil, ITableTimer tableTimer, IControlAlgorithmUtil controlAlgorithmUtil, String rngDataString) {
        super(webSocketUtil, randomUtil, controlAlgorithmUtil, rngDataString);
        this.tableTimer = tableTimer;
    }

    @Override
    public ITableTimer getTableTimer() {
        return tableTimer;
    }
}
