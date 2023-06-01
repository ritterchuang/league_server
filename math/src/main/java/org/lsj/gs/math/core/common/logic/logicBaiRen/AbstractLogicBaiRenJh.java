package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtr;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStack;
import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStackCtr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.ILogicJh;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 抽象百人金花類邏輯
public abstract class AbstractLogicBaiRenJh extends AbstractLogicBaiRen implements ILogicJh {
    private final CardWallCtr cardWallCtr; // 發牌計算器
    protected final JhStackCtr stackCtr; // 牌型計算器

    public AbstractLogicBaiRenJh(ITableBase table,
                                 TableFieldConfig tableFieldConfig,
                                 GamePlayerHlr gamePlayerHlr,
                                 PoolCtr poolCtr,
                                 IGameBetLogResultCtrBaiRen gameBetLogResultCtr,
                                 ITableUtil tableUtil,
                                 BetAreaCtrConfig betAreaCtrConfig,
                                 CardWallCtrConfig cardWallCtrConfig) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtr, betAreaCtrConfig, tableUtil);
        this.cardWallCtr = new CardWallCtr(cardWallCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.stackCtr = new JhStackCtr();
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

    // 取得金花牌型對應表
    public Map<Integer, JhStack> getStackMap(){
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
