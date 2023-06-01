package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.lhStackCtr.LhStack;
import org.lsj.gs.math.core.card.stackCtr.lhStackCtr.LhStackCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.ILogicLh;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;

// 抽象百人龍虎類邏輯
public abstract class AbstractLogicBaiRenLh extends AbstractLogicBaiRen implements ILogicLh {
    private static final int CARD_AREA_COUNT = 2; // 龍虎發牌區域數
    private final CardWallCtr cardWallCtr; // 發牌計算器
    protected final LhStackCtr stackCtr; // 牌型計算器

    public AbstractLogicBaiRenLh(ITableBase table,
                                 TableFieldConfig tableFieldConfig,
                                 GamePlayerHlr gamePlayerHlr,
                                 PoolCtr poolCtr,
                                 IGameBetLogResultCtrBaiRen gameBetLogResultCtr,
                                 ITableUtil tableUtil,
                                 BetAreaCtrConfig betAreaCtrConfig,
                                 CardWallCtrConfig cardWallCtrConfig) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtr, betAreaCtrConfig, tableUtil);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.stackCtr = new LhStackCtr();
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
    public int[] getAllAreaCardNumberArray(){
        // 1. 建立區域牌空間
        int[] result = new int[CARD_AREA_COUNT];

        // 2. 取得二維區域牌
        int[][] allAreaCard2dArray = this.getAllAreaCardNumber2dArray();

        // 3. 遍歷
        for(int areaIndex = 0; areaIndex < allAreaCard2dArray.length; areaIndex++){
            if(Objects.nonNull(allAreaCard2dArray[areaIndex]) && allAreaCard2dArray[areaIndex].length >= 1){
                result[areaIndex] = allAreaCard2dArray[areaIndex][0];
            }
        }

        return result;
    }

    // 取得所有區域牌點數
    public int[] getAllAreaCardPointArray() {
        // 1. 建立區域牌空間
        int[] result = new int[CARD_AREA_COUNT];

        // 2. 取得二維區域牌
        int[][] allAreaCard2dArray = this.getAllAreaCardPoint2dArray();

        // 3. 遍歷
        for(int areaIndex = 0; areaIndex < allAreaCard2dArray.length; areaIndex++){
            if(Objects.nonNull(allAreaCard2dArray[areaIndex]) && allAreaCard2dArray[areaIndex].length >= 1){
                result[areaIndex] = allAreaCard2dArray[areaIndex][0];
            }
        }

        return result;
    }

    // 取得所有區域牌號
    private int[][] getAllAreaCardNumber2dArray(){
        return this.cardWallCtr.getAllAreaCardNumber2dArray();
    }

    // 取得所有區域牌點數
    private int[][] getAllAreaCardPoint2dArray(){
        return this.cardWallCtr.getAllAreaCardPoint2dArray();
    }


    //* 牌型結果相關 *//

    // 設定龍虎牌型
    @Override
    public void setStackMap() {
        this.stackCtr.setStackMap(
                this.stackCtr.calculateStackMap(
                        this.cardWallCtr.getAllAreaCardListMap()
                )
        );
    }

    // 取得龍虎牌型對應表
    public Map<Integer, LhStack> getStackMap(){
        return this.stackCtr.getStackMap();
    }

    // 重設
    @Override
    public void reset(){
        super.reset();
        this.cardWallCtr.reset();
        this.stackCtr.reset();
    }
}
