package org.lsj.gs.math.core.common.table;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.tableConfigMgr.TableConfigMgr;
import org.lsj.gs.user.IUser;
import org.lsj.websocket.ReceiveGameCommand;

import javax.websocket.Session;

// 服務端 Message 卡牌介面
public interface ISeverTableMessageCard extends IServerTableCommon{
    //* 流程相關 *//
    void startBeforeGameBegin(); // 啟動遊戲開始前處理
    void startAfterGameEnd(); // 啟動遊戲結束後處理
    void startForceStop(); // 啟動強制靜止

    //* 傳輸協議相關 *//
    void sendCutReturn(Session session); // 傳送斷線資訊
    void receivedGameCommand(ReceiveGameCommand mathCommand); // 接收客戶端封包
    void sendUserList(); // 傳送所有玩家列表
    void sendHumanUpdateScore(); // 傳送真人玩家金額
    void sendRoundLogId(); // 傳送局號

    //* 牌桌設定管理器 *//
    TableConfigMgr getTableConfigMgr(); // 取得牌桌設定管理器

    //* 水池相關 *//
    IPoolConfig getPoolConfig(); // 取得水池

    //* 遊戲結果相關 *//
    IGameBetLogResultCard getResult(); // 取得遊戲結果

    //* 牌桌資訊 *//
    IUser getUser(); // 取得真人使用者
    int getGameId(); // 取得遊戲識別碼
    String getRoundId(); // 取得局號
}
