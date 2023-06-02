package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil;

import java.util.List;

// 連接分類計算者結果
public class ScreenPartitionResult {
    private final List<SymbolScreenPositionCluster> symbolScreenPositionClusterList; // 標誌連接位置集合列表

    public ScreenPartitionResult(List<SymbolScreenPositionCluster> symbolScreenPositionClusterList) {
        this.symbolScreenPositionClusterList = symbolScreenPositionClusterList;
    }

    public List<SymbolScreenPositionCluster> getSymbolConnectPositionClusterList() {
        return symbolScreenPositionClusterList;
    }
}
