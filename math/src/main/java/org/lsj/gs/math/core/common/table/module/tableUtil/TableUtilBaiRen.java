package org.lsj.gs.math.core.common.table.module.tableUtil;

import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;
import org.lsj.websocket.IWebSocketUtil;

// 百人牌桌工具包
public class TableUtilBaiRen extends AbstractTableUtil implements ITableUtilBaiRen{
    private final ITableTimer tableTimer; // 牌桌定時器

    public TableUtilBaiRen(IWebSocketUtil webSocketUtil, IRandomUtilMain randomUtil, ITableTimer tableTimer, IControlAlgorithmUtil controlAlgorithmUtil, String rngDataString) {
        super(webSocketUtil, randomUtil, controlAlgorithmUtil, rngDataString);
        this.tableTimer = tableTimer;
    }

    @Override
    public ITableTimer getTableTimer() {
        return tableTimer;
    }

    @Override
    public void reset(){
        super.reset();
        this.tableTimer.cancel();
    }


}
