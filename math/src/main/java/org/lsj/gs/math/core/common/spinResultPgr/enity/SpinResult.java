package org.lsj.gs.math.core.common.spinResultPgr.enity;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.enity.GameStateResult;
import org.lsj.gs.math.core.slot.animationCtr.enity.result.AnimationResult;

import java.util.List;

// 客端老虎機結果
public class SpinResult {
    private final double totalWin; // 總得分
    private final List<GameStateResult> gameStateResultList; // 客端遊戲狀態結果列表
    private final AnimationResult animationResult; // 動畫結果

    public SpinResult(double totalWin, List<GameStateResult> gameStateResultList, AnimationResult animationResult) {
        this.totalWin = totalWin;
        this.gameStateResultList = gameStateResultList;
        this.animationResult = animationResult;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public List<GameStateResult> getGameStateResultList() {
        return gameStateResultList;
    }

    public AnimationResult getAnimationResult() {
        return animationResult;
    }
}
