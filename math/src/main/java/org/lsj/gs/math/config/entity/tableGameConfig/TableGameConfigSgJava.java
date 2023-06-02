package org.lsj.gs.math.config.entity.tableGameConfig;

import org.lsj.gs.math.core.card.betCtr.qzBetCtr.QzBetCtrGameConfig;
import org.lsj.gs.math.core.card.cardWallCtr.CardWallCtrGameConfig;
import org.lsj.gs.math.core.card.resultCtr.sgResultCtr.SgBankerResultCtrGameConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrGameConfig;
import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustConfig;
import org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr.RngAlgorithmConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;

// 新看三公牌桌遊戲設定
public class TableGameConfigSgJava extends AbstractTableGameConfig {
    private final VieBankerCtrGameConfig vieBankerCtrGameConfig; // 搶莊計算器遊戲設定
    private final QzBetCtrGameConfig qzBetCtrGameConfig; // 下注計算器遊戲設定
    private final CardWallCtrGameConfig cardWallCtrGameConfig; // 牌牆計算器遊戲設定
    private final SgBankerResultCtrGameConfig sgBankerResultCtrGameConfig; // 三公結果計算器遊戲設定
    private final RobotLogicConfigBanker robotLogicConfigBanker; // 搶莊類機器人邏輯設定

    public TableGameConfigSgJava(GameAdjustConfig gameAdjustConfig, RngAlgorithmConfig rngAlgorithmConfig, VieBankerCtrGameConfig vieBankerCtrGameConfig, QzBetCtrGameConfig qzBetCtrGameConfig, CardWallCtrGameConfig cardWallCtrGameConfig, SgBankerResultCtrGameConfig sgBankerResultCtrGameConfig, RobotLogicConfigBanker robotLogicConfigBanker) {
        super(gameAdjustConfig, rngAlgorithmConfig);
        this.vieBankerCtrGameConfig = vieBankerCtrGameConfig;
        this.qzBetCtrGameConfig = qzBetCtrGameConfig;
        this.cardWallCtrGameConfig = cardWallCtrGameConfig;
        this.sgBankerResultCtrGameConfig = sgBankerResultCtrGameConfig;
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

    public SgBankerResultCtrGameConfig getSgResultCtrGameConfig() {
        return sgBankerResultCtrGameConfig;
    }

    public RobotLogicConfigBanker getRobotLogicConfigBanker() {
        return robotLogicConfigBanker;
    }
}
