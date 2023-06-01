package org.lsj.gs.math.core.common.table.module.tableUtil;

import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilNotSupportSetRngData;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.JavaRandomUtil;
import org.lsj.websocket.IWebSocketUtil;

// 抽象遊戲桌工具包
public abstract class AbstractTableUtil implements ITableUtil{
    private final IWebSocketUtil webSocketUtil; // 傳輸協議工具包
    private final IRandomUtilMain mainRandomUtil; // 主要隨機工具包
    private final IRandomUtilNotSupportSetRngData notSupportSetRndRandomUtil; // 不支援設定亂數隨機工具包
    private final IControlAlgorithmUtil controlAlgorithmUtil; // 除錯工具包

    public AbstractTableUtil(IWebSocketUtil webSocketUtil, IRandomUtilMain mainRandomUtil, IControlAlgorithmUtil controlAlgorithmUtil, String rngDataString) {
        this.webSocketUtil = webSocketUtil;
        this.mainRandomUtil = mainRandomUtil;
        this.mainRandomUtil.setRandomNumberListByString(rngDataString);
        this.notSupportSetRndRandomUtil = new JavaRandomUtil();
        this.controlAlgorithmUtil = controlAlgorithmUtil;
    }

    @Override
    public IWebSocketUtil getWebSocketUtil() {
        return webSocketUtil;
    }

    @Override
    public IRandomUtilMain getMainRandomUtil() {
        return mainRandomUtil;
    }

    @Override
    public IRandomUtilNotSupportSetRngData getNotSupportSetRngDataRandomUtil() {
        return notSupportSetRndRandomUtil;
    }

    @Override
    public IControlAlgorithmUtil getControlAlgorithmUtil() {
        return controlAlgorithmUtil;
    }

    @Override
    public void reset() {
        this.mainRandomUtil.reset();
        this.notSupportSetRndRandomUtil.reset();
        this.controlAlgorithmUtil.reset();
    }
}
