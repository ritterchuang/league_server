package org.lsj.gs.math.core.baiRen;

import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRen;
import org.lsj.gs.math.core.common.robotLogic.entity.AreaBetResultBaiRen;
import org.lsj.gs.math.core.common.table.entity.message.baiRen.*;
import org.lsj.gs.math.core.common.table.entity.message.core.data.StcMoneyData;

import java.util.ArrayList;
import java.util.List;

// 百人訊息計算者
public class StcMessageUtilBaiRen {

    // 計算 動作訊息
    public StcAction calculateStcAction(int actionCode, double actionTimeSec) {
        return new StcAction(actionCode, actionTimeSec);
    }

    // 計算 押注區金額訊息
    public StcArea calculateStcArea(AbstractLogicBaiRen logicBaiRen) {
        return new StcArea(logicBaiRen.getAreaBetArray());
    }

    // 計算 玩家下注訊息
    public StcBet calculateStcBet(AreaBetResultBaiRen areaBetResult, AbstractLogicBaiRen logicBaiRen) {
        GamePlayer gamePlayer = logicBaiRen.getAllGamePlayerMap().get(areaBetResult.getChairIndex());

        return new StcBet(
                gamePlayer.getUid(),
                areaBetResult.getAreaId(),
                areaBetResult.getBetAmount(),
                logicBaiRen.getPlayerAreaIdArray(areaBetResult.getChairIndex()),
                logicBaiRen.calculateBeginMoneyAfterBet(areaBetResult.getChairIndex())
        );
    }

    // 計算 下注結果訊息
    public StcBetResult calculateStcBetResult(int chairIndex, CtsBetBaiRenJava ctsBetBaiRenJava, int code, AbstractLogicBaiRen logicBaiRen) {
        return new StcBetResult(
                code,
                ctsBetBaiRenJava.getArea(),
                ctsBetBaiRenJava.getChips(),
                logicBaiRen.getPlayerAreaBetArray(chairIndex),
                logicBaiRen.calculateBeginMoneyAfterBet(chairIndex),
                String.valueOf(code)
        );
    }

    // 計算 玩家餘額訊息
    public StcMoney calculateStcMoney(AbstractLogicBaiRen logicBaiRen) {
        List<GamePlayer> stcMoneyGamePlayerList = logicBaiRen.calculateStcMoneyGamePlayerList();

        List<StcMoneyData> stcMoneyDataList = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < stcMoneyGamePlayerList.size(); playerIndex++) {
            GamePlayer gamePlayer = stcMoneyGamePlayerList.get(playerIndex);
            stcMoneyDataList.add(new StcMoneyData(gamePlayer.getUid(), gamePlayer.getBeginMoney()));
        }

        return new StcMoney(stcMoneyDataList.toArray(StcMoneyData[]::new));
    }

    // 計算 真人下注訊息
    public StcOwnBet calculateStcOwnBet(AbstractLogicBaiRen logicBaiRen) {
        return new StcOwnBet(
                -1, 0, logicBaiRen.getPlayerAreaBetArray(logicBaiRen.getHumanChairIndex())
        );
    }

    // 計算 榜單資訊
    public StcRichList calculateStcRichList(AbstractLogicBaiRen logicBaiRen) {
        // 1. 取得榜單列表
        List<GamePlayer> gamePlayerList = logicBaiRen.calculateBeforeGameRichGamePlayerList();

        // 2. 固定神算子位置
        int predictPlayerIndex = 1;

        // 3. 計算榜單資訊
        List<StcRichData> stcRichDataList = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < gamePlayerList.size(); playerIndex++) {
            GamePlayer gamePlayer = gamePlayerList.get(playerIndex);
            stcRichDataList.add(
                    new StcRichData(
                            gamePlayer.getUid(),
                            gamePlayer.getNickName(),
                            gamePlayer.getHeadImgUrl(),
                            gamePlayer.getAfterMoney(),
                            gamePlayer.getSex(),
                            gamePlayer.getVipLevel(),
                            (playerIndex == predictPlayerIndex) ? ConstMathBaiRen.RichPlayerType.PREDICT.getCode() : ConstMathBaiRen.RichPlayerType.RICH.getCode()
                    ));
        }

        return new StcRichList(stcRichDataList.toArray(StcRichData[]::new));
    }

    // 計算 玩家人數消息
    public StcUserNum calculateUserNum(int userNum) {
        return new StcUserNum(userNum);
    }
}
