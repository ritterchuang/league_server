package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientGameStateResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.enity.GameStateResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.enity.GameStateResultExtendDefault;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.IRoundResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.RoundResultPgrFactory;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;
import java.util.List;

// 遊戲狀態結果包裝者 開發虎機
public abstract class AbstractGameStateResultPgr implements IGameStateResultPgr{
    private final ClientGameStateResultPgrConfig config; // 客端遊戲狀態結果包裝者設定
    private final IRoundResultPgr roundResultPgr; // 局結果包裝者
    private final ITableUtil tableUtil; // 牌桌工具包

    public AbstractGameStateResultPgr(ClientGameStateResultPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.roundResultPgr = new RoundResultPgrFactory().create(config.getRoundType(), config.getClientRoundResultPgrConfig(), tableUtil); // TODO by factory
    }

    @Override
    public GameStateResult packageGameStateResult(GameStateHlrResult gameStateHlrResult) {
        return new GameStateResult(
                gameStateHlrResult.getGameStateIndex(),
                gameStateHlrResult.getGameStateId(),
                gameStateHlrResult.getTotalWin(),
                gameStateHlrResult.getGameStateType(),
                new GameStateResultExtendDefault(), // TODO 目前無遊戲需要額外帶資訊
                gameStateHlrResult.getRoundType(),
                this.packageRoundResultList(gameStateHlrResult.getRoundHlrResultList()));
    }

    private List<RoundResult> packageRoundResultList(List<RoundHlrResult> roundHlrResultList) {
        List<RoundResult> result = new ArrayList<>();

        roundHlrResultList.forEach(roundHlrResult -> result.add(this.roundResultPgr.packageRoundResult(roundHlrResult)));

        return result;
    }
}
