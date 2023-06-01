package org.lsj.gs.math.core.common.gameAdjust.module.dealCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;

// 發牌器介面
public interface IDealCtr {
    /* 發牌器相關 */
    void setShuffleType(ConstMathCommon.ShuffleType shuffleType); // 設定開牌類型

    /* 預設開牌相關 */
    GameAdjustResult deal(GameAdjustParameter gameAdjustParameter); // 預設開牌

    /* 相對開牌相關 */
    GameAdjustResult dealGameResultBigger(GameAdjustResult gameAdjustResult, GameAdjustParameter gameAdjustParameter); // 開相對大牌

    /* 絕對開牌相關 */
    GameAdjustResult dealGameResultAbsoluteWin(GameAdjustParameter gameAdjustParameter); // 必贏開牌
    GameAdjustResult dealGameResultAbsoluteLoss(GameAdjustParameter gameAdjustParameter); // 必輸開牌

    /* 查詢相關 */
    double calculateHumanScore(GameAdjustResult gameAdjustResult); // 計算真人淨利
    double calculateHumanValidBet(GameAdjustResult gameAdjustResult); // 計算真人有效投注

    /* 應用相關 */
    void applyPreGameResult(PreGameAdjustResult preGameAdjustResult); // 應用預遊戲結果
}
