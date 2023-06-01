package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBankerResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.games.qznn_k4z_java.module.niu.NiuStackTmrConfigQznnK4zJava;

// 新看四張搶莊牛牛牌桌遊戲設定
public class TableGameConfigQznnK4zJava extends AbstractTableGameConfig {
    private final VieBankerCtrGameConfig vieBankerCtrGameConfig; // 搶莊計算器遊戲設定
    private final QzBetCtrGameConfig qzBetCtrGameConfig; // 下注計算器遊戲設定
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final NiuStackCtrGameConfig niuStackCtrGameConfig; // 牛型計算器遊戲設定
    private final NiuStackTmrConfigQznnK4zJava niuStackTmrConfigQznnK4zJava; // 牛型轉換器設定
    private final NiuBankerResultCtrGameConfig niuBankerResultCtrGameConfig; // 牛牛結果計算器遊戲設定
    private final RobotLogicConfigBanker robotLogicConfigBanker; // 搶莊類機器人邏輯設定

    public TableGameConfigQznnK4zJava(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            VieBankerCtrGameConfig vieBankerCtrGameConfig,
            QzBetCtrGameConfig qzBetCtrGameConfig,
            CardWallCtrGameConfig cardWallCtrGameConfig,
            NiuStackCtrGameConfig niuStackCtrGameConfig,
            NiuBankerResultCtrGameConfig niuBankerResultCtrGameConfig,
            NiuStackTmrConfigQznnK4zJava niuStackTmrConfigQznnK4zJava,
            RobotLogicConfigBanker robotLogicConfigBanker) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.vieBankerCtrGameConfig = vieBankerCtrGameConfig;
        this.qzBetCtrGameConfig = qzBetCtrGameConfig;
        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.niuStackCtrGameConfig = niuStackCtrGameConfig;
        this.niuBankerResultCtrGameConfig = niuBankerResultCtrGameConfig;
        this.niuStackTmrConfigQznnK4zJava = niuStackTmrConfigQznnK4zJava;
        this.robotLogicConfigBanker = robotLogicConfigBanker;
    }

    public VieBankerCtrGameConfig getVieBankerCtrGameConfig() {
        return vieBankerCtrGameConfig;
    }

    public QzBetCtrGameConfig getBetCtrGameConfig() {
        return qzBetCtrGameConfig;
    }

    public CardWallCtrGameConfig getCardWallCtrGameConfig() {
        return cardWallCtrGameConfig;
    }

    public NiuStackCtrGameConfig getNiuStackCtrGameConfig() {
        return niuStackCtrGameConfig;
    }

    public NiuStackTmrConfigQznnK4zJava getNiuStackTmrConfigQznnK4zJava() {
        return niuStackTmrConfigQznnK4zJava;
    }

    public NiuBankerResultCtrGameConfig getNiuResultCtrGameConfig() {
        return niuBankerResultCtrGameConfig;
    }

    public RobotLogicConfigBanker getRobotLogicConfigQz() {
        return robotLogicConfigBanker;
    }
}
