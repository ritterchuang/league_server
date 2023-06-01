package org.lsj.gs.math.core.common.table;

import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.state.BaiRenGameState;
import org.lsj.gs.user.IUser;
import org.lsj.websocket.ReceiveGameCommand;

import java.util.List;

// 服務端Message百人介面
public interface ISeverTableMessageBaiRen extends IServerTableCommon{
    //* 流程相關 *//
    void enterTable(IUser user, double leftTimeSec); // 玩家入桌
    boolean leaveTable(); // 玩家離桌
    double updateGameState(BaiRenGameState gameState, double leftTimeSec); // 更新遊戲狀態並回傳延遲時間(秒)
    void switchBg(double leftTimeSec); // 切換頁籤處理

    //* 牌桌設定檔相關 *//
    BaiRenMathConfig getMathConfig(); // 取得牌桌數值設定

    //* 水池相關 *//
    void updatePoolConfig(IPoolConfig poolConfig); // 更新水池

    //* 遊戲結果相關 *//
    IGameBetLogResultBaiRen getResult(); // 取得遊戲結果

    //* 傳輸協議相關 *//
    void receivedGameCommand(ReceiveGameCommand mathCommand); // 接收客戶端封包

    //* 牌桌資訊 *//
    int getTableId(); // 取得牌桌識別碼
    List<Object> getChartList(); // 取得路圖列表
    int getPlayerNum(); // 取得玩家人數
    boolean isHumanBet(); // 玩家是否押注標誌

    //* 亂數相關 *//
    void setRngData(String rngDataString); // 設定亂數
}
