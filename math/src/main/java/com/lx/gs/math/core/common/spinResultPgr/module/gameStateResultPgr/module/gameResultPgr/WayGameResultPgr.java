package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr;

import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResultExtendWayGame;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.winResult.WayWinResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.ClientSymbolHitPgrFactory;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.IClientSymbolHitPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;

import java.util.ArrayList;
import java.util.List;

// 算分結果包裝者 路
public class WayGameResultPgr implements IGameResultPgr{
    private final GameResultPgrConfig config; // 算分結果包裝者設定
    private final IClientSymbolHitPgr clientSymbolHitPgr; // 客端打擊包裝者設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public WayGameResultPgr(GameResultPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.clientSymbolHitPgr = new ClientSymbolHitPgrFactory().create(config.getClientSymbolHitConfig(), tableUtil);
        this.tableUtil = tableUtil;
    }

    // 包裝遊戲算分結果
    @Override
    public GameResult packageGameResult(ScreenResult screenResult, GameCtrResult gameCtrResult) {
        // 1. 轉型
        GameCtrResultExtendWay resultExtendWay = (GameCtrResultExtendWay) gameCtrResult.getGameCtrResultExtend();

        // 2. 取得總得分
        double totalWin = gameCtrResult.getTotalWin();

        // 3. 取得算分類型
        ConstMathSlot.GameHitType gameHitType = gameCtrResult.getGameHitType();

        // 4. 計算路得分列表
        List<WayWinResult> wayWinResultList = this.calculateWayWinResultList(screenResult, resultExtendWay);

        // 5. 回傳
        return new GameResult(totalWin, gameHitType, new GameResultExtendWayGame(gameCtrResult.getGameCtrResultExtend().getGameHitDirectionType(), wayWinResultList));
    }

    // 計算路得分列表
    private List<WayWinResult> calculateWayWinResultList(ScreenResult screenResult, GameCtrResultExtendWay resultExtendWay) {
        List<WayWinResult> wayWinResultList = new ArrayList<>();

        for (int index = 0; index < resultExtendWay.getWayCtrWinResultList().size(); index++) {
            wayWinResultList.add(this.packageWayWinResult(screenResult, resultExtendWay.getWayCtrWinResultList().get(index)));
        }

        return wayWinResultList;
    }

    // 包裝路得分
    private WayWinResult packageWayWinResult(ScreenResult screenResult, WayCtrWinResult wayCtrWinResult) {
        return new WayWinResult(
                wayCtrWinResult.getTotalWin(), wayCtrWinResult.getPayComboId(),
                wayCtrWinResult.getHitNumber(),
                wayCtrWinResult.getHitCount(),
                wayCtrWinResult.getHitOdds(),
                this.config.getClientSymbolHitConfig().getSymbolHitType(),
                this.clientSymbolHitPgr.calculateSymbolHitResultExtend(screenResult, wayCtrWinResult.getScreenHitPosition()));
    }

}
