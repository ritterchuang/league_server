package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect;

import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.connect.ConnectCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.PayComboClassifier;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.connectCtrUtil.ConnectCtrUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil.ScreenPartitionResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil.ScreenPartitionUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 連接 計算器
public class ConnectCtr {
    private final PayComboClassifier payComboClassifier; // 賠率表分類器
    private final ScreenPartitionUtil screenPartitionUtil; // 畫面分割工具包
    private final ConnectCtrUtil connectCtrUtil; // 連接計算者工具包

    public ConnectCtr(SymbolConfig symbolConfig, PayTableConfig payTableConfig) {
        this.payComboClassifier = new PayComboClassifier(symbolConfig, payTableConfig);
        this.screenPartitionUtil = new ScreenPartitionUtil();
        this.connectCtrUtil = new ConnectCtrUtil(symbolConfig, payTableConfig);
    }

    // 計算連接計算器結果列表 TODO 標誌列表僅支援一個標誌，之後擴充
    public List<ConnectCtrWinResult> calculateConnectCtrWinResultList(double playerCreditBase, Map<int[], List<int[]>> positionToConnectPositionListMap, ScreenGtrResult screenGtrResult) {
        ScreenPartitionResult screenPartitionResult = this.screenPartitionUtil.calculateScreenPartitionResult(positionToConnectPositionListMap, screenGtrResult.getScreenSymbol());

        // 1. 宣告空間
        List<ConnectCtrWinResult> result = new ArrayList<>();

        // 2. 遍歷所有結果
        screenPartitionResult.getSymbolConnectPositionClusterList().forEach(
                symbolScreenPositionCluster -> symbolScreenPositionCluster.getScreenPositionClusterList().forEach(
                        screenPositionCluster -> this.connectCtrUtil.calculateConnectCtrWinResult(
                                        List.of(symbolScreenPositionCluster.getSymbolId()),
                                        screenPositionCluster,
                                        playerCreditBase,
                                        screenGtrResult).
                                ifPresent(result::add)
                )
        );

        return result;
    }

    // 計算含倍數連接計算器結果列表 TODO 標誌列表僅支援一個標誌，之後擴充
    public List<ConnectCtrWinResult> calculateConnectCtrWinResultListWithScreenMultiplier(double playerCreditBase, Map<int[], List<int[]>> positionToConnectPositionListMap, ScreenGtrResult screenGtrResult, int screenMultiplier) {
        ScreenPartitionResult screenPartitionResult = this.screenPartitionUtil.calculateScreenPartitionResult(positionToConnectPositionListMap, screenGtrResult.getScreenSymbol());

        // 1. 宣告空間
        List<ConnectCtrWinResult> result = new ArrayList<>();

        // 2. 遍歷所有結果
        screenPartitionResult.getSymbolConnectPositionClusterList().forEach(
                symbolScreenPositionCluster -> symbolScreenPositionCluster.getScreenPositionClusterList().forEach(
                        screenPositionCluster -> this.connectCtrUtil.calculateConnectCtrWinResultWithScreenMultiplier(
                                        List.of(symbolScreenPositionCluster.getSymbolId()),
                                        screenPositionCluster,
                                        screenMultiplier,
                                        playerCreditBase,
                                        screenGtrResult).
                                ifPresent(result::add)
                )
        );

        return result;
    }
}
