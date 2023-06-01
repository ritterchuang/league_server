package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.ClientSymbolHitPgrFactory;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.IClientSymbolHitPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.specialFeatureResultClusterPgr.specialFeatureResultCluster.SpecialFeatureResultCluster;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 特殊事件結果集合包裝者
public class SpecialFeatureResultClusterPgr {
    private final SpecialFeatureResultClusterPgrConfig config; // 特殊事件結果集合包裝者設定
    private final IClientSymbolHitPgr clientSymbolHitPgr; // 客端打擊包裝者
    private final ITableUtil tableUtil; // 牌桌工具包

    public SpecialFeatureResultClusterPgr(SpecialFeatureResultClusterPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.clientSymbolHitPgr = new ClientSymbolHitPgrFactory().create(config.getClientSymbolHitConfig(), tableUtil);
        this.tableUtil = tableUtil;
    }

    // 包裝特殊事件結果集合
    public SpecialFeatureResultCluster packageSpecialFeatureResultCluster(ScreenResult screenResult, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        double totalWin = specialFeatureHlrResultCluster.getTotalWin();

        List<SpecialFeatureResult> specialFeatureResultList = this.calculateSpecialFeatureResultList(screenResult, specialFeatureHlrResultCluster.getSpecialFeatureHlrResultList());

        return new SpecialFeatureResultCluster(totalWin, specialFeatureResultList);
    }

    // 計算特殊事件結果列表
    private List<SpecialFeatureResult> calculateSpecialFeatureResultList(ScreenResult screenResult, List<SpecialFeatureHlrResult> specialFeatureHlrResultList) {
        if (specialFeatureHlrResultList.isEmpty()) {
            return Collections.emptyList();
        }

        List<SpecialFeatureResult> result = new ArrayList<>();
        for (int index = 0; index < specialFeatureHlrResultList.size(); index++) {
            result.add(this.packageSpecialFeatureResult(screenResult, specialFeatureHlrResultList.get(index)));
        }

        return result;
    }

    // 包裝特殊事件結果
    private SpecialFeatureResult packageSpecialFeatureResult(ScreenResult screenResult, SpecialFeatureHlrResult specialFeatureHlrResult) {
        return new SpecialFeatureResult(specialFeatureHlrResult.getTotalWin(), specialFeatureHlrResult.getSpecialFeatureType(),
                this.config.getClientSymbolHitConfig().getSymbolHitType(),
                this.clientSymbolHitPgr.calculateSymbolHitResultExtend(screenResult, specialFeatureHlrResult.getScreenHitPosition())
                );
    }
}
