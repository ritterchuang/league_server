package org.lsj.gs.math.games.cjnn_java.module.robotLogic;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRen;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRenNiu;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.robotLogic.entity.AreaBetResultBaiRen;
import org.lsj.gs.math.core.common.robotLogic.module.AbstractRobotLogic;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 新超級牛牛機器人邏輯
public class RobotLogicCjnnJava extends AbstractRobotLogic {
    private static final double STOP_BET_RESERVE_SEC = 1.0; // 停止下注保留時間
    private static final double BET_INTERVAL_SEC = 0.6; // 百人機器人下注間格秒數
    private final AbstractLogicBaiRenNiu logic;
    private final List<AreaBetResultBaiRen> areaBetResultBaiRenList; // 機器人區域押注結果對應表

    public RobotLogicCjnnJava(GamePlayerHlr gamePlayerHlr,
                              PoolCtr poolCtr,
                              ITableUtil tableUtil,
                              AbstractLogicBaiRen logic){
        super(gamePlayerHlr, tableUtil);
        this.logic = (AbstractLogicBaiRenNiu)logic;
        this.areaBetResultBaiRenList = new ArrayList<>();
    }

    // 產生機器人下注結果
    public List<AreaBetResultBaiRen> generateRobotAreaBetResultList(double leftTimeSec) {
        // 1. 清空
        this.areaBetResultBaiRenList.clear();

        // 2. 取得榜單機器人列表
        List<GamePlayer> robotPlayerList = super.gamePlayerHlr.getGamePlayerSortByBeginMoneyTopLimitRobotList(this.logic.getTopPlayerCount());

        // 3. 計算最多下注次數
        int maxBetCount = this.calculateMaxCount(leftTimeSec);

        // 4. 遍歷玩家產生區域押注結果
        robotPlayerList.forEach(gamePlayer -> this.calculateOneRobotAreaBetResult(gamePlayer, maxBetCount));

        // 5. 回傳副本
        return new ArrayList<>(this.areaBetResultBaiRenList);
    }

    // 產生一個機器人區域下注結果
    private void calculateOneRobotAreaBetResult(GamePlayer gamePlayer, int maxBetCount){
        // 1. 計算押注次數
        int betCount = this.calculateBetCount(maxBetCount);

        // 2. 計算押注時間列表
        List<Integer> betTimeMilliSecList = this.calculateBetTimeMilliSecList(betCount, maxBetCount);

        // 3. 遍歷押注
        for (int betTimeMilliSec : betTimeMilliSecList) {
            // 4. 計算押注區
            int randomAreaId = this.calculateRandomAreaId(gamePlayer);

            // 5. 計算籌碼
            int randomBet = this.calculateRandomBet(gamePlayer);

            // 6. 執行押注
            if(randomBet > 0) {
                this.areaBetResultBaiRenList.add(new AreaBetResultBaiRen(
                        betTimeMilliSec,
                        gamePlayer.getChairIndex(),
                        randomAreaId,
                        randomBet
                ));
            }
        }
    }

    // 計算最多下注次數
    private int calculateMaxCount(double leftTimeSec){
        if(leftTimeSec <= STOP_BET_RESERVE_SEC){
            return 0;
        }

        return (int) Math.floor(MathUtil.divide(
                MathUtil.subtract(leftTimeSec, STOP_BET_RESERVE_SEC),
                BET_INTERVAL_SEC)
        );
    }

    // 計算加注次數
    private int calculateBetCount(int maxBetCount){
        return (int) Math.floor(
                super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(maxBetCount, ConstMathCommon.AccuracyType.A32768)
                        / 3.0
        );
    }

    // 計算押注時間列表
    private List<Integer> calculateBetTimeMilliSecList(int randomBetCount, int maxBetCount) {
        List<Integer> result = new ArrayList<>();

        // 1. 加入押注點
        List<Integer> betPointList = new ArrayList<>();
        Stream.iterate(0, i -> 0).limit(randomBetCount).forEach(i -> betPointList.add(1));
        Stream.iterate(0, i -> 0).limit(maxBetCount - randomBetCount).forEach(i -> betPointList.add(0));

        // 2. 洗牌
        super.tableUtil.getNotSupportSetRngDataRandomUtil().shuffleList(betPointList);

        // 3. 轉換押注時間列表
        for (int pointIndex = 0; pointIndex < betPointList.size(); pointIndex++) {
            if(betPointList.get(pointIndex) == 1)
            result.add((int) MathUtil.multiply(MathUtil.multiply(
                            pointIndex,
                            BET_INTERVAL_SEC),
                            1000
                    ));
        }

        return result;
    }

    // 計算下注區域
    private int calculateRandomAreaId(GamePlayer gamePlayer){
        return super.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomElement(
                Arrays.stream(ConstMathNiu.BetAreaEnum.values())
                        .filter(betAreaEnum ->
                                betAreaEnum.getCode() != ConstMathNiu.BetAreaEnum.INVALID.getCode()
                                        && betAreaEnum.getCode() != ConstMathNiu.BetAreaEnum.BANKER.getCode())
                        .mapToInt(ConstMathNiu.BetAreaEnum::getCode).boxed().collect(Collectors.toList()),
                ConstMathCommon.AccuracyType.A32768
        );
    }

    // 計算籌碼
    private int calculateRandomBet(GamePlayer gamePlayer) {
        return super.calculateRandomElementInt(this.logic.getChipsList().stream().filter(chips ->
                MathUtil.subtract(gamePlayer.getBeginMoney(), this.calculateRobotTotalBet(gamePlayer.getChairIndex()))
                        >= chips).collect(Collectors.toList()));
    }

    // 計算機器人總押注
    private int calculateRobotTotalBet(int chairIndex){
        return Arrays.stream(this.calculateRobotAreaBetArray(chairIndex)).sum();
    }

    // 計算機器人押注區域金額
    private int[] calculateRobotAreaBetArray(int chairIndex){
        int[] result = new int[this.logic.getAreaCount()];

        for (AreaBetResultBaiRen robotAreaBetResult : this.areaBetResultBaiRenList) {
            if(robotAreaBetResult.getChairIndex() == chairIndex){
                result[robotAreaBetResult.getAreaId()] += robotAreaBetResult.getBetAmount();
            }
        }

        return result;
    }

    // 重設
    public void reset() {}
}
