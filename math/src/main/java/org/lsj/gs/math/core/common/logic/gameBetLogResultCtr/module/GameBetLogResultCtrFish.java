package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.db.GameBetLogObj;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.GameBetLogResultFish;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.detailCtr.enity.client.DetailFishReturn;
import org.lsj.gs.math.core.fish.detailCtr.module.IDetailCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.HitCtrMgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 下注記錄計算器魚機
public class GameBetLogResultCtrFish extends AbstractGameBetLogResultCtr implements IGameBetLogResultCtrFish {

    public GameBetLogResultCtrFish(long beginTime, int tableId, TableFieldConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(beginTime, tableId, config, gamePlayerHlr, poolCtr, tableUtil);
    }


    // 計算下注紀錄結果
    public IGameBetLogResultFish calculateGameBetLogResultFish(String roundId, UidScore uidScore) {
        // 1. 計算下注記錄物件
        GameBetLogObj gameBetLogObj = super.calculateGameBetLogObj(roundId, uidScore);

        // 2. 封裝
        return new GameBetLogResultFish(
                gameBetLogObj,
                new ArrayList<>(super.detail),
                super.poolCtr.getPersonControlLogString(),
                super.tableUtil.getMainRandomUtil().getRandomNumberUsedList(),
                true);
    }

    // 計算錯誤子彈使用時機下注紀錄結果
    public IGameBetLogResultFish calculateErrorBulletUsageGameBetLogResultFish(String roundId) {
        // 1. 計算UidScore
        UidScore uidScore = new UidScore(super.gamePlayerHlr.getHumanChairIndex(), super.gamePlayerHlr.getHumanUid(), 0.0, 0.0, 0.0, 0.0, 0.0);

        // 2. 計算下注紀錄物件
        GameBetLogObj gameBetLogObj = super.calculateGameBetLogObj(roundId, uidScore);

        // 3. 封裝
        return new GameBetLogResultFish(
                gameBetLogObj,
                new ArrayList<>(),
                super.poolCtr.getPersonControlLogString(),
                super.tableUtil.getMainRandomUtil().getRandomNumberUsedList(),
                false);
    }

    // 計算返還結果
    public Optional<IGameBetLogResultFish> calculateReturnGameBetLogResultFish(String roundId, HitCtrMgr hitCtrMgr, BulletMgr bulletMgr, IDetailCtr detailCtr) {
        // 1. 計算返還金額
        double returnValue = bulletMgr.calculateReturnValue();

        // 2. 若無返還，回傳空
        if (returnValue <= 0.0) {
            return Optional.empty();
        }

        // 3. 計算玩家輸贏結果
        UidScore uidScore = hitCtrMgr.calculateReturnUidScore(bulletMgr, super.gamePlayerHlr.getHumanGamePlayer(), returnValue);

        // 4. 計算下注紀錄物件
        GameBetLogObj gameBetLogObj = super.calculateGameBetLogObj(roundId, uidScore);

        // 5. 計算返還詳細記錄
        Optional<DetailFishReturn> detailFishReturn = detailCtr.calculateDetailFishReturn(bulletMgr);

        // 4. 封裝
        return Optional.of(new GameBetLogResultFish(
                gameBetLogObj,
                List.of(detailFishReturn.get()),
                super.poolCtr.getPersonControlLogString(),
                super.tableUtil.getMainRandomUtil().getRandomNumberUsedList(),
                true));
    }
}
