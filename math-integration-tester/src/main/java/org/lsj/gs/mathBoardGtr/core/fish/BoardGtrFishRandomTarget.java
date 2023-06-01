package org.lsj.gs.mathBoardGtr.core.fish;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.fish.CtsGetHitResultData;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.fish.GameResultExtendFishJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.utils.JsonUtil;

public class BoardGtrFishRandomTarget extends AbstractBoardGtrFish {
    public BoardGtrFishRandomTarget(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    // 產生局結果
    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 建構打擊請求
        CtsGetHitResultData ctsGetHitResultData = super.createRandomHitCtsGetHitResultData(super.mathFishConfig);

        // 2. 取得打擊結果
        IGameBetLogResult gameBetLogResult = null;
        try {
            gameBetLogResult = super.table.getHitResult(JsonUtil.getInstance().writeValueAsStringWithoutException(ctsGetHitResultData));

        } catch (TableException e) {
            e.printStackTrace();
        }

        // 3. 更新獎勵子彈資訊
        HitResult hitResult = super.table.getLogic().getHitResult();
        super.clientBulletGtr.updateAwardBullet(hitResult.getAwardBulletType(), hitResult.getAwardBulletExtend(), super.table.getLogic().getCurrentBullet().getBet());

        // 4. 封裝
        return new BoardGtrResult(
                super.calculateStatisticResult(),
                gameBetLogResult,
                new GameResultExtendFishJava(
                        ctsGetHitResultData.getBullet().getBulletIndex(),
                        ctsGetHitResultData.getTarget().getTargetIndex(),
                        gameBetLogResult.getGameBetLogObj().getValidBet(),
                        gameBetLogResult.getGameBetLogObj().getScore(),
                        super.table.getLogic().getCurrentBullet().getBet(),
                        hitResult));
    }
}
