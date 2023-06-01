package org.lsj.gs.math.core.common.robotLogic.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogicQz;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotRateResultBanker;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotSelectResultBanker;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 抽象搶莊類機器人邏輯
public class AbstractRobotLogicBanker extends AbstractRobotLogic {
    private final RobotLogicConfigBanker config; // 機器人設定
    private final AbstractLogicQz logic; // 搶莊邏輯
    private final Map<Integer, RobotRateResultBanker> robotVieBankerRateResultMap; // 機器人搶莊結果對應表 <座位索引, 搶莊結果>
    private final Map<Integer, RobotRateResultBanker> robotBetRateResultMap; // 機器人下注結果對應表 <座位索引, 下注結果>

    public AbstractRobotLogicBanker(GamePlayerHlr gamePlayerHlr,
                                    ITableUtil tableUtil,
                                    RobotLogicConfigBanker config,
                                    AbstractLogicQz logic) {
        super(gamePlayerHlr, tableUtil);
        this.config = config;
        this.logic = logic;
        this.robotVieBankerRateResultMap = new HashMap<>();
        this.robotBetRateResultMap = new HashMap<>();
    }

    // 產出機器人搶莊結果
    public Map<Integer, RobotRateResultBanker> generateRobotVieBankResult() {
        // 1. 清空
        this.robotVieBankerRateResultMap.clear();

        // 2. 取得機器人座位列表
        List<Integer> robotPlayerList = super.gamePlayerHlr.getRobotChairIndexList();

        // 3. 計算機器人搶莊結果
        robotPlayerList.forEach(chairIndex ->
                this.robotVieBankerRateResultMap.put(chairIndex, new RobotRateResultBanker(this.calculateActionMilliTime(this.config.getVieBankerMilliTimeSec()), this.calculateVieRate(chairIndex))));

        // 4. 回傳副本
        return new HashMap<>(this.robotVieBankerRateResultMap);
    }

    // 產出機器人下注結果
    public Map<Integer, RobotRateResultBanker> generateRobotBetResult() {
        // 1. 清空
        this.robotBetRateResultMap.clear();

        // 2. 取得機器人座位列表
        List<Integer> robotPlayerList = super.gamePlayerHlr.getRobotChairIndexList();

        // 3. 計算機器人下注結果
        robotPlayerList.stream().filter(chair -> !this.logic.isBanker(chair)).forEach(chairIndex ->
                this.robotBetRateResultMap.put(chairIndex, new RobotRateResultBanker(this.calculateActionMilliTime(this.config.getBetMilliTimeSec()), this.calculateBetRate(chairIndex))));

        // 4. 回傳副本
        return new HashMap<>(this.robotBetRateResultMap);
    }

    // 產出機器人攤牌結果
    public Map<Integer, RobotSelectResultBanker> generateRobotSelectResult() {
        Map<Integer, RobotSelectResultBanker> result = new HashMap<>();

        List<Integer> robotPlayerList = super.gamePlayerHlr.getRobotChairIndexList();

        robotPlayerList.forEach(chairIndex -> result.put(chairIndex, new RobotSelectResultBanker(this.calculateActionMilliTime(this.config.getSelectMilliTimeSec()), 1)));

        return result;
    }

    // 計算搶莊倍數
    private int calculateVieRate(int chairIndex) {
        // 1. 取得倍數列表
        List<Integer> canVieRateList = this.logic.getPlayerCanVieRateList(chairIndex);

        // 2. 取得權重
        List<Integer> weightList = this.config.getVieRateWeight().get(canVieRateList.size() - 1);

        // 3. 依照權重取得索引
        int index = this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(weightList, ConstMathCommon.AccuracyType.A32768);

        // 4. 回傳結果
        return canVieRateList.get(index);
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

    public Map<Integer, RobotRateResultBanker> getRobotVieBankerRateResultMap() {
        return robotVieBankerRateResultMap;
    }

    public Map<Integer, RobotRateResultBanker> getRobotBetRateResultMap() {
        return robotBetRateResultMap;
    }
}
