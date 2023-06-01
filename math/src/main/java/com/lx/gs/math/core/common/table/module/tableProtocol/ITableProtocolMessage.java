package com.lx.gs.math.core.common.table.module.tableProtocol;

import com.lx.gs.math.core.common.table.entity.message.core.IMessage;
import com.lx.websocket.ReceiveGameCommand;

// 牌桌傳輸介面Message
public interface ITableProtocolMessage extends ITableProtocol{
    //* 接收封包相關 *//
    void receivedGameCommand(ReceiveGameCommand mathCommand); // 接收客戶端封包

    //* 發送封包相關 *//
    void sendMessageToHumanPlayer(IMessage message); // 發送封包給真人玩家
}
