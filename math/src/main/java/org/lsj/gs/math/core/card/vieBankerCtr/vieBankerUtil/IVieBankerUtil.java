package org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil;

import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 搶莊工具包介面
public interface IVieBankerUtil {

    // 取得搶莊列表
    Map<Integer, List<Integer>> calculateCanVieRateListMap(GamePlayerHlr gamePlayerHlr, VieBankerCtrConfig config);

    // 決定莊家位置
    int calculateBankChair(Map<Integer, Integer> vieRateListMap, VieBankerCtrConfig config, GamePlayerHlr gamePlayerHlr, ITableUtil tableUtil);

    // 計算莊家倍數
    int calculateBankerRate(int maxVieRate, VieBankerCtrConfig config);
}
