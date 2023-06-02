package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBaiRenResultCtr;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBaiRenResultCtrConfig;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr.NiuBaiRenStackCtr;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr.NiuBaiRenStackCtrConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.ILogicBrNiu;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 抽象百人牛牛類邏輯
public abstract class AbstractLogicBaiRenNiu extends AbstractLogicBaiRen implements ILogicBrNiu {
    private final CardWallCtr cardWallCtr; // 發牌計算器
    protected final NiuBaiRenStackCtr stackCtr; // 牌型計算器
    protected final NiuBaiRenResultCtr resultCtr; // 結果計算器

    public AbstractLogicBaiRenNiu(ITableBase table,
                                  TableFieldConfig tableFieldConfig,
                                  GamePlayerHlr gamePlayerHlr,
                                  PoolCtr poolCtr,
                                  IGameBetLogResultCtrBaiRen gameBetLogResultCtr,
                                  ITableUtil tableUtil,
                                  BetAreaCtrConfig betAreaCtrConfig,
                                  CardWallCtrConfig cardWallCtrConfig,
                                  NiuBaiRenStackCtrConfig niuBaiRenStackCtrConfig,
                                  NiuBaiRenResultCtrConfig niuBaiRenResultCtrConfig) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtr, betAreaCtrConfig, tableUtil);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.stackCtr = new NiuBaiRenStackCtr(niuBaiRenStackCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.resultCtr = new NiuBaiRenResultCtr(niuBaiRenResultCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
    }

    //* 設定相關 *//

    // 取得最大賠率
    public int getMaxRate() {
        return this.resultCtr.getMaxRate();
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


    //* 發牌結果相關 *//

    // 取得所有區域牌號
    public int[][] getAllAreaCardNumber2dArray(){
        return this.cardWallCtr.getAllAreaCardNumber2dArray();
    }

    // 取得所有位置提牌
    public int[][] getAllAreaLiftCardNumber2dArray() {
        return this.cardWallCtr.changeAllAreaCardListMap2allPlayerCardNumberArray(this.stackCtr.getAreaLiftCardList());
    }


    //* 牌型結果相關 *//

    // 設定百人牛牌型
    @Override
    public void setStackMap() {
        this.stackCtr.setStackMap(
                this.stackCtr.calculateStackMap(
                        this.cardWallCtr.getAllAreaCardListMap()
                )
        );
    }

    // 計算押注區域輸贏陣列
    public int[] calculateAreaWinLossResult() {
        if (this.stackCtr.getStackMap().size() == 0) {
            return new int[]{};
        }

        return IntStream.range(0, ConstMathNiu.BetAreaEnum.getBetAreaCount())
                .mapToObj(
                        areaId -> ConstMathNiu.ChartEnum.calculateChartCode(
                                (NiuStackCommon) this.stackCtr.getStackMap().get(ConstMathNiu.BetAreaEnum.BANKER.getCode()),
                                (NiuStackCommon) this.stackCtr.getStackMap().get(areaId)))
                .collect(Collectors.toList())
                .stream().mapToInt(ConstMathNiu.ChartEnum::getCode).toArray();
    }

    // 取得牛牌型對應表
    public Map<Integer, AbstractStack> getStackMap() {
        return this.stackCtr.getStackMap();
    }


    //* 計算結果相關 *//

    // 設定輸贏結果對應表
    @Override
    public void setUidScoreMap(Map<Integer, UidScore> playerScoreMap){
        this.resultCtr.setUidScoreMap(playerScoreMap);
    }

    // 取得所有玩家輸贏結果
    @Override
    public Map<Integer, UidScore> getUidScoreMap(){
        return this.resultCtr.getUidScoreMap();
    }

    // 計算所有玩家得分
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap() {
        return this.resultCtr.calculateUidScoreMap(
                this.stackCtr.getStackMap(),
                super.betAreaCtr.getChairToAreaBetMap()
        );
    }

    // 提供卡牌計算所有玩家得分
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> unTakenCardListMap) {
        return this.resultCtr.calculateUidScoreMap(
                this.stackCtr.calculateStackMap(unTakenCardListMap),
                super.betAreaCtr.getChairToAreaBetMap()
        );
    }

    // 取得所有贏家總贏分
    @Override
    public double getAllWinScore(){
        return this.resultCtr.getAllWinScore();
    }

    // 計算玩家區域贏分陣列
    @Override
    public double[] calculatePlayerAreaScoreArray(int chairIndex){
        return this.resultCtr.calculateAreaNoFeeScoreArray(
                this.getStackMap(),
                this.getPlayerAreaBetMap(chairIndex));
    }

    // 計算玩家區域贏分對應表
    @Override
    public Map<Integer, Double> calculatePlayerAreaScoreMap(int chairIndex){
        // 1. 計算玩家區域贏分陣列
        double[] playerAreaScoreArray = this.calculatePlayerAreaScoreArray(chairIndex);

        // 2. 轉換為對應表
        return IntStream.range(0, playerAreaScoreArray.length)
                .boxed()
                .collect(Collectors.toMap(areaId -> areaId, areaId -> playerAreaScoreArray[areaId]));
    }

    // 取得真人輸贏結果
    @Override
    public UidScore getHumanUidScore() {
        return this.resultCtr.getHumanPlayerUidScore();
    }

    // 重設
    @Override
    public void reset(){
        super.reset();
        this.cardWallCtr.reset();
        this.stackCtr.reset();
        this.resultCtr.reset();
    }
}
