package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.AbstractStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;

import java.util.List;
import java.util.Map;

// 百人牛牛類邏輯介面
public interface ILogicBrNiu {
    //* 牌牆計算器 *//

    // 洗牌
    void shuffleCardWall();

    // 發未取走牌
    List<ICard> dealUnTakenCardList(int number);

    // 取出特定牌
    void getAppointObjCard(ICard card);

    // 設定區域牌
    void setAreaCardListMap(Map<Integer, List<ICard>> areaCardList);


    //* 牌型結果相關 *//

    // 設定牌型結果
    void setStackMap();

    // 取得牌型結果對應表
    Map<Integer, AbstractStack> getStackMap();


    //* 計算結果相關 *//

    // 設定所有玩家得分
    void setUidScoreMap(Map<Integer, UidScore> playerScoreMap);

    // 取得所有玩家輸贏結果
    Map<Integer, UidScore> getUidScoreMap();

    // 使用內存計算所有玩家得分
    Map<Integer, UidScore> calculateUidScoreMap();

    // 提供卡牌計算所有玩家得分
    Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> unTakenCardListMap);

    // 取得所有贏家總贏分
    double getAllWinScore();

    // 計算玩家區域贏分陣列
    double[] calculatePlayerAreaScoreArray(int chairIndex);

    // 計算玩家區域贏分對應表
    Map<Integer, Double> calculatePlayerAreaScoreMap(int chairIndex);
}
