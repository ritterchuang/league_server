package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 畫面切割工具包
public class ScreenPartitionUtil {

    // 畫面切割結果
    public ScreenPartitionResult calculateScreenPartitionResult(
            Map<int[], List<int[]>> positionToConnectPositionListMap
            , List<List<Integer>> screenSymbol) {

        // 1. 計算所有未分類位置列表
        List<int[]> nonGroupScreenPositionList = this.calculateNonGroupScreenPositionList(screenSymbol);

        // 2. 遍歷所有未分類列表
        List<SymbolScreenPositionCluster> symbolScreenPositionClusterList = this.calculateSymbolScreenPositionClusterList(screenSymbol, positionToConnectPositionListMap, nonGroupScreenPositionList);

        // 3. 回傳
        return new ScreenPartitionResult(symbolScreenPositionClusterList);
    }


    //* 畫面分割計算相關 *//

    // 計算 標誌畫面分割集合列表
    private List<SymbolScreenPositionCluster> calculateSymbolScreenPositionClusterList(
            List<List<Integer>> screenSymbol,
            Map<int[], List<int[]>> positionToConnectPositionListMap,
            List<int[]> nonGroupScreenPositionList) {
        // 1. 宣告空間
        List<SymbolScreenPositionCluster> result = new ArrayList<>();

        // 2. 遍歷所有未分類列表
        for (int nonGroupScreenPositionIndex = 0; nonGroupScreenPositionIndex < nonGroupScreenPositionList.size();) {
            // 3. 取得當前位置
            int[] currentPosition = nonGroupScreenPositionList.get(nonGroupScreenPositionIndex);

            // 4. 計算所有相連結果
            ScreenPositionCluster screenPositionCluster = this.calculateScreenPositionCluster(screenSymbol,
                                                                                positionToConnectPositionListMap,
                                                                                currentPosition,
                                                                                currentPosition,
                                                                                nonGroupScreenPositionList,
                                                                                new ScreenPositionCluster());

            // 5 封裝此次相連結果
            this.packageSymbolScreenPositionClusterList(result, this.getSymbolIdByPosition(screenSymbol, currentPosition), screenPositionCluster);
        }

        // 6. 回傳
        return result;
    }

    // 計算當前位置相連結果
    private ScreenPositionCluster calculateScreenPositionCluster (
            List<List<Integer>> screenSymbol,
            Map<int[], List<int[]>> positionToConnectPositionListMap,
            int[] currentPosition,
            int[] nextPosition,
            List<int[]> nonGroupPositionList,
            ScreenPositionCluster screenPositionCluster) {

        // 1. 檢查當前位置是否連接
        if (!this.isConnectPosition(screenSymbol, currentPosition, nextPosition, nonGroupPositionList)) {
            return screenPositionCluster;
        }

        // 2. 更新
        this.updateNextPosition(nextPosition, nonGroupPositionList, screenPositionCluster);

        // 3. 遍歷其他方向
        List<int[]> nextPossiblePositionList = this.calculatePossiblePositionList(positionToConnectPositionListMap, nextPosition);
        for (int[] nextPossiblePosition : nextPossiblePositionList) {
            screenPositionCluster = this.calculateScreenPositionCluster(screenSymbol, positionToConnectPositionListMap, nextPosition, nextPossiblePosition, nonGroupPositionList, screenPositionCluster);
        }

        // 4. 回傳結果
        return screenPositionCluster;
    }

    // 更新未分類群組、畫面位置集合
    private void updateNextPosition(int[] nextPosition, List<int[]> nonGroupPositionList, ScreenPositionCluster screenPositionCluster) {
        // 1. 新增連接位置
        screenPositionCluster.addPosition(nextPosition);

        // 2. 更新未分類位置列表
        nonGroupPositionList.removeIf(targetArray -> Arrays.equals(targetArray, nextPosition));
    }


    //* 封裝相關 *//

    // 封裝 標誌畫面分割集合列表
    private void packageSymbolScreenPositionClusterList(List<SymbolScreenPositionCluster> symbolScreenPositionClusterList, int symbolId, ScreenPositionCluster screenPositionCluster) {
        // 1. 取出相同標誌分組集合
        Optional<SymbolScreenPositionCluster> optionalSymbolScreenPositionCluster = symbolScreenPositionClusterList.stream()
                .filter(symbolScreenPositionCluster -> symbolScreenPositionCluster.getSymbolId() == symbolId).findAny();

        // 2. 若不存在，新增標誌畫面位置集合
        if (optionalSymbolScreenPositionCluster.isEmpty()) {
            symbolScreenPositionClusterList.add(this.packageSymbolScreenPositionCluster(symbolId, screenPositionCluster));
            return;
        }

        // 3. 存在，新增畫面位置集合
        optionalSymbolScreenPositionCluster.get().addScreenPositionCluster(screenPositionCluster);
    }

    // 封裝 標誌畫面位置集合
    private SymbolScreenPositionCluster packageSymbolScreenPositionCluster(int symbolId, ScreenPositionCluster screenPositionCluster) {
        SymbolScreenPositionCluster result = new SymbolScreenPositionCluster(symbolId);
        result.addScreenPositionCluster(screenPositionCluster);
        return result;
    }


    //* 畫面位置相關 *//

    // 位置是否連接
    private boolean isConnectPosition(List<List<Integer>> screenSymbol, int[] currentPosition, int[] nextPosition, List<int[]> nonGroupPositionList) {
        // 1. 防呆
        if (nonGroupPositionList.isEmpty() || Objects.isNull(currentPosition) || Objects.isNull(nextPosition)) {
            return false;
        }

        // 2. 新位置是否被分類過
        if (nonGroupPositionList.stream()
                .noneMatch(targetPosition ->
                        (targetPosition[0] == nextPosition[0]) && (targetPosition[1] == nextPosition[1]))) {
            return false;
        }

        // 3. 檢查標誌是否一致
        return Objects.equals(screenSymbol.get(currentPosition[0]).get(currentPosition[1]), screenSymbol.get(nextPosition[0]).get(nextPosition[1]));
    }

    // 依照位置取得標誌
    private int getSymbolIdByPosition(List<List<Integer>> screenSymbol, int[] position) {
        int columnIndex = position[0];
        int rowIndex = position[1];

        return screenSymbol.get(columnIndex).get(rowIndex);
    }

    // 計算所有未分組 畫面位置列表
    private List<int[]> calculateNonGroupScreenPositionList(List<List<Integer>> screenSymbol) {
        return IntStream.range(0, screenSymbol.size())
                .mapToObj(columnIndex ->
                        IntStream.range(0, screenSymbol.get(columnIndex).size())
                                .mapToObj(rowIndex -> new int[]{columnIndex, rowIndex})
                                .collect(Collectors.toList()))
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }

    // 取得可能位置列表
    private List<int[]> calculatePossiblePositionList(Map<int[], List<int[]>> positionToConnectPositionListMap, int[] nextPosition) {
        return positionToConnectPositionListMap.entrySet().stream()
                .filter(entry -> Arrays.equals(entry.getKey(), nextPosition))
                .map(Map.Entry::getValue)
                .findAny()
                .orElse(new ArrayList<>());
    }
}
