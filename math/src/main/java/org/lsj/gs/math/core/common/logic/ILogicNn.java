package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;

import java.util.List;
import java.util.Map;

// 牛牛類邏輯介面
public interface ILogicNn{
    //* 牌牆計算器 *//
    //** 發牌功能相關 **//

    // 洗牌
    void shuffleCardWall();

    // 發未取走牌
    List<ICard> dealUnTakenCardList(int number);

    // 取出特定牌
    void getAppointObjCard(ICard card);

    // 添加玩家手牌
    void addPlayerHandCardListMap(Map<Integer, List<ICard>> playerCardList);


    //** 發牌結果相關 **//

    // 取得所有玩家手牌牌號
    int[][] getAllPlayerHandCardNumberArray();

    // 取得所有玩家手牌
    Map<Integer, List<ICard>> getHandCardListMap();

    // 取得指定玩家手牌牌號
    int[] getHandCardNumberArray(int chairIndex);

    // 取得真人手牌
    int[] getHumanListHandCardArray();

    // 檢查玩家手牌是否存在
    boolean isInPlayerHand(int chairIndex, int[] cardNumbers);


    //** 斷線重連相關 **//

    // 取得斷線回復手牌結果
    int[][] calculateCutReturnHandCardListArray();


    //* 牛牛牌型計算器 *//
    //** 接收選牌訊息相關 **//

    // 玩家是否選過
    boolean isPlayerSelect(int chairIndex);

    //** 結束選牌相關 **//

    // 是否選擇完畢
    boolean isFinishSelect();

    // 結束選擇
    void finishSelect();


    //** 選牌相關 **//

    // 取得所有玩家選牌牌號
    int[][] getAllPlayerSelectCardNumberArray();

    // 取得所有玩家牌型
    int[] getAllPlayerSelectTypeArrayCommon();

    // 取得指定位置的選牌結果
    int getPlayerSelectTypeCommon(int chairIndex);

    // 取得指定位置的是否有牛
    boolean getPlayerIsNiu(int chairIndex);

    // 設定玩家選牛結果
    void setPlayerSelectResult(int chairIndex);

    // 取得真人玩家牌型結構
    AbstractNiuStack getHumanPlayerSelectResultCommon();

    // 取得真人選牌牌型
    int getHumanPlayerSelectTypeCommon();

    // 是否為最大牌型
    boolean isMaxStack(int[] cardNumbers);


    //** 提牌相關 **//

    // 取得所有玩家提牌封包
    int[][] getAllPlayerLiftCardNumberArray();

    // 取得指定玩家提牌牌號
    int[] getPlayerLiftCardNumber(int chairIndex);


    //* 牛牛結果計算器 *//
    //** 輸贏結果相關 **//

    // 取得所有玩家淨利
    double[] getAllPlayerScoreArray();

    // 取得指定玩家淨利
    double getPlayerScore(int chairIndex);

    // 取得真人玩家淨利
    double getHumanPlayerScore();

    //** 計算結果相關 **//

    // 使用內存計算所有玩家得分
    Map<Integer, UidScore> calculateUidScoreMap();

    // 提供卡牌計算所有玩家得分
    Map<Integer, UidScore> calculateUidScoreMap(Map<Integer, List<ICard>> playerCardList);

    // 設定所有玩家得分
    void setUidScoreMap(Map<Integer, UidScore> playerResult);
}
