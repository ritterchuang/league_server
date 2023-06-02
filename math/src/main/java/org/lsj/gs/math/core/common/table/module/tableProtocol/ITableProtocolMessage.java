package org.lsj.gs.math.core.common.table.module.tableProtocol;

import org.lsj.gs.math.core.common.table.entity.message.core.IMessage;
import org.lsj.websocket.ReceiveGameCommand;

// 牌桌傳輸介面Message
public interface ITableProtocolMessage extends ITableProtocol{
    //* 接收封包相關 *//
    void receivedGameCommand(ReceiveGameCommand mathCommand); // 接收客戶端封包

    //* 發送封包相關 *//
    void sendMessageToHumanPlayer(IMessage message); // 發送封包給真人玩家
}
