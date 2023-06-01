package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfig;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameFlowConfig.GameFlowConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 系統狀態處理者設定
public class ConditionStateHlrConfig {
    private final List<GameStateConfig> gameStateConfigList; // 遊戲狀態設定列表
    private final Map<Integer, Map<Integer, ConstMathSlot.Condition>> conditionTable; // 狀態轉移表 [i][j] = 遊戲狀態i 到 遊戲狀態j 的條件
    private final int boardInitialConditionId; // 局初始遊戲狀態Id
    private final int boardEndConditionId; // 局結束遊戲狀態Id

    public ConditionStateHlrConfig(List<GameStateConfig> gameStateConfigList, GameFlowConfig gameFlowConfig, int boardInitialConditionId, int boardEndConditionId) {
        this.gameStateConfigList = gameStateConfigList;
        this.conditionTable = this.createConditionTable(gameFlowConfig.getConditionList());
        this.boardInitialConditionId = boardInitialConditionId;
        this.boardEndConditionId = boardEndConditionId;
    }

    // 建立條件轉移表
    private Map<Integer, Map<Integer, ConstMathSlot.Condition>> createConditionTable(List<List<ConstMathSlot.Condition>> conditionList) {

        Map<Integer, Map<Integer, ConstMathSlot.Condition>> result = new HashMap<>();

        for (int indexOne = 0; indexOne < conditionList.size(); indexOne++) {
            Map<Integer, ConstMathSlot.Condition> indexTwoToConditionMap = new HashMap<>();

            for (int indexTwo = 0; indexTwo < conditionList.get(indexOne).size(); indexTwo++) {
                indexTwoToConditionMap.put(indexTwo, conditionList.get(indexOne).get(indexTwo));
            }

            result.put(indexOne, indexTwoToConditionMap);
        }

        return result;
    }

    public List<GameStateConfig> getGameStateConfigList() {
        return gameStateConfigList;
    }

    public Map<Integer, Map<Integer, ConstMathSlot.Condition>> getConditionTable() {
        return conditionTable;
    }

    public int getBoardInitialConditionId() {
        return boardInitialConditionId;
    }

    public int getBoardEndConditionId() {
        return boardEndConditionId;
    }
}
