package org.lsj.websocket;

import org.lsj.math.entity.CmdOut_NgSpin;
import org.lsj.math.entity.LinePassReelIndex;
import org.lsj.util.JsonUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@ServerEndpoint(value = "/")
@ApplicationScoped
public class SlotWebSocketServer {

//    private static final Logger LOG = LoggerFactory.getLogger(SlotWebSocketServer.class);

    @OnOpen
    public void onOpen(Session session) {
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

        // 2. 生成假資料(無視輸入)
        String fakeDataString = this.generateFakeDataString();
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

    // 生成假資料
    private String generateFakeDataString(){
        CmdOut_NgSpin cmdOut_ngSpin = new CmdOut_NgSpin(
                new ArrayList<>(){{
                    add(1);add(1);add(1);add(1);add(1);
                    add(2);add(2);add(2);add(2);add(2);
                    add(3);add(3);add(3);add(3);add(3);
                }},
                new ArrayList<>(){{
                    add(new LinePassReelIndex(
                            new ArrayList<>(){{add(2);add(2);add(2);add(2);add(2);}},
                            2,
                            2,
                            20
                    ));
                }},
                20,
                3,
                5
        );
        return JsonUtil.getInstance().writeValueAsStringWithoutException(cmdOut_ngSpin);
    }
}
