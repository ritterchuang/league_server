package org.lsj.gs.math.games.hhdz_java.module.logic;

import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigHhdzJava;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.resultCtr.jhResultCtr.JhBaiRenResultCtr;
import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrBaiRen;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRenJh;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.hhdz_java.TableHhdzJava;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 新紅黑大戰邏輯
public class LogicHhdzJava extends AbstractLogicBaiRenJh implements ILogicHhdzJava {
    private final JhBaiRenResultCtr resultCtr; // 結果計算器

    public LogicHhdzJava(TableHhdzJava table,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigHhdzJava tableGameConfig,
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
                        ConstHhdzJava.BetAreaIdHhdzJava.getBetAreaCount()
                ),
                table.getTableConfigMgr().getModuleConfigMgrCard().createCardWallCtrConfig(
                        tableGameConfig.getCardWallCtrGameConfig().getCardType(),
                        tableGameConfig.getCardWallCtrGameConfig().getInitWallList(),
                        tableGameConfig.getCardWallCtrGameConfig().getCardValueWeightMap()
                )
        );

        this.resultCtr = new JhBaiRenResultCtr(
                table.getTableConfigMgr().getModuleConfigMgrBaiRen().createHhdzBaiRenResultCtrConfig(
                        ConstHhdzJava.BetAreaIdHhdzJava.getBetAreaCount(),
                        tableFieldConfig.getFeeType(),
                        tableFieldConfig.getGameRate(),
                        tableGameConfig.getResultCtrGameConfig().getCardTypeRateConfig()
                ),
                gamePlayerHlr,
                poolCtr,
                tableUtil);
    }

    //* 牌相關 *//

    // 取得紅牌
    public int[] getRedCardArray(){
        int[][] areaCardNumber2dArray = super.getAllAreaCardNumber2dArray();

        if (areaCardNumber2dArray.length == 0) {
            return new int[]{};
        }

        return areaCardNumber2dArray[ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()];
    }

    // 取得黑牌
    public int[] getBlackCardArray(){
        int[][] areaCardNumber2dArray = super.getAllAreaCardNumber2dArray();

        if (areaCardNumber2dArray.length == 0) {
            return new int[]{};
        }

        return areaCardNumber2dArray[ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()];
    }

    // 取得金花牌型陣列
    public int[] getAreaStackArray(){
        Map<Integer, JhStack> stackMap = this.stackCtr.getStackMap();

        // 空值防呆
        if(Objects.isNull(stackMap.get(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()))
        || Objects.isNull(stackMap.get(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()))){
            return new int[2];
        }

        return new int[]{
                stackMap.get(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode()).getTypeEnumCommon().getType(),
                stackMap.get(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode()).getTypeEnumCommon().getType()
        };
    }

    // 計算贏分區域陣列
    public int[] calculateWinAreaArray(){
        return this.resultCtr.calculateWinAreasArray(super.stackCtr.getStackMap());
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