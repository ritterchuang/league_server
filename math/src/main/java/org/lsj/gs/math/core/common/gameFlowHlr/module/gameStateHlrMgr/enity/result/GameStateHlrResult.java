package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;

import java.util.Collections;
import java.util.List;

// 遊戲處理者結果
public class GameStateHlrResult {
    private final int conditionId; // 條件狀態識別碼 [ conditionTable 第幾個狀態 ]
    private final int gameStateIndex; // 遊戲狀態流水號 [ 結果列表中第幾個結果 ]
    private final int gameStateId; // 遊戲狀態識別碼 [ 設定檔第幾個狀態 ]
    private final double totalWin; // 總得分
    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型
    private final GameStateHlrResultExtend gameStateHlrResultExtend; // 遊戲狀態額外結果
    private final ConstMathSlot.RoundType roundType; // 局類型
    private final List<RoundHlrResult> roundHlrResultList; // 局結果列表
    private final CommonInputResult commonInputResult; // 共同輸入結果
    private final GameStateInputResult gameStateInputResult; // 遊戲特製輸入結果

    public GameStateHlrResult(int conditionId,
                              int gameStateIndex,
                              int gameStateId,
                              double totalWin,
                              ConstMathSlot.GameStateType gameStateType,
                              GameStateHlrResultExtend gameStateHlrResultExtend,
                              ConstMathSlot.RoundType roundType,
                              List<RoundHlrResult> roundHlrResultList,
                              CommonInputResult commonInputResult,
                              GameStateInputResult gameStateInputResult) {
        this.conditionId = conditionId;
        this.gameStateIndex = gameStateIndex;
        this.gameStateId = gameStateId;
        this.totalWin = totalWin;
        this.gameStateType = gameStateType;
        this.gameStateHlrResultExtend = gameStateHlrResultExtend;
        this.roundType = roundType;
        this.roundHlrResultList = roundHlrResultList;
        this.commonInputResult = commonInputResult;
        this.gameStateInputResult = gameStateInputResult;
    }

    public GameStateHlrResult() {
        this.conditionId = 0;
        this.gameStateIndex = -1;
        this.gameStateId = -1;
        this.totalWin = 0;
        this.gameStateType = ConstMathSlot.GameStateType.DEFAULT;
        this.gameStateHlrResultExtend = new GameStateHlrResultExtend();
        this.roundType = ConstMathSlot.RoundType.INVALID;
        this.roundHlrResultList = Collections.emptyList();
        this.commonInputResult = new CommonInputResult();
        this.gameStateInputResult = new GameStateInputResult();
    }

    public int getGameStateIndex() {
        return gameStateIndex;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    public int getConditionId() {
        return conditionId;
    }

    public GameStateHlrResultExtend getGameStateHlrResultExtend() {
        return gameStateHlrResultExtend;
    }

    public List<RoundHlrResult> getRoundHlrResultList() {
        return roundHlrResultList;
    }

    public ConstMathSlot.RoundType getRoundType() {
        return roundType;
    }

    public CommonInputResult getCommonInputResult() {
        return commonInputResult;
    }

    public GameStateInputResult getGameStateInputResult() {
        return gameStateInputResult;
    }
}
