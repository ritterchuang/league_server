package org.lsj.gs.math.core.common.table.module;

import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.state.StateMgr;
import org.lsj.gs.math.core.common.tableConfigMgr.TableConfigMgr;
import org.lsj.gs.user.IUser;

// 牌桌基礎介面
public interface ITableBase {
    //* 牌桌資訊處理器 *//
    int getTableId(); // 取得桌號
    long getBeginTimeMillis(); // 取得起始時間(毫秒)
    String getRoundId(); // 取得局號
    String getTableLogTitle(); // 建立牌桌日誌標題

    //* 牌桌設定管理器 *//
    TableConfigMgr getTableConfigMgr(); // 取得牌桌設定管理器

    //* 遊戲玩家處理器 *//
    GamePlayerHlr getGamePlayerHlr(); // 取得遊戲玩家管理器
    GamePlayer getHumanGamePlayer(); // 取得真人玩家
    void updatePlayerBeginMoneyBeforeGame(); // 遊戲前更新玩家餘額
    IUser getUser(); // 取得真人使用者
    void updateUser(IUser user); // 更新真人

    //* 演算法類型計算器 *//
    AlgorithmTypeCtr getAlgorithmTypeCtr(); // 取得演算法類型計算器

    //* 水池控制器相關 *//
    PoolCtr getPoolCtr(); // 取得水池管理器
    IPoolConfig getPoolConfig(); // 取得水池
    void calculatePersonControlAllResult(); // 計算個人控所有結果

    //* 狀態機相關 *//
    StateMgr getStateMgr(); // 取得狀態管理器
    void registerState(); // 註冊狀態機
    void switchState(int exitStateId); // 切換狀態
    void sendUpdateState(int enterStateIndex); // 傳送狀態更新訊息
}
