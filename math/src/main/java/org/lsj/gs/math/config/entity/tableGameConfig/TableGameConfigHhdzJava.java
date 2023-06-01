package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.jhResultCtr.JhBaiRenResultCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 新紅黑大戰牌桌遊戲設定
public class TableGameConfigHhdzJava extends AbstractTableGameConfigBaiRen {
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final JhBaiRenResultCtrGameConfig resultCtrGameConfig; // 金花百人結果計算器遊戲設定

    public TableGameConfigHhdzJava(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            BaiRenMathConfigHlrConfig mathConfigHlrConfig,
            CardWallCtrGameConfig cardWallCtrGameConfig,
            BetAreaCtrGameConfig betAreaCtrGameConfig,
            JhBaiRenResultCtrGameConfig resultCtrGameConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig, mathConfigHlrConfig, betAreaCtrGameConfig);

        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.resultCtrGameConfig = resultCtrGameConfig;
    }

    public CardWallCtrGameConfig getCardWallCtrGameConfig() {
        return cardWallCtrGameConfig;
    }

    public JhBaiRenResultCtrGameConfig getResultCtrGameConfig() {
        return resultCtrGameConfig;
    }
}
