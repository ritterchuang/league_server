package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.detailCtr.module.IDetailCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.HitCtrMgr;

import java.util.Optional;

// 下注記錄計算器魚機介面
public interface IGameBetLogResultCtrFish extends IGameBetLogResultCtr {

    IGameBetLogResultFish calculateGameBetLogResultFish(String roundId, UidScore uidScore); // 計算下注紀錄結果

    IGameBetLogResultFish calculateErrorBulletUsageGameBetLogResultFish(String roundId); // 計算錯誤子彈使用時機下注紀錄結果

    Optional<IGameBetLogResultFish> calculateReturnGameBetLogResultFish(String roundId, HitCtrMgr hitCtrMgr, BulletMgr bulletMgr, IDetailCtr detailCtr); // 計算返還結果
}
