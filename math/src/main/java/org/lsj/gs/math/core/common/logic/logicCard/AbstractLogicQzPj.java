package org.lsj.gs.math.core.common.logic.logicCard;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.resultCtr.pjResultCtr.PjBankerResultCtr;
import org.lsj.gs.math.core.card.resultCtr.pjResultCtr.PjBankerResultCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.pjStackCtr.PjStackCtr;
import org.lsj.gs.math.core.card.stackCtr.pjStackCtr.PjStackCtrConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogicQz;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 抽象搶莊牌九類邏輯
public abstract class AbstractLogicQzPj extends AbstractLogicQz {
    protected final CardWallCtr cardWallCtr; // 發牌計算器
    protected final PjStackCtr pjStackCtr; // 牌型計算器
    protected final PjBankerResultCtr pjBankerResultCtr; // 算分結果計算器

    private final int dealCount = 2; // 發牌數

    public AbstractLogicQzPj(ITableBase table,
                             TableFieldConfig tableFieldConfig,
                             GamePlayerHlr gamePlayerHlr,
                             PoolCtr poolCtr,
                             IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                             ITableUtil tableUtil,
                             VieBankerCtrConfig vieBankerCtrConfig,
                             QzBetCtrConfig qzBetCtrConfig,
                             CardWallCtrConfig cardWallCtrConfig,
                             PjBankerResultCtrConfig pjBankerResultCtrConfig,
                             PjStackCtrConfig pjStackCtrConfig
                             ) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrCard, tableUtil, vieBankerCtrConfig, qzBetCtrConfig);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 發牌模組
        this.pjStackCtr = new PjStackCtr(pjStackCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 選牌模組
        this.pjBankerResultCtr = new PjBankerResultCtr(pjBankerResultCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 算分模組
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
        return this.cardWallCtr.calculateCutReturnHandCardListArray(this.pjStackCtr.getAllPlayerSelectCardList(), this.dealCount);
    }


    //* 牌九牌型計算器 *//

    //** 接收選牌訊息相關 **//
    // 玩家是否選過
    public boolean isPlayerSelect(int chairIndex) {
        return this.pjStackCtr.isPlayerSelect(chairIndex);
    }


    //** 結束選牌相關 **//

    // 是否選擇完畢
    public boolean isFinishSelect() {
        return this.pjStackCtr.isFinishSelect();
    }

    // 結束選擇
    public void finishSelect() { this.pjStackCtr.finishSelect(this.cardWallCtr.getAllPlayerHandCardListMap()); }


    //** 選牌相關 **//

    // 取得所有玩家牌型
    public int[] getAllPlayerSelectTypeArrayCommon() { return this.pjStackCtr.getAllPlayerSelectTypeArray(); }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectTypeCommon(int chairIndex) { return this.pjStackCtr.getPlayerSelectType(chairIndex); }

    // 設定玩家選牛結果
    public void setPlayerSelectResult(int chairIndex) {
        this.pjStackCtr.setPlayerSelectResult(
            chairIndex,
            this.pjStackCtr.calculatePjStack(this.cardWallCtr.getHandCardList(chairIndex)));
    }


    //* 牌九結果計算器 *//
    //** 輸贏結果相關 **//

    // 取得所有玩家淨利
    public double[] getAllPlayerScoreArray() { return this.pjBankerResultCtr.getAllPlayerScoreArray(); }

    // 取得指定玩家淨利
    public double getPlayerScore(int chairIndex) { return this.pjBankerResultCtr.getPlayerScore(chairIndex); }

    // 取得真人輸贏結果
    public UidScore getHumanUidScore() {
        return this.pjBankerResultCtr.getHumanPlayerUidScore();
    }

    // 取得真人玩家淨利
    public double getHumanPlayerScore() { return this.pjBankerResultCtr.getHumanPlayerScore(); }


    //** 計算結果相關 **//
    // 計算所有玩家得分
    public Map<Integer, UidScore> calculateUidScoreMap() {
        return this.pjBankerResultCtr.calculateUidScoreMap(
                this.pjStackCtr.getAllPlayerSelectResult(),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 計算玩家得分對應表
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> playerCardList) {
        return this.pjBankerResultCtr.calculateUidScoreMap(
                this.pjStackCtr.calculatePjStack(playerCardList),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 設定所有玩家得分
    public void setUidScoreMap(Map<Integer, UidScore> uidScoreMap) { this.pjBankerResultCtr.setUidScoreMap(uidScoreMap); }
}
