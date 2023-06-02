package org.lsj.gs.math.core.common.gameAdjust.module.shuffle;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResultSlot;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameFlowHlr.GameFlowHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Map;

// 虎機發牌
public class ShuffleSlot extends AbstractShuffle implements IShuffle {
    private final ILogicSlot logic; // 邏輯
    private final GameFlowHlr gameFlowHlr; // 遊戲流程處理者

    public ShuffleSlot(AlgorithmTypeCtr algorithmTypeCtr, ILogicSlot logic, GameFlowHlr gameFlowHlr, ITableUtil tableUtil) {
        super(algorithmTypeCtr, tableUtil);
        this.logic = logic;
        this.gameFlowHlr = gameFlowHlr;
    }

    // 發預遊戲結果
    @Override
    public PreGameAdjustResult dealPreGameAdjustResult(GameAdjustParameter gameAdjustParameter) {
        // 1. 由 logicSlot 計算高低表
        ConstMathSlot.ReelRtpType reelRtpType = this.logic.getReelHEType(super.getShuffleType(), gameAdjustParameter);

        // 2. 依照高低表類型計算結果
        GameFlowHlrResult gameFlowHlrResult = this.gameFlowHlr.calculateGameFlowHlrResult(reelRtpType);

        // 3. 回傳
        return new PreGameAdjustResultSlot(gameFlowHlrResult);
    }

    // 應用預遊戲結果
    @Override
    public void applyPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        this.logic.setCurrentPreGameResult(preGameAdjustResult);
    }

    // 計算發牌結果
    @Override
    public GameAdjustResult calculateGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 由 logic 計算 allPlayerUidScoreMap
        Map<Integer, UidScore> uidScoreMap = this.logic.calculateUidScoreMap(((PreGameAdjustResultSlot) preGameAdjustResult).getGameFlowHlrResult());

        // 2. 封裝結果
        return new GameAdjustResult(uidScoreMap, preGameAdjustResult);
    }

    // 取得真人得分
    @Override
    public double calculateHumanScore(GameAdjustResult gameAdjustResult) {
        return gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getScore();
    }

    // 取得真人有效投注
    @Override
    public double calculateHumanValidBet(GameAdjustResult gameAdjustResult) {
        return gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getValidBet();
    }
}
