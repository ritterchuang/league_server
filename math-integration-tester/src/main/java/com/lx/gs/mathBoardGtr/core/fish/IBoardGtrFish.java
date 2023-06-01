package com.lx.gs.mathBoardGtr.core.fish;

import com.lx.gs.math.core.common.table.entity.message.fish.CtsGetHitResultData;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;
import com.lx.gs.mathBoardGtr.core.IBoardGtr;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;

// 魚機局產生器介面
public interface IBoardGtrFish extends IBoardGtr {

    // 取得返還資訊
    BoardGtrReturnResult getReturnResult();

    // 更新返還資訊
    void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult);

    // 計算打擊資訊
    CtsGetHitResultData createRandomHitCtsGetHitResultData(MathFishConfig mathFishConfig);
}
