package org.lsj.gs.math.games.lhd_java.module.gameAdjust;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.AlgorithmTypeCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.AbstractShuffle;
import org.lsj.gs.math.core.common.gameAdjust.module.shuffle.IShuffle;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;
import org.lsj.gs.math.games.lhd_java.entity.PreGameAdjustResultLhdJava;
import org.lsj.gs.math.games.lhd_java.module.logic.LogicLhdJava;

import java.util.*;

// 新龍虎鬥發牌
public class ShuffleLhdJava extends AbstractShuffle implements IShuffle {
    private final LogicLhdJava logic; // 邏輯
    private final int DEAL_COUNT = 2; // 發牌數

    public ShuffleLhdJava(AlgorithmTypeCtr algorithmTypeCtr, LogicLhdJava logic, ITableUtil tableUtil) {
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
        unTakenCardListMap.put(ConstLhdJava.BetAreaIdLhdJava.DRAGON.getCode(), new ArrayList<>(){{add(unTakenCardList.get(0));}});
        unTakenCardListMap.put(ConstLhdJava.BetAreaIdLhdJava.TIGER.getCode(), new ArrayList<>(){{add(unTakenCardList.get(1));}});

        // 4. 封裝
        return new PreGameAdjustResultLhdJava(unTakenCardListMap);
    }


    // 應用預遊戲結果
    @Override
    public void applyPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 將牌堆得牌改成取出
        Map<Integer, List<ICard>> unTakenCardListMap = ((PreGameAdjustResultLhdJava) preGameAdjustResult).getUnTakenCardListMap();

        // 2. 將牌改成取出狀態
        unTakenCardListMap.values().forEach(cardList -> cardList.forEach(this.logic::getAppointObjCard));

        // 3. 紀錄區域牌
        this.logic.setAreaCardListMap(unTakenCardListMap);
    }

    // 計算發牌結果
    @Override
    public GameAdjustResult calculateGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        // 1. 計算所有玩家輸贏結果
        Map<Integer, UidScore> uidScoreMap = this.logic.calculateUidScoreMap(((PreGameAdjustResultLhdJava) preGameAdjustResult).getUnTakenCardListMap());

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
