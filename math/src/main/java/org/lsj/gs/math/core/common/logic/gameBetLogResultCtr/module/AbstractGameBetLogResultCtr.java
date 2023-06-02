package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.db.GameBetLogObj;
import org.lsj.db.GameBetLogObjBuilder;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.user.IUser;
import org.lsj.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 抽象下注記錄計算器
public abstract class AbstractGameBetLogResultCtr extends AbstractModule implements IGameBetLogResultCtr {
    protected final TableFieldConfig config; // 牌桌設定
    protected long beginTime; // 起始時間
    protected int tableId; // 牌桌號碼
    protected final List<Object> detail; // 詳細記錄

    public AbstractGameBetLogResultCtr(long beginTime, int tableId, TableFieldConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.beginTime = beginTime;
        this.tableId = tableId;
        this.detail = new ArrayList<>();
    }

    // 計算下注記錄物件
    protected GameBetLogObj calculateGameBetLogObj(String roundId, UidScore uidScore) {
        // 1. 防呆
        if(Objects.isNull(uidScore)){
            return new GameBetLogObjBuilder().createGameBetLogObj();
        }

        // 2. 取得玩家
        IUser user = super.gamePlayerHlr.getHumanGamePlayer().getUser();

        // 3. 封裝資訊
        return new GameBetLogObjBuilder()
                .setRoundId(roundId)
                .setOrderId(StringUtil.getInstance().getEmptyString())
                .setUid(user.getUid())
                .setThirdAccount(user.getThirdAccount())
                .setThirdAccountId(user.getThirdAccountId())
                .setCompanyId(user.getCompanyId())
                .setPlatform(user.getPlatform())
                .setAgent(user.getDirectAgent())
                .setGameId(this.config.getGameId())
                .setGameName(this.config.getGameNameCN())
                .setFieldId(this.config.getFieldIndex())
                .setFieldName(this.config.getFieldNameCn())
                .setGsId(user.getGsid())
                .setTableId(this.tableId)
                .setChair(super.gamePlayerHlr.getHumanChairIndex())
                .setBase(this.config.getBase())
                .setReturnAward(uidScore.getReturnAward())
                .setBet(uidScore.getBet())
                .setValidBet(uidScore.getValidBet())
                .setScore(uidScore.getScore())
                .setFee(uidScore.getFee())
                .setBeginMoney(super.gamePlayerHlr.getHumanBeginMoney())
                .setBeginTime(this.beginTime)
                .setEndTime(this.calculateCurrentTime())
                .setAddTime(this.calculateCurrentTime())
                .setIp(user.getIp())
                .setAdjustInfo(this.poolCtr.getAdjustInfoString())
                .createGameBetLogObj();
    }

    //* 更新紀錄相關 *//
    // 更新起始時間
    public void updateBeginTime(){
        this.beginTime = this.calculateCurrentTime();
    }

    // 新增詳細記錄
    @Override
    public void addDetail(Object detail) {
        this.detail.add(detail);
    }

    // 重置詳細記錄
    @Override
    public void resetDetail() {
        this.detail.clear();
    }

    //* 注單資訊相關 *//
    // 取得起始時間
    @Override
    public long getBeginTime() {
        return beginTime;
    }

    // 計算當前時間
    private long calculateCurrentTime() {
        return System.currentTimeMillis();
    }

    // 重設
    public void reset(){
        this.beginTime = 0;
        this.detail.clear();
    }
}
