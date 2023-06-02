package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.IGameCtr;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameHitConfigExtendWayGame;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.utils.MathUtil;

import java.util.List;
import java.util.Map;

// 畫面計算者路 TODO 右至左、雙邊 [包裝者，依照連線方向呼叫對英方法]
public class GameCtrWay implements IGameCtr {
    private final GameCtrConfig config; // 設定檔
    private final GameHitConfigExtendWayGame wayGameExtendConfig; // 路額外設定
    private final WayCtr wayCtr; // 路計算器
    private final ITableUtil tableUtil; // 牌桌工具包


    public GameCtrWay(GameCtrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.wayGameExtendConfig = (GameHitConfigExtendWayGame) config.getGameConfig().getGameHitConfigExtend();
        this.wayCtr = new WayCtr(this.config.getSymbolConfig(), this.config.getPayTableConfig());
        this.tableUtil = tableUtil;
    }

    // 計算路得分
    @Override
    public GameCtrResult calculateGameCtrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        // 1. 計算結果
        List<WayCtrWinResult> wayCtrWinResultList = this.wayCtr.calculateWayCtrWinResultList(
                spinRequest.getPlayerCreditBase(),
                screenGtrResult,
                ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(wayCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendWay(
                        this.wayGameExtendConfig.getGameHitDirectionType(),
                        wayCtrWinResultList));
    }

    // 計算使用路得分全屏倍數
    @Override
    public GameCtrResult calculateGameCtrResultWithScreenMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier) {
        // 1. 計算結果
        List<WayCtrWinResult> wayCtrWinResultList = this.wayCtr.calculateWayCtrWinResultListWithScreenMultiplier(
                spinRequest.getPlayerCreditBase(),
                screenGtrResult,
                screenMultiplier,
                ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(wayCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendWay(
                        this.wayGameExtendConfig.getGameHitDirectionType(),
                        wayCtrWinResultList));
    }

    // 計算使用多種倍數路得分
    @Override
    public GameCtrResult calculateGameCtrResultWithMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier, Map<Integer, Integer> symbolIdToMultiplierMap, List<List<Integer>> multiplierMatrix) {
        // 1. 依照算分方向計算結果
        List<WayCtrWinResult> wayCtrWinResultList = this.wayCtr.calculateWayCtrWinResultListWithMultiplier(
                spinRequest.getPlayerCreditBase(),
                screenGtrResult,
                screenMultiplier,
                symbolIdToMultiplierMap,
                multiplierMatrix,
                this.wayGameExtendConfig.getGameHitDirectionType());

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(wayCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendWay(
                        this.wayGameExtendConfig.getGameHitDirectionType(),
                        wayCtrWinResultList));
    }


    //* 通用 *//

    // 計算總得分
    private double calculateTotalWin(List<WayCtrWinResult> wayCtrWinResultList) {
        double totalWin = 0.0;

        for (int wayWinIndex = 0; wayWinIndex < wayCtrWinResultList.size(); wayWinIndex++) {
            totalWin = MathUtil.add(totalWin, wayCtrWinResultList.get(wayWinIndex).getTotalWin());
        }

        return totalWin;
    }
}