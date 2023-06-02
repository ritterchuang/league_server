package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;

// 百人邏輯介面
public interface ILogicBaiRen extends ILogic {
    void updateAreaBet(int chairIndex, int area, int chips); // 更新投注區域
    int getPlayerTotalAreaBet(int chairIndex); // 取得玩家總押注金額
    void addDetailBaiRen(IDetailPlayBaiRen detail); // 新增詳細日誌
    IGameBetLogResultBaiRen getGameBetLogResult(UidScore uidScore); // 取得遊戲結果
    void reset(); // 重設
}
