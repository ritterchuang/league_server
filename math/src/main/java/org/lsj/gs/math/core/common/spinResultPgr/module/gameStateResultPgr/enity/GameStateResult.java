package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 客端遊戲狀態結果
public class GameStateResult {
    private final int gameStateIndex; // 遊戲狀態流水號
    private final int gameStateId; // 遊戲狀態識別碼
    private final double totalWin; // 總得分
    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型
    @JsonIgnore
    private final GameStateResultExtend gameStateResultExtend; // 遊戲狀態額外結果
    private final ConstMathSlot.RoundType roundType; // 局類型
    private final List<RoundResult> roundResultList; // 局結果列表

    public GameStateResult(int gameStateIndex, int gameStateId, double totalWin, ConstMathSlot.GameStateType gameStateType, GameStateResultExtend gameStateResultExtend, ConstMathSlot.RoundType roundType, List<RoundResult> roundResultList) {
        this.gameStateIndex = gameStateIndex;
        this.gameStateId = gameStateId;
        this.totalWin = totalWin;
        this.gameStateType = gameStateType;
        this.gameStateResultExtend = gameStateResultExtend;
        this.roundType = roundType;
        this.roundResultList = roundResultList;
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

    public GameStateResultExtend getGameStateResultExtend() {
        return gameStateResultExtend;
    }

    public ConstMathSlot.RoundType getRoundType() {
        return roundType;
    }

    public List<RoundResult> getRoundResultList() {
        return roundResultList;
    }
}
