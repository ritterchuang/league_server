package org.lsj.gs.math.core.card;

import org.lsj.ConstCommon;
import org.lsj.gs.math.core.card.cardWallCtr.CardMahjong;
import org.lsj.gs.math.core.card.cardWallCtr.CardPaiGow;
import org.lsj.gs.math.core.card.cardWallCtr.CardPoker;
import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.user.User;
import org.lsj.gs.user.UserBdr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 抽象牌型工具包
public class AbstractCardTestUtil {

    //* 創牌相關 *//

    // 依照指定牌 輸出牌列表
    protected List<ICard> generateCardList(ConstMathCard.CardType cardType, int[] cardNumberArray) {
        // 產牌
        List<ICard> result = new ArrayList<>();
        for (int cardNumber : cardNumberArray) {
            result.add(this.generateCard(cardType, cardNumber, 1));
        }

        return result;
    }

    // 創建牌權重
    protected Map<Integer, Integer> generateCardValueWeightMapByPointToWeight() {
        return new HashMap<>(){{
            put(ConstMathCard.PointType.P_A.getPoint(), 1);
            put(ConstMathCard.PointType.P_2.getPoint(),2);
            put(ConstMathCard.PointType.P_3.getPoint(),3);
            put(ConstMathCard.PointType.P_4.getPoint(),4);
            put(ConstMathCard.PointType.P_5.getPoint(),5);
            put(ConstMathCard.PointType.P_6.getPoint(),6);
            put(ConstMathCard.PointType.P_7.getPoint(),7);
            put(ConstMathCard.PointType.P_8.getPoint(),8);
            put(ConstMathCard.PointType.P_9.getPoint(),9);
            put(ConstMathCard.PointType.P_T.getPoint(),10);
            put(ConstMathCard.PointType.P_J.getPoint(),11);
            put(ConstMathCard.PointType.P_Q.getPoint(),12);
            put(ConstMathCard.PointType.P_K.getPoint(),13);
        }};
    }

    // 依照牌類型，產牌
    private ICard generateCard(ConstMathCard.CardType cardType, int cardValue, int countIndex) {
        switch (cardType) {
            case PAIGOW: return new CardPaiGow(cardValue, countIndex);
            case MAHJONG: return new CardMahjong(cardValue, countIndex);
            default: return new CardPoker(cardValue, countIndex, this.generateCardValueWeightMapByPointToWeight());
        }
    }


    //* 搶莊類型，下注相關 *//

    // 給定座位、倍數
    protected Map<Integer, Integer> generateBetRateMap(int[] chairIndexArray, int[] betArray) {
        Map<Integer, Integer> result = new HashMap<>();

        for (int index = 0; index < chairIndexArray.length; index++) {
            result.put(chairIndexArray[index], betArray[index]);
        }

        return result;
    }


    //* 玩家資訊相關 *//

    // 給定真人/機器人，初始金額，創建 Map<Integer, GamePlayer>
    protected Map<Integer, GamePlayer> generateGamePlayerMapWithBeginMoney(int[] humanAiCode, double[] beginMoneys) {
        Map<Integer, GamePlayer> result = new HashMap<>();

        for (int chairIndex = 0; chairIndex < humanAiCode.length; chairIndex++) {
            User tempUser = (User) new UserBdr()
                    .setUid(100000 + chairIndex)
                    .setIp("127.0.0.1")
                    .setRobot(humanAiCode[chairIndex])
                    .setNickName("test_" + chairIndex)
                    .setAccount("testUser_" + chairIndex)
                    .setHeadImgUrl("2")
                    .setSex(0)
                    .setBoxid(0)
                    .setRole(ConstCommon.RoleType.PLAYER.getCode())
                    .setChair(0)
                    .setState(ConstCommon.UserStateType.WAIT.getCode())
                    .setVipLevel(1)
                    .createUser();

            result.put(chairIndex, new GamePlayer(tempUser, chairIndex , beginMoneys[chairIndex]));
        }

        return result;
    }

    // 給定真人/機器人，初始金額，創建 Map<Integer, GamePlayer>
    protected Map<Integer, UidScore> generateUidScoreMap(
            List<Integer> chairIndexList,
            List<Integer> uidList,
            List<Double> betList,
            List<Double> validBetList,
            List<Double> returnAwardList,
            List<Double> scoreList,
            List<Double> feeList
            ) {
        Map<Integer, UidScore> result = new HashMap<>();

        for (int index = 0; index < chairIndexList.size(); index++) {
            int chairIndex = chairIndexList.get(index);

            UidScore tempUidScore = new UidScore(
                    chairIndex,
                    uidList.get(index),
                    betList.get(index),
                    validBetList.get(index),
                    returnAwardList.get(index),
                    scoreList.get(index),
                    feeList.get(index)
            );
            result.put(chairIndex, tempUidScore);
        }

        return result;
    }
}