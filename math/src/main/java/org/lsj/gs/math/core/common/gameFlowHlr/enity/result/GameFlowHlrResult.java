package org.lsj.gs.math.core.common.gameFlowHlr.enity.result;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResult;

import java.util.List;

// 遊戲流程處理者結果
public class GameFlowHlrResult {
    private final double totalWin; // 總得分
    private final List<GameStateHlrResult> gameStateHlrResultList; // 由謝狀態結果列表
    private final AnimationResult animationResult; // 動畫表演結果

    public GameFlowHlrResult(double totalWin, List<GameStateHlrResult> gameStateHlrResultList, AnimationResult animationResult) {
        this.totalWin = totalWin;
        this.gameStateHlrResultList = gameStateHlrResultList;
        this.animationResult = animationResult;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public List<GameStateHlrResult> getGameStateHlrResultList() {
        return gameStateHlrResultList;
    }

    public AnimationResult getAnimationResult() {
        return animationResult;
    }
}
