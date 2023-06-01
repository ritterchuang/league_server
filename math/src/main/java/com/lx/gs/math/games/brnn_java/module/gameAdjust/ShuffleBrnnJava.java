package com.lx.gs.math.games.brnn_java.module.gameAdjust;

import com.lx.gs.math.core.baiRen.ConstMathNiu;
import com.lx.gs.math.core.card.cardWallCtr.ICard;
import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.AbstractShuffle;
import com.lx.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.brnn_java.entity.PreGameAdjustResultBrnnJava;
import com.lx.gs.math.games.brnn_java.module.logic.LogicBrnnJava;

import java.util.*;

// 新百人牛牛發牌
public class ShuffleBrnnJava extends AbstractShuffle implements IShuffle {
    private final LogicBrnnJava logic; // 邏輯
    private final int DEAL_COUNT = 25; // 發牌數
    private final int DEAL_COUNT_PER_PLAYER = 5; // 每人發牌數

    public ShuffleBrnnJava(AlgorithmTypeCtr algorithmTypeCtr, LogicBrnnJava logic, ITableUtil tableUtil) {
        super(algorithmTypeCtr, tableUtil);
        this.logic = logic;
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
        // 1. 洗牌
        this.logic.shuffleCardWall();

        // 2. 發未取牌
        List<ICard> unTakenCardList = this.logic.dealUnTakenCardList(this.DEAL_COUNT);

        // 3. 分區域牌
        Map<Integer, List<ICard>> unTakenCardListMap = new HashMap<>();
        for (int betAreaId = 0; betAreaId < ConstMathNiu.BetAreaEnum.getDealAreaCount(); betAreaId++) {
            List<ICard> tempUnTakenCardList = new ArrayList<>();

            int beginUnTakenCardIndex = betAreaId * this.DEAL_COUNT_PER_PLAYER;
            int endUnTakenCardIndex = beginUnTakenCardIndex + this.DEAL_COUNT_PER_PLAYER;

            for (int unTakenCardIndex = beginUnTakenCardIndex; unTakenCardIndex < endUnTakenCardIndex; unTakenCardIndex++) {
                tempUnTakenCardList.add(unTakenCardList.get(unTakenCardIndex));
            }

            unTakenCardListMap.put(betAreaId, tempUnTakenCardList);
        }

        // 4. 封裝
        return new PreGameAdjustResultBrnnJava(unTakenCardListMap);
    }


    // 應用預遊戲結果
    @Override
    public void applyPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 將牌堆得牌改成取出
        Map<Integer, List<ICard>> unTakenCardListMap = ((PreGameAdjustResultBrnnJava) preGameAdjustResult).getUnTakenCardListMap();

        // 2. 將牌改成取出狀態
        unTakenCardListMap.values().forEach(cardList -> cardList.forEach(this.logic::getAppointObjCard));

        // 3. 紀錄區域牌
        this.logic.setAreaCardListMap(unTakenCardListMap);
    }

    // 計算發牌結果
    @Override
    public GameAdjustResult calculateGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 計算所有玩家輸贏結果
        Map<Integer, UidScore> uidScoreMap = this.logic.calculateUidScoreMap(((PreGameAdjustResultBrnnJava) preGameAdjustResult).getUnTakenCardListMap());

        // 2. 封裝結果
        return new GameAdjustResult(uidScoreMap, preGameAdjustResult);
    }

    // 取得真人得分
    @Override
    public double calculateHumanScore(GameAdjustResult gameAdjustResult) {
        if(Objects.nonNull(gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()))){
            return gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getScore();
        }

        return 0.0;
    }

    // 取得真人有效投注
    @Override
    public double calculateHumanValidBet(GameAdjustResult gameAdjustResult) {
        if(Objects.nonNull(gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()))){
            return gameAdjustResult.getUidScoreMap().get(this.logic.getHumanChairIndex()).getValidBet();
        }

        return 0.0;
    }
}
