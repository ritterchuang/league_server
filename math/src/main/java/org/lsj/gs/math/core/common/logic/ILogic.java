package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;

import java.util.List;
import java.util.Map;

// 抽象邏輯介面
public interface ILogic {
    //* 遊戲玩家處理器 *//
    //** 玩家資訊相關 **//
    Map<Integer, GamePlayer> getAllGamePlayerMap(); // 取得所有玩家
    List<Integer> getAllGamePlayerChairIndexList(); //取得玩家座位

    //** 真人相關 **//
    GamePlayer getHumanGamePlayer(); // 取得真人玩家
    int getHumanUid(); // 取得真人玩家識別碼
    int getHumanChairIndex(); // 取得真人玩家座位索引
    double getHumanPlayerBeginMoney(); // 取得真人起始金額
    double getHumanPlayerAfterMoney(); // 取得真人剩餘金額
    boolean isHumanChairIndex(int chairIndex); // 判斷是否為真人
    boolean isValidChairIndex(int chairIndex); // 判斷座位是否合法
    UidScore getHumanUidScore(); // 取得真人分數

    //* 遊戲投注紀錄計算器 *//
    void updateBetTime(); // 更新起始時間
    void addDetail(Object detail); // 新增詳細日誌
    void addDetailGameBegin(); // 新增遊戲開始詳細日誌
    void addDetailGameBeginTime(); // 新增遊戲開始 時間詳細日誌
    void addDetailGameBeginPlayer(); // 新增遊戲開始 玩家資訊詳細記錄
}
