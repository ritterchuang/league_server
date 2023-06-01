package org.lsj.gs.math.core.common.table.module.tableUtil;

import org.lsj.gs.math.core.common.table.module.tableUtil.controlAlgorithm.IControlAlgorithmUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.IRandomUtilNotSupportSetRngData;
import org.lsj.websocket.IWebSocketUtil;

// 牌桌工具包介面
public interface ITableUtil {
    IWebSocketUtil getWebSocketUtil(); // 傳輸協議工具包
    IRandomUtilMain getMainRandomUtil(); // 主要隨機工具包
    IRandomUtilNotSupportSetRngData getNotSupportSetRngDataRandomUtil(); // 不支援設定亂數隨機工具包
    IControlAlgorithmUtil getControlAlgorithmUtil(); // 強控演算法工具包
    void reset(); // 重設
}
