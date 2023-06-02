package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeReadyHandHlrMgr.enity.CascadeReadyHandHlrMgrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.module.IReadyHandHlr;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.module.ReadyHandHlrFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 消除聽牌事件處理者管理器 TODO 計算邏輯
public class CascadeReadyHandHlrMgr {
    private final CascadeReadyHandHlrMgrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final List<IReadyHandHlr> readyHandHlrList; // 消除聽牌處理者

    public CascadeReadyHandHlrMgr(CascadeReadyHandHlrMgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.readyHandHlrList = this.createReadyHandHlrList(config.getCascadeReadyHandHlrConfigCluster().getReadyHandHlrConfigList());
    }

    // 創建聽牌處理者列表
    private List<IReadyHandHlr> createReadyHandHlrList(List<ReadyHandHlrConfig> readyHandHlrConfigList) {
        if (readyHandHlrConfigList.isEmpty() == true) {
            return Collections.emptyList();
        }

        List<IReadyHandHlr> result = new ArrayList<>();
        readyHandHlrConfigList.forEach(config -> result.add(new ReadyHandHlrFactory().create(config, this.tableUtil)));

        return result;
    }
}
