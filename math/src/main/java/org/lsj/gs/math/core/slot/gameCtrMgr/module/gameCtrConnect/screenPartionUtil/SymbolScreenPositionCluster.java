package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil;

import java.util.ArrayList;
import java.util.List;

// 標誌連接座標集合
public class SymbolScreenPositionCluster {
    private int symbolId; // 標誌
    private List<ScreenPositionCluster> screenPositionClusterList; // 連接位置集合列表

    public SymbolScreenPositionCluster(int symbolId) {
        this.symbolId = symbolId;
        this.screenPositionClusterList = new ArrayList<>();
    }

    public SymbolScreenPositionCluster(int symbolId, List<ScreenPositionCluster> screenPositionClusterList) {
        this.symbolId = symbolId;
        this.screenPositionClusterList = screenPositionClusterList;
    }

    public void addScreenPositionCluster(ScreenPositionCluster screenPositionCluster) {
        this.screenPositionClusterList.add(screenPositionCluster);
    }

    public int getSymbolId() {
        return symbolId;
    }

    public List<ScreenPositionCluster> getScreenPositionClusterList() {
        return screenPositionClusterList;
    }
}
