package com.lx.gs.math.core.common.logic.logicCard;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrConfig;
import com.lx.gs.math.core.card.cardWallCtr.CardWallCtr;
import com.lx.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.card.resultCtr.ebgResultCtr.EbgBankerResultCtr;
import com.lx.gs.math.core.card.resultCtr.ebgResultCtr.EbgBankerResultCtrConfig;
import com.lx.gs.math.core.card.stackCtr.ebgStackCtr.EbgStackCtr;
import com.lx.gs.math.core.card.stackCtr.ebgStackCtr.EbgStackCtrConfig;
import com.lx.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogicQz;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.ITableBase;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 抽象搶莊二八槓類邏輯
public abstract class AbstractLogicQzEbg extends AbstractLogicQz {
    protected final CardWallCtr cardWallCtr; // 發牌計算器
    protected final EbgStackCtr ebgStackCtr; // 牌型計算器
    protected final EbgBankerResultCtr ebgBankerResultCtr; // 算分結果計算器

    private final int dealCount = 2; // 發牌數

    public AbstractLogicQzEbg(ITableBase table,
                              TableFieldConfig tableFieldConfig,
                              GamePlayerHlr gamePlayerHlr,
                              PoolCtr poolCtr,
                              IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                              ITableUtil tableUtil,
                              VieBankerCtrConfig vieBankerCtrConfig,
                              QzBetCtrConfig qzBetCtrConfig,
                              CardWallCtrConfig cardWallCtrConfig,
                              EbgBankerResultCtrConfig ebgBankerResultCtrConfig,
                              EbgStackCtrConfig ebgStackCtrConfig
                             ) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrCard, tableUtil, vieBankerCtrConfig, qzBetCtrConfig);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 發牌模組
        this.ebgStackCtr = new EbgStackCtr(ebgStackCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 選牌模組
        this.ebgBankerResultCtr = new EbgBankerResultCtr(ebgBankerResultCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 算分模組
    }



    //* 牌牆計算器 *//
    //** 發牌功能相關 **//

    // 洗牌
    public void shuffleCardWall() {
        this.cardWallCtr.shuffle();
    }

    // 發未取走牌
    public List<ICard> dealUnTakenCardList(int number) {
        return this.cardWallCtr.getUnTakenCardList(number);
    }

    // 取出特定牌
    public void getAppointObjCard(ICard card) {
        this.cardWallCtr.getAppointObjCard(card);
    }

    // 添加玩家手牌
    public void addPlayerHandCardListMap(Map<Integer, List<ICard>> playerCardList){
        this.cardWallCtr.addPlayerHandCardListMap(playerCardList);
    }


    //* 發牌結果相關 *//

    // 取得所有玩家手牌牌號
    public int[][] getAllPlayerHandCardNumberArray() {
        return this.cardWallCtr.getAllPlayerHandCardNumberArray();
    }

    // 取得指定玩家手牌牌號
    public int[] getHandCardNumberArray(int chairIndex) { return this.cardWallCtr.getHandCardNumberArray(chairIndex); }

    // 發蓋牌
    public int[] dealBackCards(int backCardsCount) {
        return this.cardWallCtr.getBackCardList(backCardsCount).stream().mapToInt(x -> x).toArray();
    }


    //* 斷線重連相關 *//

    // 取得斷線回復手牌結果
    public int[][] calculateCutReturnHandCardListArray() {
        return this.cardWallCtr.calculateCutReturnHandCardListArray(this.ebgStackCtr.getAllPlayerSelectCardList(), this.dealCount);
    }


    //* 二八槓牌型計算器 *//

    //** 接收選牌訊息相關 **//
    // 玩家是否選過
    public boolean isPlayerSelect(int chairIndex) {
        return this.ebgStackCtr.isPlayerSelect(chairIndex);
    }


    //** 結束選牌相關 **//

    // 是否選擇完畢
    public boolean isFinishSelect() {
        return this.ebgStackCtr.isFinishSelect();
    }

    // 結束選擇
    public void finishSelect() { this.ebgStackCtr.finishSelect(this.cardWallCtr.getAllPlayerHandCardListMap()); }


    //** 選牌相關 **//

    // 取得所有玩家牌型
    public int[] getAllPlayerSelectTypeArrayCommon() { return this.ebgStackCtr.getAllPlayerSelectTypeArray(); }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectTypeCommon(int chairIndex) { return this.ebgStackCtr.getPlayerSelectType(chairIndex); }

    // 設定玩家選牛結果
    public void setPlayerSelectResult(int chairIndex) {
        this.ebgStackCtr.setPlayerSelectResult(
            chairIndex,
            this.ebgStackCtr.calculateEbgStack(this.cardWallCtr.getHandCardList(chairIndex)));
    }


    //* 二八槓結果計算器 *//
    //** 輸贏結果相關 **//

    // 取得所有玩家淨利
    public double[] getAllPlayerScoreArray() { return this.ebgBankerResultCtr.getAllPlayerScoreArray(); }

    // 取得指定玩家淨利
    public double getPlayerScore(int chairIndex) { return this.ebgBankerResultCtr.getPlayerScore(chairIndex); }

    // 取得真人輸贏結果
    public UidScore getHumanUidScore() {
        return this.ebgBankerResultCtr.getHumanPlayerUidScore();
    }

    // 取得真人玩家淨利
    public double getHumanPlayerScore() { return this.ebgBankerResultCtr.getHumanPlayerScore(); }


    //** 計算結果相關 **//
    // 計算所有玩家得分
    public Map<Integer, UidScore> calculateUidScoreMap() {
        return this.ebgBankerResultCtr.calculateUidScoreMap(
                this.ebgStackCtr.getAllPlayerSelectResult(),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 計算玩家得分對應表
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> playerCardList) {
        return this.ebgBankerResultCtr.calculateUidScoreMap(
                this.ebgStackCtr.calculateEbgStack(playerCardList),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 設定所有玩家得分
    public void setUidScoreMap(Map<Integer, UidScore> uidScoreMap) { this.ebgBankerResultCtr.setUidScoreMap(uidScoreMap); }
}
