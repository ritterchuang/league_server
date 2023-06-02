package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtr;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtr;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.logic.logicCard.AbstractLogicCard;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 抽象搶莊類邏輯 TODO 多型
public abstract class AbstractLogicQz extends AbstractLogicCard implements ILogicQz{
    protected final VieBankerCtr vieBankerCtr; // 搶莊計算器
    protected final QzBetCtr qzBetCtr; // 下注計算器

    public AbstractLogicQz(ITableBase table,
                           TableFieldConfig tableFieldConfig,
                           GamePlayerHlr gamePlayerHlr,
                           PoolCtr poolCtr,
                           IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                           ITableUtil tableUtil,
                           VieBankerCtrConfig vieBankerCtrConfig,
                           QzBetCtrConfig qzBetCtrConfig) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrCard, tableUtil);
        this.vieBankerCtr = new VieBankerCtr(vieBankerCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 搶莊模組
        this.qzBetCtr = new QzBetCtr(qzBetCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 下注模組
    }

    //* 搶莊計算器 *//
    //** 可搶莊倍數 **//
    // 取得真人可搶莊倍數列表
    @Override
    public int[] getHumanCanVieRateArray() {
        return this.vieBankerCtr.getHumanCanVieRateArray();
    }

    // 取得真人搶莊最大倍數
    @Override
    public int getHumanMaxVieRate() {
        return this.vieBankerCtr.getHumanMaxVieRate();
    }

    // 取得指定玩家可搶莊列表
    @Override
    public List<Integer> getPlayerCanVieRateList(int chairIndex) { return this.vieBankerCtr.getPlayerCanVieRateList(chairIndex); }

    //** 接收搶莊訊息相關 **//
    // 是否重複搶莊
    @Override
    public boolean isPlayerVied(int chairIndex) {
        return this.vieBankerCtr.isPlayerVied(chairIndex);
    }

    // 驗證有效的搶莊倍數
    @Override
    public boolean isValidVieRate(int chairIndex, int rate) { return this.vieBankerCtr.isValidVieRate(chairIndex, rate); }

    // 更新搶莊倍數
    @Override
    public void setPlayerVieRate(int chairIndex, int rate) { this.vieBankerCtr.setPlayerVieRate(chairIndex, rate); }

    //** 結束搶莊相關 **//
    // 是否完成搶莊
    @Override
    public boolean isFinishVie() { return this.vieBankerCtr.isFinishVie(); }

    // 結束搶莊
    @Override
    public void finishVie() { this.vieBankerCtr.finishVie(); }

    //** 搶莊結果相關 **//
    // 取得莊家座位
    @Override
    public int getBankerChairIndex() {
        return this.vieBankerCtr.getBankerChairIndex();
    }

    // 取得莊家倍數
    @Override
    public int getBankerRate() {
        return this.vieBankerCtr.getBankerRate();
    }

    // 是否為莊家
    @Override
    public boolean isBanker(int chairIndex) { return this.vieBankerCtr.isBankerChairIndex(chairIndex); }

    // 取得搶莊倍數列表
    @Override
    public int[] getVieRateArray(){ return this.vieBankerCtr.getVieRateArray();}

    //** 超時搶莊相關 **//
    // 取得超時未押注表演名單
    @Override
    public Map<Integer, Integer> getTimeOutVieRate() { return this.vieBankerCtr.getTimeOutVieRateMap(); }

    //** 候選搶莊相關 **//
    // 是否表演候選搶莊
    @Override
    public boolean isShowVieBankAnimation() { return this.vieBankerCtr.isShowVieBankAnimation(); }

    // 取得搶莊候選名單
    @Override
    public int[] getVieCandidateArrayMessage() { return this.vieBankerCtr.getVieCandidateArrayMessage(); }


    //* 下注計算器 *//
    //** 可下注倍數相關 **//
    // 產出閒家押注列表
    @Override
    public void generateCanBetRateListMap() {
        this.qzBetCtr.generateCanBetRateListMap(this.vieBankerCtr.getBankerChairIndex(), this.vieBankerCtr.getBankerRate());
    }

    // 取得倍數列表
    @Override
    public List<Integer> getPlayerCanBetRateList(int chairIndex) { return this.qzBetCtr.getPlayerCanBetRateList(chairIndex); }

    // 取得真人押注列表
    @Override
    public int[] getHumanCanBetRateArray() { return this.qzBetCtr.getHumanCanBetRateArray(this.vieBankerCtr.getBankerChairIndex()); }

    //** 接受下注訊息相關 **//
    // 檢查重複押注
    @Override
    public boolean isPlayerBet(int chairIndex) { return this.qzBetCtr.isPlayerBet(chairIndex); }

    // 驗證有效的下注倍數
    @Override
    public boolean isValidBetRate(int chairIndex, int rate) { return this.qzBetCtr.isValidBetRate(chairIndex, rate); }

    // 更新下注倍數
    @Override
    public void setPlayerBetRate(int chairIndex, int rate) { this.qzBetCtr.setPlayerBetRate(chairIndex, rate); }

    //** 結束下注相關 **//
    // 檢查是否結束下注狀態
    @Override
    public boolean isFinishBet() { return this.qzBetCtr.isFinishBet(this.vieBankerCtr.getBankerChairIndex()); }

    // 結束下注
    @Override
    public void finishBet() { this.qzBetCtr.finishBet(this.vieBankerCtr.getBankerChairIndex()); }

    //** 下注結果相關 **//
    // 取得閒家倍數列表
    @Override
    public int[] getBetResultArray() { return this.qzBetCtr.getBetResultArray(); }

    //* 超時下注相關 *//
    // 取得超時未押注表演名單
    @Override
    public Map<Integer, Integer> getTimeOutBetRate() {
        return this.qzBetCtr.getTimeOutBetRateMap();
    }
}
