package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.resultCtr.bjlResultCtr.AbstractBjlBaiRenResultCtr;
import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;

import java.util.List;
import java.util.Map;

// 百家類邏輯介面
public interface ILogicBjl {
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
    Map<Integer, BjlStack> getStackMap();

    // 計算贏分區域陣列
    int[] calculateWinAreaArray();

    // 計算返分區域陣列
    int[] calculateReturnAreaArray();

    // 依照發牌區域計算點數和
    int[] calculatePlayBankPointArray();

    // 依照發牌區域計算累積點數陣列
    int[][] calculateAccumulateAreaPoint2DArray();


    //* 計算結果相關 *//

    // 取得結果計算器
    AbstractBjlBaiRenResultCtr getAbstractBjlBaiRenResultCtr();

    // 使用內存計算所有玩家得分
    Map<Integer, UidScore> calculateUidScoreMap();

    // 提供卡牌計算所有玩家得分
    Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> unTakenCardListMap);

    // 設定所有玩家得分
    void setUidScoreMap(Map<Integer, UidScore> playerScoreMap);

    // 取得所有玩家輸贏結果
    Map<Integer, UidScore> getUidScoreMap();

    // 取得所有贏家總贏分
    double getAllWinScore();

    // 計算返還金額
    int calculateReturnValue();

    // 計算玩家區域贏分陣列
    double[] calculatePlayerAreaScoreArray(int chairIndex);

    // 計算玩家區域贏分對應表
    Map<Integer, Double> calculatePlayerAreaScoreMap(int chairIndex);
}
