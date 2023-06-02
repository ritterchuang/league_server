package org.lsj.gs.math.core.common.spinResultPgr;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.spinResultPgr.enity.SpinResult;
import org.lsj.gs.math.core.common.spinResultPgr.enity.SpinResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.GameStateResultPgrFactory;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.IGameStateResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.enity.GameStateResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 虎機客端結果包裝者
public class SpinResultPgr {
    private final SpinResultPgrConfig config; // 客製押注結果包裝者
    private final ITableUtil tableUtil; // 牌桌工具包
    private final Map<Integer, IGameStateResultPgr> conditionIdToGameStateResultPgrMap; // <條件ID, 遊戲狀態包裝者> 對應表

    public SpinResultPgr(SpinResultPgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.conditionIdToGameStateResultPgrMap = this.createConditionIdToGameStateResultPgrMap();
    }

    // 包裝客製押注結果
    public SpinResult packageSpinResult(GameFlowHlrResult gameFlowHlrResult) {
        return new SpinResult(
                gameFlowHlrResult.getTotalWin(),
                this.packageGameStateResultList(gameFlowHlrResult.getGameStateHlrResultList()),
                gameFlowHlrResult.getAnimationResult());
    }

    // 包裝客製狀態結果列表
    private List<GameStateResult> packageGameStateResultList(List<GameStateHlrResult> gameStateHlrResultList) {
        List<GameStateResult> gameStateResultList = new ArrayList<>();

        gameStateHlrResultList.forEach(
                gameStateHlrResult
                        -> gameStateResultList.add(this.conditionIdToGameStateResultPgrMap.get(gameStateHlrResult.getConditionId())
                        .packageGameStateResult(gameStateHlrResult)));

        return gameStateResultList;
    }

    // 創建 <條件ID, 遊戲狀態結果包裝者> 對應表
    private Map<Integer, IGameStateResultPgr> createConditionIdToGameStateResultPgrMap() {
        Map<Integer, IGameStateResultPgr> result = new HashMap<>();

        this.config.getConditionIdToGameStateResultPgrConfigMap().forEach(
                (key, value) -> result.put(key, new GameStateResultPgrFactory().create(value, this.tableUtil)));

        return result;
    }
}
