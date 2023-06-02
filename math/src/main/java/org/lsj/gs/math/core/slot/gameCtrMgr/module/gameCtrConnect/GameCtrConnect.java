package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.connect.ConnectCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.connect.GameCtrResultExtendConnect;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.IGameCtr;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameHitConfigExtendConnectGame;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.utils.MathUtil;

import java.util.List;
import java.util.Map;

// 畫面計算器 連接 [TODO 僅支援飛鳥算法，以後擴充包含 wild 算法]
public class GameCtrConnect implements IGameCtr {
    private final GameCtrConfig config; // 設定檔
    private final GameHitConfigExtendConnectGame connectGameExtendConfig; // 連接 額外設定
    private final ConnectCtr connectCtr; // 連接計算器
    private final ITableUtil tableUtil; // 牌桌工具包

    public GameCtrConnect(GameCtrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.connectGameExtendConfig = (GameHitConfigExtendConnectGame) config.getGameConfig().getGameHitConfigExtend();
        this.connectCtr = new ConnectCtr(config.getSymbolConfig(), config.getPayTableConfig());
        this.tableUtil = tableUtil;
    }

    // 計算一般得分
    @Override
    public GameCtrResult calculateGameCtrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        // 1. 計算結果
        List<ConnectCtrWinResult> connectCtrWinResultList = this.connectCtr.calculateConnectCtrWinResultList(spinRequest.getPlayerCreditBase(), this.connectGameExtendConfig.getPositionToConnectPositionListMap(), screenGtrResult);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(connectCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendConnect(
                        this.connectGameExtendConfig.getGameHitDirectionType(),
                        connectCtrWinResultList
                )
        );
    }

    // 計算線得分全屏倍數列表
    @Override
    public GameCtrResult calculateGameCtrResultWithScreenMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier) {
        // 1. 計算結果
        List<ConnectCtrWinResult> connectCtrWinResultList = this.connectCtr.calculateConnectCtrWinResultListWithScreenMultiplier(
                spinRequest.getPlayerCreditBase(),
                this.connectGameExtendConfig.getPositionToConnectPositionListMap(),
                screenGtrResult,
                screenMultiplier);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(connectCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendConnect(
                        this.connectGameExtendConfig.getGameHitDirectionType(),
                        connectCtrWinResultList
                )
        );
    }

    // 計算多種倍數線得分列表
    @Override
    public GameCtrResult calculateGameCtrResultWithMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier, Map<Integer, Integer> symbolIdToMultiplierMap, List<List<Integer>> multiplierMatrix) {
        return null;
    }


    //* 通用 *//

    // 計算總得分
    private double calculateTotalWin(List<ConnectCtrWinResult> connectCtrWinResultList) {
        double totalWin = 0.0;

        for (int connectWinIndex = 0; connectWinIndex < connectCtrWinResultList.size(); connectWinIndex++) {
            totalWin = MathUtil.add(totalWin, connectCtrWinResultList.get(connectWinIndex).getTotalWin());
        }

        return totalWin;
    }
}
