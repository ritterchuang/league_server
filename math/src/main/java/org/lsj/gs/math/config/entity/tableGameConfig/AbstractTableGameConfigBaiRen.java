package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.baiRen.betAreaCtr.BetAreaCtrGameConfig;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfigHlrConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;

// 抽象百人牌桌遊戲設定
public class AbstractTableGameConfigBaiRen extends AbstractTableGameConfig{
    private final BaiRenMathConfigHlrConfig mathConfigHlrConfig; // 數值設定處理器的設定
    private final BetAreaCtrGameConfig betAreaCtrGameConfig; // 押注區域計算器遊戲設定

    public AbstractTableGameConfigBaiRen(GameAdjustConfig gameAdjustConfig,
                                         RngAlgorithmConfig rngAlgorithmConfig,
                                         BaiRenMathConfigHlrConfig mathConfigHlrConfig,
                                         BetAreaCtrGameConfig betAreaCtrGameConfig) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.mathConfigHlrConfig = mathConfigHlrConfig;
        this.betAreaCtrGameConfig = betAreaCtrGameConfig;
    }

    public BaiRenMathConfigHlrConfig getMathConfigHlrConfig() {
        return mathConfigHlrConfig;
    }

    public BetAreaCtrGameConfig getBetAreaCtrGameConfig() {
        return betAreaCtrGameConfig;
    }
}
