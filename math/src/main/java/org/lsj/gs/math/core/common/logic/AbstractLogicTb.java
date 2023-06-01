package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.card.betCtr.tbBetCtr.TbBetCtr;
import org.lsj.gs.math.core.card.betCtr.tbBetCtr.TbBetCtrConfig;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.logic.logicCard.AbstractLogicCard;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 抽象通比類邏輯
public abstract class AbstractLogicTb extends AbstractLogicCard {
    protected final TbBetCtr betCtr; // 下注計算器

    public AbstractLogicTb(ITableBase table,
                           TableFieldConfig tableFieldConfig,
                           GamePlayerHlr gamePlayerHlr,
                           PoolCtr poolCtr,
                           IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                           ITableUtil tableUtil,
                           TbBetCtrConfig tbBetCtrConfig) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrCard, tableUtil);
        this.betCtr = new TbBetCtr(tbBetCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 下注模組
    }


    //* 下注計算器 *//
    //** 可下注倍數相關 **//
    // 產出閒家押注列表
    public void generateCanBetRateListMap() {
        this.betCtr.generateCanBetRateListMap();
    }

    // 取得倍數列表
    public List<Integer> getPlayerCanBetRateList(int chairIndex) { return this.betCtr.getPlayerCanBetRateList(chairIndex); }

    // 取得真人押注列表
    public int[] getHumanCanBetRateArray() { return this.betCtr.getHumanCanBetRateArray(); }

    //** 接受下注訊息相關 **//
    // 檢查重複押注
    public boolean isPlayerBet(int chairIndex) { return this.betCtr.isPlayerBet(chairIndex); }

    // 驗證有效的下注倍數
    public boolean isValidBetRate(int chairIndex, int rate) { return this.betCtr.isValidBetRate(chairIndex, rate); }

    // 更新下注倍數
    public void setPlayerBetRate(int chairIndex, int rate) { this.betCtr.setPlayerBetRate(chairIndex, rate); }

    //** 結束下注相關 **//
    // 檢查是否結束下注狀態
    public boolean isFinishBet() { return this.betCtr.isFinishBet(); }

    // 結束下注
    public void finishBet() { this.betCtr.finishBet(); }

    //** 下注結果相關 **//
    // 取得閒家倍數列表
    public int[] getBetResultArray() { return this.betCtr.getBetResultArray(); }

    //* 超時下注相關 *//
    // 取得超時未押注表演名單
    public Map<Integer, Integer> getTimeOutBetRate() {
        return this.betCtr.getTimeOutBetRateMap();
    }
}
