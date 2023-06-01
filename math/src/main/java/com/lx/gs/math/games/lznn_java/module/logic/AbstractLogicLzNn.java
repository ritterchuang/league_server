package com.lx.gs.math.games.lznn_java.module.logic;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrConfig;
import com.lx.gs.math.core.card.cardWallCtr.CardWallCtr;
import com.lx.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.card.resultCtr.niuResultCtr.NiuBankerResultCtr;
import com.lx.gs.math.core.card.resultCtr.niuResultCtr.NiuBankerResultCtrConfig;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrConfig;
import com.lx.gs.math.core.card.stackCtr.niuStackCtr.lzNiuStackCtr.LzNiuStackCtr;
import com.lx.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogicQz;
import com.lx.gs.math.core.common.logic.ILogicNn;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrCard;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.ITableBase;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 抽象賴子牛牛類邏輯
public abstract class AbstractLogicLzNn extends AbstractLogicQz implements ILogicNn {
    protected final CardWallCtr cardWallCtr; // 發牌計算器
    protected final LzNiuStackCtr lzNiuStackCtr; // 牛牛牌型計算器
    protected final NiuBankerResultCtr niuResultCtr; // 牛牛結果計算器

    public AbstractLogicLzNn(ITableBase table,
                             TableFieldConfig tableFieldConfig,
                             GamePlayerHlr gamePlayerHlr,
                             PoolCtr poolCtr,
                             IGameBetLogResultCtrCard gameBetLogResultCtrCard,
                             ITableUtil tableUtil,
                             VieBankerCtrConfig vieBankerCtrConfig,
                             QzBetCtrConfig qzBetCtrConfig,
                             CardWallCtrConfig cardWallCtrConfig,
                             NiuStackCtrConfig niuStackCtrConfig,
                             NiuBankerResultCtrConfig niuBankerResultCtrConfig
                             ) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtrCard, tableUtil, vieBankerCtrConfig, qzBetCtrConfig);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 發牌模組
        this.lzNiuStackCtr = new LzNiuStackCtr(niuStackCtrConfig, this.cardWallCtr, gamePlayerHlr, poolCtr, tableUtil); // 選牌模組
        this.niuResultCtr = new NiuBankerResultCtr(niuBankerResultCtrConfig, gamePlayerHlr, poolCtr, tableUtil); // 算分模組
    }


    //* 牌牆計算器 *//
    //** 發牌功能相關 **//

    // 洗牌
    @Override
    public void shuffleCardWall() {
        this.cardWallCtr.shuffle();
    }

    // 發未取走牌
    @Override
    public List<ICard> dealUnTakenCardList(int number) {
        return this.cardWallCtr.getUnTakenCardList(number);
    }

    // 取出特定牌
    @Override
    public void getAppointObjCard(ICard card) {
        this.cardWallCtr.getAppointObjCard(card);
    }

    // 添加玩家手牌
    @Override
    public void addPlayerHandCardListMap(Map<Integer, List<ICard>> playerCardList){
        this.cardWallCtr.addPlayerHandCardListMap(playerCardList);
    }


    //* 發牌結果相關 *//

    // 取得所有玩家手牌牌號
    @Override
    public int[][] getAllPlayerHandCardNumberArray() {
        return this.cardWallCtr.getAllPlayerHandCardNumberArray();
    }

    // 取得所有玩家手牌
    @Override
    public Map<Integer, List<ICard>> getHandCardListMap(){
        return this.cardWallCtr.getHandCardListMap();
    }

    // 取得指定玩家手牌牌號
    @Override
    public int[] getHandCardNumberArray(int chairIndex) { return this.cardWallCtr.getHandCardNumberArray(chairIndex); }

    // 取得真人手牌
    @Override
    public int[] getHumanListHandCardArray() { return this.cardWallCtr.getHumanHandCardListArray(); }

    // 檢查玩家手牌是否存在
    @Override
    public boolean isInPlayerHand(int chairIndex, int[] cardNumbers) {
        return this.cardWallCtr.isInPlayerHandCard(chairIndex, cardNumbers);
    }


    //* 斷線重連相關 *//

    // 取得斷線回復手牌結果
    @Override
    public int[][] calculateCutReturnHandCardListArray() {
        return this.cardWallCtr.calculateCutReturnHandCardListArray(this.lzNiuStackCtr.getSelectedPlayerList(), this.lzNiuStackCtr.getAllPlayerSelectCardList()); }


    //* 牛牛牌型計算器 *//
    //** 接收選牌訊息相關 **//

    // 玩家是否選過
    @Override
    public boolean isPlayerSelect(int chairIndex) {
        return this.lzNiuStackCtr.isPlayerSelect(chairIndex);
    }

    //** 結束選牌相關 **//

    // 是否選擇完畢
    @Override
    public boolean isFinishSelect() {
        return this.lzNiuStackCtr.isFinishSelect();
    }

    // 結束選擇
    @Override
    public void finishSelect() { this.lzNiuStackCtr.finishSelect(this.cardWallCtr.getAllPlayerHandCardListMap()); }


    //** 選牌相關 **//

    // 取得所有玩家選牌牌號
    @Override
    public int[][] getAllPlayerSelectCardNumberArray() { return this.cardWallCtr.changeAllPlayerCardListMap2allPlayerCardNumberArray(this.lzNiuStackCtr.getAllPlayerSelectCardList()); }

    // 取得所有玩家牌型
    @Override
    public int[] getAllPlayerSelectTypeArrayCommon() { return this.lzNiuStackCtr.getAllPlayerSelectTypeArray(); }

    // 取得指定位置的選牌牌型
    @Override
    public int getPlayerSelectTypeCommon(int chairIndex) { return this.lzNiuStackCtr.getPlayerSelectType(chairIndex); }

    // 取得指定位置的是否有牛
    @Override
    public boolean getPlayerIsNiu(int chairIndex) { return ((AbstractNiuStack)this.lzNiuStackCtr.getAllPlayerSelectResult().get(chairIndex)).isNiu(); }

    // 設定玩家選牛結果
    @Override
    public void setPlayerSelectResult(int chairIndex) {
        this.lzNiuStackCtr.setPlayerSelectResult(
            chairIndex,
            this.lzNiuStackCtr.calculateNiuStack(this.cardWallCtr.getHandCardList(chairIndex)));
    }

    // 取得真人選牌結果
    @Override
    public AbstractNiuStack getHumanPlayerSelectResultCommon() { return this.lzNiuStackCtr.getHumanPlayerSelectResult(); }

    // 取得真人選牌牌型
    @Override
    public int getHumanPlayerSelectTypeCommon() {
        return this.lzNiuStackCtr.getHumanPlayerSelectType();
    }

    // 是否為最大牌型 賴子不實做
    @Override
    public boolean isMaxStack(int[] cardNumbers) {
        return this.lzNiuStackCtr.isMaxStack(this.cardWallCtr.changeCardNumberArray2CardList(cardNumbers));
    }


    //** 提牌相關 **//

    // 取得所有玩家提牌牌號
    @Override
    public int[][] getAllPlayerLiftCardNumberArray() { return this.cardWallCtr.changeAllPlayerCardListMap2allPlayerCardNumberArray(this.lzNiuStackCtr.getAllPlayerLiftCardMap()); }

    // 取得指定玩家提牌牌號
    @Override
    public int[] getPlayerLiftCardNumber(int chairIndex) { return this.cardWallCtr.changeCardList2CardNumberArray(this.lzNiuStackCtr.getPlayerLiftCardList(chairIndex)); }


    //* 牛牛結果計算器 *//
    //** 輸贏結果相關 **//

    // 取得所有玩家淨利
    @Override
    public double[] getAllPlayerScoreArray() { return this.niuResultCtr.getAllPlayerScoreArray(); }

    // 取得指定玩家淨利
    @Override
    public double getPlayerScore(int chairIndex) { return this.niuResultCtr.getPlayerScore(chairIndex); }

    // 取得真人輸贏結果
    @Override
    public UidScore getHumanUidScore() {
        return this.niuResultCtr.getHumanPlayerUidScore();
    }

    // 取得真人玩家淨利
    @Override
    public double getHumanPlayerScore() { return this.niuResultCtr.getHumanPlayerScore(); }


    //** 計算結果相關 **//

    // 計算所有玩家得分
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap() {
        return this.niuResultCtr.calculateUidScoreMap(
                this.lzNiuStackCtr.getAllPlayerSelectResult(),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 計算玩家得分對應表
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> playerCardList) {
        return this.niuResultCtr.calculateUidScoreMap(
                this.lzNiuStackCtr.calculateNiuStack(playerCardList),
                super.qzBetCtr.getBetRateMap(),
                super.vieBankerCtr.getBankerRate(),
                super.vieBankerCtr.getBankerChairIndex());
    }

    // 設定所有玩家得分
    @Override
    public void setUidScoreMap(Map<Integer, UidScore> uidScoreMap) { this.niuResultCtr.setUidScoreMap(uidScoreMap); }
}
