package org.lsj.websocket;

import com.fasterxml.jackson.databind.JsonNode;

import javax.websocket.Session;

// 傳輸協議工具介面
public interface IWebSocketUtil {
    // POMELO格式
    void sendResponse(Session session, String message);
    void sendResponse(Session session, String main, String sub, JsonNode data);
    void sendErrorResponse(Session session, ProtocolCode code);

    // COMMAND格式
    void sendResponse(Session session, ProtocolCode protocolCode, String command, JsonNode dataJsonNode);
    void sendErrorResponse(Session session, ProtocolCode code, String command);
}
