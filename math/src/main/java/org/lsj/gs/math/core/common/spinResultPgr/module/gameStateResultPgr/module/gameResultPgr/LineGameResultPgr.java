package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.GameResultExtendLineGame;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult.winResult.LineWinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.ClientSymbolHitPgrFactory;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil.IClientSymbolHitPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.GameCtrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.GameCtrResultExtendLine;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.LineCtrWinResult;

import java.util.ArrayList;
import java.util.List;

// 算分結果包裝者 Line
public class LineGameResultPgr implements IGameResultPgr{
    private final GameResultPgrConfig config; // 算分結果包裝者設定
    private final IClientSymbolHitPgr clientSymbolHitPgr; // 客端打擊包裝者設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public LineGameResultPgr(GameResultPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.clientSymbolHitPgr = new ClientSymbolHitPgrFactory().create(config.getClientSymbolHitConfig(), tableUtil);
        this.tableUtil = tableUtil;
    }

    // 包裝遊戲算分結果
    @Override
    public GameResult packageGameResult(ScreenResult screenResult, GameCtrResult gameCtrResult) {
        // 1. 轉型
        GameCtrResultExtendLine resultExtendLine = (GameCtrResultExtendLine) gameCtrResult.getGameCtrResultExtend();

        // 2. 取得總得分
        double totalWin = gameCtrResult.getTotalWin();

        // 3. 取得算分類型
        ConstMathSlot.GameHitType gameHitType = gameCtrResult.getGameHitType();

        // 4. 計算路得分列表
        List<LineWinResult> LineWinResultList = this.calculateLineWinResultList(screenResult, resultExtendLine);

        // 5. 回傳
        return new GameResult(totalWin, gameHitType, new GameResultExtendLineGame(gameCtrResult.getGameCtrResultExtend().getGameHitDirectionType(), LineWinResultList));
    }

    // 計算路得分列表
    private List<LineWinResult> calculateLineWinResultList(ScreenResult screenResult, GameCtrResultExtendLine resultExtendLine) {
        List<LineWinResult> LineWinResultList = new ArrayList<>();

        for (int index = 0; index < resultExtendLine.getLineCtrWinResultList().size(); index++) {
            LineWinResultList.add(this.packageLineWinResult(screenResult, resultExtendLine.getLineCtrWinResultList().get(index)));
        }

        return LineWinResultList;
    }

    // 包裝路得分
    private LineWinResult packageLineWinResult(ScreenResult screenResult, LineCtrWinResult lineCtrWinResult) {
        return new LineWinResult(
                lineCtrWinResult.getTotalWin(),
                lineCtrWinResult.getLineId(),
                lineCtrWinResult.getPayComboId(),
                lineCtrWinResult.getHitNumber(),
                lineCtrWinResult.getHitOdds(),
                lineCtrWinResult.getMultiplier(),
                this.config.getClientSymbolHitConfig().getSymbolHitType(),
                this.clientSymbolHitPgr.calculateSymbolHitResultExtend(screenResult, lineCtrWinResult.getScreenHitPosition()));
    }

}
