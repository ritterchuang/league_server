package org.lsj.gs.math.core.slot.accumulateWinCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.utils.MathUtil;

import java.util.List;

// 累積得分計算者 [分數一直往後疊加]
public class AccumulateWinCtr {

    // 計算當前場次累積贏分
    public AccumulateWinCtrResult calculateAccumulateWinCtrResultRound(
            int currentRoundIndex,
            double currentRoundWin,
            GameStateHlrResult beforeGameStateHlrResult,
            List<RoundHlrResult> roundHlrResultList
            ) {
        // 1. 第一場 且 為初始狀態之後
        if (currentRoundIndex == 0 && beforeGameStateHlrResult.getGameStateType().equals(ConstMathSlot.GameStateType.DEFAULT)) {
            return new AccumulateWinCtrResult(0, MathUtil.moneyScale(currentRoundWin));
        }

        // 1. 第一場 且 為初始狀態之後
        if (currentRoundIndex == 0 && beforeGameStateHlrResult.getGameStateType().equals(ConstMathSlot.GameStateType.DEFAULT) == false) {
            double beforeAccWin = beforeGameStateHlrResult.getRoundHlrResultList().get(beforeGameStateHlrResult.getRoundHlrResultList().size() - 1).getAccumulateWinCtrResult().getAfterAccWin();
            double afterAccWin = MathUtil.add(beforeAccWin, currentRoundWin);
            return new AccumulateWinCtrResult(MathUtil.moneyScale(beforeAccWin), MathUtil.moneyScale(afterAccWin));
        }

        // 2. 之後場次，為前一場往上加
        double beforeAccWin = roundHlrResultList.get(currentRoundIndex - 1).getAccumulateWinCtrResult().getAfterAccWin();
        double afterAccWin = MathUtil.add(beforeAccWin, currentRoundWin);
        return new AccumulateWinCtrResult(MathUtil.moneyScale(beforeAccWin), MathUtil.moneyScale(afterAccWin));
    }

    // 計算當前消除次數累積得分 TODO test
    public AccumulateWinCtrResult calculateAccumulateWinCtrResultCascade(
            int currentCascadeTimes,
            double currentCascadeWin,
            List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 第一場
        if (currentCascadeTimes == 0) {
            return new AccumulateWinCtrResult(0, MathUtil.moneyScale(currentCascadeWin));
        }

        // 2. 之後場次，為前一場往上加
        double beforeAccWin = cascadeHlrResultList.get(currentCascadeTimes - 1).getAccumulateWinCtrResult().getAfterAccWin();
        double afterAccWin = MathUtil.add(beforeAccWin, currentCascadeWin);
        return new AccumulateWinCtrResult(MathUtil.moneyScale(beforeAccWin), MathUtil.moneyScale(afterAccWin));
    }
}
