package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBaiRenResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr.NiuBaiRenStackCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.games.brnn_java.module.niu.NiuStackTmrConfigBrnnJava;

// 新百人牛牛牌桌遊戲設定
public class TableGameConfigBrnnJava extends AbstractTableGameConfigBaiRen {
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final NiuBaiRenStackCtrGameConfig niuBaiRenStackCtrGameConfig; // 牛牛百人牌型計算器遊戲設定
    private final NiuBaiRenResultCtrGameConfig niuBaiRenResultCtrGameConfig; // 牛牛百人結果計算器遊戲設定
    private final NiuStackTmrConfigBrnnJava niuStackTmrConfigBrnnJava; // 牛型轉換器設定

    public TableGameConfigBrnnJava(GameAdjustConfig gameAdjustConfig,
                                   RngAlgorithmConfig rngAlgorithmConfig,
                                   BaiRenMathConfigHlrConfig mathConfigHlrConfig,
                                   CardWallCtrGameConfig cardWallCtrGameConfig,
                                   BetAreaCtrGameConfig betAreaCtrGameConfig,
                                   NiuBaiRenStackCtrGameConfig niuBaiRenStackCtrGameConfig,
                                   NiuStackTmrConfigBrnnJava niuStackTmrConfigBrnnJava,
                                   NiuBaiRenResultCtrGameConfig niuBaiRenResultCtrGameConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig, mathConfigHlrConfig, betAreaCtrGameConfig);
        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.niuBaiRenStackCtrGameConfig = niuBaiRenStackCtrGameConfig;
        this.niuStackTmrConfigBrnnJava = niuStackTmrConfigBrnnJava;
        this.niuBaiRenResultCtrGameConfig = niuBaiRenResultCtrGameConfig;
    }

    public CardWallCtrGameConfig getCardWallCtrGameConfig() {
        return cardWallCtrGameConfig;
    }

    public NiuBaiRenStackCtrGameConfig getNiuBaiRenStackCtrGameConfig() {
        return niuBaiRenStackCtrGameConfig;
    }

    public NiuBaiRenResultCtrGameConfig getNiuBaiRenResultCtrGameConfig() {
        return niuBaiRenResultCtrGameConfig;
    }

    public NiuStackTmrConfigBrnnJava getNiuStackTmrConfigBrnnJava() {
        return niuStackTmrConfigBrnnJava;
    }
}
