package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.cascadeSpecialFeatureHlrMgr.enity.config.CascadeSpecialFeatureHlrMgrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module.ISpecialFeatureHlr;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module.SpecialFeatureHlrFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 消除 特殊事件處理者 管理器 TODO 計算邏輯
public class CascadeSpecialFeatureHlrMgr {
    private final CascadeSpecialFeatureHlrMgrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final List<ISpecialFeatureHlr> specialFeatureHlrList; // 特殊事件處理者列表

    public CascadeSpecialFeatureHlrMgr(CascadeSpecialFeatureHlrMgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.specialFeatureHlrList = this.createSpecialFeatureHlrList(config.getCascadeSpecialFeatureHlrConfigCluster().getSpecialFeatureHlrConfigList());
    }


    // 創建特殊事件處理者列表
    private List<ISpecialFeatureHlr> createSpecialFeatureHlrList(List<SpecialFeatureHlrConfig> specialFeatureHlrConfigList) {
        if (specialFeatureHlrConfigList.isEmpty()) {
            return Collections.emptyList();
        }

        List<ISpecialFeatureHlr> result = new ArrayList<>();
        specialFeatureHlrConfigList.forEach(config -> result.add(new SpecialFeatureHlrFactory().create(config, this.tableUtil)));

        return result;
    }
}
