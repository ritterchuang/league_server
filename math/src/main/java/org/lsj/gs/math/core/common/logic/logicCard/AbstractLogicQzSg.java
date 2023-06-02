package org.lsj.gs.math.core.common.logic.logicCard;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.resultCtr.sgResultCtr.SgBankerResultCtr;
import org.lsj.gs.math.core.card.resultCtr.sgResultCtr.SgBankerResultCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.SgStackCtr;
import org.lsj.gs.math.core.card.stackCtr.sgStackCtr.SgStackCtrConfig;
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

// 抽象搶莊三公類邏輯
public abstract class AbstractLogicQzSg extends AbstractLogicQz {
    protected final CardWallCtr cardWallCtr; // 發牌計算器
    protected final SgStackCtr sgStackCtr; // 牌型計算器
    protected final SgBankerResultCtr sgBankerResultCtr; // 算分結果計算器

    private final int dealCount = 3; // 發牌數

    public AbstractLogicQzSg(ITableBase table,
                             TableFieldConfig tableFieldConfig,
                             GamePlayerHlr gamePlayerHlr,
                             PoolCtr poolCtr,
                             IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                             ITableUtil tableUtil,
                             VieBankerCtrConfig vieBankerCtrConfig,
                             QzBetCtrConfig qzBetCtrConfig,
                             CardWallCtrConfig cardWallCtrConfig,
                             SgBankerResultCtrConfig sgBankerResultCtrConfig,
                             SgStackCtrConfig sgStackCtrConfig
                             ) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrCard, tableUtil, vieBankerCtrConfig, qzBetCtrConfig);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 發牌模組
        this.sgStackCtr = new SgStackCtr(sgStackCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 選牌模組
        this.sgBankerResultCtr = new SgBankerResultCtr(sgBankerResultCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 算分模組
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

    // 取得所有玩家手牌
    public Map<Integer, List<ICard>> getHandCardListMap(){
        return this.cardWallCtr.getHandCardListMap();
    }


    //* 斷線重連相關 *//

    // 取得斷線回復手牌結果
    public int[][] calculateCutReturnHandCardListArray() {
        return this.cardWallCtr.calculateCutReturnHandCardListArray(this.sgStackCtr.getAllPlayerSelectCardList(), this.dealCount);
    }


    //* 三公牌型計算器 *//
    //** 接收選牌訊息相關 **//

    // 玩家是否選過
    public boolean isPlayerSelect(int chairIndex) {
        return this.sgStackCtr.isPlayerSelect(chairIndex);
    }


    //** 結束選牌相關 **//
    // 是否選擇完畢
    public boolean isFinishSelect() {
        return this.sgStackCtr.isFinishSelect();
    }

    // 結束選擇
    public void finishSelect() { this.sgStackCtr.finishSelect(this.cardWallCtr.getAllPlayerHandCardListMap()); }


    //** 選牌相關 **//

    // 取得所有玩家牌型
    public int[] getAllPlayerSelectTypeArrayCommon() { return this.sgStackCtr.getAllPlayerSelectTypeArray(); }

    // 取得指定位置的選牌牌型
    public int getPlayerSelectTypeCommon(int chairIndex) { return this.sgStackCtr.getPlayerSelectType(chairIndex); }

    // 設定玩家選牛結果
    public void setPlayerSelectResult(int chairIndex) {
        this.sgStackCtr.setPlayerSelectResult(
            chairIndex,
            this.sgStackCtr.calculateSgStack(this.cardWallCtr.getHandCardList(chairIndex)));
    }


    //* 三公結果計算器 *//
    //** 輸贏結果相關 **//

    // 取得所有玩家淨利
    public double[] getAllPlayerScoreArray() { return this.sgBankerResultCtr.getAllPlayerScoreArray(); }

    // 取得指定玩家淨利
    public double getPlayerScore(int chairIndex) { return this.sgBankerResultCtr.getPlayerScore(chairIndex); }

    // 取得真人輸贏結果
    public UidScore getHumanUidScore() {
        return this.sgBankerResultCtr.getHumanPlayerUidScore();
    }

    // 取得真人玩家淨利
    public double getHumanPlayerScore() { return this.sgBankerResultCtr.getHumanPlayerScore(); }


    //** 計算結果相關 **//

    // 計算所有玩家得分
    public Map<Integer, UidScore> calculateUidScoreMap() {
        return this.sgBankerResultCtr.calculateUidScoreMap(
                this.sgStackCtr.getAllPlayerSelectResult(),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 計算玩家得分對應表
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> playerCardList) {
        return this.sgBankerResultCtr.calculateUidScoreMap(
                this.sgStackCtr.calculateSgStack(playerCardList),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 設定所有玩家得分
    public void setUidScoreMap(Map<Integer, UidScore> uidScoreMap) { this.sgBankerResultCtr.setUidScoreMap(uidScoreMap); }

    // 取得贏家列表
    public int[] getWinnerList() {
        return this.sgBankerResultCtr.getWinnerList(); }
}
