package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;
import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStackCtr;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.ILogicBjl;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

// 抽象百人百家樂類邏輯
public abstract class AbstractLogicBaiRenBjl extends AbstractLogicBaiRen implements ILogicBjl {
    private final CardWallCtr cardWallCtr; // 發牌計算器
    protected final BjlStackCtr stackCtr; // 牌型計算器

    public AbstractLogicBaiRenBjl(ITableBase table,
                                  TableFieldConfig tableFieldConfig,
                                  GamePlayerHlr gamePlayerHlr,
                                  PoolCtr poolCtr,
                                  IGameBetLogResultCtrBaiRen gameBetLogResultCtr,
                                  ITableUtil tableUtil,
                                  BetAreaCtrConfig betAreaCtrConfig,
                                  CardWallCtrConfig cardWallCtrConfig) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtr, betAreaCtrConfig, tableUtil);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.stackCtr = new BjlStackCtr();
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

    // 設定區域牌
    @Override
    public void setAreaCardListMap(Map<Integer, List<ICard>> areaCardList){
        this.cardWallCtr.setAreaCardListMap(areaCardList);
    }


    //** 發牌結果相關 **//

    // 取得所有區域牌號
    public int[][] getAllAreaCardNumber2dArray(){
        return this.cardWallCtr.getAllAreaCardNumber2dArray();
    }

    // 取得庄牌
    public int[] getBankCardArray(){
        int[][] areaCardNumber2dArray = this.getAllAreaCardNumber2dArray();

        if (areaCardNumber2dArray.length == 0) {
            return new int[]{};
        }

        return areaCardNumber2dArray[ConstMathBjl.DealAreaEnum.BANK.getCode()];
    }

    // 取得閒牌
    public int[] getPlayCardArray(){
        int[][] areaCardNumber2dArray = this.getAllAreaCardNumber2dArray();

        if (areaCardNumber2dArray.length == 0) {
            return new int[]{};
        }

        return areaCardNumber2dArray[ConstMathBjl.DealAreaEnum.PLAY.getCode()];
    }


    //* 下注相關 *//

    // 計算押注類型總投注
    public int getBetTypeChips(ConstMathBjl.BetAreaTypeEnum betAreaTypeEnum) {
        return IntStream.range(0, ConstMathBjl.BetAreaEnum.getBetAreaCount())
                .filter(betAreaId ->
                            ConstMathBjl.BetAreaEnum.checkSameBetAreaType(betAreaId, betAreaTypeEnum))
                .map(betAreaId -> this.betAreaCtr.getAreaBetArray()[betAreaId])
                .sum();
    }


    //* 牌型結果相關 *//

    // 設定百家牌型
    @Override
    public void setStackMap() {
        this.stackCtr.setStackMap(
                this.stackCtr.calculateStackMap(
                        this.cardWallCtr.getAllAreaCardListMap()
                )
        );
    }

    // 取得百家牌型對應表
    @Override
    public Map<Integer, BjlStack> getStackMap(){
        return this.stackCtr.getStackMap();
    }

    // 計算贏分區域陣列
    @Override
    public int[] calculateWinAreaArray(){
        return this.getAbstractBjlBaiRenResultCtr().calculateWinAreasArray(this.stackCtr.getStackMap(), this.betAreaCtr.getAreaBetArray());
    }

    // 計算返分區域陣列
    @Override
    public int[] calculateReturnAreaArray(){
        return this.getAbstractBjlBaiRenResultCtr().calculateReturnBetAreasArray(this.stackCtr.getStackMap(), this.betAreaCtr.getAreaBetArray());
    }

    // 依照發牌區域計算點數和
    @Override
    public int[] calculatePlayBankPointArray() {
        return new int[]{
                this.stackCtr.calculateAreaPint(ConstMathBjl.DealAreaEnum.PLAY),
                this.stackCtr.calculateAreaPint(ConstMathBjl.DealAreaEnum.BANK),
        };
    }

    // 依照發牌區域計算累積點數陣列
    @Override
    public int[][] calculateAccumulateAreaPoint2DArray() {
        return new int[][]{
                this.stackCtr.calculateAccumulateAreaPointArray(ConstMathBjl.DealAreaEnum.PLAY),
                this.stackCtr.calculateAccumulateAreaPointArray(ConstMathBjl.DealAreaEnum.BANK),
        };
    }


    //* 計算結果相關 *//

    // 取得所有玩家輸贏結果
    @Override
    public Map<Integer, UidScore> getUidScoreMap(){
        return this.getAbstractBjlBaiRenResultCtr().getUidScoreMap();
    }

    // 設定輸贏結果對應表
    @Override
    public void setUidScoreMap(Map<Integer, UidScore> playerScoreMap){
        this.getAbstractBjlBaiRenResultCtr().setUidScoreMap(playerScoreMap);
    }

    // 取得所有贏家總贏分
    @Override
    public double getAllWinScore(){
        return this.getAbstractBjlBaiRenResultCtr().getAllWinScore();
    }

    // 計算返還金額
    @Override
    public int calculateReturnValue() {
        // 1. 準備玩家下注區域金額、返還區域
        Map<Integer, Integer> areaBet = super.betAreaCtr.getChairToAreaBetMap().getOrDefault(super.gamePlayerHlr.getHumanChairIndex(), new HashMap<>());
        int[] returnAreaIdArray = this.calculateReturnAreaArray();

        // 2. 計算返還金額
        return Arrays.stream(returnAreaIdArray).map(returnAreaId -> areaBet.getOrDefault(returnAreaId, 0)).sum();
    }

    // 計算玩家區域贏分陣列
    @Override
    public double[] calculatePlayerAreaScoreArray(int chairIndex){
        return this.getAbstractBjlBaiRenResultCtr().calculateAreaNoFeeScoreArray(
                chairIndex,
                this.getStackMap(),
                super.betAreaCtr.getChairToAreaBetMap());
    }

    // 計算玩家區域贏分對應表
    @Override
    public Map<Integer, Double> calculatePlayerAreaScoreMap(int chairIndex){
        Map<Integer, Double> result = new HashMap<>();

        // 1. 計算玩家區域贏分陣列
        double[] playerAreaScoreArray = this.calculatePlayerAreaScoreArray(chairIndex);

        // 2. 轉換為對應表
        for (int areaIndex = 0; areaIndex < playerAreaScoreArray.length; areaIndex++) {
            if(playerAreaScoreArray[areaIndex] > 0.0){
                result.put(areaIndex, playerAreaScoreArray[areaIndex]);
            }
        }

        return result;
    }

    // 計算所有玩家得分
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap() {
        return this.getAbstractBjlBaiRenResultCtr().calculateUidScoreMap(
                this.stackCtr.getStackMap(),
                super.betAreaCtr.getChairToAreaBetMap()
        );
    }

    // 提供卡牌，計算所有玩家得分
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> unTakenCardListMap) {
        return this.getAbstractBjlBaiRenResultCtr().calculateUidScoreMap(
                this.stackCtr.calculateStackMap(unTakenCardListMap),
                super.betAreaCtr.getChairToAreaBetMap()
        );
    }

    // 取得真人輸贏結果
    @Override
    public UidScore getHumanUidScore() {
        return this.getAbstractBjlBaiRenResultCtr().getHumanPlayerUidScore();
    }

    // 重設
    @Override
    public void reset(){
        super.reset();
        this.cardWallCtr.reset();
        this.stackCtr.reset();
    }
}
