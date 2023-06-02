package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil;

import org.lsj.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 連接座標集合
public class ScreenPositionCluster {
    private List<int[]> symbolPositionList; // 連接位置列表 [i][0] = 第i個為第幾軸, [i][1] = 第i個為第幾列

    public ScreenPositionCluster() {
        this.symbolPositionList = new ArrayList<>();
    }

    public ScreenPositionCluster(List<int[]> symbolPositionList) {
        this.symbolPositionList = symbolPositionList;
    }

    public void addPosition(int[] symbolPosition) {
        int[] symbolPositionCopy = JsonUtil.getInstance().deepCopy(symbolPosition, int[].class);

        this.symbolPositionList.add(symbolPositionCopy);
    }

    public boolean isContainPosition(int[] targetPosition) {
        return this.symbolPositionList.stream().anyMatch(position -> Arrays.equals(position, targetPosition));
    }

    public List<int[]> getSymbolPositionList() {
        return symbolPositionList;
    }
}
