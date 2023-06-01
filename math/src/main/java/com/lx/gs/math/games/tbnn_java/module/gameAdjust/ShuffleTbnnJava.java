package com.lx.gs.math.games.tbnn_java.module.gameAdjust;

import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.AbstractShuffle;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.tbnn_java.entity.PreGameAdjustResultTbnnJava;
import com.lx.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 通比牛牛發牌
public class ShuffleTbnnJava extends AbstractShuffle implements IShuffle {
    private final LogicTbnnJava logic; // 邏輯
    private final int DEAL_COUNT_PER_PLAYER = 5; // 每人發牌數

    public ShuffleTbnnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicTbnnJava logicTBNN, ITableUtil tableUtil) {
        super(algorithmTypeCtr, tableUtil);
        this.logic = logicTBNN;
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
        return new PreGameAdjustResultTbnnJava(unTakenCardListMap);
    }


    // 應用預遊戲結果
    @Override
    public void applyPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 將牌堆得牌改成取出
        Map<Integer, List<ICard>> unTakenCardListMap = ((PreGameAdjustResultTbnnJava) preGameAdjustResult).getAllPlayerUnTakenCardListMap();

        // 2. 將牌改成取出狀態
        unTakenCardListMap.values().forEach(cardList -> cardList.forEach(this.logic::getAppointObjCard));

        // 3. 紀錄手牌
        this.logic.addPlayerHandCardListMap(unTakenCardListMap);
    }

    // 計算發牌結果
    @Override
    public GameAdjustResult calculateGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 計算所有玩家輸贏結果
        Map<Integer, UidScore> uidScoreMap = this.logic.calculateUidScoreMap(((PreGameAdjustResultTbnnJava) preGameAdjustResult).getAllPlayerUnTakenCardListMap());

        // 2. 封裝結果
        return new GameAdjustResult(uidScoreMap, preGameAdjustResult);
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
