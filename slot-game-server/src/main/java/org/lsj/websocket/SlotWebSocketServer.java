package org.lsj.websocket;

import org.lsj.db.CompanyFieldObjBuilder;
import org.lsj.gs.FieldConfigBuilder;
import org.lsj.gs.math.core.common.table.ISeverTableCommandSlot;
import org.lsj.gs.math.core.common.table.TableFactory;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtend;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.entity.CmdOut_NgSpin;
import org.lsj.gs.pool.AgencyPool;
import org.lsj.gs.pool.PersonControlConfig;
import org.lsj.gs.user.User;
import org.lsj.gs.user.UserBdr;
import org.lsj.utils.JsonUtil;

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
                    new FieldConfigBuilder()
                            .setGameId(1)
                            .setMinUser((short) 1)
                            .setMaxUser((short) 1)
                            .setFieldConfigMap(new HashMap<>(){{
                        put(1101, new CompanyFieldObjBuilder()
                                .setGameId(1)
                                .setLimitMin(0)
                                .setLimitKick(0)
                                .setBase(1)
                                .createCompanyFieldObj());
                    }}).createFieldConfig(),
                    1101,
                    new AgencyPool(),
                    new PersonControlConfig(null, null),
                    new UserBdr()
                            .setBalance(10000)
                            .setSession(session)
                            .createUser());
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
        try {
            this.mathTable.getSpinResult(JsonUtil.getInstance().writeValueAsStringWithoutException(
                    new ClientSpinRequest(1, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, ConstMathSlot.BetSpinType.NONE_NORMAL,new BetSpinTypeExtend())
            ));

            this.mathTable.sendCmdOutResultToHumanPlayer();
        } catch (TableException e) {
            e.printStackTrace();
        }
//        CmdOut_NgSpin cmdOut_ngSpin = this.mathTable.getSpinResult2(message);
//        String fakeDataString = JsonUtil.getInstance().writeValueAsStringWithoutException(cmdOut_ngSpin);
//        ByteBuffer messageByteBuffer = this.stringToByteBuffer(fakeDataString);
//        System.out.println("send message: " + fakeDataString);
        try {
//            session.getBasicRemote().sendBinary(messageByteBuffer);
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
