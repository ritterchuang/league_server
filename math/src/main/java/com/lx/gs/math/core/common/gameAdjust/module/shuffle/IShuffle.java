package com.lx.gs.math.core.common.gameAdjust.module.shuffle;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;

// 發牌介面
public interface IShuffle {
    // 發預遊戲結果
    PreGameAdjustResult dealPreGameAdjustResult(GameAdjustParameter gameAdjustParameter);

    // 應用預遊戲結果
    void applyPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult);

    // 計算發牌結果
    GameAdjustResult calculateGameAdjustResult(PreGameAdjustResult preGameAdjustResult);

    // 計算真人淨利
    double calculateHumanScore(GameAdjustResult gameAdjustResult);

    // 計算真人有效投注
    double calculateHumanValidBet(GameAdjustResult gameAdjustResult);

    // 設定開牌類型
    void setShuffleType(ConstMathCommon.ShuffleType shuffleType);
}
