package org.lsj.gs.math.core.common.gameAdjust.module.shuffle;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResultFish;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.logic.ILogicFish;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.utils.MathUtil;

import java.util.Map;

// 捕魚發牌
public class ShuffleFish extends AbstractShuffle implements IShuffle {
    private final ILogicFish logic; // 邏輯

    public ShuffleFish(AlgorithmTypeCtr algorithmTypeCtr, ILogicFish logic, ITableUtil tableUtil) {
        super(algorithmTypeCtr, tableUtil);
        this.logic = logic;
    }

    // 發預遊戲結果
    @Override
    public PreGameAdjustResult dealPreGameAdjustResult(GameAdjustParameter gameAdjustParameter) {
        return new PreGameAdjustResultFish(this.logic.calculateHitResult(
                this.logic.getCurrentBullet(),
                this.logic.getCurrentTarget(),
                this.logic.getCurrentHit(),
                super.getShuffleType(),
                gameAdjustParameter));
    }

    // 應用預遊戲結果
    @Override
    public void applyPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        this.logic.setCurrentPreGameResult(preGameAdjustResult);
    }

    // 計算發牌結果
    @Override
    public GameAdjustResult calculateGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 計算玩家得分結果
        Map<Integer, UidScore> allPlayerUidScoreMap = this.logic.calculateUidScoreMap(((PreGameAdjustResultFish) preGameAdjustResult).getHitResult());

        // 2. 封裝結果
        return new GameAdjustResult(allPlayerUidScoreMap, preGameAdjustResult);
    }

    // 取得真人得分
    @Override
    public double calculateHumanScore(GameAdjustResult gameAdjustResult) {
        // 1. 取得此次預發牌結果
        HitResult hitResult = ((PreGameAdjustResultFish)gameAdjustResult.getPreGameResult()).getHitResult();

        // 2. 取得獎勵子彈預期得分
        double awardBulletExpectTotalWin = this.logic.calculateAwardBulletExpectTotalWin(hitResult);

        // 3. 取得玩家得分
        double playerScore = gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getScore();

        // 4. 回傳總得分
        return MathUtil.moneyScale(MathUtil.add(awardBulletExpectTotalWin, playerScore));
    }

    // 取得真人有效投注
    @Override
    public double calculateHumanValidBet(GameAdjustResult gameAdjustResult) {
        return gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getValidBet();
    }
}
