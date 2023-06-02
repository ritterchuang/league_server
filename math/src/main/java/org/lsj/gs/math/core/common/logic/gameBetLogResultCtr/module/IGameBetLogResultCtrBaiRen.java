package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 下注記錄計算器百人介面
public interface IGameBetLogResultCtrBaiRen extends IGameBetLogResultCtr {

    void addDetailPlayBaiRen(IDetailPlayBaiRen detailPlayBaiRen); // 新增百人遊戲詳細記錄

    IGameBetLogResultBaiRen calculateGameBetLogResult(String roundId, UidScore uidScore); // 計算下注紀錄結果
}
