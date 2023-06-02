package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.bjlResultCtr.BjlBaiRenResultCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 新免傭百家樂牌桌遊戲設定
public class TableGameConfigMybjlJava extends AbstractTableGameConfigBaiRen {
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final BjlBaiRenResultCtrGameConfig resultCtrGameConfig; // 百家樂百人結果計算器遊戲設定

    public TableGameConfigMybjlJava(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            BaiRenMathConfigHlrConfig mathConfigHlrConfig,
            CardWallCtrGameConfig cardWallCtrGameConfig,
            BetAreaCtrGameConfig betAreaCtrGameConfig,
            BjlBaiRenResultCtrGameConfig resultCtrGameConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig, mathConfigHlrConfig, betAreaCtrGameConfig);

        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.resultCtrGameConfig = resultCtrGameConfig;
    }

    public CardWallCtrGameConfig getCardWallCtrGameConfig() {
        return cardWallCtrGameConfig;
    }

    public BjlBaiRenResultCtrGameConfig getResultCtrGameConfig() {
        return resultCtrGameConfig;
    }
}
