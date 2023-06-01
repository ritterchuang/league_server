package com.lx.gs.math.config.entity.tableGameConfig;

import com.lx.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import com.lx.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import com.lx.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import com.lx.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 新龍虎鬥牌桌遊戲設定
public class TableGameConfigLhdJava extends AbstractTableGameConfigBaiRen {
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定

    public TableGameConfigLhdJava(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            BaiRenMathConfigHlrConfig mathConfigHlrConfig,
            BetAreaCtrGameConfig betAreaCtrGameConfig,
            CardWallCtrGameConfig cardWallCtrGameConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig, mathConfigHlrConfig, betAreaCtrGameConfig);

        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
    }

    public CardWallCtrGameConfig getCardWallCtrGameConfig() {
        return cardWallCtrGameConfig;
    }
}
