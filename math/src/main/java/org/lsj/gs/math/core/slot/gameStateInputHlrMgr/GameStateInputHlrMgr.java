package org.lsj.gs.math.core.slot.gameStateInputHlrMgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputHlrMgrConfig;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.module.GameStateInputHlrFactory;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.module.IGameStateInputHlr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 遊戲輸入處理者
public class GameStateInputHlrMgr {
    private final GameStateInputHlrMgrConfig config;
    private final ITableUtil tableUtil;
    private final Map<Integer, IGameStateInputHlr> gameStateInputHlrMap;

    public GameStateInputHlrMgr(GameStateInputHlrMgrConfig gameStateInputHlrMgrConfig, ITableUtil tableUtil) {
        this.config = gameStateInputHlrMgrConfig;
        this.tableUtil = tableUtil;
        this.gameStateInputHlrMap = this.createGameStateInputHlrMap();
    }

    // 計算遊戲額外輸入資訊
    public GameStateInputResult getGameStateInputResult(int conditionId, List<RoundHlrResult> roundHlrResultList) {
        return this.gameStateInputHlrMap.get(conditionId).calculateGameStateInputResult(roundHlrResultList);
    }

    // 創建進度處理者對應表
    private Map<Integer, IGameStateInputHlr> createGameStateInputHlrMap() {
        Map<Integer, IGameStateInputHlr> result = new HashMap<>();

        this.config.getConditionIdToGameStateInputConfigMap().forEach(
                (key, value) -> result.put(key, new GameStateInputHlrFactory().create(value, this.tableUtil)));

        return result;
    }
}
