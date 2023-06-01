package org.lsj.gs.math.games.qznn_ksz_java.module.gameAdjust;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.AbstractShuffle;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.qznn_ksz_java.entity.PreGameAdjustResultQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.logic.LogicQznnKszJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 看三張搶莊牛牛發牌
public class ShuffleQznnKszJava extends AbstractShuffle implements IShuffle {
    private final LogicQznnKszJava logic; // 邏輯
    private final int DEAL_COUNT_PER_PLAYER = 2; // 每人發牌數

    public ShuffleQznnKszJava(AlgorithmTypeCtr algorithmTypeCtr, LogicQznnKszJava logicQZNN, ITableUtil tableUtil) {
        super(algorithmTypeCtr, tableUtil);
        this.logic = logicQZNN;
    }

    // 發預遊戲結果
    @Override
    public PreGameAdjustResult dealPreGameAdjustResult(GameAdjustParameter gameAdjustParameter) {
        switch(super.getShuffleType()){
            case NATURE: return this.dealPreGameResult();
            default: return null;
        }
    }

    private PreGameAdjustResult dealPreGameResult(){
        // 1. 取得玩家列表
        List<Integer> allGamePlayerChairIndexList = this.logic.getAllGamePlayerChairIndexList();

        // 2. 洗牌
        this.logic.shuffleCardWall();

        // 3. 發未取牌
        List<ICard> unTakenCardList = this.logic.dealUnTakenCardList(this.DEAL_COUNT_PER_PLAYER * allGamePlayerChairIndexList.size());

        // 4. 分牌
        Map<Integer, List<ICard>> unTakenCardListMap = new HashMap<>();
        allGamePlayerChairIndexList.forEach(chairIndex ->
                unTakenCardListMap.put(
                        chairIndex,
                        unTakenCardList.stream().skip((long) unTakenCardListMap.size() * this.DEAL_COUNT_PER_PLAYER).limit(this.DEAL_COUNT_PER_PLAYER).collect(Collectors.toList())));

        // 5. 封裝
        return new PreGameAdjustResultQznnKszJava(unTakenCardListMap);
    }


    // 應用預遊戲結果
    @Override
    public void applyPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 將牌堆得牌改成取出
        Map<Integer, List<ICard>> unTakenCardListMap = ((PreGameAdjustResultQznnKszJava) preGameAdjustResult).getAllPlayerUnTakenCardListMap();

        // 2. 將牌改成取出狀態
        unTakenCardListMap.values().forEach(cardList -> cardList.forEach(this.logic::getAppointObjCard));

        // 3. 紀錄手牌
        this.logic.addPlayerHandCardListMap(unTakenCardListMap);
    }

    // 計算發牌結果
    @Override
    public GameAdjustResult calculateGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 整合牌
        Map<Integer, List<ICard>> playerCardList = this.calculateMergerPlayerCardList(preGameAdjustResult);

        // 2. 計算所有玩家輸贏結果
        Map<Integer, UidScore> uidScoreMap = this.logic.calculateUidScoreMap(playerCardList);

        // 3. 封裝結果
        return new GameAdjustResult(uidScoreMap, preGameAdjustResult);
    }

    // 計算合併後的所有玩家卡牌
    private Map<Integer, List<ICard>> calculateMergerPlayerCardList(PreGameAdjustResult preGameAdjustResult){
        Map<Integer, List<ICard>> result = new HashMap<>(){};

        // 1. 前三張手牌
        Map<Integer, List<ICard>> playerCardListFirstThree = this.logic.getHandCardListMap();

        // 2. 後兩張手牌
        Map<Integer, List<ICard>> playerCardListLastTwo = ((PreGameAdjustResultQznnKszJava) preGameAdjustResult).getAllPlayerUnTakenCardListMap();

        // 3. 加入前三張
        playerCardListFirstThree.forEach((chairIndex, cardList) -> result.put(chairIndex, new ArrayList<>()));
        result.forEach((chairIndex, cardList) -> cardList.addAll(playerCardListFirstThree.get(chairIndex)));

        // 4. 加入後兩張
        result.forEach((chairIndex, cardList) -> cardList.addAll(playerCardListLastTwo.get(chairIndex)));

        // 5. 回傳
        return result;
    }

    // 取得真人得分
    @Override
    public double calculateHumanScore(GameAdjustResult gameAdjustResult) {
        return gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getScore();
    }

    // 取得真人有效投注
    @Override
    public double calculateHumanValidBet(GameAdjustResult gameAdjustResult) {
        return gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getValidBet();
    }
}
