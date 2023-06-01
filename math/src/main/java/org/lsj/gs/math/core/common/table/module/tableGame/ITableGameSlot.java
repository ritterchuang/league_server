package org.lsj.gs.math.core.common.table.module.tableGame;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturn;

import java.util.Optional;

// 遊戲介面 老虎機
public interface ITableGameSlot extends ITableGame{
    //* 流程相關 *//
    void startBeforeGameEnd(); // 啟動遊戲結束前處理

    //* 遊戲設定相關 *//
    MathSlotConfig getMathSlotConfig(); // 取得數值設定

    //* 遊戲玩家相關 *//
    double getSpinCost(String ctsSpinRequestString) throws TableException; // 取得玩家下注金額

    boolean checkHumanBeginMoneyEnough(double cost); // 檢查真人玩家初始金額足夠性

    void updateHumanAfterMoney(double score); // 更新真人玩家初始金額

    //* 水池相關 *//
    void updatePoolConfig(IPoolConfig poolConfig); // 更新水池資訊

    //* 遊戲結果相關 *//
    IGameBetLogResultSlot getSpinResult(String ctsSpinRequestString) throws TableException; // 取得打擊結果

    void sendSpinResultToHumanPlayer(); // 傳送打擊結果給客端

    //* 斷線重連相關 *//
    Optional<SlotCutReturn> getSlotCutReturn(); // 取得斷線重連資訊

    void updateLastPlayedProgressResult(String ctsLastPlayedProgressResult) throws TableException; // 更新客端表演進度

    //* 亂數相關 *//
    void setRngData(String rngDataString); // 設定亂數

    //* 重置相關 *//
    void reset(); // 重置
}
