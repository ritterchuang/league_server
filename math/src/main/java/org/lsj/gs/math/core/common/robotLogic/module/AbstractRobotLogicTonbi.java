package org.lsj.gs.math.core.common.robotLogic.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogicTb;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigTonbi;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotRateResultTonbi;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotSelectResultTonbi;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 抽象通比類機器人邏輯
public class AbstractRobotLogicTonbi extends AbstractRobotLogic {
    private final RobotLogicConfigTonbi config; // 機器人設定
    private final AbstractLogicTb logic; // 通比邏輯
    private final Map<Integer, RobotRateResultTonbi> robotBetRateResultMap; // 機器人下注結果對應表 <座位索引, 下注結果>

    public AbstractRobotLogicTonbi(GamePlayerHlr gamePlayerHlr,
                                   ITableUtil tableUtil,
                                   RobotLogicConfigTonbi config,
                                   AbstractLogicTb logic) {
        super(gamePlayerHlr, tableUtil);
        this.config = config;
        this.logic = logic;
        this.robotBetRateResultMap = new HashMap<>();
    }


    // 產出機器人下注結果
    public Map<Integer, RobotRateResultTonbi> generateRobotBetResult() {
        // 1. 清空
        this.robotBetRateResultMap.clear();

        // 2. 取得機器人座位列表
        List<Integer> robotPlayerList = super.gamePlayerHlr.getRobotChairIndexList();

        // 3. 計算機器人下注結果
        robotPlayerList.stream().forEach(chairIndex ->
                this.robotBetRateResultMap.put(chairIndex, new RobotRateResultTonbi(this.calculateActionMilliTime(this.config.getBetMilliTimeSec()), this.calculateBetRate(chairIndex))));

        // 4. 回傳副本
        return new HashMap<>(this.robotBetRateResultMap);
    }

    // 產出機器人攤牌結果
    public Map<Integer, RobotSelectResultTonbi> generateRobotSelectResult() {
        Map<Integer, RobotSelectResultTonbi> result = new HashMap<>();

        List<Integer> robotPlayerList = super.gamePlayerHlr.getRobotChairIndexList();

        robotPlayerList.forEach(chairIndex -> result.put(chairIndex, new RobotSelectResultTonbi(this.calculateActionMilliTime(this.config.getSelectMilliTimeSec()), 1)));

        return result;
    }


    // 計算押注倍數
    private int calculateBetRate(int chairIndex) {
        // 1. 取得倍數列表
        List<Integer> canBetRateList = this.logic.getPlayerCanBetRateList(chairIndex);

        // 2. 取得權重
        List<Integer> weightList = this.config.getBetRateWeight().get(canBetRateList.size() - 1);

        // 3. 依照權重取得索引
        int index = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(weightList, ConstMathCommon.AccuracyType.A32768);

        // 4. 回傳結果
        return canBetRateList.get(index);
    }

    // 計算動作秒數
    private long calculateActionMilliTime(long milliTime) {
        return this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy((int) milliTime, ConstMathCommon.AccuracyType.A32768);
    }


    public Map<Integer, RobotRateResultTonbi> getRobotBetRateResultMap() {
        return robotBetRateResultMap;
    }
}
