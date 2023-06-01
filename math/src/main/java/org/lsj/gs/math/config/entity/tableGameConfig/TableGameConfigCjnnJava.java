package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBaiRenResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr.NiuBaiRenStackCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.games.cjnn_java.module.niu.NiuStackTmrConfigCjnnJava;

// 新超級牛牛牌桌遊戲設定
public class TableGameConfigCjnnJava extends AbstractTableGameConfigBaiRen {
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final NiuBaiRenStackCtrGameConfig niuBaiRenStackCtrGameConfig; // 牛牛百人牌型計算器遊戲設定
    private final NiuBaiRenResultCtrGameConfig niuBaiRenResultCtrGameConfig; // 牛牛百人結果計算器遊戲設定
    private final NiuStackTmrConfigCjnnJava niuStackTmrConfigCjnnJava; // 牛型轉換器設定

    public TableGameConfigCjnnJava(GameAdjustConfig gameAdjustConfig,
                                   RngAlgorithmConfig rngAlgorithmConfig,
                                   BaiRenMathConfigHlrConfig mathConfigHlrConfig,
                                   CardWallCtrGameConfig cardWallCtrGameConfig,
                                   BetAreaCtrGameConfig betAreaCtrGameConfig,
                                   NiuBaiRenStackCtrGameConfig niuBaiRenStackCtrGameConfig,
                                   NiuStackTmrConfigCjnnJava niuStackTmrConfigCjnnJava,
                                   NiuBaiRenResultCtrGameConfig niuBaiRenResultCtrGameConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig, mathConfigHlrConfig, betAreaCtrGameConfig);
        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.niuBaiRenStackCtrGameConfig = niuBaiRenStackCtrGameConfig;
        this.niuStackTmrConfigCjnnJava = niuStackTmrConfigCjnnJava;
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

    public NiuStackTmrConfigCjnnJava getNiuStackTmrConfigCjnnJava() {
        return niuStackTmrConfigCjnnJava;
    }
}
