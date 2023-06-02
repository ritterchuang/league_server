package org.lsj.gs.math.core.common.table.module.tableProtocol;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.websocket.ProtocolCode;

import javax.websocket.Session;

// 遊戲桌傳輸處理器 Command
public class TableProtocolHlrCommand {
    private final ITableUtil tableUtil; // 牌桌工具包

    public TableProtocolHlrCommand(ITableUtil tableUtil) {
        this.tableUtil = tableUtil;
    }

    // 傳送封包給玩家
    public void sendMessageToHumanPlayer(ProtocolCode protocolCode, String command, Session session, JsonNode dataJsonNode) {
        this.tableUtil.getWebSocketUtil().sendResponse(session, protocolCode, command, dataJsonNode);
    }

    // 傳送問題消息給玩家
    public void sendErrorMessageToHumanPlayer(ProtocolCode protocolCode, String command, Session session) {
        this.tableUtil.getWebSocketUtil().sendErrorResponse(session, protocolCode, command);
    }

    // 傳送狀態更新訊息
    public void sendUpdateState(int enterStateIndex) {
    }

    // 重設
    public void reset() {}
}
