package org.lsj.gs.math.core.common.table.module.tableProtocol;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.websocket.ProtocolCode;

// 牌桌傳輸介面Command
public interface ITableProtocolCommand extends ITableProtocol{
    // 傳送封包給玩家
    void sendMessageToHumanPlayer(ProtocolCode protocolCode, String command, JsonNode dataJsonNode);

    // 傳送問題消息給玩家
    void sendErrorMessageToHumanPlayer(ProtocolCode protocolCode, String command);
}
