package org.lsj.gs.mathStr.core.module;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.websocket.IWebSocketUtil;
import org.lsj.websocket.ProtocolCode;

import javax.websocket.Session;

// 空的傳輸協議工具
public class EmptyWebSocketUtil implements IWebSocketUtil {
    // POMELO格式
    @Override
    public void sendResponse(Session session, String message) {}
    @Override
    public void sendResponse(Session session, String main, String sub, JsonNode data) {}
    @Override
    public void sendErrorResponse(Session session, ProtocolCode code) {}

    // COMMAND格式
    @Override
    public void sendResponse(Session session, ProtocolCode protocolCode, String command, JsonNode dataJsonNode) {}
    @Override
    public void sendErrorResponse(Session session, ProtocolCode code, String command) {}
}
