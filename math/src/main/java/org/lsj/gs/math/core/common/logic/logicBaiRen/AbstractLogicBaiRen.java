package org.lsj.gs.math.core.common.logic.logicBaiRen;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtr;
import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogic;
import org.lsj.gs.math.core.common.logic.ILogic;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultBaiRen;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.entity.detail.play.IDetailPlayBaiRen;
import org.lsj.gs.math.core.common.table.module.ITableBase;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.List;
import java.util.Map;

// 抽象百人類邏輯
public abstract class AbstractLogicBaiRen extends AbstractLogic implements ILogicBaiRen, ILogic {
    private static final int TOP_PLAYER_COUNT = 6; // 排行榜人數
    protected final BetAreaCtr betAreaCtr; // 押注區域計算器
    protected final IGameBetLogResultCtrBaiRen gameBetLogResultCtr; // 遊戲投注紀錄計算器

    public AbstractLogicBaiRen(ITableBase table,
                               TableFieldConfig tableFieldConfig,
                               GamePlayerHlr gamePlayerHlr,
                               PoolCtr poolCtr,
                               IGameBetLogResultCtrBaiRen gameBetLogResultCtr,
                               BetAreaCtrConfig betAreaCtrConfig,
                               ITableUtil tableUtil) {
        super(table, tableFieldConfig, gamePlayerHlr, poolCtr, gameBetLogResultCtr, tableUtil);
        this.betAreaCtr = new BetAreaCtr(betAreaCtrConfig, gamePlayerHlr, poolCtr, tableUtil);
        this.gameBetLogResultCtr = gameBetLogResultCtr;
    }

    //* 押注區域計算器 *//

    //** 存取投注區域資訊 *//

    // 更新投注區域
    @Override
    public void updateAreaBet(int chairIndex, int areaId, int betAmount) {
        this.betAreaCtr.updateAreaBet(chairIndex, areaId, betAmount);
    }

    // 取得所有押注區域金額對應表
    public Map<Integer, Integer> getPlayerAreaBetMap(int chairIndex){
        return this.betAreaCtr.getPlayerAreaBetMap(chairIndex);
    }

    // 取得所有押注區域金額陣列
    public int[] getAreaBetArray(){
        return this.betAreaCtr.getAreaBetArray();
    }

    // 取得押注區域數
    public int getAreaCount(){
        return this.betAreaCtr.getAreaCount();
    }

    // 取得指定玩家總押注金額
    @Override
    public int getPlayerTotalAreaBet(int chairIndex){
        return this.betAreaCtr.getPlayerTotalAreaBet(chairIndex);
    }

    // 計算押注後剩餘金額
    public double calculateBeginMoneyAfterBet(int chairIndex){
        return this.betAreaCtr.calculateBeginMoneyAfterBet(
                this.getAllGamePlayerMap().get(chairIndex).getBeginMoney(),
                chairIndex);
    }

    // 取得指定玩家押注區域金額陣列
    public int[] getPlayerAreaBetArray(int chairIndex) {
        return this.betAreaCtr.getPlayerAreaBetArray(chairIndex);
    }

    // 取得指定玩家押注區域識別碼陣列
    public int[] getPlayerAreaIdArray(int chairIndex){
        return this.betAreaCtr.getPlayerAreaIdArray(chairIndex);
    }

    // 取得下注籌碼列表
    public List<Integer> getChipsList() {
        return this.betAreaCtr.getChipsList();
    }

    // 取得押注區域限紅陣列
    public List<Integer> getAreasTopLimitList(){
        return this.betAreaCtr.getAreasTopLimitList();
    }

    // 取得押注區域限紅對應表 <區域識別碼, 押注區域限紅>
    public Map<Integer, Integer> getAreasTopLimitMap() {
        return this.betAreaCtr.getAreasTopLimitMap();
    }


    //** 接收下注資訊相關 **//

    // 驗證下注區域合法性
    public boolean isValidArea(int area) {
        return this.betAreaCtr.isValidArea(area);
    }

    // 驗證下注金額列表合法性
    public boolean isValidChips(int chips) {
        return this.betAreaCtr.isValidChips(chips);
    }

    // 驗證下注單點限額
    public boolean isAreaBetToTopLimit(int chairIndex, int area, int chips) {
        return this.betAreaCtr.isAreaBetToTopLimit(chairIndex, area, chips);
    }

    // 驗證下注區域可行性
    public boolean canBetToArea(int chairIndex, int area) {
        return this.betAreaCtr.canBetToArea(chairIndex, area);
    }

    // 新增詳細日誌
    @Override
    public void addDetailBaiRen(IDetailPlayBaiRen detail) { this.gameBetLogResultCtr.addDetailPlayBaiRen(detail);}


    //* 遊戲投注紀錄計算器 *//

    // 計算下注紀錄結果
    @Override
    public IGameBetLogResultBaiRen getGameBetLogResult(UidScore uidScore) {
        return this.gameBetLogResultCtr.calculateGameBetLogResult(super.table.getRoundId(), uidScore);
    }


    //* 牌桌資訊相關 *//
    public int getTopPlayerCount(){
        return TOP_PLAYER_COUNT;
    }


    //* 玩家資訊相關 *//

    // 計算 stc_money 玩家資訊
    public List<GamePlayer> calculateStcMoneyGamePlayerList() {
        List<GamePlayer> result = super.gamePlayerHlr.getGamePlayerSortByBeginMoneyTopLimitList(this.getTopPlayerCount());

        if (result.stream().anyMatch(gamePlayer -> gamePlayer.getUid() == super.gamePlayerHlr.getHumanUid())) {
            return result;
        }

        result.add(super.gamePlayerHlr.getHumanGamePlayer());

        return result;
    }

    // 計算 押注前榜單列表
    public List<GamePlayer> calculateBeforeGameRichGamePlayerList() {
        return super.gamePlayerHlr.getGamePlayerSortByBeginMoneyTopLimitList(this.getTopPlayerCount());
    }

    // 玩家是否押注標誌
    public boolean isHumanBet() {
        return (this.getPlayerTotalAreaBet(this.getHumanChairIndex()) > 0);
    }

    // 重設
    @Override
    public void reset(){
        this.betAreaCtr.reset();
        this.gameBetLogResultCtr.reset();
    }
}
