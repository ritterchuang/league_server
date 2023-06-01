package org.lsj.gs.math.core.common.table.module.tableGame;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.AreaBetResultBaiRen;
import org.lsj.gs.math.core.common.table.entity.message.baiRen.CtsBetBaiRenJava;
import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;
import org.lsj.gs.user.IUser;

// 遊戲介面 百人
public interface ITableGameBaiRen extends ITableGame{
    //* 牌桌資訊 *//
    long calculateSpendSec(); // 計算花費時間(秒)

    //* 流程相關 *//
    void enterTable(IUser user, double leftTimeSec); // 真人入桌
    boolean isMoneyEnough(int chairIndex, int previousTotalBet, int chips); // 驗證玩家餘額
    boolean isMoneyEnoughToPayRate(int chairIndex, int previousTotalBet, int chips, int typeRate); // 驗證玩家餘額夠不夠賠倍數
    boolean leaveTable(); // 真人離桌

    //* 遊戲設定相關 *//
    BaiRenMathConfig getMathConfig(); // 取得牌桌數值設定
    int getPlayerNum(); // 取得玩家人數

    //* 水池相關 *//
    void updatePoolConfig(IPoolConfig poolConfig); // 更新水池

    //* 傳送協議相關 *//
    void sendUserList(); // 傳送所有玩家列表
    void sendUpdateUser(int uid, double dollar); // 傳送玩家餘額資訊
    void sendRoundLogId(); // 傳送局號
    void sendConfig(ObjectNode configNode); // 傳送設定

    void sendAction(int actionCode, double actionTimeSec); // 傳送動作
    void sendArea(); // 傳送區域押注
    void sendBet(AreaBetResultBaiRen areaBetResult); // 傳送玩家下注訊息
    void sendBetResult(int chairIndex, CtsBetBaiRenJava ctsBetBaiRenJava, int code); // 傳送下注結果訊息
    void sendChart(); // 傳送路線圖
    void sendMoney(); // 傳送富豪榜與真人餘額
    void sendOwnBet(); // 傳送下注訊息
    void sendRichList(); // 傳送下注前 富豪榜
    void sendUserNum(); // 傳送玩家人數

    //* 定時器相關 *//
    ITableTimer getTableTimer(); // 取得牌桌定時器

    //* 亂數相關 *//
    void setRngData(String rngDataString); // 設定亂數

    //* 重置相關 *//
    void reset(); // 重置牌桌
}
