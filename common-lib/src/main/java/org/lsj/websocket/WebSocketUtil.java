package org.lsj.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

// 傳輸協議工具
public class WebSocketUtil implements IWebSocketUtil {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketUtil.class);

    // 單例模式
    private static final WebSocketUtil instance = new WebSocketUtil();

    public static WebSocketUtil getInstance() {
        return instance;
    }

    @Override
    public void sendResponse(Session session, String message) {
        ByteBuffer messageByteBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        sendBinary(session, messageByteBuffer);
    }

    @Override
    public void sendResponse(Session session, String main, String sub, JsonNode data) {
        ObjectNode responseNode = JsonUtil.getInstance().getObjectMapper().createObjectNode()
                .put("main", main)
                .put("sub", sub)
                .set("data", data);
        ByteBuffer messageByteBuffer = ByteBuffer.wrap(responseNode.toString().getBytes(StandardCharsets.UTF_8));
        sendBinary(session, messageByteBuffer);
    }

    @Override
    public void sendResponse(Session session, ProtocolCode protocolCode, String command, JsonNode dataJsonNode) {
        final JsonNode responseJsonNode = JsonUtil.getInstance().getObjectMapper().createObjectNode()
                .put("code", protocolCode.getCode())
                .put("command", command)
                .set("data", dataJsonNode);

        ByteBuffer messageByteBuffer = ByteBuffer.wrap(responseJsonNode.toString().getBytes(StandardCharsets.UTF_8));
        sendBinary(session, messageByteBuffer);
    }

    @Override
    // 回應錯誤
    public void sendErrorResponse(Session session, ProtocolCode code) {
        final ObjectNode dataObjectNode = new ObjectMapper().createObjectNode().put("code", code.getCode());
        final String main = (String) session.getUserProperties().get("main");
        final String sub = (String) session.getUserProperties().get("sub");

        JsonNode responseJsonNode;
        if (main == null || main.equals("")
                || sub == null || sub.equals("")) {
            responseJsonNode = dataObjectNode;
        } else {
            responseJsonNode = JsonUtil.getInstance().getObjectMapper().createObjectNode()
                    .put("main", main)
                    .put("sub", sub)
                    .set("data", dataObjectNode);
        }
        ByteBuffer messageByteBuffer = ByteBuffer.wrap(responseJsonNode.toString().getBytes(StandardCharsets.UTF_8));
        sendBinary(session, messageByteBuffer);
    }

    public void sendErrorResponse(Session session, ProtocolCode code, String command) {
        final ObjectNode dataObjectNode = new ObjectMapper().createObjectNode()
                .put("code", code.getCode())
                .put("command", command);

        ByteBuffer messageByteBuffer = ByteBuffer.wrap(dataObjectNode.toString().getBytes(StandardCharsets.UTF_8));
        sendBinary(session, messageByteBuffer);
    }

    private void sendBinary(Session session, ByteBuffer messageByteBuffer) {
        try {
            if (session.isOpen()) {
                LOG.debug("{} send response, message: {}", LogUtil.getLogPrefix(session, 0), new String(messageByteBuffer.array(), StandardCharsets.UTF_8));
                session.getBasicRemote().sendBinary(messageByteBuffer);
            } else {
                LOG.warn("{} session not open, message: {}", LogUtil.getLogPrefix(session, 0), new String(messageByteBuffer.array(), StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            LOG.error("{} send message error, message: {}, exMessage: {}", LogUtil.getLogPrefix(session, 0), e.getMessage(), new String(messageByteBuffer.array(), StandardCharsets.UTF_8), e);
        }
    }
}
