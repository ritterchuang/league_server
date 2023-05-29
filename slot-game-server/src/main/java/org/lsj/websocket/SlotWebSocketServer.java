package org.lsj.websocket;

import org.lsj.math.core.ISeverTableCommandSlot;
import org.lsj.math.core.TableFactory;
import org.lsj.math.entity.CmdOut_NgSpin;
import org.lsj.util.JsonUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@ServerEndpoint(value = "/")
@ApplicationScoped
public class SlotWebSocketServer {
    // 牌桌工廠
    private TableFactory tableFactory = new TableFactory();

    // 虎機牌桌 TODO
    ISeverTableCommandSlot mathTable;

//    private static final Logger LOG = LoggerFactory.getLogger(SlotWebSocketServer.class);

    @OnOpen
    public void onOpen(Session session) {
        // 1. 建立牌桌 TODO 資訊
        this.mathTable = tableFactory.createISeverTableCommandSlot(1, fieldConfig, 101, agencyPool, personControlConfig, user);
//        LOG.info("{} session connected, session id: {}", LogUtil.getLogPrefix(session, 0), session.getId());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
//        LOG.info("{} session closed, session id: {}, close reason: {}, reason phrase: {}"
//                , LogUtil.getLogPrefix(session, 0)
//                , session.getId(), closeReason.getCloseCode(), closeReason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
//        LOG.error("{} session onError, message: {}", LogUtil.getLogPrefix(session, 0), throwable.getMessage(), throwable);
    }

    @OnMessage
    public void onMessage(ByteBuffer messageBytes, Session session) {
        String message = new String(messageBytes.array(), StandardCharsets.UTF_8);
//        LOG.debug("{} message: {}", LogUtil.getLogPrefix(session, 0), message);

        // 1. 打印輸入參數
        System.out.println("received message: " + message);

        // 2. 取得結果 TODO 與客端協議 仍在設計中
        CmdOut_NgSpin cmdOut_ngSpin = this.mathTable.getSpinResult2(message);
        String fakeDataString = JsonUtil.getInstance().writeValueAsStringWithoutException(cmdOut_ngSpin);
        ByteBuffer messageByteBuffer = this.stringToByteBuffer(fakeDataString);
        System.out.println("send message: " + fakeDataString);
        try {
            session.getBasicRemote().sendBinary(messageByteBuffer);
//            if (session.isOpen()) {
////                LOG.debug("{} send response, message: {}", LogUtil.getLogPrefix(session, 0), new String(messageByteBuffer.array(), StandardCharsets.UTF_8));
//            } else {
//                LOG.warn("{} session not open, message: {}", LogUtil.getLogPrefix(session, 0), new String(messageByteBuffer.array(), StandardCharsets.UTF_8));
//            }
        } catch (Exception e) {
//            LOG.error("{} send message error, message: {}, exMessage: {}", LogUtil.getLogPrefix(session, 0), e.getMessage(), new String(messageByteBuffer.array(), StandardCharsets.UTF_8), e);
            e.printStackTrace();
        }
    }

    // 字串 轉 字節緩衝區
    private ByteBuffer stringToByteBuffer(String string){
        return ByteBuffer.wrap(string.getBytes(StandardCharsets.UTF_8));
    }
}
