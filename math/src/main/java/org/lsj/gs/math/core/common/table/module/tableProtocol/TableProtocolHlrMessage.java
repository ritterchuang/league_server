package org.lsj.gs.math.core.common.table.module.tableProtocol;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.table.entity.message.core.*;
import org.lsj.gs.math.core.common.table.entity.message.core.data.StcUserListData;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 遊戲桌傳輸處理者 Message
public class TableProtocolHlrMessage {
    private final ITableUtil tableUtil; // 牌桌工具包

    public TableProtocolHlrMessage(ITableUtil tableUtil) {
        this.tableUtil = tableUtil;
    }

    //* 會話相關 *//
    // 傳送封包訊息給玩家
    public void sendMessageBySession(IMessage message, Session session) {
        this.tableUtil.getWebSocketUtil().sendResponse(session, message.toString());
    }

    // 傳送節點給玩家
    public void sendMessageByNode(String main, String sub, ObjectNode configNode, Session session){
        this.tableUtil.getWebSocketUtil().sendResponse(session, main, sub, configNode);
    }


    //* 玩家相關 *//

    // 轉換玩家列表
    public StcUserListData[] transformGamePlayerList2UserListArray(List<GamePlayer> gamePlayerList, GamePlayer humanGamePlayer){
        List<StcUserListData> userList = new ArrayList<>();
        userList.add(new StcUserListData(humanGamePlayer));
        gamePlayerList.stream().filter(GamePlayer::isRobot).forEach(player -> userList.add(new StcUserListData(player)));

        return userList.toArray(StcUserListData[]::new);
    }

    // 傳送玩家資訊
    public void sendUpdateUser(int uid, double dollar, Session session){
        this.sendMessageBySession(
                new StcUpdateUserFrame(uid, dollar),
                session
        );
    }

    // 傳送所有玩家列表
    public void sendUserList(List<GamePlayer> gamePlayerList, GamePlayer humanGamePlayer, Session session) {
        this.sendMessageBySession(
                new StcUserList(this.transformGamePlayerList2UserListArray(gamePlayerList, humanGamePlayer)),
                session);
    }

    // 傳送真人玩家金額
    public void sendHumanUpdateScore(double[] allPlayerBeginMoneyArray, Session session) {
        this.sendMessageBySession(
                new StcUpdateScores(Arrays.stream(allPlayerBeginMoneyArray).boxed().collect(Collectors.toList()).stream().mapToDouble(d-> MathUtil.moneyScale(d)).toArray()),
                session);
    }


    //* 遊戲桌相關 *//

    // 傳送設定
    public void sendConfig(ObjectNode configNode, Session session){
        this.sendMessageByNode(
                ConstMessageCore.MessageEnum.STC_CONFIG.getMain(),
                ConstMessageCore.MessageEnum.STC_CONFIG.getSub(),
                configNode,
                session);
    }

    // 傳送狀態更新訊息
    public void sendUpdateState(int enterStateIndex, Session session){
        this.sendMessageBySession(new StcUpdateStage(enterStateIndex), session);
    }

    // 傳送局號
    public void sendRoundLogId(String roundId, Session session) {
        this.sendMessageBySession(new StcUpdateLogId(roundId), session);
    }

    // 重設
    public void reset() {}
}
