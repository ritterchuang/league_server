package org.lsj.gs.math.games.lhd_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigLhdJava;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.resultCtr.lhdResultCtr.LhdResultCtr;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRenLh;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.lhd_java.TableLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 新龍虎鬥邏輯
public class LogicLhdJava extends AbstractLogicBaiRenLh implements ILogicLhdJava {
    private final LhdResultCtr resultCtr; // 結果計算器

    public LogicLhdJava(TableLhdJava table,
                        TableFieldConfig tableFieldConfig,
                        TableGameConfigLhdJava tableGameConfig,
                        GamePlayerHlr gamePlayerHlr,
                        PoolCtr poolCtr,
                        IGameBetLogResultCtrBaiRen gameBetLogResultCtr,
                        ITableUtil tableUtil) {
        // 1. 初始化
        super(  table,
                tableFieldConfig,
                gamePlayerHlr,
                poolCtr,
                gameBetLogResultCtr,
                tableUtil,
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createBetAreaCtrConfig(
                        tableGameConfig.getBetAreaCtrGameConfig().getChipsOddsList(),
                        tableGameConfig.getBetAreaCtrGameConfig().getAreasTopLimitOddsMap(),
                        tableGameConfig.getBetAreaCtrGameConfig().getCantBetTogetherArray(),
                        ConstLhdJava.BetAreaIdLhdJava.getBetAreaCount()
                ),
                table.getTableConfigMgr().getModuleConfigMgrCard().createCardWallCtrConfig(
                        tableGameConfig.getCardWallCtrGameConfig().getCardType(),
                        tableGameConfig.getCardWallCtrGameConfig().getInitWallList(),
                        tableGameConfig.getCardWallCtrGameConfig().getCardValueWeightMap()
                )
        );

        this.resultCtr = new LhdResultCtr(
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createLhResultCtrConfig(
                    ConstLhdJava.BetAreaIdLhdJava.getBetAreaCount()
                ),
                gamePlayerHlr,
                poolCtr,
                tableUtil);
    }

    //** 計算結果相關 **//

    // 取得所有玩家輸贏結果
    public Map<Integer, UidScore> getUidScoreMap(){
        return this.resultCtr.getUidScoreMap();
    }

    // 取得所有贏家總贏分
    public double getAllWinScore(){
        return this.resultCtr.getAllWinScore();
    }

    // 計算玩家區域贏分陣列
    public double[] calculatePlayerAreaScoreArray(int chairIndex){
        return this.resultCtr.calculateAreaNoFeeScoreArray(
                this.getStackMap(),
                this.getPlayerAreaBetMap(chairIndex));
    }

    // 計算玩家區域贏分對應表
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

    // 設定輸贏結果對應表
    @Override
    public void setUidScoreMap(Map<Integer, UidScore> playerScoreMap){
        this.resultCtr.setUidScoreMap(playerScoreMap);
    }

    // 計算所有玩家得分
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap() {
        return this.resultCtr.calculateUidScoreMap(
                super.stackCtr.getStackMap(),
                super.betAreaCtr.getChairToAreaBetMap()
        );
    }

    // 取得真人輸贏結果
    @Override
    public UidScore getHumanUidScore() {
        return this.resultCtr.getHumanPlayerUidScore();
    }

    // 提供卡牌計算所有玩家得分
    @Override
    public Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> unTakenCardListMap) {
        return this.resultCtr.calculateUidScoreMap(
                super.stackCtr.calculateStackMap(unTakenCardListMap),
                super.betAreaCtr.getChairToAreaBetMap()
        );
    }

    // 重設
    @Override
    public void reset(){
        super.reset();
        this.resultCtr.reset();
    }
}