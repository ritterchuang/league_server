package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.GameCtrResultExtendLine;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.LineCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.IGameCtr;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameHitConfigExtendLineGame;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.utils.MathUtil;

import java.util.List;
import java.util.Map;

// 畫面計算器 線
public class GameCtrLine implements IGameCtr {
    private final GameCtrConfig config; // 設定檔
    private final GameHitConfigExtendLineGame lineGameExtendConfig; // 線 額外設定
    private final LineCtr lineCtr; // 線計算器
    private final ITableUtil tableUtil; // 牌桌工具包

    public GameCtrLine(GameCtrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.lineGameExtendConfig = (GameHitConfigExtendLineGame) config.getGameConfig().getGameHitConfigExtend();
        this.lineCtr = new LineCtr(config.getSymbolConfig(), config.getPayTableConfig(), this.lineGameExtendConfig.getLinePositionIndexList());
        this.tableUtil = tableUtil;
    }

    // 計算一般得分
    @Override
    public GameCtrResult calculateGameCtrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        // 1. 計算結果
        List<LineCtrWinResult> lineCtrWinResultList = this.lineCtr.calculateLineCtrWinResultList(
                spinRequest.getPlayerCreditBase(),
                screenGtrResult,
                ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(lineCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendLine(
                        this.lineGameExtendConfig.getGameHitDirectionType(),
                        lineCtrWinResultList
                )
        );
    }

    // 計算線得分全屏倍數列表
    @Override
    public GameCtrResult calculateGameCtrResultWithScreenMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier) {
        // 1. 計算結果
        List<LineCtrWinResult> lineCtrWinResultList = this.lineCtr.calculateLineCtrWinResultListWithScreenMultiplier(
                spinRequest.getPlayerCreditBase(),
                screenGtrResult,
                screenMultiplier,
                ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(lineCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendLine(
                        this.lineGameExtendConfig.getGameHitDirectionType(),
                        lineCtrWinResultList
                )
        );
    }

    // 計算多種倍數線得分列表
    @Override
    public GameCtrResult calculateGameCtrResultWithMultiplier(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, int screenMultiplier, Map<Integer, Integer> symbolIdToMultiplierMap, List<List<Integer>> multiplierMatrix) {
        // 1. 計算結果
        List<LineCtrWinResult> lineCtrWinResultList = this.lineCtr.calculateLineCtrWinResultListWithMultiplier(
                spinRequest.getPlayerCreditBase(),
                screenGtrResult,
                screenMultiplier,
                symbolIdToMultiplierMap,
                multiplierMatrix,
                ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(lineCtrWinResultList);

        // 3. 回傳
        return new GameCtrResult(
                MathUtil.moneyScale(totalWin),
                this.config.getGameConfig().getGameHitType(),
                new GameCtrResultExtendLine(
                        this.lineGameExtendConfig.getGameHitDirectionType(),
                        lineCtrWinResultList
                )
        );
    }


    //* 通用 *//

    // 計算總得分
    private double calculateTotalWin(List<LineCtrWinResult> lineCtrWinResultList) {
        double totalWin = 0.0;

        for (int lineWinIndex = 0; lineWinIndex < lineCtrWinResultList.size(); lineWinIndex++) {
            totalWin = MathUtil.add(totalWin, lineCtrWinResultList.get(lineWinIndex).getTotalWin());
        }

        return totalWin;
    }
}
