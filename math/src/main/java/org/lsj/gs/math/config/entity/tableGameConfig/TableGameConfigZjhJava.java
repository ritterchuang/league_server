package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.niuResultCtr.NiuBankerResultCtrGameConfig;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.games.zjh_java.module.niu.NiuStackTmrConfigZjhJava;

// 新搶莊牛牛牌桌遊戲設定
public class TableGameConfigZjhJava extends AbstractTableGameConfig {
    private final VieBankerCtrGameConfig vieBankerCtrGameConfig; // 搶莊計算器遊戲設定
    private final QzBetCtrGameConfig qzBetCtrGameConfig; // 下注計算器遊戲設定
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final NiuStackCtrGameConfig niuStackCtrGameConfig; // 牛型計算器遊戲設定
    private final NiuStackTmrConfigZjhJava niuStackTmrConfig; // 牛型轉換器設定
    private final NiuBankerResultCtrGameConfig niuBankerResultCtrGameConfig; // 牛牛結果計算器遊戲設定

    private final RobotLogicConfigBanker robotLogicConfigBanker; // 搶莊類機器人邏輯設定

    public TableGameConfigZjhJava(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            VieBankerCtrGameConfig vieBankerCtrGameConfig,
            QzBetCtrGameConfig qzBetCtrGameConfig,
            CardWallCtrGameConfig cardWallCtrGameConfig,
            NiuStackCtrGameConfig niuStackCtrGameConfig,
            NiuBankerResultCtrGameConfig niuBankerResultCtrGameConfig,
            NiuStackTmrConfigZjhJava niuStackTmrConfig,
            RobotLogicConfigBanker robotLogicConfigBanker) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.vieBankerCtrGameConfig = vieBankerCtrGameConfig;
        this.qzBetCtrGameConfig = qzBetCtrGameConfig;
        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.niuStackCtrGameConfig = niuStackCtrGameConfig;
        this.niuBankerResultCtrGameConfig = niuBankerResultCtrGameConfig;
        this.niuStackTmrConfig = niuStackTmrConfig;
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

    public NiuStackTmrConfigZjhJava getNiuStackTmrConfig() {
        return niuStackTmrConfig;
    }

    public NiuBankerResultCtrGameConfig getNiuResultCtrGameConfig() {
        return niuBankerResultCtrGameConfig;
    }

    public RobotLogicConfigBanker getRobotLogicConfigQz() {
        return robotLogicConfigBanker;
    }
}
