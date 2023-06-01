package org.lsj.websocket;

import com.lx.db.CompanyFieldObjBuilder;
import com.lx.gs.FieldConfigBuilder;
import com.lx.gs.math.core.common.table.ISeverTableCommandSlot;
import com.lx.gs.math.core.common.table.TableFactory;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.entity.CmdOut_NgSpin;
import com.lx.gs.pool.AgencyPool;
import com.lx.gs.pool.PersonControlConfig;
import com.lx.gs.user.User;
import com.lx.utils.JsonUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
        try {
            this.mathTable = tableFactory.createISeverTableCommandSlot(
                    new AtomicInteger(1),
                    new FieldConfigBuilder().setFieldConfigMap(new HashMap<>(){{
                        put(101, new CompanyFieldObjBuilder()
                                .setLimitMin(0)
                                .setLimitKick(0)
                                .setBase(1)
                                .createCompanyFieldObj());
                    }}).createFieldConfig(),
                    101,
                    new AgencyPool(),
                    new PersonControlConfig(null, null),
                    new User());
        } catch (TableException e) {
            e.printStackTrace();
        }
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
