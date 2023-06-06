package org.lsj.gs.math.core.common.table;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturn;
import org.lsj.gs.math.entity.CmdOut_NgSpin;

import java.util.Optional;

// 服務端 Command 老虎機介面
public interface ISeverTableCommandSlot extends IServerTableCommon{
    //* 流程相關 *//
    void startBeforeGameEnd(); // 啟動遊戲結束前處理

    //* 遊戲玩家相關 *//
    double getSpinCost(String ctsSpinRequestString) throws TableException; // 取得玩家下注金額

    //* 遊戲設定相關 *//
    MathSlotConfig getMathSlotConfig(); // 取得數值設定

    //** 水池相關 **//
    IPoolConfig getPoolConfig(); // 取得水池
    void updatePoolConfig(IPoolConfig poolConfig); // 更新水池資訊

    //* 遊戲結果相關 *//
    IGameBetLogResultSlot getSpinResult(String ctsSpinRequestString) throws TableException; // 取得打擊結果

    // TODO 應急用
    CmdOut_NgSpin getSpinResult2(String ctsSpinRequestString); // 取得打擊結果

    void sendCmdOutResultToHumanPlayer(); // 將結果送給客端

    void sendSpinResultToHumanPlayer(); // 將打擊結果送給客端

    //* 斷線重連相關 *//
    void updateLastPlayedProgressResult(String ctsLastPlayedProgressResult) throws TableException; // 更新客端表演進度

    Optional<SlotCutReturn> getSlotCutReturn(); // 取得斷線重連資訊

    //* 亂數相關 *//
    void setRngData(String rngDataString); // 設定亂數
}
