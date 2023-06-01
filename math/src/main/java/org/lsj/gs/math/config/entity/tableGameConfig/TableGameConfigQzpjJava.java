package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;

// 新搶莊牌九牌桌遊戲設定 TODO PjStackGame, PjResultGame config
public class TableGameConfigQzpjJava extends AbstractTableGameConfig {
    private final VieBankerCtrGameConfig vieBankerCtrGameConfig; // 搶莊計算器遊戲設定
    private final QzBetCtrGameConfig qzBetCtrGameConfig; // 下注計算器遊戲設定
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final RobotLogicConfigBanker robotLogicConfigBanker; // 搶莊類機器人邏輯設定

    public TableGameConfigQzpjJava(
            GameAdjustConfig gameAdjustConfig,
            RngAlgorithmConfig rngAlgorithmConfig,
            VieBankerCtrGameConfig vieBankerCtrGameConfig,
            QzBetCtrGameConfig qzBetCtrGameConfig,
            CardWallCtrGameConfig cardWallCtrGameConfig,
            RobotLogicConfigBanker robotLogicConfigBanker) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.vieBankerCtrGameConfig = vieBankerCtrGameConfig;
        this.qzBetCtrGameConfig = qzBetCtrGameConfig;
        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
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

    public RobotLogicConfigBanker getRobotLogicConfigBanker() {
        return robotLogicConfigBanker;
    }
}
