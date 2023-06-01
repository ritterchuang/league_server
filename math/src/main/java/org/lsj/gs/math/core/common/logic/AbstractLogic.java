package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.entity.detail.DetailGameBegin;
import org.lsj.gs.math.core.common.table.entity.detail.DetailUserInfo;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

// 抽象邏輯
public abstract class AbstractLogic implements ILogic {
    protected final GamePlayerHlr gamePlayerHlr; // 遊戲玩家處理器
    protected final PoolCtr poolCtr; // 水池控制器
    protected final IGameBetLogResultCtr gameBetLogResultCtr; // 遊戲投注紀錄計算器
    protected final ITableBase table; // 牌桌

    public AbstractLogic(ITableBase table,
                         TableFieldConfig tableFieldConfig,
                         GamePlayerHlr gamePlayerHlr,
                         PoolCtr poolCtr,
                         IGameBetLogResultCtr gameBetLogResultCtr,
                         ITableUtil tableUtil){
        this.table = table;
        this.gamePlayerHlr = gamePlayerHlr;
        this.poolCtr = poolCtr;
        this.gameBetLogResultCtr = gameBetLogResultCtr;
    }

    //* 遊戲玩家處理器 *//
    //** 玩家資訊相關 **//
    // 取得所有玩家
    @Override
    public Map<Integer, GamePlayer> getAllGamePlayerMap(){
        return this.gamePlayerHlr.getAllGamePlayerMap();
    }

    // 取得所有玩家座位索引陣列
    @Override
    public List<Integer> getAllGamePlayerChairIndexList() {
        return this.gamePlayerHlr.getAllGamePlayerChairIndexList();
    }

    //** 真人相關 **//
    // 取得真人玩家
    @Override
    public GamePlayer getHumanGamePlayer(){
        return this.gamePlayerHlr.getAllGamePlayerMap().get(this.getHumanChairIndex());
    }

    // 取得真人玩家識別碼
    @Override
    public int getHumanUid() { return this.gamePlayerHlr.getHumanUid(); }

    // 取得真人玩家座位索引
    @Override
    public int getHumanChairIndex() {
        return this.gamePlayerHlr.getHumanChairIndex();
    }

    // 取得真人起始金額
    @Override
    public double getHumanPlayerBeginMoney() { return this.gamePlayerHlr.getHumanBeginMoney(); }

    // 取得真人起始金額
    @Override
    public double getHumanPlayerAfterMoney() { return this.gamePlayerHlr.getHumanAfterMoney(); }

    // 判斷是否為真人
    @Override
    public boolean isHumanChairIndex(int chairIndex) {
        return this.gamePlayerHlr.isHumanChairIndex(chairIndex);
    }

    // 判斷座位是否合法
    @Override
    public boolean isValidChairIndex(int chairIndex) {
        return this.gamePlayerHlr.isValidChairIndex(chairIndex);
    }

    //** 遊戲投注紀錄計算器 **//
    // 更新起始時間
    @Override
    public void updateBetTime(){
        this.gameBetLogResultCtr.updateBeginTime();
    }

    // 新增詳細日誌
    @Override
    public void addDetail(Object detail) { this.gameBetLogResultCtr.addDetail(detail); }

    // 新增遊戲開始詳細日誌
    @Override
    public void addDetailGameBegin(){
        // 1. 遊戲開始詳細紀錄
        this.addDetailGameBeginTime();

        // 2. 玩家資訊詳細記錄
        this.addDetailGameBeginPlayer();
    }

    // 新增遊戲開始 時間詳細日誌
    @Override
    public void addDetailGameBeginTime(){
        this.addDetail(new DetailGameBegin(
                Instant.ofEpochMilli(this.gameBetLogResultCtr.getBeginTime()).atOffset(ZoneOffset.of("+08:00")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "begin"));
    }

    // 新增遊戲開始 玩家資訊詳細記錄
    @Override
    public void addDetailGameBeginPlayer(){
        for(GamePlayer gamePlayer: this.gamePlayerHlr.getAllGamePlayerList()){
            this.addDetail(new DetailUserInfo(
                    gamePlayer.getChairIndex(),
                    gamePlayer.getBeginMoney(),
                    gamePlayer.getAccount(),
                    gamePlayer.getNickName()));
        }
    }
}
