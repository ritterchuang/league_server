package org.lsj.gs.math.core.common.table.module.tableGame;

import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;

import javax.websocket.Session;
import java.util.List;

// 遊戲介面 棋牌
public interface ITableGameCard extends ITableGame{
    //* 牌桌資訊 *//
    long calculateSpendSec(); // 計算花費時間(秒)
    int getGameId(); // 取得遊戲編碼

    //* 流程相關 *//
    void startBeforeGameBegin(); // 啟動遊戲開始前處理
    void startAfterGameEnd(); // 啟動遊戲結束後處理
    void startForceStop(); // 啟動強制靜止

    //* 遊戲玩家相關 *//
    List<GamePlayer> getAllGamePlayerList(); // 取得所有玩家資訊

    //* 傳輸協議相關 *//
    void sendUpdateUser(int uid, double dollar); // 傳送玩家資訊
    void sendUserList(); // 傳送所有玩家列表
    void sendHumanUpdateScore(); // 傳送真人玩家金額
    void sendRoundLogId(); // 傳送局號

    //* 遊戲結果相關 *//
    IGameBetLogResult getResult(); // 取得局結果

    //* 定時器相關 *//
    ITableTimer getTableTimer(); // 取得牌桌定時器
    int getLeftSecond(int timerId); // 取得指定定時器剩餘時間

    //* 斷線相關 *//
    void sendCutReturn(Session session); // 傳送斷線資訊
}
